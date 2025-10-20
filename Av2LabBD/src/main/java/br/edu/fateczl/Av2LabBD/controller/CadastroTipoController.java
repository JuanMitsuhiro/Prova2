package br.edu.fateczl.Av2LabBD.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.Av2LabBD.model.Curiosidade;
import br.edu.fateczl.Av2LabBD.model.Desenvolvedora;
import br.edu.fateczl.Av2LabBD.repository.ICuriosidadeRepository;
import br.edu.fateczl.Av2LabBD.repository.IDesenvolvedoraRepository;


@Controller
public class CadastroTipoController {
	
	@Autowired
	private ICuriosidadeRepository cRepository;
	
	@Autowired
	private IDesenvolvedoraRepository dRepository;
	
	@RequestMapping(name = "cadastroTipo", value = "/cadastroTipo", method = RequestMethod.GET)
	public ModelAndView medicoGet(
			@RequestParam Map<String, String> params,
			ModelMap model) {
		String erro = "";
		List<Curiosidade> curiosidades = new ArrayList<>();
		List<Desenvolvedora> desenvolvedoras = new ArrayList<>();
		
		try {
            desenvolvedoras = dRepository.findAllByOrderByNomeAsc();
            
            curiosidades = cRepository.findAllByOrderByDesenvolvedoraCodigoAsc();

        } catch (Exception e) {
            erro = e.getMessage();
        } finally {
            model.addAttribute("erro", erro);
            model.addAttribute("desenvolvedoras", desenvolvedoras);
            model.addAttribute("curiosidades", curiosidades);
            model.addAttribute("curiosidade", new Curiosidade());
        }

		return new ModelAndView("cadastroTipo");
	}
	
	@RequestMapping(name = "cadastroTipo", value = "/cadastroTipo", method = RequestMethod.POST)
	public ModelAndView medicoPost(
			@RequestParam Map<String, String> params,
			ModelMap model) {
		String saida = "";
		String erro = "";
		List<Desenvolvedora> desenvolvedoras = new ArrayList<>();
		List<Curiosidade> curiosidades = new ArrayList<Curiosidade>();
		Curiosidade c = new Curiosidade();
		String cmd = "";
		
		try {
			desenvolvedoras = dRepository.findAllByOrderByNomeAsc(); 
			curiosidades = cRepository.findAllByOrderByDesenvolvedoraCodigoAsc();
			
			String texto = params.get("texto");
			String desenvolvedora = params.get("desenvolvedora");
			
			cmd = params.get("botao");
			
			if (cmd.equalsIgnoreCase("Inserir") && texto != null) {
				c.setTexto(texto);
				c.setDesenvolvedora(dRepository.findCurso(Integer.parseInt(desenvolvedora)));
				cRepository.save(c);
				saida = "Curiosidade cadastrada com sucesso.";
			}


		} catch (NumberFormatException e) {
			saida = "";
			erro = e.getMessage();
			if (erro.contains("input string")) {
				erro = "Preencha os campos corretamente";
			}
		} finally {
			c = null;
			if (!cmd.equalsIgnoreCase("Listar")) {
				curiosidades = null;
			}

			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("curiosidade", c);
			model.addAttribute("desenvolvedoras", desenvolvedoras);
			model.addAttribute("curiosidades", curiosidades);
		}

		return new ModelAndView("cadastroTipo");
	}
}

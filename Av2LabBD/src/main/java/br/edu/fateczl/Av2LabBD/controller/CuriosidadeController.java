package br.edu.fateczl.Av2LabBD.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.Av2LabBD.service.CuriosidadeDesenvolvedoraService;


@Controller
public class CuriosidadeController {
	
	@Autowired
	private CuriosidadeDesenvolvedoraService cdService;
	
	
	@RequestMapping(name = "curiosidades", value = "/curiosidades", method = RequestMethod.GET)
	public ModelAndView curiosidadeGet(
			@RequestParam Map<String, String> params,
			ModelMap model) {
        String erro = "";
        try {
        	cdService.verificaDadosCarregados();
        } catch (Exception e) {
            erro = e.getMessage();
        }
        model.addAttribute("erro", erro);
        return new ModelAndView("curiosidades");
	}
	
	@RequestMapping(name = "curiosidades", value = "/curiosidades", method = RequestMethod.POST)
	public ModelAndView curiosidadePost(
			@RequestParam Map<String, String> params,
			ModelMap model) {
		
		String saida = "";
        String erro = "";
        String cmd = "";

        try {
            cmd = params.get("botao");
            if (cmd != null) {
                saida = cdService.curiosidadeAleatoria(cmd);
            }
        } catch (Exception e) {
            erro = e.getMessage();
        } finally {
    		model.addAttribute("erro", erro);
    		model.addAttribute("saida", saida);
        }


		return new ModelAndView("curiosidades");
	}
}

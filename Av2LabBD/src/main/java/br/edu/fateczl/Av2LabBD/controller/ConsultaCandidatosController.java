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

import br.edu.fateczl.Av2LabBD.model.Candidato;
import br.edu.fateczl.Av2LabBD.model.Curso;
import br.edu.fateczl.Av2LabBD.repository.ICandidatoRepository;
import br.edu.fateczl.Av2LabBD.repository.ICursoRepository;

@Controller
public class ConsultaCandidatosController {
	
	@Autowired
	private ICandidatoRepository candRepository;
	
	@Autowired
	private ICursoRepository curRepository;
	
	@RequestMapping(name = "consultaCandidatos", value = "/consultaCandidatos", method = RequestMethod.GET)
	public ModelAndView medicoGet(
			@RequestParam Map<String, String> params,
			ModelMap model) {
		List<Candidato> candidatos = new ArrayList<>();
		List<Curso> cursos = new ArrayList<>();
		List<String> bairros = new ArrayList<>();

        cursos = curRepository.findAllByOrderByNomeAsc();
        candidatos = candRepository.findAllByOrderByNomeAsc();
        bairros = candRepository.findDistinctBairros();
        
        model.addAttribute("cursos", cursos);
        model.addAttribute("candidatos", candidatos);
        model.addAttribute("bairros", bairros);

		return new ModelAndView("consultaCandidatos");
	}
	
	@RequestMapping(name = "consultaCandidatos", value = "/consultaCandidatos", method = RequestMethod.POST)
	public ModelAndView medicoPost(
			@RequestParam Map<String, String> params,
			ModelMap model) {
		String saida = "";
		String erro = "";
		List<Candidato> candidatos = new ArrayList<>();
		List<Curso> cursos = new ArrayList<>();
		List<String> bairros = new ArrayList<>();
		String cmd = "";
		
		
		try {
			cursos = curRepository.findAllByOrderByNomeAsc();
	        candidatos = candRepository.findAllByOrderByNomeAsc();
	        bairros = candRepository.findDistinctBairros();
			
	        String curso = params.get("cursoFiltro");
			String bairro = params.get("bairroFiltro");
			String outro = params.get("outrosFiltros");
			
			cmd = params.get("botao");
			
			if (cmd != null && cmd.equalsIgnoreCase("Filtrar")) {
	            if (curso != null && !curso.trim().isEmpty()) {
	                candidatos = candRepository.findByCurso_Codigo(Integer.parseInt(curso.trim()));
	                saida = "Filtrado por curso";
	                
	            } else if (bairro != null && !bairro.trim().isEmpty()) {
	                candidatos = candRepository.findByBairro(bairro.trim());
	                saida = "Filtrado por bairro";
	                
	            } else if (outro != null && !outro.trim().isEmpty()) {
					if (outro.equalsIgnoreCase("curso")) {
						candidatos = candRepository.findAllByOrderByCurso_Nome();
					}
						
					if (outro.equalsIgnoreCase("bairro")) {
						candidatos = candRepository.findAllByOrderByBairroAsc();
					}
						
					if (outro.equalsIgnoreCase("recente")) {
						candidatos = candRepository.findTop10ByOrderByDataHoraAsc();
					}
						
					if (outro.equalsIgnoreCase("antigo")) {
						candidatos = candRepository.findTop10ByOrderByDataHoraDesc();
					}
	            }		
			}

		} catch (NumberFormatException e) {
			saida = "";
			erro = e.getMessage();
			if (erro.contains("input string")) {
				erro = "Preencha os campos corretamente";
			}
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("cursos", cursos);
	        model.addAttribute("candidatos", candidatos);
	        model.addAttribute("bairros", bairros);
		}

		return new ModelAndView("consultaCandidatos");
	}
}

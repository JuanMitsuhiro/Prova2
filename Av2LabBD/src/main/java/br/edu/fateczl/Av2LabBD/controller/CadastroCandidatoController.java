package br.edu.fateczl.Av2LabBD.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
public class CadastroCandidatoController {

	@Autowired
	private ICandidatoRepository candidatoRepository;
	
	@Autowired
	private ICursoRepository cursoRepository;
	
	@RequestMapping(name = "cadastroCandidato", value = "/cadastroCandidato", method = RequestMethod.GET)
	public ModelAndView candidatoGet(
			@RequestParam Map<String, String> params,
			ModelMap model) {

		List<Curso> cursos = new ArrayList<>();
		
		cursos = cursoRepository.findAll();
		
		model.addAttribute("candidato", new Candidato());
		model.addAttribute("cursos", cursos);

		return new ModelAndView("cadastroCandidato");
	}
	
	@RequestMapping(name = "cadastroCandidato", value = "/cadastroCandidato", method = RequestMethod.POST)
	public ModelAndView candidatoPost(
			@RequestParam Map<String, String> params,
			ModelMap model) {
		String saida = "";
		String erro = "";
		List<Curso> cursos = new ArrayList<>();
		Candidato c = new Candidato();
		String cmd = "";
		
		try {
			cursos = cursoRepository.findAll();
			
			String nome = params.get("nome");
			String email = params.get("email");
			String telefone = params.get("telefone");
			String bairro = params.get("bairro");
			String idCurso = params.get("curso");
			
			cmd = params.get("botao");
			
			
			
			if (cmd.equalsIgnoreCase("Cadastrar")) {
				c.setNome(nome);
				c.setEmail(email);
				c.setTelefone(telefone);
				c.setBairro(bairro);
				c.setCurso(cursoRepository.findById(Integer.parseInt(idCurso)).get());
				c.setDataHora(LocalDateTime.now());
				
				candidatoRepository.save(c);
				
				saida = "Candidato cadastrado com sucesso.";
				c = new Candidato();
			}

		} catch (IllegalArgumentException | DataIntegrityViolationException  e) {
			saida = "";
			erro = e.getMessage();
			if (erro.contains("input string")) {
				erro = "Preencha os campos corretamente";
			}

		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("candidato", c);
			model.addAttribute("cursos", cursos);
		}

		return new ModelAndView("cadastroCandidato");
	}
}

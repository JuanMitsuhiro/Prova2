package br.edu.fateczl.Av2LabBD.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.Av2LabBD.model.LoginAdmin;
import br.edu.fateczl.Av2LabBD.repository.ILoginAdminRepository;


@Controller
public class LoginAdminController {
	
	@Autowired
	private ILoginAdminRepository loginRepository;
    
    @RequestMapping(name = "loginAdmin", value = "/loginAdmin", method = RequestMethod.GET)
    public ModelAndView loginGet(ModelMap model) {
        return new ModelAndView("loginAdmin");
    }
    
    @RequestMapping(name = "loginAdmin", value = "/loginAdmin", method = RequestMethod.POST)
    public ModelAndView loginPost(@RequestParam Map<String, String> params, ModelMap model) {
        
        
        String saida = "";
        String erro = "";
        String login = params.get("login");
        String senha = params.get("senha");
        String cmd = "";
        
        try {
        	cmd = params.get("botao");
  
        	if (cmd.equalsIgnoreCase("Entrar")) {
        		if ("admin".equals(login) && "RAj@lfO%".equals(senha)) {
                    return new ModelAndView("redirect:/areaAdmin");
                } else {
        			erro = "Dados incorretos, tente novamente.";
        			return new ModelAndView("loginAdmin");
        		}
        		
        	}    
        } catch (Exception e) {
        	saida = "";
			erro = e.getMessage();
        } finally {
        	model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
        }

        return new ModelAndView("loginAdmin");
    }  
}

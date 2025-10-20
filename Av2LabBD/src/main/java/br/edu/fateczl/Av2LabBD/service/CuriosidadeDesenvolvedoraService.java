package br.edu.fateczl.Av2LabBD.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import br.edu.fateczl.Av2LabBD.model.Curiosidade;
import br.edu.fateczl.Av2LabBD.model.Desenvolvedora;
import br.edu.fateczl.Av2LabBD.repository.ICuriosidadeRepository;
import br.edu.fateczl.Av2LabBD.repository.IDesenvolvedoraRepository;

@Service
public class CuriosidadeDesenvolvedoraService {
	
	@Autowired
	private ICuriosidadeRepository cRepository;
	
	@Autowired
	private IDesenvolvedoraRepository dRepository;
	
	
	public String curiosidadeAleatoria(String desenvolvedora){
		int codCuriosidade = 0;
		String textoCuriosidade = "";
		
		while(codCuriosidade == 0) {
			codCuriosidade = cRepository.findCodigoCuriosidadeAleatorio(dRepository.findCodigoByNome(desenvolvedora));
		}
		
		Curiosidade curiosidade  = cRepository.findCuriosidade(codCuriosidade);
		textoCuriosidade = curiosidade.getTexto();
		return textoCuriosidade;
	}
	
	public void verificaDadosCarregados() throws IOException {
		if (dRepository.count() == 0) {
	    	carregarDesenvolvedoras();
		}
		if (cRepository.count() == 0) {
	    	carregarCuriosidades();
		}
	}

	private void carregarDesenvolvedoras() throws IOException {
		File arquivo = ResourceUtils.getFile("classpath:txts/desenvolvedoras.txt");
			try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
		    	String linha;
		    	while ((linha = br.readLine()) != null) {
		        	Desenvolvedora d = new Desenvolvedora();
		         	d.setNome(linha.trim());
		         	dRepository.save(d);
		    	}
			}
	}

	private void carregarCuriosidades() throws IOException {
		List<Desenvolvedora> devs = dRepository.findAll();
		for (Desenvolvedora d : devs) {
	    	String nomeArquivo = "classpath:txts/" + d.getNome().toLowerCase() + ".txt";
	     	File arquivo = ResourceUtils.getFile(nomeArquivo);
	        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
	        	String linha;
	        	while ((linha = br.readLine()) != null) {
	        		Curiosidade c = new Curiosidade();
	            	c.setTexto(linha.trim());
	            	c.setDesenvolvedora(d);
	            	cRepository.save(c);
	        	}
	        }
		}
	}

}

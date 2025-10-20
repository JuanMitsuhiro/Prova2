package br.edu.fateczl.Av2LabBD.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fateczl.Av2LabBD.model.Curso;

public interface ICursoRepository extends JpaRepository<Curso, Integer>{

	public Optional<Curso> save(int codigo);
	
	public List<Curso> findAllByOrderByNomeAsc();
	

}

package br.edu.fateczl.Av2LabBD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.fateczl.Av2LabBD.model.Desenvolvedora;

@Repository
public interface IDesenvolvedoraRepository extends JpaRepository<Desenvolvedora, Integer>{
	
	
	@Query("SELECT d.codigo FROM Desenvolvedora d WHERE d.nome = ?1")
	public Integer findCodigoByNome(String nome);
	
	public List<Desenvolvedora> findAllByOrderByNomeAsc();
	
	@Query("SELECT d FROM Desenvolvedora d WHERE d.codigo = ?1")
	public Desenvolvedora findCurso(int codigoCurso);
	
}

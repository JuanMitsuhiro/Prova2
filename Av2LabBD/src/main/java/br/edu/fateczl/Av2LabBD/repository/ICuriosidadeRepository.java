package br.edu.fateczl.Av2LabBD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.fateczl.Av2LabBD.model.Curiosidade;


@Repository
@Transactional
public interface ICuriosidadeRepository extends JpaRepository<Curiosidade, Integer>{
	
	@Query("SELECT c FROM Curiosidade c WHERE codigo = ?1")
	public Curiosidade findCuriosidade(int codigo);
	
	@Query(value = "DECLARE @result INT; EXEC sp_retornacuriosidade ?1, @result OUTPUT; SELECT @result", 
	           nativeQuery = true)
	public int findCodigoCuriosidadeAleatorio(int desenvolvedora);
	
	public List<Curiosidade> findAllByOrderByDesenvolvedoraCodigoAsc();
}

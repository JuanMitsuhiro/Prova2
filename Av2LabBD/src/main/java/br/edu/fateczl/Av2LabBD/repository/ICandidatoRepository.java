package br.edu.fateczl.Av2LabBD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.fateczl.Av2LabBD.model.Candidato;

public interface ICandidatoRepository extends JpaRepository<Candidato, Integer>{
	
	
	public List<Candidato> findAllByOrderByNomeAsc();
	
	@Query("SELECT c FROM Candidato c")
	public List<Candidato> listarCandidatos();
	
	//lista bairros diferentes
	@Query("SELECT DISTINCT c.bairro FROM Candidato c ORDER BY c.bairro")
    List<String> findDistinctBairros();
	
	
	//FILTROS
	
	//por curso escolhido
	public List<Candidato> findByCurso_Codigo(int cursoCodigo);
	
	
	//por bairro
	public List<Candidato> findByBairro(String bairro);
	
	
	//ordem por curso
	public List<Candidato> findAllByOrderByCurso_Nome();
	
	@Query("SELECT c FROM Candidato c ORDER BY c.curso.nome")
	List<Candidato> findAllOrderByCurso();
	
	@Query(value = "SELECT c.* FROM candidato c " +
            "INNER JOIN curso cur ON c.curso_codigo = cur.codigo " +
            "ORDER BY cur.nome", 
    nativeQuery = true)
	List<Candidato> findAllOrderByCursoNative();
	
	//ordem por bairro
	public List<Candidato> findAllByOrderByBairroAsc();
	
	@Query("SELECT c FROM Candidato c ORDER BY c.bairro ASC")
	public List<Candidato> buscarOrdemporBairro();

	
	//10 cadastros mais recentes
	public List<Candidato> findTop10ByOrderByDataHoraAsc();
	
//	@Query("SELECT TOP 10 c FROM Candidato c ORDER BY c.dataHora ASC")
//	public List<Candidato> buscar10PrimeirosCadastrados();
	
	
	//10 cadastros mais antigos
	public List<Candidato> findTop10ByOrderByDataHoraDesc();
	
//	@Query("SELECT TOP 10 c FROM Candidato c ORDER BY c.dataHora DESC")
//	public List<Candidato> buscar10UltimosCadastrados();
	
}

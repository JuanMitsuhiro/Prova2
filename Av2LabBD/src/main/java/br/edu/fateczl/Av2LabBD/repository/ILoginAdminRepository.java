package br.edu.fateczl.Av2LabBD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import br.edu.fateczl.Av2LabBD.model.LoginAdmin;

public interface ILoginAdminRepository extends JpaRepository<LoginAdmin, String>{
	
	@Procedure(name = "Depto.sp_depto_sigla")
	public boolean validarLogin(@Param("login") String login, @Param("senha") String senha);

}

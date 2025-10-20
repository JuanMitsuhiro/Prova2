<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Candidato</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<br />
	<div align="center">
		<jsp:include page="menu.jsp"/>
	</div>
	<br/>
	<div class="conteiner" align="center">
		<h1>Cadastro de Candidato</h1>
		<br/>
		<form action="cadastroCandidato" method="post">
			<table>
				<tr>
					<td colspan="4">
						<label for="nome">Nome:</label>
						<input type="text" 
						id="nome" name="nome" placeholder="Nome completo"
						value='<c:out value="${candidato.nome}"/>'required>
					</td>				
				<tr/>
				<tr>
					<td colspan="4">
						<label for="email">Email:</label>
						<input type="text" 
						id="email" name="email" placeholder="exemplo@exemplo.com"
						value='<c:out value="${candidato.email}"/>'required>
					</td>				
				<tr/>
				<tr>
					<td colspan="3">
						<label for="telefone">Telefone:</label>
						<input type="tel" maxlength="11" pattern="[0-9]*"
						id="telefone" name="telefone" placeholder="(DDD + telefone)"
						value='<c:out value="${candidato.telefone}"/>'>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<label for="bairro">Bairro:</label>
						<input type="text" 
						id="bairro" name="bairro" placeholder="Bairro de residência"
						value='<c:out value="${candidato.bairro}"/>'required>
					</td>				
				<tr/>
				<tr>
					<td colspan="3">
						<label for="curso">Curso de interesse:</label>
						<select id="curso" name="curso" class="form-select" aria-label="Selecione um curso">
			            	<option selected value="">-- Selecione uma opção --</option>	
			            	<c:forEach var="curso" items="${cursos}">
			                	<option value="${curso.codigo}"
			                		<c:if test="${candidato.curso.codigo == curso.codigo}">selected</c:if>>
			                		${curso.nome}
			                	</option>
			            	</c:forEach>
			        </select>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit"
						id="botao" name="botao" value="Cadastrar"
						class="btn btn-success">
					</td>																		
				</tr>
			</table>
		
		</form>
	</div>
	<br />
	<div class="conteiner" align="center">
		<h2 style="color: blue;"><c:out value="${saida}" /></h2>
	</div>
	<div class="conteiner" align="center">
		<c:if test="${not empty erro}">
			<h2 style="color: red;"><c:out value="${erro}" /></h2>
		</c:if>
	</div>
</body>
</html>
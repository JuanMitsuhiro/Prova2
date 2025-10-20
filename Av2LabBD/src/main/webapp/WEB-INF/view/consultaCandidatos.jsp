<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Consulta de Candidatos</title>
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
		<h1>Consulta de Candidato</h1>
		<br/>
		<form action="consultaCandidatos" method="post">
			<table>
				<tr>
					<td colspan="3">
						<label for="cursoFiltro">Por Curso:</label>
						<select id="cursoFiltro" name="cursoFiltro" class="form-select" aria-label="Selecione um Curso">
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
					<td colspan="3">
                        <label for="bairroFiltro">Por Bairro:</label>
                        <select id="bairroFiltro" name="bairroFiltro" class="form-select">
                            <option value="">-- Selecione um bairro --</option>  
                            <c:forEach var="bairro" items="${bairros}">
                                <option value="${bairro}">
                                    ${bairro}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
				</tr>
				<tr>
					<td colspan="2">
						<label for="outrosFiltros">Outros Filtros:</label>
					    <select id="outrosFiltros" name="outrosFiltros" class="form-select" aria-label="Selecione um filtro">
					    	<option value="">Nenhum</option>
					        <option value="curso">Ordem por Curso</option>
					        <option value="bairro">Ordem por Bairro</option>
					        <option value="recente">10 mais recentes</option>
					        <option value="antigo">10 mais antigos</option>
					    </select>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit"
						id="botao" name="botao" value="Filtrar"
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
		<div class="conteiner" align="center">
		<c:if test="${not empty candidatos}">
			<table class="table table-info table-striped">
				<thead>
					<tr>
						<th>Nome</th>
						<th>email</th>
						<th>Telefone</th>
						<th>Bairro</th>
						<th>Curso</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${candidatos}">
						<tr>
							<td>${c.nome}</td>
							<td>${c.email}</td>
							<td>${c.telefone}</td>
							<td>${c.bairro}</td>
							<td>${c.curso.nome}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<div align="center">
		<jsp:include page="menu.jsp"/>
	</div>
	<br/>
	<div class="conteiner" align="center">
		<h1>Cadastro de Curiosidades</h1>
		<br/>
		<form action="cadastroTipo" method="post">
			<table >
				<tr>
					<td colspan="3">
						<label for="texto">Curiosidade:</label>
   						<div class="input-group">
						    <span class="input-group-text" id="basic-addon1">Curiosidade</span>
                            <input type="text" class="form-control" maxlength="255" id="texto" 
                             name="texto" size="40" value='<c:out value="${curiosidade.texto}"/>'>
						</div>
					</td>	
				<tr/>
				<tr>
					<td colspan="1">
						<label for="desenvolvedora">Desenvolvedora:</label>
						<select id="desenvolvedora" name="desenvolvedora" class="form-select" aria-label="Selecione uma desenvolvedora">
			            	<option selected value="">-- Selecione uma opção --</option>	
			            	<c:forEach var="desenvolvedora" items="${desenvolvedoras}">
			                	<option value="${desenvolvedora.codigo}"
			                		<c:if test="${curiosidade.desenvolvedora_codigo == desenvolvedora.codigo}">selected</c:if>>
			                		${desenvolvedora.nome}
			                	</option>
			            	</c:forEach>
			        </select>
				</tr>
				<tr>
					<td>
						<input type="submit"
						id="botao" name="botao" value="Inserir"
						class="btn btn-success">
					</td>													
					<td>
						<input type="submit"
						id="botao" name="botao" value="Listar"
						class="btn btn-secondary">
					</td>											
				</tr>
			</table>
		
		</form>
	</div>
	<br />
	<div class="conteiner" align="center">
		<c:if test="${not empty saida}">
			<h2 style="color: blue;"><c:out value="${saida}" /></h2>
		</c:if>
	</div>
	<div class="conteiner" align="center">
		<c:if test="${not empty erro}">
			<h2 style="color: red;"><c:out value="${erro}" /></h2>
		</c:if>
	</div>
	<div class="conteiner" align="center">
		<c:if test="${not empty curiosidades}">
			<table class="table table-info table-striped">
				<thead>
					<tr>
						<th>Desenvolvedora</th>
						<th>Texto</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${curiosidades}">
						<tr>
							<td>${c.desenvolvedora.nome}</td>
							<td>${c.texto}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>
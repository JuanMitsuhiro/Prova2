<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Curiosidades</title>
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
		<h1>Você Sabia?</h1>
		<br/>
		<form action="curiosidades" method="post">
			<table>
				<tr>
					<td>
						<input type="submit"
						id="botao" name="botao" value="MICROSOFT"
						class="btn btn-success">
					</td>	
					<td>
						<input type="submit"
						id="botao" name="botao" value="NINTENDO"
						class="btn btn-success">
					</td>	
					<td>
						<input type="submit"
						id="botao" name="botao" value="SEGA"
						class="btn btn-success">
					</td>	
					<td>
						<input type="submit"
						id="botao" name="botao" value="SONY"
						class="btn btn-success">
					</td>																		
				</tr>

			</table>		
		</form>
	</div >
	<br />
	<div class="conteiner" align="center"><h5 style="color: blue;"><c:out value="${saida}" /></h5></div>
	<div class="conteiner" align="center">
 		<h2 style="color: blue;"><c:out value="${erro}" /></h2>
		<c:if test="${not empty saida}">
			<p class="text-muted">Redirecionando para cadastro em 15 segundos...</p>
 			<meta http-equiv="refresh" content="15; url=cadastroCandidato">
		</c:if>
	</div>
</body>
</html>
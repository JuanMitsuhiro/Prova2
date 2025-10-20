<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Administrador</title>
</head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<body>
	<br />
	<div align="center">
		<jsp:include page="menu.jsp"/>
	</div>
	<br />
	<div class="conteiner" align="center">
		<h1>Login</h1>
		<br />
		<form action="loginAdmin" method="post">
			<table>
				<tr>
					<td colspan="2">
						<label for="login">Login:</label>
   						<input type="text" maxlength="12"
						id="login" name="login">
					</td>
				</tr>
				<tr >
					<td colspan="4" >
						<label for="senha">Senha:</label>
						<input type="password" maxlength="8"
						id="senha" name="senha">
					</td>
				</tr>
				<tr align="center">
					<td>
						<input type="submit"
						id="botao" name="botao" value="Entrar"
						class="btn btn-success">
					</td>								
					<td>
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
</body>
</html>
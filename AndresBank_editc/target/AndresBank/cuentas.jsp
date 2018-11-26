<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Login</title>
<link rel="stylesheet" href="./css/styles.css" />
</head>
<body>
	<h1>Tus cuentas</h1>

	<ul>
		<c:forEach var="unaCuenta" items="${lista_cuentas}">
			<li>
			${unaCuenta.nombre}|${unaCuenta.numero}| ${unaCuenta.saldo}
			<a href="./editarcuenta?cid=${unaCuenta.cid}">Editar</a>
			<a href="./BorrarCuenta?cid=${unaCuenta.cid}">Borrar Cuenta</a>
			</li>
		</c:forEach>
	</ul>

	<div>
		<a href="./crearcuenta">Contratar cuenta</a>
	</div>

</body>
</html>
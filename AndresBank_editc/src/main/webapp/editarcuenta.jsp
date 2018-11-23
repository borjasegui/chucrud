<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Login</title>
<link rel="stylesheet" href="./css/styles.css" />
</head>
<body>
	<h1>Editar cuenta</h1>
	
	<div class="error">${mensaje_error}</div>
	<form action="" method="POST">
		<input type="hidden" name="cid" value="${micuenta.cid}" />
		
		<div>
			<input type="text" placeholder="Nombre" name="nombre" minlength="3" value="${micuenta.nombre}"/>
		</div>
		<div>
			<input type="text" placeholder="Número" name="numero" value="${micuenta.numero}"/>
		</div>
		<div>
			<input type="text" placeholder="Saldo" name="saldo" value="${micuenta.saldo}"/>
		</div>
		<div>
			<button>Guardar</button>
			
		</div>
	</form>
</body>
</html>
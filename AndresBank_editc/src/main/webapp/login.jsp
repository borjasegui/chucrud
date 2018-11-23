<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Login</title>
<link rel="stylesheet" href="./css/styles.css" />
</head>
<body>
	<h1>Login</h1>
	
	<div class="error">${mensaje_error}</div>
	<form action="" method="POST">
		<div>
			<input type="text" placeholder="Dni" name="dni" minlength="9" />
		</div>
		<div>
			<input type="text" placeholder="Password" name="pass" />
		</div>
		<div>
			<button>Acceder</button>
		</div>
	</form>
</body>
</html>
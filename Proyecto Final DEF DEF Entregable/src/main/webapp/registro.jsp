<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="css/style.css">
	    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
	    <script src="//unpkg.com/alpinejs" defer></script>
	    <title>Formulario de Registro</title>
	    <style>
	   		 .hidden {
	    		display:none;
	    	}
	    	
	    	.error {
	    		color: orange;
	    	}
	    </style>
	</head>	
	<body>
		<% 
		String mostrar= "false";
		HttpSession sesion = request.getSession();
		if (sesion.getAttribute("falloEdad") != null) {
			mostrar = String.valueOf(sesion.getAttribute("falloEdad")); 
		}
		System.out.println(mostrar);
		%>
		<main  x-data="{ show:<%=mostrar%>}">
			<div class="formIyR">		
		    <form action="/ProyectoFinal/CrearUsuarioServlet" method="post" name="registro_user">
		        <h1>FORMULARIO DE REGISTRO</h1>
		        <input class="input" type="text" placeholder="Nombre" name="nombre" required>
		        <input class="input" type="text" placeholder="Apellidos" name="apellidos" required>
		        <p class="error"  x-bind:class="! show ? 'hidden' : ''">Debes ser mayor de edad para registrarte.</p>
		        <input class="input" type="date" placeholder="Fecha nacimiento" max="" name="f_nacimiento">
		        <input class="input" type="text" placeholder="DNI" required name="dni" pattern="[0-9]{8}[A-Za-z]{1}">
		        <input class="input" type="tel" placeholder="Teléfono" required name="tel">
		        <input class="input" type="email" placeholder="Correo electrónico" name="email">
		        <input class="input" type="password" placeholder="Contraseña" required name="pass"  minlength="4">
		        <div class="check">
		            <input type="checkbox" name="terminos" id="terminos" required>
		            <label for="terminos">Acepto términos y condiciones.</label>
		        </div>
		         <a class="styleEnlaces" href="pagHotel.html"><button class="btnRegistro">¡Regístrate!</button></a>
		    </form>
		    <p class="link"><a href="index.jsp">¿Ya tienes cuenta?</a></p>
		    </div>
	    </main>
		<%
	    String restart = "false";
		session.setAttribute("falloEdad", restart);
	    %>	    
	</body>
</html>
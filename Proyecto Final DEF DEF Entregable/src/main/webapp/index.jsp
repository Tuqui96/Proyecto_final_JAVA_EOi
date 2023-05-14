<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<%@ page import="entity.Usuario" %>

<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
	    <link rel="stylesheet" href="css/style.css">
	    <script src="https://unpkg.com/alpinejs" defer></script>
	    <title>Inicio de Sesion</title>
	</head>
	
	<body>
		 <div class="formIyR">			 
			<% 
			String mostrar= "false";
			HttpSession sesion = request.getSession();
			if (sesion.getAttribute("sesionFallo") != null) {
				mostrar = String.valueOf(sesion.getAttribute("sesionFallo")); 
			}
			System.out.println(mostrar);
			%>
			<main x-data="{ show:<%=mostrar%>}">
			    <form action="/ProyectoFinal/InicioSesionServlet" method="post">
			        <h1>Iniciar Sesión</h1> 
			        <input class="input" type="text" placeholder="Usuario (DNI)" name="dni"> 
			        <p class="error"  x-bind:class="! show ? 'hidden' : ''"> Contraseña incorrecta. Intentalo de nuevo:</p>
			        <input class="input" type="password" placeholder="Contraseña" name="pass">
			        <a class="styleEnlaces" href="pagHotel.html"><button class="btnInicio" >Iniciar Sesion</button></a>
			    </form>
			    <p class="link"><a href="registro.jsp">¿Aun no tienes cuenta?</a></p>
		    </main>
		    <%
		    String restart = "false";
			session.setAttribute("sesionFallo", restart);
		    %>
		</div>
	</body>
</html>
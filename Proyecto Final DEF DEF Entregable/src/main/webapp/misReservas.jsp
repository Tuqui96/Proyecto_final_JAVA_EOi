<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="service.ReservaService" %>
<%@ page import="entity.Reserva" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Usuario" %>

<!DOCTYPE html>
<html>
	    <head>
        <meta charset="UTF-8">
        <title>Maya | Mis reservas</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="css/stylePag.css">
		<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    </head>
    <body>
		<header>
	        <a href="pagHotel.html" class="">
	            <img class="imgLogo" src="https://jaddyus.github.io/newsletter/logo-hotel.png" alt="Logo Maya Hoteles">
	        </a>
	        <nav class="containerHeader">
	            <div class="navigation">
	                <a href="pagHotel.html">Inicio</a>
	                <a href="misReservas.jsp">Mis reservas</a>
	                <a href="index.jsp">Cerrar sesion</a>
	            </div>
	        </nav>
	    </header>
	    
	    <main>
	    	<div class="reservas-pendientes">
				<h1>Mis Reservas</h1>
		    	<h2>Reservas pendientes</h2>
		   		<table class="reservas-table">
		        	<tr>
			            <th>Fecha de inicio</th>
			            <th>Fecha de fin</th>
			            <th>Estado</th>
		        	</tr>
		        	<% 
		        	
		        	HttpSession sesion = request.getSession(); 
		        	int idUsuario = (int)sesion.getAttribute("idUsuario");
		        	
		        	ReservaService reservaService= new ReservaService();
		        	ArrayList<Reserva> reservas = reservaService.findReservasUsuarioPendientes(idUsuario);
		        	
		        	
		        	for (Reserva reserva : reservas) { %>
		            <tr>
		                <td><%= reserva.getFechaEntrada() %></td>
		                <td><%= reserva.getFechaSalida() %></td>
		                <td><%= reserva.getEstadoReserva() %></td>
		                <td>
		                	<form action="/ProyectoFinal/SeleccionReservaServlet" method="post" name="editar_reserva">
		                		<input type="hidden" name="id_reserva" value="<%= reserva.getIdReserva() %>">        
		                		<input type="submit" value="Editar"> 
		                	</form>   
		                </td>	
		                <td> 
		                	<form action="/ProyectoFinal/EliminarReservaServlet" method="post" name="eliminar_reserva">
		                		<input type="hidden" name="id_reserva" value="<%= reserva.getIdReserva() %>">                     
		                		<input type="submit" value="Eliminar">
		                	</form>               
		                </td>
		            </tr>
		        <% } %>
		    </table>
		    </div>
		    <div class="historico">
		    <h2>Histórico de reservas</h2>
		   		<table>
		        	<tr>
		        		<th>ID</th>
		        		<th>Hab</th>
			            <th>Fecha de inicio</th>
			            <th>Fecha de fin</th>
			            <th>Estado</th>
		        	</tr>
		        	<% 
		        	
		        	ReservaService reservaService2= new ReservaService();
		        	ArrayList<Reserva> reservas2 = reservaService.findReservasUsuario(idUsuario);
		        	
		        	for (Reserva reserva2 : reservas2) { %>
		            <tr>
		                <td><%= reserva2.getIdReserva() %></td>
		            	<td><%= reserva2.getIdHabitacion() %></td>
		                <td><%= reserva2.getFechaEntrada() %></td>
		                <td><%= reserva2.getFechaSalida() %></td>
		                <td><%= reserva2.getEstadoReserva() %></td>
		            </tr>
		        <% } %>
		    </table> 
   		 </div>
    </main>          
	</body>
</html>
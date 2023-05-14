<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="service.ReservaService" %>
<%@ page import="entity.Reserva" %>
<%@ page import="service.UsuarioService" %>
<%@ page import="entity.Usuario" %>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
	    <head>
        <meta charset="UTF-8">
        <title>Maya | Administración</title>
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
			    <h1>Administracion del hotel</h1>
		    	<h2>Reservas pendientes</h2>
		   		<table class="reservas-table">
		        	<tr>
		        		<th>ID</th>
		        		<th>Hab</th>
			            <th>Fecha de inicio</th>
			            <th>Fecha de fin</th>
		        	</tr>
		        	<% 
		        
		        	ReservaService reservaService= new ReservaService();
		        	ArrayList<Reserva> reservas = reservaService.findReservaPorEstado();
		        	
		        	for (Reserva reserva : reservas) { %>
		            <tr>
		            	<td><%= reserva.getIdReserva() %></td>
		            	<td><%= reserva.getIdHabitacion() %></td>
		                <td><%= reserva.getFechaEntrada() %></td>
		                <td><%= reserva.getFechaSalida() %></td>
		                <td>
		                	<form action="/ProyectoFinal/aceptarReservaServlet" method="post" name="confirmar_reserva">
		                		<input type="hidden" name="id_reserva" value="<%= reserva.getIdReserva() %>">        
		                		<input type="submit" value="Confirmar"> 
		                	</form>   
		                </td>	
		                <td> 
		                	<form action="/ProyectoFinal/rechazarReservaServlet" method="post" name="rechazar_reserva">
		                		<input type="hidden" name="id_reserva" value="<%= reserva.getIdReserva() %>">                     
		                		<input type="submit" value="Rechazar">
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
		        		<th>Reserva</th>
		        		<th>Habitación</th>
		        		<th>Cliente</th>
			            <th>Fecha de inicio</th>
			            <th>Fecha de fin</th>
			            <th>Estado</th>
		        	</tr>
		        	<% 
		        
		        	ReservaService reservaService2= new ReservaService();
		        	ArrayList<Reserva> reservas2 = reservaService.findReservasHotel();
		        	Usuario usuario;
		        	UsuarioService usuarioService = new UsuarioService();
		        	
		        	for (Reserva reserva2 : reservas2) { 
		        		usuario = usuarioService.findUsuarioPorID(reserva2.getIdUsuario());%>
		            <tr>
		                <td><%= reserva2.getIdReserva() %></td>
		            	<td><%= reserva2.getIdHabitacion() %></td>
		            	<td><%= usuario.getNombre() %> <%= usuario.getApellidos() %></td>
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
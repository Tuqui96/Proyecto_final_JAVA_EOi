<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="service.ReservaService" %>
<%@ page import="entity.Reserva" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Maya | Editar Reserva</title>
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
    	<% 
	    	HttpSession sesion = request.getSession(); 
	    	int idReserva = (int)sesion.getAttribute("reservaEditar");
	    	ReservaService reservaService = new ReservaService();
	    	Reserva reserva = reservaService.obtenerReservaPorIdReserva(idReserva);
    	%>
    	<main>
    		<div class="reservas-pendientes">
    		<table class="reservas-table">
    			<tr>
    				<th>Fecha entrada</th>
    				<th>Fecha salida</th>
    			</tr>
    			<tr>
    				<td><%= reserva.getFechaEntrada() %></td>
    				<td><%= reserva.getFechaSalida() %></td>
    			</tr>
    		</table>
    		</div>
    		<form action="/ProyectoFinal/EditarReservaServlet" method="post" name="crear_reserva">
                <fieldset>
                    <legend>Editar reserva</legend>
                    <p>
                    <label for="fecha_entrada">Fecha entrada:</label>
                    <input type="date" id="fecha_entrada" name="fecha_entrada" required>
                    </p>
                    <p>
                        <label for="fecha_salida">Fecha salida:</label>
                        <input type="date" id="fecha_salida" name="fecha_salida" required>
                    </p>
                    <p>
                        <label for="pax">Cantidad personas:</label>
                        <input type="number" id="pax" name="pax" min="1" max="3" required>
                    </p>
                    <p>
                        <input type="submit" value="Confirmar">
                    </p>
                </fieldset>
            </form>
    	</main>
	</body>
</html>
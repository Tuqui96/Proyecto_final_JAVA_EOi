

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Reserva;
import service.ReservaService;


@WebServlet("/EditarReservaServlet")

public class EditarReservaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {

		HttpSession sesion = request.getSession(); 
    	int idReserva = (int)sesion.getAttribute("reservaEditar");
    	ReservaService reservaService = new ReservaService();
    	Reserva reserva = reservaService.obtenerReservaPorIdReserva(idReserva);
    	
    	reserva.setFechaEntrada(Date.valueOf(request.getParameter("fecha_entrada")));
    	reserva.setFechaSalida(Date.valueOf(request.getParameter("fecha_salida")));
    	//reserva.setPersonas(request.getParameter("personas"));

    	reservaService.actualizarReserva(reserva);
    	
    	response.sendRedirect(request.getContextPath() + "/misReservas.jsp");
        
    }
}
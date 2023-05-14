

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ReservaService;

@WebServlet("/aceptarReservaServlet")
public class aceptarReservaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReservaService reservaService = new ReservaService();
		
		PrintWriter out = response.getWriter();
		
		// Obtener el ID de la reserva a aceptar desde la sesión
		int idReserva = Integer.parseInt(request.getParameter("id_reserva"));

		// Actualizar el estado de la reserva a "ACEPTADA"
		boolean reservaAceptada = reservaService.aceptarReserva(idReserva);
		if (!reservaAceptada) {
			String message = "Error al aceptar la reserva";
			out.print(message);
		} 
		response.sendRedirect(request.getContextPath() + "/adminHotel.jsp");
	}
}

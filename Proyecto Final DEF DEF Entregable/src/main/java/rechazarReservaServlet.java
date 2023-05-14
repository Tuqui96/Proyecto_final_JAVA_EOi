

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import service.ReservaService;

@WebServlet("/rechazarReservaServlet")
public class rechazarReservaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ReservaService reservaService = new ReservaService();
		
		PrintWriter out = response.getWriter();
		
		// Obtener el ID de la reserva a aceptar desde la sesión
		int idReserva = Integer.parseInt(request.getParameter("id_reserva"));

		// Actualizar el estado de la reserva a "Rechazada"
		boolean reservaRechazada = reservaService.rechazarReserva(idReserva);
		if (reservaRechazada) {
			// Volver a la pagina
			String message = "Reserva rechazada";
			out.print(message);
		} else {
			String message = "Error al rechazar la reserva";
			out.print(message);
		}
		response.sendRedirect(request.getContextPath() + "/adminHotel.jsp");

	}

}

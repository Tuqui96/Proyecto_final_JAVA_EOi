

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ReservaService;

@WebServlet("/CrearReservaServlet")
public class CrearReservaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
        PrintWriter out = response.getWriter();
        ReservaService ReservaService = new ReservaService();

        Date fechaEntrada = Date.valueOf(request.getParameter("fecha_entrada"));
        Date fechaSalida = Date.valueOf(request.getParameter("fecha_salida"));
        int cantPersonas = Integer.parseInt(request.getParameter("pax"));

        int idUsuario = (int)sesion.getAttribute("idUsuario");
        if (fechaSalida.after(fechaEntrada)) {
            boolean reservaCreada = ReservaService.crearReserva(idUsuario, fechaEntrada, fechaSalida, cantPersonas);
            if (reservaCreada) {
                response.sendRedirect(request.getContextPath() + "/misReservas.jsp");
            } else {
                String message = "No hay habitaciones disponibles.";
                out.print(message);
                response.sendRedirect(request.getContextPath() + "/pagHotel.html");
            }
        }else {
            String message = "Fechas incorrectas";
            out.print(message);
        }
    }
}
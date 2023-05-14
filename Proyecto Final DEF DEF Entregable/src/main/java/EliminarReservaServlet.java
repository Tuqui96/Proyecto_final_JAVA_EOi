

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ReservaService;


@WebServlet("/EliminarReservaServlet")
public class EliminarReservaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
    	
    	ReservaService reservaService = new ReservaService();

        int idReserva = Integer.parseInt(request.getParameter("id_reserva"));
        
        reservaService.eliminarEstado(idReserva);
        reservaService.eliminarReserva(idReserva);
       
        response.sendRedirect(request.getContextPath() + "/misReservas.jsp");
            
        
    }
}
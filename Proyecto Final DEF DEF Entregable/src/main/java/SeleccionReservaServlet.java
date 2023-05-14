

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SeleccionReservaServlet
 */
@WebServlet("/SeleccionReservaServlet")
public class SeleccionReservaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {

    	HttpSession session = request.getSession();
    	
        // Obtener el ID de la reserva a editar
        int idReserva = Integer.parseInt(request.getParameter("id_reserva"));
        
        //Guardarla en la sesión
        session.setAttribute("reservaEditar",idReserva);
        
        // Buscar la reserva por su ID y pasarla a la página JSP para editarla
        response.sendRedirect(request.getContextPath() + "/editarReserva.jsp");        
    }

}

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UsuarioService;


@WebServlet("/InicioSesionServlet")
public class InicioSesionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		UsuarioService usuarioService = new UsuarioService();
		String dni = request.getParameter("dni");
		String password = request.getParameter("pass");
		String contrasena = usuarioService.findContrasenaPorDNI(dni);
		
		int rol=usuarioService.findUsuarioPorDNI(dni).getRol();

		if (contrasena.equals(password)){
		
			if (rol==9) {
				session.setAttribute("idUsuario", (int)usuarioService.findIdUsuarioPorDNI(dni));
				response.sendRedirect(request.getContextPath() + "/adminHotel.jsp");
			}
			if (rol==1) {
				session.setAttribute("idUsuario", usuarioService.findIdUsuarioPorDNI(dni));
				response.sendRedirect(request.getContextPath() + "/pagHotel.html"); 
			}
												
		}else {
			String fallo = "true";
			session.setAttribute("sesionFallo", fallo);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}

}
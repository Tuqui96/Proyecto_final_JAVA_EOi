import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UsuarioService;
import entity.Usuario;

@WebServlet("/CrearUsuarioServlet")
public class CrearUsuarioServlet extends HttpServlet {
   
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioService usuarioService = new UsuarioService();
		HttpSession session = request.getSession();
		Usuario usuario = new Usuario();
		
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		Date nacimiento = Date.valueOf(request.getParameter("f_nacimiento"));
		String password = request.getParameter("pass");
		String email = request.getParameter("email");
		String telefono = request.getParameter("tel");
		String dni = request.getParameter("dni");
		LocalDate minLegalAgeDate = LocalDate.now().minusYears(18);
        LocalDate birthDate = nacimiento.toLocalDate();
        
        
        if (birthDate.isBefore(minLegalAgeDate)) {
        	usuario.setDni(dni);
        	usuario.setNombre(nombre);
        	usuario.setApellidos(apellidos);
        	usuario.setContrasena(password);
        	usuario.setFechaNacimiento(nacimiento);
        	usuario.setEmail(email);
        	usuario.setTelefono(telefono);
    		usuarioService.crearUsuario(usuario);
    		response.sendRedirect(request.getContextPath() + "/index.jsp");
        }else {
        	
        	String fallo = "true";
			session.setAttribute("falloEdad", fallo);
			response.sendRedirect(request.getContextPath() + "/registro.jsp");
        }
    }
}
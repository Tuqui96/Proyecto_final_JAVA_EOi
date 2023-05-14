
package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Usuario;

public class UsuarioRepository {

  private java.sql.Connection openConnection() {
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String user = "System";
    String pass = "mercy";

    try {
      Connection conexion = DriverManager.getConnection(url, user, pass);
      System.out.println("Conectando a " + url);
      return conexion;
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      System.out.println("Error: ");
      e.printStackTrace();
    }
    return null;
  }

  // Crear usuario
  public boolean crearUsuario(Usuario usuario) {
    Connection conexion = openConnection();
    String querry = "INSERT INTO USUARIOS VALUES (sqs_id_usuario.nextVal, ?, ?, ?, ?, ?, ?, ?, '1')";
    try {
      PreparedStatement pst = conexion.prepareStatement(querry);
      pst.setString(1, usuario.getDni());
      pst.setString(2, usuario.getNombre());
      pst.setString(3, usuario.getApellidos());
      pst.setDate(4, usuario.getFechaNacimiento());
      pst.setString(5, usuario.getContrasena());
      pst.setString(6, usuario.getTelefono());
      pst.setString(7, usuario.getEmail());
      pst.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  // Actualizar usuario
  public boolean actualizarUsuario(Usuario usuario) {
    Connection conexion = openConnection();
    String query = "UPDATE USUARIOS SET NOMBRE = ?, APELLIDOS = ?, FECHA_NACIMIENTO = ?, CONTRASEÑA = ?, TELEFONO = ?, EMAIL, ? WHERE DNI = ?";
    try {
      PreparedStatement pst = conexion.prepareStatement(query);
      pst.setString(1, usuario.getNombre());
      pst.setString(2, usuario.getApellidos());
      pst.setDate(3, usuario.getFechaNacimiento());
      pst.setString(4, usuario.getContrasena());
      pst.setString(5, usuario.getTelefono());
      pst.setString(6, usuario.getEmail());
      pst.setString(7, usuario.getDni());
      pst.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  // Buscar usuario por DNI
  public Usuario findUsuarioPorDNI(String dni) {
    Usuario usuario = new Usuario();
    String querry = "SELECT * FROM USUARIOS WHERE DNI = ?";
    try {
      Connection conexion = openConnection();
      PreparedStatement pst = conexion.prepareStatement(querry);
      pst.setString(1, dni);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        usuario = new Usuario(rs.getInt(1),
            dni,
            rs.getString(3),
            rs.getString(4),
            rs.getDate(5),
            rs.getString(6),
            rs.getString(7),
            rs.getString(8),
            rs.getInt(9));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return usuario;
  }

  // Buscar usuario por DNI
  public Usuario findUsuarioPorID(int id) {
    Usuario usuario = new Usuario();
    String querry = "SELECT * FROM USUARIOS WHERE ID_USER = ?";
    try {
      Connection conexion = openConnection();
      PreparedStatement pst = conexion.prepareStatement(querry);
      pst.setInt(1, id);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        usuario = new Usuario(id,
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getDate(5),
            rs.getString(6),
            rs.getString(7),
            rs.getString(8),
            rs.getInt(9));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return usuario;
  }

  
  //Buscar ID usuario por DNI
  public int findIdUsuarioPorDNI(String dni) {
	    int idUsuario = 0;
	    String querry = "SELECT * FROM USUARIOS WHERE DNI = ?";
	    try {
	      Connection conexion = openConnection();
	      PreparedStatement pst = conexion.prepareStatement(querry);
	      pst.setString(1, dni);
	      ResultSet rs = pst.executeQuery();
	      while (rs.next()) {
	          idUsuario = rs.getInt(1);
	        }
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    return idUsuario;
	  }
  
  // Buscar contraseña de usuario por DNI
  public String findContrasenaPorDNI(String dni) {
    String contrasena = "0";
    String querry = "SELECT CONTRASENYA FROM USUARIOS WHERE DNI = ?";
    try {
      Connection conexion = openConnection();
      PreparedStatement pst = conexion.prepareStatement(querry);
      pst.setString(1, dni);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        contrasena = rs.getString(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return contrasena;
  }

  // Eliminar usuario
  public boolean eliminarUsuario(String dni) {
    Connection conexion = openConnection();
    String query = "DELETE FROM USUARIOS WHERE DNI = ?";
    try {
      PreparedStatement pst = conexion.prepareStatement(query);
      pst.setString(1, dni);
      pst.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  // Ver todos los Usuarios
  public ArrayList<Usuario> findTodosUsuarios() {
    ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    String querry = "SELECT * FROM USUARIOS";

    try {
      Connection conexion = openConnection();
      PreparedStatement pst = conexion.prepareStatement(querry);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        Usuario usuario = new Usuario(rs.getInt(0),
            rs.getString(1),
            rs.getString(2),
            rs.getString(3),
            rs.getDate(4),
            rs.getString(5),
            rs.getString(6),
            rs.getString(7),
            rs.getInt(8));

        listaUsuarios.add(usuario);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return listaUsuarios;
  }

  // Ver todos los Usuarios por Rol
  public ArrayList<Usuario> findTodosUsuariosPorRol(int rol) {
    ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    String querry = "SELECT * FROM USUARIOS WHERE ROL = ?";
    try {
      Connection conexion = openConnection();
      PreparedStatement pst = conexion.prepareStatement(querry);
      pst.setInt(1, rol);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        Usuario usuario = new Usuario(rs.getInt(0),
            rs.getString(1),
            rs.getString(2),
            rs.getString(3),
            rs.getDate(4),
            rs.getString(5),
            rs.getString(6),
            rs.getString(7),
            rol);

        listaUsuarios.add(usuario);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return listaUsuarios;
  }

}
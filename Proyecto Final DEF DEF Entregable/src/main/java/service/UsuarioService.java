package service;

import java.util.ArrayList;

import entity.Usuario;
import repository.UsuarioRepository;

public class UsuarioService {
  private UsuarioRepository repository;

  public UsuarioService() {
    this.repository = new UsuarioRepository();
  }

  public boolean crearUsuario(Usuario usuario) {
    return repository.crearUsuario(usuario);
  }

  public boolean actualizarUsuario(Usuario usuario) {
    return repository.actualizarUsuario(usuario);
  }

  public Usuario findUsuarioPorDNI(String dni) {
    return repository.findUsuarioPorDNI(dni);
  }
  
  public int findIdUsuarioPorDNI(String dni) {
	  return repository.findIdUsuarioPorDNI(dni);
  }

  public Usuario findUsuarioPorID(int id) {
    return repository.findUsuarioPorID(id);
  }

  public String findContrasenaPorDNI(String dni) {
    return repository.findContrasenaPorDNI(dni);
  }

  public boolean eliminarUsuario(String dni) {
    return repository.eliminarUsuario(dni);
  }

  public ArrayList<Usuario> findTodosUsuarios() {
    return repository.findTodosUsuarios();
  }

  public ArrayList<Usuario> findTodosUsuariosPorRol(int rol) {
    return repository.findTodosUsuariosPorRol(rol);
  }

}
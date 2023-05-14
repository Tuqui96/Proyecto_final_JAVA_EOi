package entity;

import java.sql.Date;

public class Usuario {
  private int idUser, rol;
  private String dni, nombre, apellidos, contrasena, telefono, email;
  private Date fechaNacimiento;

  public Usuario() {
    // TODO Auto-generated constructor stub
  }

  public Usuario(int idUser, String dni, String nombre, String apellidos, Date fechaNacimiento, String contrasena,
      String telefono,
      String email, int rol) {
    this.idUser = idUser;
    this.rol = rol;
    this.dni = dni;
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.contrasena = contrasena;
    this.telefono = telefono;
    this.email = email;
    this.fechaNacimiento = fechaNacimiento;
  }

  public int getIdUser() {
    return this.idUser;
  }

  public void setIdUser(int idUser) {
    this.idUser = idUser;
  }

  public int getRol() {
    return this.rol;
  }

  public void setRol(int rol) {
    this.rol = rol;
  }

  public String getDni() {
    return this.dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellidos() {
    return this.apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public String getContrasena() {
    return this.contrasena;
  }

  public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
  }

  public String getTelefono() {
    return this.telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getFechaNacimiento() {
    return this.fechaNacimiento;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  @Override
  public String toString() {
    return "{" +
        " idUser='" + getIdUser() + "'" +
        ", rol='" + getRol() + "'" +
        ", dni='" + getDni() + "'" +
        ", nombre='" + getNombre() + "'" +
        ", apellidos='" + getApellidos() + "'" +
        ", contrasena='" + getContrasena() + "'" +
        ", telefono='" + getTelefono() + "'" +
        ", email='" + getEmail() + "'" +
        ", fechaNacimiento='" + getFechaNacimiento() + "'" +
        "}";
  }

}
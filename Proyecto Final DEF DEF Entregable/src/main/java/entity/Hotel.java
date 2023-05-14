package entity;

public class Hotel {
	private int idHotel;
	private String nombreHotel;
	private String ubicacion;
	private int capacidad;
	private String descripcion;
	
	
	//Constructor 
	public Hotel(int idHotel, String nombreHotel, String ubicacion, int capacidad, String descripcion) {
		this.idHotel = idHotel;
		this.nombreHotel = nombreHotel;
		this.ubicacion = ubicacion;
		this.capacidad=capacidad;
		this.descripcion = descripcion;
	}
	
	//Getters y setters
	public Hotel() {
	}
	public int getIdHotel() {
		return idHotel;
	}
	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}
	public String getNombreHotel() {
		return nombreHotel;
	}
	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}
	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Hotel [idHotel=" + idHotel + ", nombreHotel=" + nombreHotel + ", ubicacion=" + ubicacion
				+ ", capacidad=" + capacidad + ", descripcion=" + descripcion + "]";
	}
	

}

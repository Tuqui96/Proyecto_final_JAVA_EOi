package entity;

import java.sql.Date;

public class Reserva {
	private int idReserva, idUsuario, idHotel, idHabitacion;
	private String estadoReserva;
	private Date fechaEntrada, fechaSalida;

	public Reserva() {
	}

	// Constructor para guardar las reservas en la tabla
	public Reserva(int idUsuario, int idHotel, int idHabitacion, Date fechaEntrada, Date fechaSalida,
			String estadoReserva) {
		this.idUsuario = idUsuario;
		this.idHotel = idHotel;
		this.idHabitacion = idHabitacion;
		this.estadoReserva = estadoReserva;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
	}
	
	public Reserva(int idReserva, int idUsuario, int idHotel, int idHabitacion, Date fechaEntrada, Date fechaSalida,
			String estadoReserva) {
		this.idReserva = idReserva;
		this.idUsuario = idUsuario;
		this.idHotel = idHotel;
		this.idHabitacion = idHabitacion;
		this.estadoReserva = estadoReserva;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	public int getIdHabitacion() {
		return idHabitacion;
	}

	public void setIdHabitacion(int idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	public String getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(String estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", idUsuario=" + idUsuario + ", idHotel=" + idHotel
				+ ", idHabitacion=" + idHabitacion + ", estadoReserva=" + estadoReserva + ", fechaEntrada="
				+ fechaEntrada + ", fechaSalida=" + fechaSalida + "]";
	}

}

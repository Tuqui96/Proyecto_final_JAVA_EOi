package service;

import java.sql.Date;
import java.util.ArrayList;

import entity.Reserva;
import repository.ReservaRepository;

public class ReservaService {
	private ReservaRepository repository;

	public ReservaService() {
		this.repository =new ReservaRepository();
	}

	public int obtenerIdHabitacionDisponible(Date fechaEntrada, Date fechaSalida) {
		return repository.obtenerIdHabitacionDisponible(fechaEntrada, fechaSalida);
	}
	
	public Reserva obtenerReservaPorIdReserva(int idReserva) {
		return repository.obtenerReservaPorIdReserva(idReserva);
	}

	public boolean crearReserva(int idUser, Date fechaEntrada, Date fechaSalida, int cantPersonas) {
		return repository.crearReserva(idUser, fechaEntrada, fechaSalida, cantPersonas);
	}

	public boolean actualizarReserva(Reserva reserva) {
		return repository.actualizarReserva(reserva);
	}

	public ArrayList<Reserva> findReservasUsuario(int id_user) {
		return repository.findReservasUsuario(id_user);
	}
	
	public ArrayList<Reserva> findReservasUsuarioPendientes (int id_user) {
		return repository.findReservasUsuarioPendientes(id_user);
	}

	public ArrayList<Reserva> findReservaPorEstado() {
		return repository.findReservaPorEstado();
	}

	public ArrayList<Reserva> findReservasHotel() {
		return repository.findReservasHotel();
	}

	public boolean aceptarReserva(int idReserva) {
		return repository.aceptarReserva(idReserva);
	}

	public boolean rechazarReserva(int idReserva) {
		return repository.rechazarReserva(idReserva);
	}

	public boolean eliminarReserva(int idReserva) {
		return repository.eliminarReserva(idReserva);
	}

	public boolean eliminarEstado(int idReserva) {
		return repository.eliminarEstado(idReserva);
	}
	
}
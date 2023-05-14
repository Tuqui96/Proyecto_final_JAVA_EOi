package service;

import entity.Hotel;
import repository.HotelRepository;

public class HotelService {

	private HotelRepository repository;

	public HotelService() {
		this.repository = new HotelRepository();
	}
	
	public Hotel findByID(int idHotel) {
		return repository.findByID(idHotel);
	}
	
	public Hotel[] findAll() {
		return repository.findAll();
	}
	
	public boolean create(Hotel hotel) {
		return repository.create(hotel);
	}
	
	public boolean delete(int idHotel) {
		return repository.delete(idHotel);
	}
	
	public boolean update(int idHotel, String nombre, String ubicacion, int capacidad, String descripcion) {
		return this.repository.update(idHotel,nombre,ubicacion,capacidad,descripcion);
	}
	
	
}

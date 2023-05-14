package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Hotel;

public class HotelRepository {

private java.sql.Connection openConnection() throws SQLException {
		
		String url  = "jdbc:oracle:thin:@localhost:1521:xe";;
		String user = "SYSTEM";
		String pass = "mercy";
		
		return DriverManager.getConnection(url, user, pass);
	}
	
	public Hotel  findByID(int idHotel) {
		Connection conexion = null;
		Hotel hotelEncontrado=null;
		
		try {
			conexion=openConnection();
			
			String sentSQL="SELECT * FROM HOTEL WHERE ID_HOTEL = ?";
			PreparedStatement pst=conexion.prepareStatement(sentSQL);
			pst.setInt(1, idHotel);

			ResultSet rs=pst.executeQuery();
			
			hotelEncontrado = new Hotel(idHotel,rs.getString("NOMBRE"),rs.getString("UBICACION"),rs.getInt("CAPACIDAD"), rs.getString("DESCRIPCION"));
			rs.close();
			pst.close();
			conexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return hotelEncontrado;
	}

	public Hotel[] findAll() {

		Hotel[] todosHoteles = new Hotel[50];
		int i=0;
		
		try {
			Connection conexion = openConnection();
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM HOTEL");
			
			while (rs.next()) 
			{
				todosHoteles[i] = new Hotel();
				todosHoteles[i].setIdHotel(rs.getInt("ID_HOTEL"));
				todosHoteles[i].setNombreHotel(rs.getString("Nombre"));
				todosHoteles[i].setUbicacion(rs.getString("Ubicacion"));
				todosHoteles[i].setCapacidad(rs.getInt("Capacidad"));
				todosHoteles[i].setDescripcion(rs.getString("Descripcion"));
				
				i++;	
			}
			
			rs.close();
			st.close();
			conexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return todosHoteles;
	}
	
	
	public boolean create (Hotel hotel) {
	
		boolean control=false;
		String insertQuery = "INSERT INTO hotel(ID_HOTEL, NOMBRE, UBICACION, CAPACIDAD ,DESCRIPCION) VALUES (?,?,?,?,?)";
		int rows = 0;
		
		try {
			Connection conexion = openConnection();
			
			PreparedStatement pst = conexion.prepareStatement(insertQuery);
			pst.setInt(1, hotel.getIdHotel());
			pst.setString(2, hotel.getNombreHotel());
			pst.setString(3, hotel.getUbicacion());
			pst.setInt(4, hotel.getCapacidad());
			pst.setString(5, hotel.getDescripcion());
			
			System.out.println("Ejecutando la query: " + insertQuery);
			rows = pst.executeUpdate();
			System.out.println("Registros afectados:" + rows);
			
			control=true;

			pst.close();
			conexion.close();
			
		} catch (SQLException e) {
			control=false;
			e.printStackTrace();
		}
		
		return control;
	}
	
	public boolean delete (int idHotel) {
		
		String deleteQuery = "DELETE FROM HOTEL WHERE ID_HOTEL=?";
		boolean control=false;
		int rows = 0;
		
		try {
			Connection conexion = openConnection();
			
			PreparedStatement pst = conexion.prepareStatement(deleteQuery);
			pst.setInt(1, idHotel);
			
			System.out.println("Ejecutando la query: " + deleteQuery);
			rows = pst.executeUpdate();
			System.out.println("Registros afectados:" + rows);
			
			control=true;
			
			pst.close();
			conexion.close();
		} catch (SQLException e) {
			control=false;
			e.printStackTrace();
		}
		
		return control;
	}
	
	public boolean update(int idHotel, String nombre, String ubicacion, int capacidad, String descripcion) {
		
		String updateQuery = "UPDATE hotel SET Nombre=?, Ubicacion=?, Capacidad=?, Descripcion=? WHERE ID_HOTEL=?";
		Connection conexion = null;
		PreparedStatement stmt = null;
		int rows = 0;//ayuda a controlar cambios
		
		try {
			conexion = openConnection();
			stmt = conexion.prepareStatement(updateQuery);
			stmt.setString(1, nombre);
			stmt.setString(2, ubicacion);
			stmt.setInt(3, capacidad);
			stmt.setString(4, descripcion);
			stmt.setInt(5, idHotel);
			
			
			System.out.println("Ejecutando la query: " + updateQuery);
			rows = stmt.executeUpdate();
			
			System.out.println("Registros afectados:" + rows);
			stmt.close();
			conexion.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}

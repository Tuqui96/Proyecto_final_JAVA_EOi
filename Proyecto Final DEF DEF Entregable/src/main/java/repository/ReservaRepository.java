package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Reserva;

public class ReservaRepository {

	private java.sql.Connection openConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "SYSTEM";
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

	// Obtener id_habitacion disponible
	public int obtenerIdHabitacionDisponible(Date fechaEntrada, Date fechaSalida) {
		Connection conexion = openConnection();
		String query ="SELECT h.id_habitacion FROM habitacion h LEFT JOIN reservas r ON h.id_habitacion = r.id_habitacion AND (r.estado_reserva = 'Aceptada' OR r.estado_reserva = 'Pendiente') AND ((r.fecha_entrada <= ? AND r.fecha_salida >= ?) OR (r.fecha_entrada <= ? AND r.fecha_salida >= ?) OR (r.fecha_entrada >= ? AND r.fecha_salida <= ?)) WHERE r.id_habitacion IS NULL ORDER BY h.id_habitacion ASC FETCH FIRST ROW ONLY";
		
		try {
			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setDate(1, fechaEntrada);
	        pst.setDate(2, fechaEntrada);
	        pst.setDate(3, fechaSalida);
	        pst.setDate(4, fechaSalida);
	        pst.setDate(5, fechaEntrada);
	        pst.setDate(6, fechaSalida);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
            	int idHab = rs.getInt("id_habitacion");
            	System.out.println(idHab);
				return idHab;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
		
	//Obtener objeto reserva por idReserva
	public Reserva obtenerReservaPorIdReserva(int idReserva) {
		String query = "SELECT * FROM RESERVAS WHERE ID_RESERVA = ?";
		Reserva reserva = null;
		try {
			Connection conexion = openConnection();
			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setInt(1, idReserva);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				reserva = new Reserva(rs.getInt("id_reserva"), rs.getInt("id_cliente"), rs.getInt("id_hotel"),
						rs.getInt("id_habitacion"), rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
						rs.getString("estado_reserva"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return reserva;
	}

	// Crear reservas
	public boolean crearReserva(int idUser, Date fechaEntrada, Date fechaSalida, int cantPersonas) {
		Connection conexion = openConnection();
		int idHabitacion = obtenerIdHabitacionDisponible(fechaEntrada, fechaSalida);
		if(idHabitacion==-1) {
			return false;	
		}else {
		String queryCrearReserva = "INSERT INTO RESERVAS VALUES (sqs_id_reserva.nextVal,?, 1,?,?,?,?,'Pendiente')";
		try {
			PreparedStatement pst = conexion.prepareStatement(queryCrearReserva);
			System.out.println(idUser);
			pst.setInt(1, idUser);
			pst.setInt(2, idHabitacion);
			pst.setDate(3, fechaEntrada);
			pst.setDate(4, fechaSalida);
			pst.setInt(5, cantPersonas);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return false;
		}
	}

	// Actualizar reserva
	public boolean actualizarReserva(Reserva reserva) {
		Connection conexion = openConnection();
		String query = "UPDATE RESERVAS SET ESTADO_RESERVA = 'Pendiente', FECHA_ENTRADA = ?, FECHA_SALIDA = ? WHERE ID_RESERVA = ?";
		try {
			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setDate(1, reserva.getFechaEntrada());
			pst.setDate(2, reserva.getFechaSalida());
			pst.setInt(3, reserva.getIdReserva());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	// Ver reservas por usuario
	public ArrayList<Reserva> findReservasUsuario(int id_user) {
		ArrayList<Reserva> listaReservas = new ArrayList<>();
		String query = "SELECT * FROM RESERVAS WHERE ID_CLIENTE = ?";
		try {
			Connection conexion = openConnection();
			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setInt(1, id_user);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Reserva reserva = new Reserva(rs.getInt("id_reserva"), rs.getInt("id_cliente"), rs.getInt("id_hotel"),
						rs.getInt("id_habitacion"), rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
						rs.getString("estado_reserva"));
				listaReservas.add(reserva);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaReservas;
	}
	
	//Ver reservas por usuario (pendientes)
	
	public ArrayList<Reserva> findReservasUsuarioPendientes (int id_user) {
		ArrayList<Reserva> listaReservas = new ArrayList<>();
		String query = "SELECT * FROM RESERVAS WHERE ID_CLIENTE = ? AND estado_reserva = 'Pendiente'";
		try {
			Connection conexion = openConnection();
			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setInt(1, id_user);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Reserva reserva = new Reserva(rs.getInt("id_reserva"), rs.getInt("id_cliente"), rs.getInt("id_hotel"),
						rs.getInt("id_habitacion"), rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
						rs.getString("estado_reserva"));
				listaReservas.add(reserva);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaReservas;
	}

	// Buscar reservas por estado pendiente
	public ArrayList<Reserva> findReservaPorEstado() {
		ArrayList<Reserva> listaReservas = new ArrayList<>();
		String query = "SELECT * FROM reservas WHERE estado_reserva = 'Pendiente'";
		try {
			Connection conexion = openConnection();
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Reserva reserva = new Reserva(rs.getInt("id_reserva"), rs.getInt("id_cliente"), rs.getInt("id_hotel"),
						rs.getInt("id_habitacion"), rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
						rs.getString("estado_reserva"));
				listaReservas.add(reserva);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaReservas;
	}
	
	// Ver todas
	public ArrayList<Reserva> findReservasHotel() {
		ArrayList<Reserva> listaReservas = new ArrayList<>();
		String query = "SELECT * FROM RESERVAS WHERE ID_HOTEL=1";
		try {
			Connection conexion = openConnection();
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Reserva reserva = new Reserva(rs.getInt("id_reserva"), rs.getInt("id_cliente"), rs.getInt("id_hotel"),
						rs.getInt("id_habitacion"), rs.getDate("fecha_entrada"), rs.getDate("fecha_salida"),
						rs.getString("estado_reserva"));
				listaReservas.add(reserva);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaReservas;
	}

	// Aceptar reserva
	public boolean aceptarReserva(int idReserva) {
        Connection conexion = openConnection();
        String query="UPDATE RESERVAS SET ESTADO_RESERVA='Aceptada' where id_reserva=?";
        try {
            PreparedStatement pst = conexion.prepareStatement(query);
            pst.setInt(1, idReserva);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

	// Rechazar reserva
	public boolean rechazarReserva(int idReserva) {

        Connection conexion = openConnection();
        String query="UPDATE RESERVAS SET ESTADO_RESERVA='Rechazada' where id_reserva=?";
        try {
            PreparedStatement pst = conexion.prepareStatement(query);
            pst.setInt(1, idReserva);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }

	// Eliminar reserva
	public boolean eliminarReserva(int idReserva) {
		Connection conexion = openConnection();
		String queryEliminarReserva = "DELETE FROM reservas WHERE id_reserva = ?";
		try {
			PreparedStatement pst = conexion.prepareStatement(queryEliminarReserva);
			pst.setInt(1, idReserva);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//Actualizar estado
		public boolean eliminarEstado(int idReserva) {	
		Connection conexion = openConnection();
		String  query = "UPDATE habitacion SET estado_habitacion = 'Disponible' WHERE id_habitacion = (SELECT id_habitacion FROM reservas WHERE id_reserva = ?)";
		try {
			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setInt(1, idReserva);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}


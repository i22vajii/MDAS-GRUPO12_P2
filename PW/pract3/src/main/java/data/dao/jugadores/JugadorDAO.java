package data.dao.jugadores;

import java.sql.*;			
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

import data.dto.jugadores.JugadorDTO;
import data.common.DBConnection;
import data.common.SQLProperties;

/**
 * Gestiona las operaciones CRUD relacionadas con los jugadores en la base de datos.
 */
public class JugadorDAO {

	private SQLProperties sqlProperties;

	public JugadorDAO() {
		sqlProperties = new SQLProperties();
	}
	
	/**
	 * Actualiza la fecha de inscripción asociada al correo de un usuario.
	 */
	public boolean updateFechaInscripcion(String correo, Date fecha) {
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();		
			String query = sqlProperties.getSQLQuery("sql.update.updateFechaInscripcion");

			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setDate(1, fecha);
			pstmt.setString(2, correo);
			pstmt.executeUpdate();
			
			if (pstmt != null) { 
				pstmt.close(); 
			}
			dbConnection.closeConnection();
			return true;
		} catch (Exception e) {
			return false;
		}
	}	
	
	/**
	 * Crea un nuevo registro de usuario en la base de datos con los datos proporcionados.
	 */
	public boolean createNewUserRegistro(String correo, String nombre_y_apellidos, Date fecha_nacimiento, String contraseña) {
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = sqlProperties.getSQLQuery("sql.insert.createNewUserRegistro");
			
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, correo);
			pstmt.setString(2, nombre_y_apellidos);
			pstmt.setDate(3, fecha_nacimiento);
			pstmt.setString(4, contraseña);
			pstmt.executeUpdate();
			
			if (pstmt != null) { 
				pstmt.close(); 
			}
			dbConnection.closeConnection();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	/**
	 * Valida las credenciales de un usuario y devuelve sus datos de sesión si es correcto.
	 */
	public JugadorDTO requestUserInicioSesion(String correo, String contraseña) {
		JugadorDTO user = null;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();

			String query = sqlProperties.getSQLQuery("sql.select.requestUserInicioSesion");			
			
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, correo);
			pstmt.setString(2, contraseña);
			ResultSet rs = (ResultSet) pstmt.executeQuery();

			while (rs.next()) {
				String nombre_y_apellidos = rs.getString("Nombre_y_apellidos");
				Date fecha_nacimiento = rs.getDate("Fecha_de_nacimiento");
				Date fecha_inscripcion = rs.getDate("Fecha_de_inscripcion");
				boolean admin = rs.getBoolean("Administrador");
				
				user = new JugadorDTO(nombre_y_apellidos, fecha_nacimiento, fecha_inscripcion, correo, contraseña, admin);
			}

			if (pstmt != null) { 
				pstmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * Actualiza la información personal de un usuario existente.
	 */
	public boolean updateDatos(String nombre, String contraseña, Date fecha, String correo) {
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();		
			String query = sqlProperties.getSQLQuery("sql.update.updateDatos");

			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, nombre);
			pstmt.setString(2, contraseña);
			pstmt.setDate(3, fecha);
			pstmt.setString(4, correo);
			pstmt.executeUpdate();
			
			if (pstmt != null) { 
				pstmt.close(); 
			}
			dbConnection.closeConnection();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	/**
	 * Recupera un listado completo de todos los clientes registrados.
	 */
	public ArrayList<JugadorDTO> requestAllClients() {
		ArrayList<JugadorDTO> listOfUsers = new ArrayList<JugadorDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = sqlProperties.getSQLQuery("sql.select.requestAllClients");
			
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet rs = (ResultSet) pstmt.executeQuery();

			while (rs.next()) {
				String correo = rs.getString("Correo");
				String nombre_y_apellidos = rs.getString("Nombre_y_apellidos");
				Date fecha_inscripcion = rs.getDate("Fecha_de_inscripcion");
				listOfUsers.add(new JugadorDTO(correo, nombre_y_apellidos, fecha_inscripcion));
			}

			if (pstmt != null) { 
				pstmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		return listOfUsers;
	}
}
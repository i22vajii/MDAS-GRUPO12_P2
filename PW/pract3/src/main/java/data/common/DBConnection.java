package data.common;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

/**
 * Gestiona la conexión a la base de datos MySQL utilizando los parámetros
 * definidos en el archivo de configuración externo config.properties.
 */
public class DBConnection {

    protected Connection connection = null;
    protected String url;
    protected String user;
    protected String password;

    /**
     * Inicializa la conexión cargando las propiedades de configuración.
     */
    public DBConnection() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Error, unable to find config.properties");
                return;
            } 
            
            properties.load(input); 
            url = properties.getProperty("db.url"); 
            user = properties.getProperty("db.username"); 
            password = properties.getProperty("db.password"); 
            
        } catch (IOException ex) {
            ex.printStackTrace(); 
        } 
    }
    
    /**
     * Establece y devuelve una conexión a la base de datos MySQL.
     * * @return La conexión a la base de datos o null si ocurre algún error.
     */
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Database connection successfully opened!");
        } catch (SQLException e) {
            System.err.println("Connection to MySQL has failed!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            e.printStackTrace();
        }
        return this.connection;
    }
    
    /**
     * Cierra la conexión a la base de datos si se encuentra activa.
     */
    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
                System.out.println("Database connection successfully closed!");
            }
        } catch (SQLException e) {
            System.err.println("Error while trying to close the connection.");
            e.printStackTrace();
        }
    }
}
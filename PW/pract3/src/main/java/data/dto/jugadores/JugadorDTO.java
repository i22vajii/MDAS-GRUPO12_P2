package data.dto.jugadores;

import java.util.Date;			
import java.util.Calendar;

/**
 * La clase {@code JugadorDTO} representa a un jugador con información personal como
 * nombre, fecha de nacimiento, fecha de inscripción y correo electrónico.
 */
public class JugadorDTO {
    
    private String nombreYApellidos;
    private Date fechaNacimiento;
    private Date fechaInscripcion;
    private String correo;
    private String contrasena;
    private boolean admin;

    /**
     * Crea un nuevo jugador sin inicializar sus atributos.
     */
    public JugadorDTO(){

    }

    /**
     * Crea un nuevo jugador con la información proporcionada.
     * 
     * @param nombreYApellidos El nombre y apellidos del jugador.
     
     * @param fechanacimiento La fecha de nacimiento del jugador.
     * @param correo El correo electrónico del jugador.
     */
    public JugadorDTO(String nombreYApellidos, Date fechanacimiento, String correo){


        this.nombreYApellidos = nombreYApellidos;


        this.fechaNacimiento = fechanacimiento;
        this.fechaInscripcion = new Date();
        this.correo = correo;
    }
    public JugadorDTO(String nombreYApellidos, Date fechanacimiento, Date fechainscripcion, String correo){


        this.nombreYApellidos = nombreYApellidos;


        this.fechaNacimiento = fechanacimiento;
        this.fechaInscripcion = fechainscripcion;
        this.correo = correo;
    }

    public JugadorDTO(String nombreYApellidos, Date fechanacimiento, Date fechainscripcion, String correo, String contraseña, boolean admin){


        this.nombreYApellidos = nombreYApellidos;


        this.fechaNacimiento = fechanacimiento;
        this.fechaInscripcion = fechainscripcion;
        this.correo = correo;
        this.admin = admin;
        this.contrasena = contraseña;
    }

    public JugadorDTO(String correo, String nombreYApellidos, Date fechainscripcion){


        this.nombreYApellidos = nombreYApellidos;


        this.fechaInscripcion = fechainscripcion;
        this.correo = correo;
    }

    /**
     * Devuelve el nombre y apellidos del jugador.
     *
     * @return El nombre y apellidos del jugador.
     */
    public String getNombreyapellidos(){

        return nombreYApellidos;

    }

    /**
     * Establece el nombre y apellidos del jugador.
     *
     * @param nombreYApellidos El nuevo nombre y apellidos del jugador.
     
     */
    public void setNombreyapellidos(String nombreYApellidos){


        this.nombreYApellidos = nombreYApellidos;


    }

    /**
     * Devuelve la fecha de nacimiento del jugador.
     *
     * @return La fecha de nacimiento del jugador.
     */
    public Date getFechaNacimiento(){

        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del jugador.
     *
     * @param fechanacimiento La nueva fecha de nacimiento del jugador.
     */
    public void setFechaNacimiento(Date fechanacimiento){

        this.fechaNacimiento = fechanacimiento;
    }

    /**
     * Devuelve la fecha de inscripción del jugador.
     *
     * @return La fecha de inscripción del jugador.
     */
    public Date getFechaInscripcion(){

        return fechaInscripcion;
    }
    
    /**
     * Establece la fecha de inscripción del jugador.
     *
     * @param fechainscripcion La nueva fecha de inscripción del jugador.
     */
    public void setFechaInscripcion(Date fechainscripcion){

        this.fechaInscripcion = fechainscripcion;
    }

    /**
     * Devuelve el correo electrónico del jugador.
     *
     * @return El correo electrónico del jugador.
     */
    public String getCorreo(){

        return correo;
    }
    
    /**
     * Establece el correo electrónico del jugador.
     *
     * @param correo El nuevo correo electrónico del jugador.
     */
    public void setCorreo(String correo){

        this.correo = correo;
    }

    /**
     * Devuelve una representación en cadena del jugador.
     *
     * @return Una cadena que representa la información del jugador.
     */
    public String toString(){

        return "Jugador: nombre y apellidos = "+nombreYApellidos+", fecha de nacimiento = "+fechaNacimiento+

        ", fecha de inscripcion = "+fechaInscripcion+", correo = "+correo+".";
    }

    /**
     * Calcula la antigüedad del jugador en años desde su fecha de inscripción.
     *
     * @return La antigüedad del jugador en años.
     */
    public int calcularAntiguedad(){

        Date fechaactual = new Date();

        Calendar calendarinscripcion = Calendar.getInstance();
        calendarinscripcion.setTime(fechaInscripcion);

        Calendar calendarActual = Calendar.getInstance();
        calendarActual.setTime(fechaactual);

        int antiguedad = calendarActual.get(Calendar.YEAR) - calendarinscripcion.get(Calendar.YEAR);

        if (calendarActual.get(Calendar.MONTH) < calendarinscripcion.get(Calendar.MONTH) ||
        (calendarActual.get(Calendar.MONTH) == calendarinscripcion.get(Calendar.MONTH) && 
        calendarActual.get(Calendar.DAY_OF_MONTH) < calendarinscripcion.get(Calendar.DAY_OF_MONTH))){

            antiguedad--;
        }

        return antiguedad;
    }

    public void setContrasena(String contraseña){
        this.contrasena=contraseña;
    }

    public void setAdmin(boolean admin){
        this.admin=admin;
    }

    public String getContrasena(){
        return contrasena;
    }

    public boolean getAdmin(){
        return admin;
    }

}

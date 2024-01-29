package clases;

import java.io.Serializable;

/**
 * Objeto consulta
 * @author Grupo 5
 */
public class Consulta implements Serializable{
    //Declaración de variables
    private String id;
    private String fecha;
    private String consultorio;
    private String diagnostico;
    private String receta;
    private String historialPaciente;
    private String carnetMedico;

    /**
     * Constructor vacío consulta
     */
    public Consulta() {
    }

    /**
     * Recupera el valor de id
     * @return retorna el valor de la variable
     */
    public String getId() {    
        return id;
    }

    /**
     * Pone un valor a  id
     * @param id variable que contiene el dato a colocar
     */
    public void setId(String id) {   
        this.id = id;
    }

    /**
     *  Recupera el valor de fecha
     * @return retorna el valor de la variable
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Pone un valor a  fecha
     * @param fecha variable que contiene el dato a colocar
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     *  Recupera el valor de consultorio
     * @return retorna el valor de la variable
     */
    public String getConsultorio() {
        return consultorio;
    }

    /**
     * Pone un valor a  consultorio
     * @param consultorio variable que contiene el dato a colocar
     */
    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    /**
     * Recupera el valor de diagnostico
     * @return retorna el valor de la variable
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * Pone un valor a  diagnostico
     * @param diagnostico variable que contiene el dato a colocar
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * Recupera el valor de receta
     * @return retorna el valor de la variable
     */
    public String getReceta() {
        return receta;
    }

    /**
     * Pone un valor a  receta
     * @param receta variable que contiene el dato a colocar
     */
    public void setReceta(String receta) {
        this.receta = receta;
    }

    /**
     * Recupera el valor de historialPaciente
     * @return retorna el valor de la variable
     */
    public String getHistorialPaciente() {
        return historialPaciente;
    }

    /**
     * Pone un valor a  historialPaciente
     * @param historialPaciente variable que contiene el dato a colocar
     */
    public void setHistorialPaciente(String historialPaciente) {
        this.historialPaciente = historialPaciente;
    }

    /**
     * Recupera el valor de carnetMedico
     * @return retorna el valor de la variable
     */
    public String getCarnetMedico() {
        return carnetMedico;
    }

    /**
     * Pone un valor a  carnetMedico
     * @param carnetMedico variable que contiene el dato a colocar
     */
    public void setCarnetMedico(String carnetMedico) {
        this.carnetMedico = carnetMedico;
    }

    
    
    /**
     * Devuelve una cadena con los datos del objeto 
     */
    @Override
    public String toString() {
        return "Consulta{" + "id=" + id + ", fecha=" + fecha + ", consultorio=" + consultorio + ", diagnostico=" + diagnostico + ", receta=" + receta + '}';
    }
    
    
}
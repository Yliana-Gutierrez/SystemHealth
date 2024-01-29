package clases;

import java.io.Serializable;

/**
 * Objeto de tipo paciente
 * @author Grupo 5
 */
public class Paciente extends Persona implements Serializable{
    //Declaración de variables
    private String idHistorial;
    
    //constructor

    /**
     * Inicializa la clase con el constructor vacío
     */
    public Paciente() {
        
    }

    //Getters and setters

    /**
     * Recupera el valor de idHistorial
     * @return retorna el valor de la variable 
     */
    public String getIdHistorial() {
        return idHistorial;
    }

    /**
     * Pone un valor a  idHistorial
     * @param idHistorial variable que contiene el dato a colocar
     */
    public void setIdHistorial(String idHistorial) {
        this.idHistorial = idHistorial;
    }

    /**
     * Recupera el valor de nombre
     * @return retorna el valor de la variable
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Pone un valor a  nombre
     * @param nombre variable que contiene el dato a colocar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Recupera el valor de apellidos
     * @return retorna el valor de la variable
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Pone un valor a  apellidos
     * @param apellidos variable que contiene el dato a colocar
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Recupera el valor de cedula
     * @return retorna el valor de la variable
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Pone un valor a  cedula
     * @param cedula variable que contiene el dato a colocar
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Recupera el valor de telefono
     * @return retorna el valor de la variable
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Pone un valor a  telefono
     * @param telefono variable que contiene el dato a colocar
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Recupera el valor de direccion
     * @return retorna el valor de la variable
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Pone un valor a  direccion
     * @param direccion variable que contiene el dato a colocar
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * Devuelve una cadena con los datos del objeto
     */
    @Override
    public String toString() {
        return "Paciente{" + "idHistorial=" + idHistorial + ", nombre=" + nombre + ", apellidos=" + apellidos + ", cedula=" + cedula + ", telefono=" + telefono + ", direccion=" + direccion + '}';
    }
    
    
}

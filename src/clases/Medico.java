package clases;

import java.io.Serializable;

/**
 * Objeto de tipo médico
 * @author Grupo 5
 */
public class Medico extends Persona implements Serializable{
    //Declaración de variables
    
    private String carnet;
    private String especialidad;

    //Constructor

    /**
     * Inicializa la clase Medico con el constructor vacío
     */
    public Medico() {
    }

    //Getters and setters

    /**
     * Recupera el valor de carnet
     * @return retorna el valor de la variable
     */
    public String getCarnet() {
        return carnet;
    }

    /**
     * Pone un valor a  carnet
     * @param carnet variable que contiene el dato a colocar
     */
    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    /**
     * Recupera el valor de especialidad
     * @return retorna el valor de la variable
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     *  Pone un valor a  especialidad
     * @param especialidad variable que contiene el dato a colocar
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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
        return "Medico{" + "carnet=" + carnet + ", especialidad=" + especialidad + ", nombre=" + nombre + ", apellidos=" + apellidos+ ", cedula=" + cedula  + ", telefono=" + telefono + ", direccion=" + direccion +'}';
    }
    
}

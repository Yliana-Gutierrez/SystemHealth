package clases;

import java.io.Serializable;

/**
 * Clase para generar los objetos tipos hospital
 * @author Grupo 5
 */
public class Hospital implements Serializable{
    //Declaración de variables
    private String nombre;
    private String ruc;
    private String telefono;

    //Constructor

    /**
     * Inicializa la clase con el constructor vacío
     */
    public Hospital() {
    }

    //Getters and setters

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
     * Recupera el valor de ruc
     * @return retorna el valor de la variable
     */
    public String getRuc() {
        return ruc;
    }

    /**
     * Pone un valor a  ruc
     * @param ruc variable que contiene el dato a colocar
     */
    public void setRuc(String ruc) {
        this.ruc = ruc;
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
     * Devuelve una cadena con los datos del objeto 
     */
    @Override
    public String toString() {
        return "Hospital{" + "nombre=" + nombre + ", ruc=" + ruc + ", telefono=" + telefono + '}';
    }
    
    
}

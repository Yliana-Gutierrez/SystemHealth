package clases;

import java.io.Serializable;

/**
 * Objeto de dirección
 * @author Grupo 5
 */
public class Direccion implements Serializable{
    //Declaración de variables
    private String calle;
    private String ciudad;
    private String numero;

    

    /**
     *Constructor vacío dirección
     */
    public Direccion() {
    }


    /**
     * Recupera el valor de
     * @return retorna el valor de la variable
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Pone un valor a  calle
     * @param calle variable que contiene el dato a colocar
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Recupera el valor de ciudad
     * @return retorna el valor de la variable
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Pone un valor a  ciudad
     * @param ciudad variable que contiene el dato a colocar
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Recupera el valor de numero
     * @return retorna el valor de la variable
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Pone un valor a  numero
     * @param numero variable que contiene el dato a colocar
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    
    /**
     * Devuelve una cadena con los datos del objeto 
     * @return retorna el valor de la variable 
     */
    @Override
    public String toString() {
        return "Direccion{" + "calle=" + calle + ", ciudad=" + ciudad + ", numero=" + numero + '}';
    }
    
}

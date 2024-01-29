package principal;

import gui.VentanaPrincipal;

/*
 * Sistema de gestión de consultas para Hospitales 
 * Creado el 12 de marzo de 2022
 * @author Jhon Merchán
 * @version POO-2022
 */

/**
 * @author Grupo 5
 */
public class Principal {

    /**
    * Método principal
     * @param args contiene la información suministrada argumentos de la línea de comandos como un Array de String objetos
    */
    
    public static void main(String[] args) {
        //Muestra la pantalla principal
         VentanaPrincipal framePrincipal = new VentanaPrincipal();
         framePrincipal.setVisible(true);
    }    
}

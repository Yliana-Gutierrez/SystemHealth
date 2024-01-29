package listeners;

import gui.VentanaMensaje;
import gui.VentanaPacienteSecundaria;
import gui.VentanaBienvenida;
import java.awt.Frame;
import java.util.Random;

/**
 * Clase con las funciones que tienen la lógica de los eventos
 * @author Grupo 5
 */
public class FuncionesAuxiliares {
    
    
    /**
     * Abre una ventana de tipo JDialog con mensaje de información del sistema
     *
     * @param titulo tipo string que contiene el título
     * @param mensaje tipo string que contiene el mensaje
     * @param ventana tipo frame con la ventana padre
     */
    public static void mensajeBienvenida(String titulo, String mensaje,Frame ventana)
    {      
        // Prepara una forma para Desplegar el Mensaje
        VentanaBienvenida ventanaBienvenida = new VentanaBienvenida(titulo,mensaje,true,ventana);        
        ventanaBienvenida.setVisible(true);
    }
    
    /**
     * Abre una ventana de tipo JDialog con mensaje de información del sistema
     *
     * @param titulo tipo string que contiene el título
     * @param mensaje tipo string que contiene el mensaje
     * @param ventana tipo frame con la ventana padre
     */
    public static void mensajeVentana(String titulo, String mensaje,Frame ventana)
    {      
        // Prepara una forma para Desplegar el Mensaje
        VentanaMensaje ventanaMensaje = new VentanaMensaje(titulo,mensaje,true,ventana);        
        ventanaMensaje.setVisible(true);
    }   

    /**
     * Abre la venta del paciente para ingresar uno nuevo
     * @param ventana tipo frame con la ventana padre
     */
    public static void pacienteVentana(Frame ventana)
    {      
        // Prepara una forma para Desplegar el la ventana del paciente
        VentanaPacienteSecundaria paciente = new VentanaPacienteSecundaria(ventana,true);        
        paciente.setVisible(true);
    }   
    
    /**
     * Obtiene una cadena y extrae los dos primeros caracteres
     * @param cadena tipo string que contiene los datos de la cadena
     * @param ventana tipo frame con la ventana padre
     * @return String con primeros caracteres
     */
    public static String obtenerDosPrimeros(String cadena,Frame ventana){
        String auxiliar="";
         try
        {         
            for (int i=0;i<2;i++){
                auxiliar+=cadena.charAt(i);
            }
        }
        // Captura el Error
        catch(Exception error) 
        {
            // 0 Si hay Error
            mensajeVentana("ERROR","Ingrese un nombre y/o apellido\n\nDescripción:"+error.getMessage(),ventana);
        }
        return auxiliar;
    }
        
    /**
     * Toma la cadena de la cédula y extra los dos primeros y los dos últimos caracteres
     * @param cedula tipo string que contiene los datos de la cedula
     * @param ventana tipo frame con la ventana padre
     * @return string con la nueva cadena de los caracteres extraídos
     */
    public static String obtenerCaracteres(String cedula,Frame ventana){
        String auxiliar="";
        // Captura el Error
        try
        {         
            for (int i=0;i<10;i++){
                if ( i == 0 || i == 1 || i == 8 || i == 9) {
                    auxiliar+=cedula.charAt(i);
                }
            }
        }
        // Captura el Error
        catch(Exception error) 
        {
            // 0 Si hay Error
            mensajeVentana("ERROR","La cédula es inválida\n\nDescripción:"+error.getMessage(),ventana);
        }
        return auxiliar;
    }
    
    /**
     * Obtiene una cadena y extrae los dos últimos caracteres
     * @param cadena tipo string que contiene los datos de la cadena
     * @param ventana tipo frame con la ventana padre
     * @return retorna el string con los caracteres
     */
    public static String obtenerDosUltimos(String cadena,Frame ventana){
        String auxiliar="";
        try
        {         
            for (int i=0;i<cadena.length();i++){
                if(i>=cadena.length()-2){
                    auxiliar+=cadena.charAt(i);
                }   
            }
        }
        // Captura el Error
        catch(Exception error) 
        {
            // 0 Si hay Error
            mensajeVentana("ERROR","Ingrese un nombre y/o apellido\n\nDescripción:"+error.getMessage(),ventana);
        }
        return auxiliar;
    }
     
    /**     
     * @param cadena tipo string que contiene los datos de la palabra
     * @param ventana tipo frame con la ventana padre
     * @return retorna el string con los caracteres
     */
    public static String extraerNombre(String cadena, Frame ventana){
        String auxiliar="";
        Boolean verificar=false;
        try
        {         
            for (int i=0;i<cadena.length();i++){                
                if(verificar){
                    auxiliar+=cadena.charAt(i);
                }
                if(cadena.charAt(i)==' '){
                    verificar=true;
                }   
            }
        }
        // Captura el Error
        catch(Exception error) 
        {
            // 0 Si hay Error
            mensajeVentana("ERROR","Ingrese un nombre y/o apellido\n\nDescripción:"+error.getMessage(),ventana);
        }
        
        if(verificar ){
            return auxiliar;
        }else{
            return cadena;
        }
        
    }
    /**
     * Genera un número random desde el número 100
     * @return String con número aleatorio generado
     */
    public static String obtenerRandomCien(){
        String numeroRandom;
	Random random = new Random();
        int value = 100+ random.nextInt(899);
        numeroRandom=String.valueOf(value);
        return numeroRandom;
    }
    
    /**
     * Genera un número random desde el número 10
     * @return String con número aleatorio generado
     */
    public static String obtenerRandomDiez(){
        String numeroRandom;
	Random random = new Random();
        int value = 10 + random.nextInt(89);
        numeroRandom=String.valueOf(value);
        return numeroRandom;
    }
    
    /**
     * Genera el código para el carnet del médico
     * @param cedula tipo string que contiene los datos de la cédula
     * @param nombre tipo string que contiene los datos del nombre
     * @param apellido tipo string que contiene los datos del apellido
     * @param ventana tipo frame con la ventana padre
     * @return retorna el id del carnet
     */
    public static String generarCarnet( String cedula, String nombre, String apellido, Frame ventana){
        String codigoCarnet="";
        nombre=extraerNombre(nombre,ventana);
        codigoCarnet+=obtenerCaracteres(cedula,ventana);
        codigoCarnet+=obtenerDosUltimos(nombre,ventana);
        codigoCarnet+=obtenerDosPrimeros(apellido, ventana);
        codigoCarnet+=obtenerRandomDiez();
        
        return codigoCarnet;
    }

    /**
     * Genera el código para el historial del paciente
     * @param cedula tipo string que contiene los datos de la cédula
     * @param nombre tipo string que contiene los datos del nombre
     * @param apellido tipo string que contiene los datos del apellido
     * @param ventana tipo frame con la ventana padre
     * @return retorna el id del historial
     */
    public static String generaCodigoHistorial(String cedula, String nombre, String apellido,Frame ventana){
        String CodigoHistorial="";
        apellido=extraerNombre(apellido,ventana);
        CodigoHistorial+=obtenerCaracteres(cedula, ventana);
        CodigoHistorial+=obtenerDosPrimeros(nombre, ventana);
        CodigoHistorial+=obtenerDosUltimos(apellido, ventana);
        CodigoHistorial+=obtenerRandomCien();
        
        return CodigoHistorial;
    }    
}

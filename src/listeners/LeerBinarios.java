package listeners;

import clases.Consulta;
import clases.Hospital;
import clases.Medico;
import clases.Paciente;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import static listeners.FuncionesAuxiliares.mensajeVentana;

/**
 * Clase con funciones que permiten recuperar los datos de los archivos binarios
 * @author Grupo 5
 */
public class LeerBinarios {
    /**
     * Obtiene los datos de los archivos binarios .med del hospital 
     * @param ventana lleva la venta padre
     * @return retorna un ArrayList con los datos
     */
    public static Hospital obtenerHospital(Frame ventana){
        File archivo =new File("hospital.med");
        Hospital hospital=new Hospital();
        try{
            FileInputStream file = new FileInputStream(archivo);
            ObjectInputStream stream;
            while(file.available()>0){
                stream=new ObjectInputStream(file);
                hospital =(Hospital) stream.readObject();
            }
        }catch(Exception error){
             mensajeVentana("ERROR","No se pudo leer los datos\n\nDescripción:"+error.getMessage(),ventana);
        }
        return hospital;
    }
    
    /**
     * Obtiene los datos de los archivos binarios .med
     * @param ventana lleva la venta padre
     * @return retorna un ArrayList con los datos
     */
    public static ArrayList<Paciente> obtenerPacientes(Frame ventana){
        ArrayList <Paciente> pacientes= new ArrayList<Paciente>();
        File archivo =new File("paciente.med");
        
        try{
            FileInputStream file = new FileInputStream(archivo);
            ObjectInputStream stream;
            while(file.available()>0){
                stream=new ObjectInputStream(file);
                pacientes= (ArrayList<Paciente>) stream.readObject();
            }
        }catch(Exception error){
            mensajeVentana("ERROR","No se pudo leer los datos\n\nDescripción:"+error.getMessage(),ventana);
        }
        return pacientes;
    }
     
    /**
     * Obtiene los datos de los archivos binarios .med
     * @param ventana lleva la venta padre
     * @return retorna un ArrayList con los datos
     */
    public static ArrayList<Medico> obtenerMedicos(Frame ventana){
        ArrayList <Medico> medicos= new ArrayList<Medico>();
        File archivo =new File("medico.med");
        
        try{
            FileInputStream file = new FileInputStream(archivo);
            ObjectInputStream stream;
            while(file.available()>0){
                stream=new ObjectInputStream(file);
                medicos= (ArrayList<Medico>) stream.readObject();
            }
        }catch(Exception error){
             mensajeVentana("ERROR","No se pudo leer los datos\n\nDescripción:"+error.getMessage(),ventana);
        }
        return medicos;
    }
    
    /**
     * Obtiene los datos de los archivos binarios .med
     * @param ventana frame con la ventana padre
     * @return retorna un ArrayList con los datos
     */
    public static ArrayList<Consulta> obtenerConsultas(Frame ventana){
        ArrayList <Consulta> consultas= new ArrayList<Consulta>();
        File archivo =new File("consulta.med");
        
        try{
            FileInputStream file = new FileInputStream(archivo);
            ObjectInputStream stream;
            while(file.available()>0){
                stream=new ObjectInputStream(file);
                consultas= (ArrayList<Consulta>) stream.readObject();
            }
        }catch(Exception error){
             mensajeVentana("ERROR","No se pudo leer los datos\n\nDescripción:"+error.getMessage(),ventana);
        }
        return consultas;
    }
}

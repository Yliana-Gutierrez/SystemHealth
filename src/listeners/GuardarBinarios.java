package listeners;

import clases.Consulta;
import clases.Hospital;
import clases.Medico;
import clases.Paciente;
import java.awt.Frame;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import static listeners.FuncionesAuxiliares.mensajeVentana;

/**
 * Guarda la información de los objetos
 * @author Grupo 5
 */
public class GuardarBinarios {
    
    /**
     * Guarda los datos en un archivo binario
     * @param hospital tipo ArrayList con la información del objeto
     * @param ventana tipo frame con la ventana padre
     */
    public static void guardarHospital(Hospital hospital,Frame ventana){
        File archivo =new File("hospital.med");
        try{
            FileOutputStream file =new FileOutputStream(archivo);
            ObjectOutputStream stream = new ObjectOutputStream(file);
            stream.writeObject(hospital);
            stream.close();
            file.close();
            
        }catch (IOException error){
            mensajeVentana("ERROR","No se pudo guardar los datos\n\nDescripción:"+error.getMessage(),ventana);
        }
    }
    
    /**
     * Guarda los datos en un archivo binario
     * @param pacientes tipo ArrayList con la información del objeto
     * @param ventana tipo frame con la ventana padre
     */
    public static void guardarPaciente(ArrayList<Paciente> pacientes,Frame ventana){
        File archivo =new File("paciente.med");
        try{
            FileOutputStream file =new FileOutputStream(archivo);
            ObjectOutputStream stream = new ObjectOutputStream(file);
            stream.writeObject(pacientes);
            stream.close();
            file.close();
            
        }catch (Exception error){
            mensajeVentana("ERROR","No se pudo guardar los datos\n\nDescripción:"+error.getMessage(),ventana);
        }
    }

    /**
     * Guarda los datos en un archivo binario
     * @param medicos tipo ArrayList con la información del objeto
     * @param ventana tipo frame con la ventana padre
     */
    public static void guardarMedico(ArrayList<Medico> medicos,Frame ventana){
        File archivo =new File("medico.med");
        try{
            FileOutputStream file =new FileOutputStream(archivo);
            ObjectOutputStream stream = new ObjectOutputStream(file);
            stream.writeObject(medicos);
            stream.close();
            file.close();
            
        }catch (Exception error){
            mensajeVentana("ERROR","No se pudo guardar los datos\n\nDescripción:"+error.getMessage(),ventana);
        }
    }
    
    /**
     * Guarda los datos en un archivo binario
     * @param consultas tipo ArrayList con la información del objeto
     * @param ventana tipo frame con la ventana padre
     */
    public static void guardarConsulta(ArrayList<Consulta> consultas,Frame ventana){
        File archivo =new File("consulta.med");
        try{
            FileOutputStream file =new FileOutputStream(archivo);
            ObjectOutputStream stream = new ObjectOutputStream(file);
            stream.writeObject(consultas);
            stream.close();
            file.close();
            
        }catch (Exception error){
            mensajeVentana("ERROR","No se pudo guardar los datos\n\nDescripción:"+error.getMessage(),ventana);
        }
    }
}

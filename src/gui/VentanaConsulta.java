package gui;

import clases.Consulta;
import clases.Medico;
import clases.Paciente;
import java.awt.Color;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import listeners.GuardarBinarios;
import static listeners.FuncionesAuxiliares.mensajeVentana;
import static listeners.FuncionesAuxiliares.pacienteVentana;
import listeners.LeerBinarios;

/**
 *
 * @author Grupo 5
 */
public class VentanaConsulta extends javax.swing.JInternalFrame {
    //variable de tipo frame para guardar la ventana padre
    private java.awt.Frame ventana;
    private ArrayList <Consulta> consultas= new ArrayList<>();
    private ArrayList <Medico> medicos= new ArrayList<>();
    private ArrayList <Paciente> pacientes= new ArrayList<>();
    private String seleccion="";
    private Boolean encontroDato=false;
    private Integer numeroPosicionDatos;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    /**
     * Crea una nueva forma de IFrameConsulta
     * @param parent variable con la ventana padre
     */
    public VentanaConsulta(java.awt.Frame parent) {
        ventana=parent;
        initComponents();
        // Quita la Barra de título
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
       
       
        this.setVisible(true);
    }
    
    
    /**
     * Verifica que no haya campos vacíos y devuelve un mensaje
     * @return un valor falso si encuentra un campo vacío
     */
    public boolean verificarDatos(){
        String mensaje="";
        boolean verificar=true;
        if(txtConsultorio.getText().equals("")){
            mensaje+="Ingrese el nombre del consultorio\n";
            verificar=false;
        }
        if(txtIdHistorial.getText().equals("")){
            mensaje+="Ingrese el ID Historial del paciente\n";
            verificar=false;
        }
        if(txtCarnet.getText().equals("")){
            mensaje+="Ingrese el carnet del médico\n";
            verificar=false;
        }
        if(txaDiagnostico.getText().equals("")){
            mensaje+="Ingrese un diagnóstico\n";
            verificar=false;
        }
        if(txaReceta.getText().equals("")){
            mensaje+="Ingrese una receta\n";
            verificar=false;
        }
               
        if(!verificar){
            mensajeVentana("ATENCIÓN",mensaje,ventana);
        }        
        return verificar;
    }

    /**
     * Obtiene los datos de los archivos y los guarda en los arraylist
     */
    public void leerDatos(){
        File archivo =new File("consulta.med");
        if(archivo.canRead()){
            consultas= LeerBinarios.obtenerConsultas(ventana);
        }
        File archivoMedico =new File("medico.med");
        if(archivoMedico.canRead()){
            medicos= LeerBinarios.obtenerMedicos(ventana);
        }
        File archivoPaciente =new File("paciente.med");
        if(archivoPaciente.canRead()){
            pacientes= LeerBinarios.obtenerPacientes(ventana);
        }
        
    }
    
    /**
     * Genera el Id de la clase consulta
     */
    public void generarIdConsulta(){
        Integer idConsulta;
        leerDatos();
        if(consultas.size()!=0){
            idConsulta =(Integer.valueOf(consultas.get(consultas.size()-1).getId())+1);
        }else{
            idConsulta = 1;
        }
        txtConsulta.setText(String.valueOf(idConsulta));
    }
    
    /**
     *  guarda los datos de la consulta en el archivo .dat
     */
    public void guardarConsulta(){
        
        if(verificarDatos())
        {
            leerDatos();
            Consulta consulta=new Consulta();
            consulta.setId(txtConsulta.getText());
            consulta.setFecha(txtFecha.getText());
            consulta.setConsultorio(txtConsultorio.getText());
            consulta.setDiagnostico(txaDiagnostico.getText());
            consulta.setReceta(txaReceta.getText());
            consulta.setHistorialPaciente(txtIdHistorial.getText());
            consulta.setCarnetMedico(txtCarnet.getText());
            consultas.add(consulta);
            GuardarBinarios.guardarConsulta(consultas,ventana);
            mensajeVentana("INFORMACIÓN","Se añadio la consulta",ventana);
            bloquearCampos();
        }
    }
    
    /**
     *  guarda los datos de la consulta según la edición realizada
     */
    public void actualizarConsulta(){
        
        if(verificarDatos())
        {
            consultas.get(numeroPosicionDatos).setId(txtConsulta.getText());
            consultas.get(numeroPosicionDatos).setFecha(txtFecha.getText());
            consultas.get(numeroPosicionDatos).setConsultorio(txtConsultorio.getText());
            consultas.get(numeroPosicionDatos).setDiagnostico(txaDiagnostico.getText());
            consultas.get(numeroPosicionDatos).setReceta(txaReceta.getText());
            consultas.get(numeroPosicionDatos).setHistorialPaciente(txtIdHistorial.getText());
            consultas.get(numeroPosicionDatos).setCarnetMedico(txtCarnet.getText());
            
            GuardarBinarios.guardarConsulta(consultas,ventana);
            mensajeVentana("INFORMACIÓN","Se actualizó la consulta",ventana);
            bloquearCampos();
        }        
    }
    
    /**
     *  elimina la consulta 
     */
    public void removerConsulta(){
        
        if(verificarDatos())
        {
            consultas.remove(consultas.get(numeroPosicionDatos));
            GuardarBinarios.guardarConsulta(consultas,ventana);
            mensajeVentana("INFORMACIÓN","Se removió la consulta",ventana);
            bloquearCampos();
        }        
    }
    
    /**
     *  habilita los componenes de la busqueda
     */
    public void habilitarBusqueda(){
        txtBuscar.setEnabled(true);
        botBuscar.setEnabled(true);
        lblBuscar.setForeground(Color.BLACK);
        lblBuscar.setEnabled(true);
    }
    
    /**
     * deshabilida los componentes de la busqueda
     */
    public void bloquearBusqueda(){
        txtBuscar.setEnabled(false);
        botBuscar.setEnabled(false);
        txtBuscar.setText("");
        
         lblBuscar.setForeground(Color.GRAY);
        lblBuscar.setEnabled(false);
    }
    
    /**
     * Busca la información de la consulta
     */
    public void encontrarInformacion(){
        encontroDato=false;
        for(int i=0;i<consultas.size();i++){
            if(consultas.get(i).getId().equals(txtBuscar.getText())){
                encontroDato=true;
                numeroPosicionDatos=i;
                txtConsulta.setText(consultas.get(i).getId());
                txtFecha.setText(consultas.get(i).getFecha());
                txtConsultorio.setText(consultas.get(i).getConsultorio());
                txtIdHistorial.setText(consultas.get(i).getHistorialPaciente());
                txtCarnet.setText(consultas.get(i).getCarnetMedico());
                txaDiagnostico.setText(consultas.get(i).getDiagnostico());
                txaReceta.setText(consultas.get(i).getReceta());
            }
        }
    }
    
    /**
     * Busca la información del médico 
     * @return retorna un valor verdadero si encuentra al médico
     */
    public boolean encontrarInformacionMedico(){
        boolean verificarMedico=false;
         for(int i=0;i<medicos.size();i++){
            if(medicos.get(i).getCarnet().equals(txtCarnet.getText())){
                verificarMedico=true;
            }
        }
        if(verificarMedico){
             mensajeVentana("INFORMACIÓN","Se encontro al médico con carnet "+txtCarnet.getText(),ventana);
        }else{
            txtCarnet.setText("");
            mensajeVentana("INFORMACIÓN","El carnet no existe",ventana);
        }
         return verificarMedico;
    }
    
    /**
     * busca si existe el paciente
     * @return retorna un valor verdadero si encuentra al paciente
     */
    public boolean encontrarInformacionPaciente(){
        boolean verificarPaciente=false;
         for(int i=0;i<pacientes.size();i++){
            if(pacientes.get(i).getIdHistorial().equals(txtIdHistorial.getText())){
                verificarPaciente=true;
            }
        }
        if(verificarPaciente){
             mensajeVentana("INFORMACIÓN","El paciente fue encontrado",ventana);
        }else{
            txtIdHistorial.setText("");
            btnAniadirPaciente.setEnabled(true);
            mensajeVentana("INFORMACIÓN","El paciente no existe",ventana);
        }
         return verificarPaciente;
    }
    
    /**
     * habilita los componetes de los campos
     */
    public void habilitarCampos(){
        txtConsultorio.setEnabled(true);
        txtCarnet.setEnabled(true);
        txtIdHistorial.setEnabled(true);
        txaDiagnostico.setEnabled(true);
        txaReceta.setEnabled(true);
        botAceptar.setEnabled(true);
        botCancelar.setEnabled(true);
        botBuscarMedico.setEnabled(true);
        botBuscarPaciente.setEnabled(true);
        btnAniadirPaciente.setEnabled(true);
    } 
    
    /**
     * deshabilita los componentes de las opciones
     */
    public void bloquearOpciones(){
        botNuevo.setEnabled(false);
        botActualizar.setEnabled(false);
        botRemover.setEnabled(false);
    }
    
    /**
     * habilita los componentes de las opciones
     */
    public void activarOpciones(){
        botNuevo.setEnabled(true);
        botActualizar.setEnabled(true);
        botRemover.setEnabled(true);
    }
    
    /**
     * deshabilita los componentes de los campos
     */
    public void bloquearCampos(){
        txtFecha.setEnabled(false);
        txtConsulta.setEnabled(false);
        txtConsultorio.setEnabled(false);
        txtCarnet.setEnabled(false);
        txtIdHistorial.setEnabled(false);
        txaDiagnostico.setEnabled(false);
        txaReceta.setEnabled(false);
        
        botAceptar.setEnabled(false);
        botCancelar.setEnabled(false);
    }
    
    /**
     * vacio los campos
     */
    public void limpiarCajasTexto(){        
        txaDiagnostico.setText("");
        txtIdHistorial.setText("");
        txaReceta.setText("");
        txtConsultorio.setText("");
        txtCarnet.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        botAceptar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        botCancelar = new javax.swing.JButton();
        botNuevo = new javax.swing.JButton();
        botActualizar = new javax.swing.JButton();
        botRemover = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        botBuscar = new javax.swing.JButton();
        lblBuscar = new javax.swing.JLabel();
        lblConsulta = new javax.swing.JLabel();
        txtConsulta = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        lblConsultorio = new javax.swing.JLabel();
        txtConsultorio = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaReceta = new javax.swing.JTextArea();
        lblReceta = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDiagnostico = new javax.swing.JTextArea();
        lblDiagnostico = new javax.swing.JLabel();
        lblCarnet = new javax.swing.JLabel();
        txtCarnet = new javax.swing.JTextField();
        botBuscarMedico = new javax.swing.JButton();
        lblIdHistorial = new javax.swing.JLabel();
        txtIdHistorial = new javax.swing.JTextField();
        botBuscarPaciente = new javax.swing.JButton();
        btnAniadirPaciente = new javax.swing.JButton();

        setBorder(null);
        setPreferredSize(new java.awt.Dimension(900, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlPrincipal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        botAceptar.setBackground(java.awt.Color.blue);
        botAceptar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        botAceptar.setForeground(new java.awt.Color(255, 255, 255));
        botAceptar.setText("Aceptar");
        botAceptar.setEnabled(false);
        botAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botAceptarActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(204, 0, 0));
        lblTitulo.setText("Consulta");

        botCancelar.setBackground(java.awt.Color.red);
        botCancelar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        botCancelar.setForeground(new java.awt.Color(255, 255, 255));
        botCancelar.setText("Cancelar");
        botCancelar.setEnabled(false);
        botCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCancelarActionPerformed(evt);
            }
        });

        botNuevo.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        botNuevo.setText("Agregar");
        botNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botNuevoActionPerformed(evt);
            }
        });

        botActualizar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        botActualizar.setText("Actualizar");
        botActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botActualizarActionPerformed(evt);
            }
        });

        botRemover.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        botRemover.setText("Eliminar");
        botRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botRemoverActionPerformed(evt);
            }
        });

        txtBuscar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtBuscar.setEnabled(false);

        botBuscar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        botBuscar.setText("Buscar");
        botBuscar.setEnabled(false);
        botBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botBuscarActionPerformed(evt);
            }
        });

        lblBuscar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblBuscar.setText("Buscar por ID consulta:");
        lblBuscar.setEnabled(false);

        lblConsulta.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblConsulta.setText("ID consulta:");

        txtConsulta.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtConsulta.setEnabled(false);

        lblFecha.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblFecha.setText("Fecha:");

        txtFecha.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtFecha.setEnabled(false);

        lblConsultorio.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblConsultorio.setText("Nombre del Consultorio:");

        txtConsultorio.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtConsultorio.setEnabled(false);
        txtConsultorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConsultorioActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        txaReceta.setColumns(20);
        txaReceta.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txaReceta.setRows(5);
        txaReceta.setEnabled(false);
        jScrollPane2.setViewportView(txaReceta);

        lblReceta.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblReceta.setText("Receta:");

        txaDiagnostico.setColumns(20);
        txaDiagnostico.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txaDiagnostico.setRows(5);
        txaDiagnostico.setEnabled(false);
        jScrollPane1.setViewportView(txaDiagnostico);

        lblDiagnostico.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblDiagnostico.setText("Diagnóstico:");

        lblCarnet.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblCarnet.setText("Carnet Médico:");

        txtCarnet.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtCarnet.setEnabled(false);

        botBuscarMedico.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        botBuscarMedico.setText("Buscar ");
        botBuscarMedico.setEnabled(false);
        botBuscarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botBuscarMedicoActionPerformed(evt);
            }
        });

        lblIdHistorial.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblIdHistorial.setText("ID Historial Paciente:");

        txtIdHistorial.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtIdHistorial.setEnabled(false);

        botBuscarPaciente.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        botBuscarPaciente.setText("Buscar ");
        botBuscarPaciente.setEnabled(false);
        botBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botBuscarPacienteActionPerformed(evt);
            }
        });

        btnAniadirPaciente.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        btnAniadirPaciente.setText("Agregar");
        btnAniadirPaciente.setEnabled(false);
        btnAniadirPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAniadirPacienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addComponent(botBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btnAniadirPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblReceta)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblDiagnostico)
                                .addComponent(txtCarnet)
                                .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                    .addComponent(lblIdHistorial)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtIdHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblConsultorio)
                                .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                    .addComponent(lblConsulta)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblFecha)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtConsultorio)
                                .addComponent(lblCarnet)
                                .addComponent(jScrollPane2))
                            .addComponent(botBuscarMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(51, 51, 51)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botActualizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botRemover, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botAceptar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(lblTitulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblBuscar)
                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lblConsultorio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtConsultorio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblIdHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(botBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAniadirPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addComponent(lblCarnet, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCarnet, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(botBuscarMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblReceta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(botBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(botNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botCancelar)
                                .addGap(49, 49, 49))))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botNuevo.getAccessibleContext().setAccessibleName("btnNuevo");

        getContentPane().add(pnlPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCancelarActionPerformed
        // Sale de la Forma
        bloquearCampos();
        limpiarCajasTexto();
        activarOpciones();
        bloquearBusqueda();
    }//GEN-LAST:event_botCancelarActionPerformed

    private void botNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botNuevoActionPerformed
        // TODO add your handling code here:
        seleccion="Guardar";
        txtFecha.setText(dtf.format(LocalDateTime.now()));
        
        generarIdConsulta();
        habilitarCampos();
        limpiarCajasTexto();
        bloquearOpciones();
        bloquearBusqueda();
    }//GEN-LAST:event_botNuevoActionPerformed

    private void botAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botAceptarActionPerformed
        // TODO add your handling code here:
        if(encontrarInformacionPaciente()&& encontrarInformacionMedico()){
            switch (seleccion ){
            case "Guardar":
                guardarConsulta();                
                break;
            case "actualizar":
                actualizarConsulta();
                break;
            case "remover":
                bloquearCampos();
                removerConsulta();
                break;
            default:
                System.out.println("Default");
            }
             activarOpciones();
            bloquearBusqueda();
        }
    }//GEN-LAST:event_botAceptarActionPerformed

    private void botActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botActualizarActionPerformed
        // TODO add your handling code here:
        seleccion="actualizar";
        leerDatos();
        limpiarCajasTexto();
        habilitarBusqueda();
        bloquearOpciones();
        botCancelar.setEnabled(true);
        
    }//GEN-LAST:event_botActualizarActionPerformed

    private void botBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botBuscarActionPerformed
        // TODO add your handling code here:
        encontrarInformacion();
        if(encontroDato){
            if(seleccion.equals("actualizar")){
                habilitarCampos();
            }else{
                botAceptar.setEnabled(true);
                botCancelar.setEnabled(true);
            }
        }else{
            mensajeVentana("INFORMACIÓN",
                    "La consulta no existe",
                    ventana);
            botCancelar.setEnabled(true);
        }
        
    }//GEN-LAST:event_botBuscarActionPerformed

    private void botRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botRemoverActionPerformed
        // TODO add your handling code here:
        seleccion="remover";
        leerDatos();
        limpiarCajasTexto();
        habilitarBusqueda();
        bloquearOpciones();
        botCancelar.setEnabled(true);


    }//GEN-LAST:event_botRemoverActionPerformed

    private void txtConsultorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsultorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsultorioActionPerformed

    private void botBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botBuscarPacienteActionPerformed
        // TODO add your handling code here:
        encontrarInformacionPaciente();
    }//GEN-LAST:event_botBuscarPacienteActionPerformed

    private void botBuscarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botBuscarMedicoActionPerformed
        // TODO add your handling code here:
        encontrarInformacionMedico();
    }//GEN-LAST:event_botBuscarMedicoActionPerformed

    private void btnAniadirPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAniadirPacienteActionPerformed
        // TODO add your handling code here:
        pacienteVentana(ventana);
        leerDatos();
    }//GEN-LAST:event_btnAniadirPacienteActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botAceptar;
    private javax.swing.JButton botActualizar;
    private javax.swing.JButton botBuscar;
    private javax.swing.JButton botBuscarMedico;
    private javax.swing.JButton botBuscarPaciente;
    private javax.swing.JButton botCancelar;
    private javax.swing.JButton botNuevo;
    private javax.swing.JButton botRemover;
    private javax.swing.JButton btnAniadirPaciente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCarnet;
    private javax.swing.JLabel lblConsulta;
    private javax.swing.JLabel lblConsultorio;
    private javax.swing.JLabel lblDiagnostico;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblIdHistorial;
    private javax.swing.JLabel lblReceta;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTextArea txaDiagnostico;
    private javax.swing.JTextArea txaReceta;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCarnet;
    private javax.swing.JTextField txtConsulta;
    private javax.swing.JTextField txtConsultorio;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIdHistorial;
    // End of variables declaration//GEN-END:variables
}

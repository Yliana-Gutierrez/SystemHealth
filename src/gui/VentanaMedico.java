package gui;

import clases.Direccion;
import clases.Especialidad;
import clases.Medico;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import listeners.GuardarBinarios;
import listeners.FuncionesAuxiliares;
import static listeners.FuncionesAuxiliares.mensajeVentana;
import listeners.LeerBinarios;

/**
 *
 * @author Grupo 5
 */
public class VentanaMedico extends javax.swing.JInternalFrame {
    //variable de tipo frame para guardar la ventana padre
    private java.awt.Frame ventana;
    private ArrayList <Medico> medicos= new ArrayList<>();
    private String seleccion="";
    private Boolean encontroDato=false;
    private Integer numeroPosicionDatos;
    
    
    /**
     * Crea una nueva forma de IFrameMedico
     * @param parent variable con la ventana padre
     */
    public VentanaMedico(java.awt.Frame parent) {
        ventana=parent;
        initComponents();
        cargarEspecialidades();
        // Quita la Barra de título
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
       
        this.setVisible(true);
    }
    /**
     * carga los datos de la clase espcialidad al combo box
     */
    private void cargarEspecialidades()
    {
        // Limpia el Control
        cboEspecialidad.removeAll();
        cboEspecialidad.addItem("Seleccione");
        cboEspecialidad.addItem(Especialidad.Cardiólogo.toString());
        cboEspecialidad.addItem(Especialidad.Dermatólogo.toString());
        cboEspecialidad.addItem(Especialidad.General.toString());
        cboEspecialidad.addItem(Especialidad.Ginecólogo.toString());
        cboEspecialidad.addItem(Especialidad.Neurólogo.toString());
        cboEspecialidad.addItem(Especialidad.Oftalmólogo.toString());
        cboEspecialidad.addItem(Especialidad.Traumatólogo.toString());
        cboEspecialidad.addItem(Especialidad.Urólogo.toString());
    }
    
    /**
     * Verifica que no haya campos vacíos y devuelve un mensaje
     * @return retorna un valor falso si encuentra un campo vacío
     */
    public boolean verificarDatos(){
        boolean verificar=true;
        String mensaje="";
        if(txtNombre.getText().equals("")){
            mensaje+="Llene el campo nombre\n";
            verificar=false;
        }
        if(txtApellidos.getText().equals("")){
            mensaje+="Llene el campo apellido\n";
            verificar=false;
        }
        if(txtCedula.getText().equals("")){
            mensaje+="Llene el campo cédula\n";
            verificar=false;
        }
        if(txtTelefono.getText().equals("")){
            mensaje+="Llene el campo teléfono\n";
            verificar=false;
        }
        if(txtCiudad.getText().equals("")){
            mensaje+="Llene el campo ciudad\n";
            verificar=false;
        }
        if(txtCalle.getText().equals("")){
            mensaje+="Llene el campo calle\n";
            verificar=false;
        }
        if(txtNumero.getText().equals("")){
            mensaje+="Llene el campo número\n";
            verificar=false;
        }
        if(cboEspecialidad.getSelectedItem().equals("Seleccione")){
            mensaje+="Seleccione una especialidad\n";
            verificar=false;
        }
               
        if(!verificar){
            mensajeVentana("ATENCIÓN",mensaje,ventana);
        }
        
        return verificar;
    }

    /**
     * Obtiene los datos del archivo .dat
     */
    public void leerDatos(){
        File archivo =new File("medico.med");
        if(archivo.canRead()){
            medicos= LeerBinarios.obtenerMedicos(ventana);
        }
        
    }
    
    /**
     * genera el código del carnet del médico y devuelve un valor bool con la confirmación
     * @return retorna un valor verdadero si se genera con éxito
     */
    public boolean generarCarnet(){
        String cedula, nombre, apellido, idCarnet;
        boolean verificar;
        cedula=txtCedula.getText();
        nombre= txtNombre.getText().toUpperCase();
        apellido=txtApellidos.getText().toUpperCase();
        
        idCarnet=FuncionesAuxiliares.generarCarnet(cedula, nombre, apellido,ventana);
        if(idCarnet.length()==10){
            txtCarnet.setText(idCarnet);
            verificar=true;
        }else{
            verificar=false;
            mensajeVentana("ERROR","No se pudo generar el carnet, revise todos los campos",ventana);
        }
        
        return verificar;
    }
    
    /**
     * guarda los datos del médico creado en el archivo .dat
     */
    public void guardarMedico(){
        
        if(verificarDatos()&&generarCarnet())
        {
            leerDatos();
            Direccion direccion = new Direccion();
            direccion.setCiudad(txtCiudad.getText());
            direccion.setCalle(txtCalle.getText());
            direccion.setNumero(txtNumero.getText());

            Medico medico=new Medico();
            medico.setNombre(txtNombre.getText());
            medico.setApellidos(txtApellidos.getText());
            medico.setCedula(txtCedula.getText());
            medico.setTelefono(txtTelefono.getText());
            medico.setDireccion(direccion);
            medico.setCarnet(txtCarnet.getText());
            medico.setEspecialidad(cboEspecialidad.getSelectedItem().toString());
            medicos.add(medico);
            GuardarBinarios.guardarMedico(medicos,ventana);
            mensajeVentana("INFORMACIÓN","Se añadio el medico",ventana);
            bloquearCampos();
        }
    }
    
    /**
     * edita los datos del médico seleccionado
     */
    public void actualizarMedico(){
        
        if(verificarDatos())
        {
            medicos.get(numeroPosicionDatos).getDireccion().setCiudad(txtCiudad.getText());
            medicos.get(numeroPosicionDatos).getDireccion().setCalle(txtCalle.getText());
            medicos.get(numeroPosicionDatos).getDireccion().setNumero(txtNumero.getText());
            medicos.get(numeroPosicionDatos).setNombre(txtNombre.getText());
            medicos.get(numeroPosicionDatos).setApellidos(txtApellidos.getText());
            medicos.get(numeroPosicionDatos).setCedula(txtCedula.getText());
            medicos.get(numeroPosicionDatos).setTelefono(txtTelefono.getText());
            medicos.get(numeroPosicionDatos).setCarnet(txtCarnet.getText());
            medicos.get(numeroPosicionDatos).setEspecialidad(cboEspecialidad.getSelectedItem().toString());
            
            GuardarBinarios.guardarMedico(medicos,ventana);
            mensajeVentana("INFORMACIÓN","Se actualizó el medico",ventana);
            bloquearCampos();
        }        
    }
    
    /**
     * elimina el médico seleccionado
     */
    public void removerMedico(){
        
        if(verificarDatos())
        {
            medicos.remove(medicos.get(numeroPosicionDatos));
            GuardarBinarios.guardarMedico(medicos,ventana);
            mensajeVentana("INFORMACIÓN","Se removió el medico",ventana);
            bloquearCampos();
        }        
    }
    
    /**
     * habilita los componentes necesarios
     */
    public void habilitarBusqueda(){
        txtBuscar.setEnabled(true);
        btnBuscar.setEnabled(true);
        lblBuscar.setForeground(Color.BLACK);
        lblBuscar.setEnabled(true);
    }
    
    /**
     * deshabilita los campos necesarios
     */
    public void bloquearBusqueda(){
        txtBuscar.setEnabled(false);
        btnBuscar.setEnabled(false);
        txtBuscar.setText("");
        
        lblBuscar.setForeground(Color.GRAY);
        lblBuscar.setEnabled(false);
    }
    
    /**
     * busca el dato en el arreglo
     */
    public void encontrarInformacion(){
        encontroDato=false;
        for(int i=0;i<medicos.size();i++){
            if(medicos.get(i).getCarnet().equals(txtBuscar.getText())){
                encontroDato=true;
                numeroPosicionDatos=i;
                txtNombre.setText(medicos.get(i).getNombre());
                txtApellidos.setText(medicos.get(i).getApellidos());
                txtCalle.setText(medicos.get(i).getDireccion().getCalle());
                txtCedula.setText(medicos.get(i).getCedula());
                txtCiudad.setText(medicos.get(i).getDireccion().getCiudad());
                txtNumero.setText(medicos.get(i).getDireccion().getNumero());
                txtTelefono.setText(medicos.get(i).getTelefono());
                txtCarnet.setText(medicos.get(i).getCarnet());
                cboEspecialidad.setSelectedItem(medicos.get(i).getEspecialidad());
            }
        }
    }
    
    /**
     * habilita los campos necesarios
     */
    public void habilitarCampos(){
        txtNombre.setEnabled(true);
        txtApellidos.setEnabled(true);
        txtCalle.setEnabled(true);
        txtCedula.setEnabled(true);
        txtCiudad.setEnabled(true);
        txtNumero.setEnabled(true);
        cboEspecialidad.setEnabled(true);
        txtTelefono.setEnabled(true);
        btnAceptar.setEnabled(true);
        btnCancelar.setEnabled(true);
    } 
    
    /**
     * deshabilita los campos necesarios
     */
    public void bloquearOpciones(){
        btnNuevo.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnRemover.setEnabled(false);
    }
    
    /**
     * habilita los campos necesarios
     */
    public void activarOpciones(){
        btnNuevo.setEnabled(true);
        btnActualizar.setEnabled(true);
        btnRemover.setEnabled(true);
    }
    
    /**
     * deshabilita los campos necesarios
     */
    public void bloquearCampos(){
        txtNombre.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtCalle.setEnabled(false);
        txtCedula.setEnabled(false);
        txtCiudad.setEnabled(false);
        txtNumero.setEnabled(false);
        txtTelefono.setEnabled(false);
        cboEspecialidad.setEnabled(false);
        btnAceptar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }
    
    /**
     * vacía los campos del formulario
     */
    public void limpiarCajasTexto(){        
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCalle.setText("");
        txtCedula.setText("");
        txtCiudad.setText("");
        txtNumero.setText("");
        txtTelefono.setText("");
        txtCarnet.setText("");
         cboEspecialidad.setSelectedItem("Seleccione");
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
        btnAceptar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblCedula = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        lblCiudad = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        txtCalle = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        lblNumero = new javax.swing.JLabel();
        lblCalle = new javax.swing.JLabel();
        lblCarnet = new javax.swing.JLabel();
        txtCarnet = new javax.swing.JTextField();
        cboEspecialidad = new javax.swing.JComboBox<>();
        lblEspecialidad = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setBorder(null);
        setPreferredSize(new java.awt.Dimension(900, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlPrincipal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAceptar.setBackground(java.awt.Color.blue);
        btnAceptar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptar.setText("Aceptar");
        btnAceptar.setEnabled(false);
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(204, 0, 0));
        lblTitulo.setText("Médico");

        btnCancelar.setBackground(java.awt.Color.red);
        btnCancelar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnNuevo.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        btnNuevo.setText("Agregar");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnActualizar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnRemover.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        btnRemover.setText("Eliminar");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        lblBuscar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblBuscar.setText("Buscar por carnet:");
        lblBuscar.setEnabled(false);

        txtBuscar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtBuscar.setEnabled(false);

        btnBuscar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setEnabled(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lblNombre.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblNombre.setText("Nombres:");

        txtNombre.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtNombre.setEnabled(false);

        lblApellido.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblApellido.setText("Apellidos:");

        txtApellidos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtApellidos.setEnabled(false);

        lblTelefono.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblTelefono.setText("Teléfono:");

        txtTelefono.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtTelefono.setEnabled(false);
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        lblCedula.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblCedula.setText("Cédula:");

        txtCedula.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtCedula.setEnabled(false);
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        lblCiudad.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblCiudad.setText("Ciudad:");

        txtCiudad.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtCiudad.setEnabled(false);

        txtCalle.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtCalle.setEnabled(false);

        txtNumero.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtNumero.setEnabled(false);

        lblNumero.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblNumero.setText("Número:");

        lblCalle.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblCalle.setText("Calle:");

        lblCarnet.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblCarnet.setText("Carnet:");

        txtCarnet.setEditable(false);
        txtCarnet.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        cboEspecialidad.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        cboEspecialidad.setEnabled(false);

        lblEspecialidad.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblEspecialidad.setText("Especialidad:");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblTitulo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCalle)
                            .addComponent(txtCiudad)
                            .addComponent(txtCedula)
                            .addComponent(txtTelefono)
                            .addComponent(txtApellidos)
                            .addComponent(txtNombre)
                            .addComponent(txtCarnet)
                            .addComponent(txtNumero)
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEspecialidad)
                                    .addComponent(lblCarnet)
                                    .addComponent(lblCalle)
                                    .addComponent(lblCiudad)
                                    .addComponent(lblCedula)
                                    .addComponent(lblTelefono)
                                    .addComponent(lblApellido)
                                    .addComponent(lblNombre)
                                    .addComponent(lblNumero)
                                    .addComponent(cboEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 13, Short.MAX_VALUE)))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemover, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrincipalLayout.createSequentialGroup()
                            .addComponent(lblBuscar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)))
                .addGap(32, 32, 32))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCarnet, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCarnet, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBuscar)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(211, 211, 211)
                                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar)
                                .addGap(93, 93, 93))
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91))))))
        );

        btnNuevo.getAccessibleContext().setAccessibleName("btnNuevo");

        getContentPane().add(pnlPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        encontrarInformacion();
        if(encontroDato){
            if(seleccion.equals("actualizar")){
                habilitarCampos();
            }else{
                btnAceptar.setEnabled(true);
                btnCancelar.setEnabled(true);
            }
        }else{
            mensajeVentana("ATRNCIÓN","No se encontro información",ventana);
        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // TODO add your handling code here:
        seleccion="remover";
        leerDatos();
        limpiarCajasTexto();
        habilitarBusqueda();
        bloquearOpciones();
        btnCancelar.setEnabled(true);
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        seleccion="actualizar";
        leerDatos();
        limpiarCajasTexto();
        habilitarBusqueda();
        bloquearOpciones();
        btnCancelar.setEnabled(true);

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        seleccion="Guardar";
        habilitarCampos();
        limpiarCajasTexto();
        bloquearOpciones();
        bloquearBusqueda();
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // Sale de la Forma
        bloquearCampos();
        limpiarCajasTexto();
        activarOpciones();
        bloquearBusqueda();
        

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        // TODO add your handling code here:
        if ("0123456789".indexOf(evt.getKeyChar())<0)
        {
            if (evt.getKeyChar()!=(char)KeyEvent.VK_DELETE)
            {

                evt.consume();
            }
        }
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
        if ("0123456789".indexOf(evt.getKeyChar())<0)
        {
            if (evt.getKeyChar()!=(char)KeyEvent.VK_DELETE)
            {

                evt.consume();
            }
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        switch (seleccion){
            case "Guardar":
            guardarMedico();
            break;
            case "actualizar":
            actualizarMedico();
            break;
            case "remover":
            bloquearCampos();
            removerMedico();
            break;
            default:
            System.out.println("Default");
        }
        activarOpciones();
        bloquearBusqueda();

    }//GEN-LAST:event_btnAceptarActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRemover;
    private javax.swing.JComboBox<String> cboEspecialidad;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCalle;
    private javax.swing.JLabel lblCarnet;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblCiudad;
    private javax.swing.JLabel lblEspecialidad;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtCarnet;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}

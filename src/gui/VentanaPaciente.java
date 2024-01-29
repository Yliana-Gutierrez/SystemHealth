package gui;

import clases.Direccion;
import clases.Paciente;
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
public class VentanaPaciente extends javax.swing.JInternalFrame {
    //variable de tipo frame para guardar la ventana padre
    private java.awt.Frame ventana;
    private ArrayList <Paciente> pacientes= new ArrayList<>();
    private String seleccion="";
    private Boolean encontroDato=false;
    private Integer numeroPosicionDatos;
    
    
    /**
     * Crea una nueva forma de IFramePaciente
     * @param parent variable con la ventana padre
     */
    public VentanaPaciente(java.awt.Frame parent) {
        ventana=parent;
        initComponents();
        // Quita la Barra de título
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
       
        this.setVisible(true);
    }
    /**
     * Verifica que no haya campos vacíos y devuelve un mensaje
     * @return retorna un valor falso si se encuentra un campo vacío
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
        if(!verificar){
            mensajeVentana("ATENCIÓN",mensaje,ventana);
        }
        
        return verificar;
    }

    /**
     * recupera los datos del archivo .dat
     */
    public void leerDatos(){
           //Verifica si existe el archivo para proceder a leerlo
        File archivo =new File("paciente.med");
        if(archivo.canRead()){
            //recupera los datos del archivo paciente y los guarda en la variable
            pacientes= LeerBinarios.obtenerPacientes(ventana);
        }
        
    }
    
    /**
     * Genera el código para el historial 
     * @return retorna una verificación de creación
     */
    public boolean generarIdHistorial(){
        String cedula, nombre, apellido, idHistorial;
        boolean verificar;
        cedula=txtCedula.getText();
        nombre= txtNombre.getText().toUpperCase();
        apellido=txtApellidos.getText().toUpperCase();
        
        idHistorial=FuncionesAuxiliares.generaCodigoHistorial(cedula, nombre, apellido,ventana);
        if(idHistorial.length()==11){
            txtHistorial.setText(idHistorial);
            verificar=true;
        }else{
            verificar=false;
            mensajeVentana("ERROR","Revise que todos los campos este lleno",ventana);
        }
        
        return verificar;
    }
    
    /**
     * guarda al paciente ingresado en el archivo .dat
     */
    public void guardarPaciente(){
        
        if(verificarDatos()&&generarIdHistorial())
        {
            leerDatos();
            Direccion direccion = new Direccion();
            direccion.setCiudad(txtCiudad.getText());
            direccion.setCalle(txtCalle.getText());
            direccion.setNumero(txtNumero.getText());

            Paciente paciente=new Paciente();
            paciente.setNombre(txtNombre.getText());
            paciente.setApellidos(txtApellidos.getText());
            paciente.setCedula(txtCedula.getText());
            paciente.setTelefono(txtTelefono.getText());
            paciente.setDireccion(direccion);
            paciente.setIdHistorial(txtHistorial.getText());
            pacientes.add(paciente);
            GuardarBinarios.guardarPaciente( pacientes,ventana);
            mensajeVentana("INFORMACIÓN","Se añadio el paciente",ventana);
            bloquearCampos();
        }
        
        
    }
    
    /**
     * edita los datos del paciente seleccionado
     */
    public void actualizarPaciente(){
        
        if(verificarDatos())
        {
            pacientes.get(numeroPosicionDatos).getDireccion().setCiudad(txtCiudad.getText());
            pacientes.get(numeroPosicionDatos).getDireccion().setCalle(txtCalle.getText());
            pacientes.get(numeroPosicionDatos).getDireccion().setNumero(txtNumero.getText());
            pacientes.get(numeroPosicionDatos).setNombre(txtNombre.getText());
            pacientes.get(numeroPosicionDatos).setApellidos(txtApellidos.getText());
            pacientes.get(numeroPosicionDatos).setCedula(txtCedula.getText());
            pacientes.get(numeroPosicionDatos).setTelefono(txtTelefono.getText());
            pacientes.get(numeroPosicionDatos).setIdHistorial(txtHistorial.getText());
            GuardarBinarios.guardarPaciente( pacientes,ventana);
            mensajeVentana("INFORMACIÓN","Se edito con éxito el paciente",ventana);
            bloquearCampos();
        }        
    }
    
    /**
     * elimina al paciente seleccionado
     */
    public void removerPaciente(){
        
        if(verificarDatos())
        {
            pacientes.remove(pacientes.get(numeroPosicionDatos));
            GuardarBinarios.guardarPaciente( pacientes,ventana);
            mensajeVentana("INFORMACIÓN","Se elimino con éxito el paciente",ventana);
            bloquearCampos();
        }        
    }
    
    /**
     * habilita los campos necesarios
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
     * busca si el dato existe en el array
     */
    public void encontrarInformacion(){
        encontroDato=false;
        for(int i=0;i<pacientes.size();i++){
            if(pacientes.get(i).getIdHistorial().equals(txtBuscar.getText())){
                encontroDato=true;
                numeroPosicionDatos=i;
                txtNombre.setText(pacientes.get(i).getNombre());
                txtApellidos.setText(pacientes.get(i).getApellidos());
                txtCalle.setText(pacientes.get(i).getDireccion().getCalle());
                txtCedula.setText(pacientes.get(i).getCedula());
                txtCiudad.setText(pacientes.get(i).getDireccion().getCiudad());
                txtNumero.setText(pacientes.get(i).getDireccion().getNumero());
                txtTelefono.setText(pacientes.get(i).getTelefono());
                txtHistorial.setText(pacientes.get(i).getIdHistorial());
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
        txtTelefono.setEnabled(true);
        btnAceptar.setEnabled(true);
        btnCancelar.setEnabled(true);
    } 
    
    /**
     * deshabilita los campos necesarios
     */
    public void bloquearOpciones(){
        btnNuevo.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }
    
    /**
     * habilita los campos necesarios
     */
    public void activarOpciones(){
        btnNuevo.setEnabled(true);
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
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
        txtHistorial.setText("");
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
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        lblCedula = new javax.swing.JLabel();
        lblCiudad = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        lblCalle = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        lblNumero = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        lblId = new javax.swing.JLabel();
        txtHistorial = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
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
        lblTitulo.setText("Paciente");

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

        btnEditar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        btnEditar.setText("Actualizar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        lblBuscar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblBuscar.setText("Buscar por ID Historial:");
        lblBuscar.setEnabled(false);

        txtBuscar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtBuscar.setEnabled(false);

        btnBuscar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setEnabled(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtNombre.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtNombre.setEnabled(false);

        lblNombre.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblNombre.setText("Nombres:");

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

        txtCedula.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtCedula.setEnabled(false);
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        lblCedula.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblCedula.setText("Cédula:");

        lblCiudad.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblCiudad.setText("Ciudad:");

        txtCiudad.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtCiudad.setEnabled(false);

        lblCalle.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblCalle.setText("Calle:");

        txtCalle.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtCalle.setEnabled(false);

        lblNumero.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblNumero.setText("Número:");

        txtNumero.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtNumero.setEnabled(false);

        lblId.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblId.setText("ID Historial:");

        txtHistorial.setEditable(false);
        txtHistorial.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblId)
                        .addComponent(lblTitulo))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNombre)
                            .addComponent(lblApellido)
                            .addComponent(lblCedula)
                            .addComponent(lblCiudad)
                            .addComponent(lblCalle)
                            .addComponent(lblNumero)
                            .addComponent(lblTelefono)
                            .addComponent(txtHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                            .addComponent(txtNombre)
                            .addComponent(txtApellidos)
                            .addComponent(txtTelefono)
                            .addComponent(txtCedula)
                            .addComponent(txtCiudad)
                            .addComponent(txtCalle)
                            .addComponent(txtNumero))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 34, Short.MAX_VALUE))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBuscar)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)))
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(lblCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnNuevo.getAccessibleContext().setAccessibleName("btnNuevo");

        getContentPane().add(pnlPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // Sale de la Forma
        bloquearCampos();
        limpiarCajasTexto();
        activarOpciones();
        bloquearBusqueda();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        seleccion="Guardar";
        habilitarCampos();
        limpiarCajasTexto();
        bloquearOpciones();
        bloquearBusqueda();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        switch (seleccion){
            case "Guardar":
                guardarPaciente();                
                break;
            case "actualizar":
                actualizarPaciente();
                break;
            case "remover":
                bloquearCampos();
                removerPaciente();
                break;
            default:
                System.out.println("Default");
        }
        activarOpciones();
        bloquearBusqueda();
        
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        seleccion="actualizar";
        leerDatos();
        limpiarCajasTexto();
        habilitarBusqueda();
        bloquearOpciones();
        btnCancelar.setEnabled(true);
        
    }//GEN-LAST:event_btnEditarActionPerformed

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
            mensajeVentana("INFORMACIÓN","No se encontro al paciente en los registros",ventana);
            btnCancelar.setEnabled(true);
        }
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        seleccion="remover";
        leerDatos();
        limpiarCajasTexto();
        habilitarBusqueda();
        bloquearOpciones();
        btnCancelar.setEnabled(true);

    }//GEN-LAST:event_btnEliminarActionPerformed

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
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCalle;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblCiudad;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtHistorial;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}

package gui;

import clases.Hospital;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import listeners.GuardarBinarios;
import static listeners.FuncionesAuxiliares.mensajeVentana;
import listeners.LeerBinarios;

/**
 *
 * @author Grupo 5
 */
public class VentanaHospital extends javax.swing.JInternalFrame {
     //variable de tipo frame para guardar la ventana padre
    java.awt.Frame ventana;
    /**
     * Crea una nueva forma de IFrameHospital
     * @param parent variable con la ventana padre
     */
    public VentanaHospital(java.awt.Frame parent) {
        initComponents();
        ventana=parent;
        // Quita la Barra de título
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        leerDatos();
        this.setVisible(true);
    }
    
    /**
     * lee los datos del archivo hospital.dat
     */
    public void leerDatos(){
        Hospital hospital= new Hospital();
        //Verifica si existe el archivo para proceder a leerlo
       File archivo =new File("hospital.med");
        if(archivo.canRead()){
            //recupera los datos del archivo hopsital y los guarda en la variable
            hospital=LeerBinarios.obtenerHospital(ventana);
        }
        txtNombre.setText(hospital.getNombre());
        txtRuc.setText(hospital.getRuc());
        txtTelefono.setText(hospital.getTelefono());
    }
    
    /**
     * guarda los cambios realizados del hospital
     */
    public void guardarHospital(){
        
        Hospital hospital = new Hospital();
        hospital.setNombre(txtNombre.getText());
        hospital.setRuc(txtRuc.getText());
        hospital.setTelefono(txtTelefono.getText());


        GuardarBinarios.guardarHospital( hospital,ventana);
        mensajeVentana("INFORMACIÓN","Información actualizada",ventana);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblRuc = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        botGuardar = new javax.swing.JButton();
        botSalir = new javax.swing.JButton();

        setBorder(null);
        setPreferredSize(new java.awt.Dimension(900, 600));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(204, 51, 0));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("DATOS DEL HOSPITAL");

        lblNombre.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblNombre.setText("Institución");

        txtNombre.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        lblRuc.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblRuc.setText("RUC");

        txtRuc.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucKeyTyped(evt);
            }
        });

        lblTelefono.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblTelefono.setText("Teléfono");

        txtTelefono.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        botGuardar.setBackground(java.awt.Color.blue);
        botGuardar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        botGuardar.setForeground(new java.awt.Color(255, 255, 255));
        botGuardar.setText("Guardar");
        botGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botGuardarActionPerformed(evt);
            }
        });

        botSalir.setBackground(new java.awt.Color(255, 0, 0));
        botSalir.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        botSalir.setForeground(new java.awt.Color(255, 255, 255));
        botSalir.setText("Cerrar");
        botSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(304, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNombre)
                    .addComponent(lblRuc)
                    .addComponent(lblTelefono)
                    .addComponent(txtNombre)
                    .addComponent(txtRuc)
                    .addComponent(txtTelefono)
                    .addComponent(botGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(288, 288, 288))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblTitulo)
                .addGap(54, 54, 54)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(botGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botSalirActionPerformed
        // Sale de la Forma
        VentanaPrincipal.hospital=null;
        dispose();
    }//GEN-LAST:event_botSalirActionPerformed

    private void botGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botGuardarActionPerformed
        // TODO add your handling code here:
        guardarHospital();
    }//GEN-LAST:event_botGuardarActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        VentanaPrincipal.paciente=null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyTyped
        // TODO add your handling code here:
        if ("0123456789".indexOf(evt.getKeyChar())<0)
        {          
            if (evt.getKeyChar()!=(char)KeyEvent.VK_DELETE)
            {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtRucKeyTyped

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

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botGuardar;
    private javax.swing.JButton botSalir;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRuc;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}

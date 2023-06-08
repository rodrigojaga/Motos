/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTAS;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ColorModel;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import static javax.swing.text.StyleConstants.Background;


/**
 *
 * @author rodri
 */
public class tabla extends javax.swing.JFrame {

    /**
     * Creates new form tabla
     */
    private JMenuItem mniServicios;
    private JMenuItem mniProductos;
    private JMenuItem mniInventario;
    private JMenuItem mniSalir;
    private String logoServicios = "/IMAGENES/servicios.png";
    private String logoProductos = "/IMAGENES/casco.png";
    private String logoInventario = "/IMAGENES/inventario.png";
    private String logoSalir = "/IMAGENES/salir.png";
    DefaultTableModel t1 = new DefaultTableModel();
    public tabla() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        mniServicios = new JMenuItem(" Servicios ", getIcono(logoServicios));
        mniProductos = new JMenuItem(" Productos ", getIcono(logoProductos));
        mniInventario = new JMenuItem(" Inventario ", getIcono(logoInventario));
        mniSalir = new JMenuItem(" Salir ", getIcono(logoSalir));
        mniSalir.setOpaque(true);
        mniSalir.setBackground(new Color(214,123,121));
        //mniSeguridad.setOpaque(true);
        //mniSeguridad.setBackground(Color.red);
        
        raton();
        cursoryMargenes();
        mbar.add(mniServicios);
        mbar.add(mniProductos);
        mbar.add(mniInventario);
        mbar.add(mniSalir);
        
        
        mniSalir.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        
        mniServicios.addActionListener((ActionEvent e) -> {
            seleccion(jpServicios);
        });
        
        mniProductos.addActionListener((ActionEvent e) -> {
            seleccion(jpProductos);
        });
        mniInventario.addActionListener((ActionEvent e) -> {
            seleccion(jpInventario);
        });
//        mniProductos.setOpaque(true);
//        mniProductos.setBackground(new Color(160,214,212) );
//        mniProductos.setForeground(Color.WHITE);
        
    }
    
    
    private Icon getIcono(String ruta){
        return new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(30,30,0));
    }
    
    private void cursoryMargenes(){
        mbar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mniServicios.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Border borde = BorderFactory.createLineBorder(Color.BLACK, 1);
        mniServicios.setBorderPainted(true);
        mniProductos.setBorderPainted(true);
        mniInventario.setBorderPainted(true);
        mniSalir.setBorderPainted(true);
        mniServicios.setBorder(borde);
        mniProductos.setBorder(borde);
        mniInventario.setBorder(borde);
        mniSalir.setBorder(borde);
        mniServicios.setRolloverEnabled(true);
        mniServicios.setRolloverIcon(getIcono(logoServicios));
        
        
        //Margenes arriba-izquierda-abajo-derecha
//        Insets margen = new Insets(0,60,0,0);
//        mniServicios.setMargin(margen);
//        mniProductos.setMargin(margen);
//        mniInventario.setMargin(margen);
//        mniSalir.setMargin(margen);
        
    }
    
    private void raton(){
        Color cm = mniServicios.getBackground();
        Color sa = mniSalir.getBackground();
        mniServicios.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                mniServicios.setOpaque(true);
                mniServicios.setBackground(Color.ORANGE);
            }
            @Override
            public void mouseExited(MouseEvent e){
                mniServicios.setOpaque(false);
                mniServicios.setBackground(cm);
            }
        });
        
        mniProductos.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                mniProductos.setOpaque(true);
                mniProductos.setBackground(Color.ORANGE);
            }
            @Override
            public void mouseExited(MouseEvent e){
                mniProductos.setOpaque(false);
                mniProductos.setBackground(cm);
                
            }
        });
        
        mniInventario.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                mniInventario.setOpaque(true);
                mniInventario.setBackground(Color.ORANGE);
            }
            @Override
            public void mouseExited(MouseEvent e){
                mniInventario.setOpaque(false);
                mniInventario.setBackground(cm);    
            }
        });
        
        mniSalir.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                mniSalir.setOpaque(true);
                mniSalir.setBackground(new Color(255,109,109));
            }
            @Override
            public void mouseExited(MouseEvent e){
                mniSalir.setOpaque(true);
                mniSalir.setBackground(sa);
                
            }
        });
    }
    
    private void seleccion(JPanel jPanel){
        jPanel.setSize(jpPrincipal.getWidth(),jpPrincipal.getHeight());
        jpPrincipal.removeAll();
        jpPrincipal.add(jPanel);
        jpPrincipal.repaint();
        activarseleccion(jPanel);
    }
    
    private void activarseleccion(JPanel jPanel){
        jpProductos.setEnabled(false);
        jpProductos.setVisible(false);
        jpInventario.setEnabled(false);
        jpInventario.setVisible(false);
        jPanel.setVisible(true);
        jPanel.setEnabled(true);
        datosServicios();
    }
    
    private void datosServicios() {
        String columnas[] = {"ID Servicios", "Nombre", "Dirección", "Correo Electronico", "Telefono"};
        t1 = new DefaultTableModel(null, columnas);
//        ah4DAORelacional ad = new ah4DAORelacional();
//        for (sucursalesPOO dat : ad.listarSucursales()) {
//            Object ayuda[] = new Object[5];
//            ayuda[0] = dat.getId();
//            ayuda[1] = dat.getNombre();
//            ayuda[2] = dat.getDireccion();
//            ayuda[3] = dat.getCorreo();
//            ayuda[4] = dat.getTelefono();
//            t1.addRow(ayuda);
//        }
        tablaServicios.setModel(t1);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPrincipal = new javax.swing.JPanel();
        jpInventario = new javax.swing.JPanel();
        jpServicios = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaServicios = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        Crear = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jpProductos = new javax.swing.JPanel();
        mbar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jpPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpInventarioLayout = new javax.swing.GroupLayout(jpInventario);
        jpInventario.setLayout(jpInventarioLayout);
        jpInventarioLayout.setHorizontalGroup(
            jpInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 733, Short.MAX_VALUE)
        );
        jpInventarioLayout.setVerticalGroup(
            jpInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        tablaServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaServicios);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
        );

        Crear.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        Crear.setText("Crear");

        jTextField1.setText("jTextField1");

        jTextField2.setText("jTextField2");

        jTextField3.setText("jTextField3");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addComponent(Crear)
                .addGap(89, 89, 89))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Crear)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpServiciosLayout = new javax.swing.GroupLayout(jpServicios);
        jpServicios.setLayout(jpServiciosLayout);
        jpServiciosLayout.setHorizontalGroup(
            jpServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpServiciosLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpServiciosLayout.setVerticalGroup(
            jpServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpProductosLayout = new javax.swing.GroupLayout(jpProductos);
        jpProductos.setLayout(jpProductosLayout);
        jpProductosLayout.setHorizontalGroup(
            jpProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 733, Short.MAX_VALUE)
        );
        jpProductosLayout.setVerticalGroup(
            jpProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpPrincipalLayout = new javax.swing.GroupLayout(jpPrincipal);
        jpPrincipal.setLayout(jpPrincipalLayout);
        jpPrincipalLayout.setHorizontalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpServicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpServicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setJMenuBar(mbar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tabla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Crear;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JPanel jpInventario;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JPanel jpProductos;
    private javax.swing.JPanel jpServicios;
    private javax.swing.JMenuBar mbar;
    private javax.swing.JTable tablaServicios;
    // End of variables declaration//GEN-END:variables
}

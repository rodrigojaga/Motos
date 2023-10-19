
package VISTAS;

import IMAGENES.TextPrompt;
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
import MODELO.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rodri
 */
public class tabla extends javax.swing.JFrame {

    /**
     * Creates new form tabla
     */
    private boolean filtroResult = false;
    //Cosas relacionadas a las vistas de usuario
    private JMenuItem mniServicios;
    private JMenuItem mniProductos;
    private JMenuItem mniInventario;
    private JMenuItem mniSalir;
    //Rutas de las imagenes usadas
    private String logoServicios = "/IMAGENES/servicios.png";
    private String logoProductos = "/IMAGENES/casco.png";
    private String logoInventario = "/IMAGENES/inventario.png";
    private String logoSalir = "/IMAGENES/salir.png";
    
    //Modelos para las diferentes tablas
    DefaultTableModel t1 = new DefaultTableModel();
    DefaultTableModel tProductos = new DefaultTableModel();
    DefaultTableModel tEnProceso = new DefaultTableModel();
    int id;
    public tabla() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        //Pone los logos donde deben ir
        mniServicios = new JMenuItem(" Servicios ", getIcono(logoServicios));
        mniProductos = new JMenuItem(" Articulos ", getIcono(logoProductos));
        mniInventario = new JMenuItem(" En Proceso ", getIcono(logoInventario));
        mniSalir = new JMenuItem(" Salir ", getIcono(logoSalir));
        mniSalir.setOpaque(true);
        mniSalir.setBackground(new Color(214,123,121));

        
        raton();
        cursoryMargenes();
        mbar.add(mniServicios);
        mbar.add(mniProductos);
        mbar.add(mniInventario);
        mbar.add(mniSalir);
        
        //Action Listeners de los panels para aparecer y desaparecer
        mniSalir.addActionListener((ActionEvent e) -> {
            Login ln = new Login();
            ln.setVisible(true);
            dispose();
        });
        
        mniServicios.addActionListener((ActionEvent e) -> {
            seleccion(jpServicios);
            crearServicio.setVisible(true);  
            campoTexto(true);
            datosServicios();
            btnModificar.setVisible(false);
        });
        
        mniProductos.addActionListener((ActionEvent e) -> {
            seleccion(jpProductos);
            crearProducto.setVisible(true);
            campoTextoProducto(true);
            datosProductos();
            btnModificarPro.setVisible(false);
            
        });
        mniInventario.addActionListener((ActionEvent e) -> {
            seleccion(jpInventario);
            crearTarea.setVisible(true);
            campoTextoEnProceso(true);
            datosEnProceso();
            btnModificarTrabajo.setVisible(false);
            
        });

        //Blanqueamiento de todo
        campoTexto(false);
        campoTextoProducto(false);
        campoTextoEnProceso(false);
    }
    
    /**
     * Pone un logo donde se requiera
     * @param ruta
     * @return 
     */
    private Icon getIcono(String ruta){
        return new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(30,30,0));
    }
    
    
    /**
     * revela quita Campose de texto en panel Servicio
     * @param option 
     */
    private void campoTexto(boolean option){
        btnModificar.setVisible(option);
        crearServicio.setVisible(option);
        txtNombre.setVisible(option);
        txtDescripcion.setVisible(option);
        txtPrecio.setVisible(option);
        txtContacto.setVisible(option);
        TextPrompt tfU = new TextPrompt("Nombre del servicio",txtNombre);
        TextPrompt tfC = new TextPrompt("Descripcion del servicio",txtDescripcion);
        TextPrompt tfa = new TextPrompt("Precio del servicio",txtPrecio);
        TextPrompt tfqq = new TextPrompt("Contactate a: ",txtContacto);
    }
    
    /**
     * revela quita Campose de texto en panel productos
     * @param option 
     */
    private void campoTextoProducto(boolean option){
        btnModificarPro.setVisible(option);
        crearProducto.setVisible(option);
        txtNombreProducto.setVisible(option);
        txtPrecioPro.setVisible(option);
        txtMarca.setVisible(option);
        txtStock.setVisible(option);
        TextPrompt tfU = new TextPrompt("Nombre del producto",txtNombreProducto);
        TextPrompt tfC = new TextPrompt("Precio del Producto",txtPrecioPro);
        TextPrompt tfa = new TextPrompt("Marca del producto",txtMarca);
        TextPrompt tf1 = new TextPrompt("Existencias del producto",txtStock);
    }
    
    /**
     * revela quita Campos de texto en panel EnProceso
     * @param option 
     */
    private void campoTextoEnProceso(boolean option){
        btnModificarTrabajo.setVisible(option);
        crearTarea.setVisible(option);
        txtDescripcionProceso.setVisible(option);
        txtNombreCliente.setVisible(option);
        txtVehiculo.setVisible(option);
        cbMecanico.setVisible(option);
        TextPrompt tfU = new TextPrompt("Breve descripcion de lo que hay que hacer",txtDescripcionProceso);
        TextPrompt tfC = new TextPrompt("Nombre del cliente",txtNombreCliente);
        TextPrompt tfa = new TextPrompt("Tipo de Vehiculo",txtVehiculo);    
        cbMecanico.removeAllItems();
        cbMecanico.addItem("Mecanico a Cargo:");
        //llene el combobox con los mecanicos registrados
        EnProcesoImplementacion ei = new EnProcesoImplementacion();
        try{
        ei.llenarComboBoxPuesto().forEach(dato -> cbMecanico.addItem(dato));
        }catch(Exception e){
            
        }
        cbMecanico.addItem("Nuevo Mecanicus");
    }
    
    /**
     * Mas metodos relacionados a las vistas
     */
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
    }
    /**
     * Metodos relacionados al mouse
     */
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
    /**
     * Activa la seleccion que se ha clickeado
     * @param jPanel 
     */
    private void seleccion(JPanel jPanel){
        jPanel.setSize(jpPrincipal.getWidth(),jpPrincipal.getHeight());
        jpPrincipal.removeAll();
        jpPrincipal.add(jPanel);
        jpPrincipal.repaint();
        activarseleccion(jPanel);
    }
    /**
     * Activa al panel seleccionado y deactiva todos los demas
     * @param jPanel 
     */
    private void activarseleccion(JPanel jPanel){
        jpProductos.setEnabled(false);
        jpProductos.setVisible(false);
        jpInventario.setEnabled(false);
        jpInventario.setVisible(false);
        jpServicios.setVisible(false);
        jpServicios.setEnabled(false);
        jPanel.setVisible(true);
        jPanel.setEnabled(true);
        
        
        
    }
    
    /**
     * Muestra datos en la tabla tablaServicios, obtiene los datos del metodo
     * listar en la clase ServicioImplementacion
     */
    private void datosServicios() {
        //Establece las columnas y sus nombres
        String columnas[] = {"ID", "Servicio","Descripcion","Precio","Contacto"};
        t1 = new DefaultTableModel(null, columnas);
        ServicioImplementacion im = new ServicioImplementacion();
        for(servicioDAO dat: im.listar()){
            Object ayuda[] = new Object[5];
            ayuda[0] = dat.getId();
            ayuda[1] = dat.getNombreServicio();
            ayuda[2] = dat.getDescripcion();
            ayuda[3] = dat.getPrecio();
            ayuda[4] = dat.getContacto();
            //Asgina todos los datos a una fila
            t1.addRow(ayuda);
        }
        //Agrega las filas a la tabla
        tablaServicios.setModel(t1);   
    }
    
    /**
     * Muestra datos en la tabla de la pestaña de productos
     * Los datos los obtiene del metodo listar en la clase ProductoImplementacion
     */
    private void datosProductos() {
        //Establece las columnas y sus nombres 
        String columnas[] = {"ID", "Producto","Marca","Precio","Existencias"};
        tProductos = new DefaultTableModel(null, columnas);
        ProductoImplementacion im = new ProductoImplementacion();
        for(productoDAO dat: im.listar()){
            Object ayuda[] = new Object[5];
            ayuda[0] = dat.getIntID();
            ayuda[1] = dat.getStrnombreProducto();
            ayuda[2] = dat.getStrMarca();
            ayuda[3] = dat.getDblPrecioProducto();
            ayuda[4] = dat.getIntStock();
            //Asgina todos los datos a una fila
            tProductos.addRow(ayuda);
        }
        //Agrega las filas a la tabla
        tablaProductos.setModel(tProductos);   
    }
    
    /**
     * Muestra datos en la tabla de la pestaña de EnProceso. Obtiene los datos
     * del metodo listar en la clase EnProcesoImplementacion
     */
    private void datosEnProceso() {
        //Establece las columnas y sus nombres 
        String columnas[] = {"ID", "Descripcion del Trabajo","Mecanico a Cargo","Dueño","Tipo de Vehiculo"};
        tEnProceso = new DefaultTableModel(null, columnas);
        EnProcesoImplementacion im = new EnProcesoImplementacion();
        for(EnProcesoDAO dat: im.listar()){
            Object ayuda[] = new Object[5];
            ayuda[0] = dat.getIntID();
            ayuda[1] = dat.getStrDescripcionProceso();
            ayuda[2] = dat.getStrNombreMecanico();
            ayuda[3] = dat.getStrNombreDueno();
            ayuda[4] = dat.getStrVehiculo();
            //Asgina todos los datos a una fila
            tEnProceso.addRow(ayuda);
        }
        //Agrega las filas a la tabla
        tablaEnProceso.setModel(tEnProceso);   
    }
    
    /**
     * Muestra datos en los textField de la pestaña servicios al hacer click en alguna fila
     */
    private void mostrarDatos(){
        
        id = (int)tablaServicios.getValueAt(tablaServicios.getSelectedRow(), 0);
        String servicio = String.valueOf(tablaServicios.getValueAt(tablaServicios.getSelectedRow(), 1));
        String descripcion = String.valueOf(tablaServicios.getValueAt(tablaServicios.getSelectedRow(), 2));
        double precio = (double)tablaServicios.getValueAt(tablaServicios.getSelectedRow(), 3);
        String contacto = String.valueOf(tablaServicios.getValueAt(tablaServicios.getSelectedRow(), 4));
        txtNombre.setText(servicio);
        txtDescripcion.setText(descripcion);
        txtPrecio.setText(String.valueOf(precio));    
        txtContacto.setText(contacto);
        btnModificar.setVisible(true);
        
    }
    
    /**
     * Muestra datos en los textField de la pestaña productos al hacer click en alguna fila
     */
    private void mostrarDatosProducto(){
        
        id = (int)tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 0);
        String producto = String.valueOf(tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 1));
        String marca = String.valueOf(tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 2));
        double precio = (double)tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 3);
        int stock = (int)(tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 4));
        txtNombreProducto.setText(producto);
        txtMarca.setText(marca);
        txtPrecioPro.setText(String.valueOf(precio));    
        txtStock.setText(String.valueOf(stock));
        btnModificarPro.setVisible(true);
        
    }
    
    /**
     * Muestra datos en los textField de la pestaña EnProceso al hacer click en alguna fila
     */
    private void mostrarDatosEnProceso(){
        
        id = (int)tablaEnProceso.getValueAt(tablaEnProceso.getSelectedRow(), 0);
        String desc = String.valueOf(tablaEnProceso.getValueAt(tablaEnProceso.getSelectedRow(), 1));
        String mecanico = String.valueOf(tablaEnProceso.getValueAt(tablaEnProceso.getSelectedRow(), 2));
        //System.out.println(mecanico);
        String dueno = String.valueOf(tablaEnProceso.getValueAt(tablaEnProceso.getSelectedRow(), 3));
        String vehiculo= String.valueOf(tablaEnProceso.getValueAt(tablaEnProceso.getSelectedRow(), 4));
        txtDescripcionProceso.setText(desc);
        txtNombreCliente.setText(dueno);
        txtVehiculo.setText(vehiculo);    
        //Establece en el combobox el item que es similar al nombre del mecanico en la tabla
        for (int i = 0; i < cbMecanico.getItemCount(); i++) {            
            if (separarCadena(cbMecanico.getItemAt(i).toString().trim(),1).equals(mecanico)) {
            cbMecanico.setSelectedItem(cbMecanico.getItemAt(i));
            break; 
        }
}
        btnModificarTrabajo.setVisible(true);
        
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaEnProceso = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        crearTarea = new javax.swing.JButton();
        txtDescripcionProceso = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        txtVehiculo = new javax.swing.JTextField();
        btnModificarTrabajo = new javax.swing.JButton();
        cbMecanico = new javax.swing.JComboBox<>();
        jpServicios = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaServicios = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        crearServicio = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtContacto = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        jpProductos = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        crearProducto = new javax.swing.JButton();
        txtNombreProducto = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtPrecioPro = new javax.swing.JTextField();
        btnModificarPro = new javax.swing.JButton();
        txtStock = new javax.swing.JTextField();
        mbar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jpPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        tablaEnProceso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaEnProceso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEnProcesoMouseClicked(evt);
            }
        });
        tablaEnProceso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaEnProcesoKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tablaEnProceso);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        crearTarea.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        crearTarea.setText("Crear Tarea");
        crearTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearTareaActionPerformed(evt);
            }
        });

        txtDescripcionProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionProcesoActionPerformed(evt);
            }
        });
        txtDescripcionProceso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionProcesoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionProcesoKeyTyped(evt);
            }
        });

        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });

        txtVehiculo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVehiculoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVehiculoKeyTyped(evt);
            }
        });

        btnModificarTrabajo.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnModificarTrabajo.setText("Modificar");
        btnModificarTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarTrabajoActionPerformed(evt);
            }
        });

        cbMecanico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMecanico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMecanicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(crearTarea, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(btnModificarTrabajo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(89, 89, 89))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcionProceso)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVehiculo)
                    .addComponent(cbMecanico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(txtDescripcionProceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(cbMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                .addComponent(btnModificarTrabajo)
                .addGap(18, 18, 18)
                .addComponent(crearTarea)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jpInventarioLayout = new javax.swing.GroupLayout(jpInventario);
        jpInventario.setLayout(jpInventarioLayout);
        jpInventarioLayout.setHorizontalGroup(
            jpInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInventarioLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpInventarioLayout.setVerticalGroup(
            jpInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        tablaServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaServiciosMouseClicked(evt);
            }
        });
        tablaServicios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaServiciosKeyPressed(evt);
            }
        });
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

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        crearServicio.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        crearServicio.setText("Crear Servicio");
        crearServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearServicioActionPerformed(evt);
            }
        });

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });

        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });

        txtContacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContactoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContactoKeyTyped(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(crearServicio, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(89, 89, 89))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre)
                    .addComponent(txtDescripcion)
                    .addComponent(txtPrecio)
                    .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnModificar)
                .addGap(18, 18, 18)
                .addComponent(crearServicio)
                .addGap(25, 25, 25))
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

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
        });
        tablaProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaProductosKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tablaProductos);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        crearProducto.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        crearProducto.setText("Crear Producto");
        crearProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearProductoActionPerformed(evt);
            }
        });

        txtNombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreProductoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreProductoKeyTyped(evt);
            }
        });

        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMarcaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaKeyTyped(evt);
            }
        });

        txtPrecioPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioProKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioProKeyTyped(evt);
            }
        });

        btnModificarPro.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnModificarPro.setText("Modificar");
        btnModificarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProActionPerformed(evt);
            }
        });

        txtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStockKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(crearProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(btnModificarPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(89, 89, 89))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreProducto)
                    .addComponent(txtMarca)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecioPro, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPrecioPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                .addComponent(btnModificarPro)
                .addGap(18, 18, 18)
                .addComponent(crearProducto)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jpProductosLayout = new javax.swing.GroupLayout(jpProductos);
        jpProductos.setLayout(jpProductosLayout);
        jpProductosLayout.setHorizontalGroup(
            jpProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpProductosLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpProductosLayout.setVerticalGroup(
            jpProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void tablaServiciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaServiciosMouseClicked
        //Al precionar una fila de la tabla, muestra los datos en los text Fields
        mostrarDatos();
    }//GEN-LAST:event_tablaServiciosMouseClicked

    private void crearServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearServicioActionPerformed
        //Crea un nuevo servicio al dar click al boton, luego de crearlo
        //Botta todos los text fields 
        crearServicio();
        flashPoint();
    }//GEN-LAST:event_crearServicioActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        //Invoca al metodo modificar, pasandole parametros para que sepa donde
        //debe hacer modificaciones 
        modificar("SERVICIO",t1,btnModificar,obtenerObjeto());
    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        //Escucha cada vez que se escribe en los text fields para aplicar el filtro 
        //Deseado por el usuario
        ObtenerData();
    }//GEN-LAST:event_txtNombreKeyReleased

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        //Al precionar una fila de la tabla, muestra los datos en los text Fields
        mostrarDatosProducto();
    }//GEN-LAST:event_tablaProductosMouseClicked

    private void crearProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearProductoActionPerformed
        //Crea un nuevo producto al dar click al boton, luego de crearlo
        //Botta todos los text fields 
        crearProducto();
        flashPoint();
    }//GEN-LAST:event_crearProductoActionPerformed

    private void btnModificarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProActionPerformed
        //Invoca al metodo modificar, pasandole parametros para que sepa donde
        //debe hacer modificaciones 
        modificar("PRODUCTOS",tProductos,btnModificarPro,obtenerObjetoProduto());
    }//GEN-LAST:event_btnModificarProActionPerformed

    private void btnModificarTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarTrabajoActionPerformed
        //Invoca al metodo modificar, pasandole parametros para que sepa donde
        //debe hacer modificaciones 
        modificar("ENPROCESO", tEnProceso, btnModificarTrabajo, obtenerObjetoEnProceso());
    }//GEN-LAST:event_btnModificarTrabajoActionPerformed

    private void txtDescripcionProcesoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionProcesoKeyReleased
        //Escucha cada vez que se escribe en los text fields para aplicar el filtro 
        //Deseado por el usuario
        ObtenerDataEnProceso();
    }//GEN-LAST:event_txtDescripcionProcesoKeyReleased

    private void txtDescripcionProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionProcesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionProcesoActionPerformed

    private void crearTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearTareaActionPerformed
        //Crea un nuevo enproceso o tarea al dar click al boton, luego de crearlo
        //Botta todos los text fields 
        crearEnProceso();
        flashPoint();
    }//GEN-LAST:event_crearTareaActionPerformed

    private void tablaEnProcesoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEnProcesoMouseClicked
        //Al precionar una fila de la tabla, muestra los datos en los text Fields y Combo box
        mostrarDatosEnProceso();
    }//GEN-LAST:event_tablaEnProcesoMouseClicked

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        //Escucha cada vez que se escribe en los text fields para aplicar el filtro 
        //Deseado por el usuario
        ObtenerData();
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void txtPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyReleased
        //Escucha cada vez que se escribe en los text fields para aplicar el filtro 
        //Deseado por el usuario
        ObtenerData();
    }//GEN-LAST:event_txtPrecioKeyReleased

    private void txtContactoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactoKeyReleased
        //Escucha cada vez que se escribe en los text fields para aplicar el filtro 
        //Deseado por el usuario
        ObtenerData();
    }//GEN-LAST:event_txtContactoKeyReleased

    private void tablaServiciosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaServiciosKeyPressed
        //UN escuchador que al seleccionar una fila y presionar la tecla SUPR (Suprimir)
        //Eliminar el registro
        //Parametros enviados para que el metodo sepa que eliminar
        eliminarDB(evt,tablaServicios,"SERVICIOS");
    }//GEN-LAST:event_tablaServiciosKeyPressed

    private void tablaProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductosKeyPressed
        //UN escuchador que al seleccionar una fila y presionar la tecla SUPR (Suprimir)
        //Eliminar el registro
        //Parametros enviados para que el metodo sepa que eliminar
        eliminarDB(evt,tablaProductos,"PRODUCTOS");
    }//GEN-LAST:event_tablaProductosKeyPressed

    private void txtNombreProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProductoKeyReleased
        //Escucha cada vez que se escribe en los text fields para aplicar el filtro 
        //Deseado por el usuario
        ObtenerDataProducto();
    }//GEN-LAST:event_txtNombreProductoKeyReleased

    private void txtMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyReleased
        //Escucha cada vez que se escribe en los text fields para aplicar el filtro 
        //Deseado por el usuario
        ObtenerDataProducto();
    }//GEN-LAST:event_txtMarcaKeyReleased

    private void txtPrecioProKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioProKeyReleased
        //Escucha cada vez que se escribe en los text fields para aplicar el filtro 
        //Deseado por el usuario
        ObtenerDataProducto();
    }//GEN-LAST:event_txtPrecioProKeyReleased

    private void txtStockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyReleased
        //Escucha cada vez que se escribe en los text fields para aplicar el filtro 
        //Deseado por el usuario
        ObtenerDataProducto();
    }//GEN-LAST:event_txtStockKeyReleased

    private void txtNombreProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProductoKeyTyped
        txtNombreProducto.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_txtNombreProductoKeyTyped

    private void txtMarcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyTyped
        txtMarca.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_txtMarcaKeyTyped

    private void txtPrecioProKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioProKeyTyped
        txtPrecioPro.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_txtPrecioProKeyTyped

    private void txtStockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyTyped
        txtStock.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_txtStockKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        txtNombre.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        txtDescripcion.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        txtPrecio.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtContactoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactoKeyTyped
        txtContacto.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_txtContactoKeyTyped

    private void cbMecanicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMecanicoActionPerformed
        try{
            String itemSeleccionado = cbMecanico.getSelectedItem().toString();
                if (itemSeleccionado.equals("Nuevo Mecanicus")) {
                    addMecanicus am = new addMecanicus();
                    am.setVisible(true);
                }else{
                    ObtenerDataEnProceso();
                }
        }catch(Exception e){
            System.out.println(e.getCause());
        }
    }//GEN-LAST:event_cbMecanicoActionPerformed

    private void txtNombreClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyReleased
        //Escucha cada vez que se escribe en los text fields para aplicar el filtro 
        //Deseado por el usuario
        ObtenerDataEnProceso();
    }//GEN-LAST:event_txtNombreClienteKeyReleased

    private void txtVehiculoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVehiculoKeyReleased
        //Escucha cada vez que se escribe en los text fields para aplicar el filtro 
        //Deseado por el usuario
        ObtenerDataEnProceso();
    }//GEN-LAST:event_txtVehiculoKeyReleased

    private void txtDescripcionProcesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionProcesoKeyTyped
        txtDescripcionProceso.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_txtDescripcionProcesoKeyTyped

    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped
        txtNombreCliente.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_txtNombreClienteKeyTyped

    private void txtVehiculoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVehiculoKeyTyped
        txtVehiculo.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_txtVehiculoKeyTyped

    private void tablaEnProcesoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaEnProcesoKeyPressed
        //UN escuchador que al seleccionar una fila y presionar la tecla SUPR (Suprimir)
        //Eliminar el registro
        //Parametros enviados para que el metodo sepa que eliminar
        eliminarDB(evt, tablaEnProceso, "ENPROCESO");
    }//GEN-LAST:event_tablaEnProcesoKeyPressed

    /**
     * Metodo que refresaca los datos de los mecanicos para listar el nuevo mecanico 
     * Se llama desde fuera de la clase donde esta creado
     */
    public void volver(){
        seleccion(jpInventario);
        crearTarea.setVisible(true);        
        datosEnProceso();
        btnModificarTrabajo.setVisible(false);        
        cbMecanico.removeAllItems();        
        cbMecanico.addItem("Mecanico a Cargo:");
        EnProcesoImplementacion ei = new EnProcesoImplementacion();
        ei.llenarComboBoxPuesto().forEach(dato -> cbMecanico.addItem(dato));
        cbMecanico.addItem("Nuevo Mecanicus");       
        
    }
    
    /**
     * Limpiar casillas de todas las pestañas
     */
    private void flashPoint(){
        //TextFields en pestaña servicios        
        txtNombre.setText("");
        txtPrecio.setText("");
        txtDescripcion.setText("");
        txtContacto.setText("");
        
        //TextFields en pestaña productos
        txtNombreProducto.setText("");       
        txtPrecioPro.setText("");
        txtMarca.setText("");
        txtStock.setText("");
        
        //TextFields en pestaña EnProceso
        txtDescripcionProceso.setText("");
        txtNombreCliente.setText("");
        txtVehiculo.setText("");
        cbMecanico.setSelectedIndex(0);
        
        
    }
    
    /**
     * Obtiene los datos y los envia al filtro 
     */
    private void ObtenerData(){
        String nombre = txtNombre.getText();
        String precio = txtPrecio.getText();
        String desc = txtDescripcion.getText();
        String contacto = txtContacto.getText();
        filtrarN(nombre,precio,desc,contacto);
    }
    
    
    /**
     * Obtiene los datos de productos y los envia al filtro 
     */
    private void ObtenerDataProducto(){
        String nombre = txtNombreProducto.getText();
        String marca = txtMarca.getText();
        String precio = txtPrecioPro.getText();
        String stock = txtStock.getText();
        filtrarProducto(nombre,marca,precio,stock);
    }
    
    /**
     * Obtiene los datos de En proceso y los envia al filtro 
     */
    private void ObtenerDataEnProceso(){
        String desc = txtDescripcionProceso.getText();
        String dueno = txtNombreCliente.getText();
        String vehiculo = txtVehiculo.getText();
        String mecanico="";
        if(!cbMecanico.getSelectedItem().toString().equals("Mecanico a Cargo:")){
            mecanico = separarCadena(String.valueOf(cbMecanico.getSelectedItem()),1);
        }        
        filtrarEnProceso(desc, vehiculo, dueno, mecanico);
    }
    
    /**
     * filtra resultados segun lo que escribe el ususario en los Text Fields 
     * @param nombre
     * @param precio
     * @param desc
     * @param contacto 
     */
    private void filtrarN(String nombre,String precio,String desc,String contacto){
        
        if(precio.equals("")||precio.isEmpty()){
            precio = "0";
        }
        
        if(nombre.isEmpty() || nombre.equals("") && 
                precio.isEmpty()||precio.equals("")&& 
                desc.isEmpty()||desc.equals("")&& 
                contacto.isEmpty()||contacto.equals("")){
            datosServicios();
            filtroResult = false;
            
        }
        
        
        
        servicioDAO sd= new servicioDAO(nombre,desc,Double.parseDouble(precio),contacto);
        ServicioImplementacion im = new ServicioImplementacion();
        
        LinkedList<servicioDAO> lk = new LinkedList<>();
        if(im.filtrar(sd).isEmpty()){            
            filtroResult = true;
            
        }else{
            tablaServicios.removeAll();           
            String columnas[] = {"ID", "Servicio","Descripcion","Precio","Contacto"};
            t1 = new DefaultTableModel(null, columnas);
            for(servicioDAO dat: im.filtrar(sd)){
                Object[] ayuda = new Object[5];
                ayuda[0] = dat.getId();
                ayuda[1] = dat.getNombreServicio();
                ayuda[2] = dat.getDescripcion();
                ayuda[3] = dat.getPrecio();
                ayuda[4] = dat.getContacto();
                t1.addRow(ayuda);            
            }
            /*
            Agrega un filtro a la tabla, lo que permite ordenarlos de 
            - Mayor a menos en caso de numeros y vicevesa
            - Orden alfabetico en caso de registros de texto
            Se ejecuta al dar click en el encabezado de la columna
            */            
            tablaServicios.setModel(t1);
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(t1);
            tablaServicios.setRowSorter(sorter);
        }
        
        
    }
    
    /**
     * filtra resultados segun lo que escribe el ususario en productos
     * @param nombre
     * @param marca
     * @param precio
     * @param stock 
     */
    private void filtrarProducto(String nombre, String marca, String precio, String stock) {
        if(precio.equals("")||precio.isEmpty()){
            precio = "0";
        }
        if(stock.equals("")||stock.isEmpty()){
            stock = "0";
        }
            
        if(nombre.isEmpty() || nombre.equals("") && 
                precio.isEmpty()||precio.equals("")&& 
                marca.isEmpty()||marca.equals("")&& 
                stock.isEmpty()||stock.equals("")){
            datosProductos();
            filtroResult = false;
            
        }
        
        productoDAO sd= new productoDAO(nombre, Integer.parseInt(stock),Double.parseDouble(precio), marca);
        ProductoImplementacion im = new ProductoImplementacion();
        
        LinkedList<productoDAO> lk = new LinkedList<>();
        if(im.filtrar(sd).isEmpty()){           
            filtroResult = true;            
        }else{
            tablaProductos.removeAll();                       
            String columnas[] = {"ID", "Producto","Marca","Precio","Existencias"};
            tProductos = new DefaultTableModel(null, columnas);
            for(productoDAO dat: im.filtrar(sd)){
                Object ayuda[] = new Object[5];
                ayuda[0] = dat.getIntID();
                ayuda[1] = dat.getStrnombreProducto();
                ayuda[2] = dat.getStrMarca();
                ayuda[3] = dat.getDblPrecioProducto();
                ayuda[4] = dat.getIntStock();
                tProductos.addRow(ayuda);
            }
            /*
            Agrega un filtro a la tabla, lo que permite ordenarlos de 
            - Mayor a menos en caso de numeros y vicevesa
            - Orden alfabetico en caso de registros de texto
            Se ejecuta al dar click en el encabezado de la columna
            */ 
            tablaProductos.setModel(tProductos);
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tProductos);
            tablaProductos.setRowSorter(sorter);
        }
    }
    
    /**
     * filtra resultados segun lo que escribe el ususario en EnProceso
     * @param desc
     * @param vehiculo
     * @param nombreDueno
     * @param mecanico 
     */
    private void filtrarEnProceso(String desc, String vehiculo, String nombreDueno, String mecanico){       
        if(desc.isEmpty() || desc.equals("") && 
                vehiculo.isEmpty()||vehiculo.equals("")&& 
                nombreDueno.isEmpty()||nombreDueno.equals("")&& 
                mecanico.isEmpty()||mecanico.equals("")){
            datosEnProceso();
            filtroResult = false;
            
        }
        
        EnProcesoDAO sd= new EnProcesoDAO(desc,nombreDueno,vehiculo,mecanico);        
        EnProcesoImplementacion im = new EnProcesoImplementacion();
        
        LinkedList<EnProcesoDAO> lk = new LinkedList<>();
        if(im.filtrar(sd).isEmpty()){           
            filtroResult = true;            
        }else{
            tablaEnProceso.removeAll();                       
            String columnas[] = {"ID", "Descripcion del Trabajo","Mecanico a Cargo","Dueño","Tipo de Vehiculo"};
            tEnProceso = new DefaultTableModel(null, columnas);
            for(EnProcesoDAO dat: im.filtrar(sd)){
                Object ayuda[] = new Object[5];
                ayuda[0] = dat.getIntID();
                ayuda[1] = dat.getStrDescripcionProceso();
                ayuda[2] = dat.getStrNombreMecanico();
                ayuda[3] = dat.getStrNombreDueno();
                ayuda[4] = dat.getStrVehiculo();
                tEnProceso.addRow(ayuda);
            }
            /*
            Agrega un filtro a la tabla, lo que permite ordenarlos de 
            - Mayor a menos en caso de numeros y vicevesa
            - Orden alfabetico en caso de registros de texto
            Se ejecuta al dar click en el encabezado de la columna
            */ 
            tablaEnProceso.setModel(tEnProceso);
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tEnProceso);
            tablaEnProceso.setRowSorter(sorter);
        }
    }
    
    /**
     * toma los datos de los text field y los conbvierte en un objeto tipo servicioDAO
     * @return servicioDAO
     */
    private servicioDAO obtenerObjeto(){
        String nombre = txtNombre.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        String desc = txtDescripcion.getText();
        String contacto = txtContacto.getText();
        
        return new servicioDAO(id,nombre,desc, precio, contacto);
    }
    
    /**
     * toma los datos de los text field y los conbvierte en un objeto tipo prodcutoDAO
     * @return productoDAO
     */
    private productoDAO obtenerObjetoProduto(){
        String nombre = txtNombreProducto.getText();
        String marca = txtMarca.getText();
        double precio = Double.parseDouble(txtPrecioPro.getText());
        int stock = Integer.parseInt(txtStock.getText());
        
        return new productoDAO(id,nombre,stock,precio,marca);
    }
    
    /**
     * toma los datos de los text field y los conbvierte en un objeto tipo EnProcesoDAO
     * @return EnProcesoDAO
     */
    private EnProcesoDAO obtenerObjetoEnProceso(){
        String desc = txtDescripcionProceso.getText();
        String dueno = txtNombreCliente.getText();
        String vehiculo = txtVehiculo.getText();
        int mecanico = Integer.parseInt(separarCadena(cbMecanico.getSelectedItem().toString(), 0).trim());
        
        return new EnProcesoDAO(id,desc,mecanico,dueno,vehiculo);
    }
    
    
    /**
     * Crea un nuevo servicio en la base de datos
     * tomando los datos de los text fields de la pestaña servicios
     */
    private void crearServicio(){
        ServicioImplementacion im = new ServicioImplementacion();
        String nombre = txtNombre.getText();
        String precio = txtPrecio.getText();
        String desc = txtDescripcion.getText();
        String contacto = txtContacto.getText();
        if(!(nombre.isEmpty()||nombre.equals(""))&&
                !(precio.isEmpty()||precio.equals(""))&&
                !(desc.isEmpty()||desc.equals(""))&&
                !(contacto.isEmpty()||contacto.equals(""))){
            im.crear(new servicioDAO(txtNombre.getText().trim(),txtDescripcion.getText().trim(),
                Double.parseDouble(txtPrecio.getText().trim()),txtContacto.getText().trim()));
        
            t1.fireTableDataChanged();
            tablaServicios.removeAll();
            flashPoint();
            filtrarN("","","","");
            filtroResult = false;
        }else{
            JOptionPane.showMessageDialog(null, "LLene todos los campos antes de continuar");                   
            Color ca = new Color(255,160,160);
            if(txtNombre.getText().equals("")){
                txtNombre.setBackground(ca);
            }
            if(txtPrecio.getText().equals("")){
                txtPrecio.setBackground(ca);
            }
            if(txtDescripcion.getText().equals("")){
                txtDescripcion.setBackground(ca);
            }
            if(txtContacto.getText().equals("")){
                txtContacto.setBackground(ca);
            }
        
        }
        
    }
    
   
    /**
     * crea un nuvo producto en la base de datos
     * tomando los datos de los text fields de la pestaña productos
     */
    private void crearProducto(){
        ProductoImplementacion im = new ProductoImplementacion();
        String nombre = txtNombreProducto.getText();
        String marca = txtMarca.getText();
        String precio = txtPrecioPro.getText();
        String stock = txtStock.getText();
        if(!(nombre.isEmpty()||nombre.equals(""))&&
                !(precio.isEmpty()||precio.equals(""))&&
                !(marca.isEmpty()||marca.equals(""))&&
                !(stock.isEmpty()||stock.equals(""))){
            im.crear(new productoDAO(nombre,Integer.parseInt(stock),
            Double.parseDouble(precio),marca));
        
            tProductos.fireTableDataChanged();
            tablaProductos.removeAll();
            flashPoint();
            filtrarProducto("","","","");
            filtroResult = false;
        }else{
            JOptionPane.showMessageDialog(null, "LLene todos los campos antes de continuar");                   
            Color ca = new Color(255,160,160);
            if(txtNombreProducto.getText().equals("")){
                txtNombreProducto.setBackground(ca);
            }
            if(txtMarca.getText().equals("")){
                txtMarca.setBackground(ca);
            }
            if(txtStock.getText().equals("")){
                txtStock.setBackground(ca);
            }
            if(txtPrecioPro.getText().equals("")){
                txtPrecioPro.setBackground(ca);
            }
        
        }
        
    }
    
    
    /**
     * crear una nueva tarea de proceso 
     * tomando los datos de los text fields de la pestaña EnProceso
     */
    private void crearEnProceso(){
        EnProcesoImplementacion im = new EnProcesoImplementacion();
        String desc = txtDescripcionProceso.getText();
        String dueno = txtNombreCliente.getText();
        String vehiculo = txtVehiculo.getText();
        String mecanico = separarCadena(String.valueOf(cbMecanico.getSelectedItem()),0);
        if(!(desc.isEmpty()||desc.equals(""))&&
                !(dueno.isEmpty()||dueno.equals(""))&&
                !(vehiculo.isEmpty()||vehiculo.equals(""))&&
                !(mecanico.isEmpty()||mecanico.equals(""))){
            im.crear(new EnProcesoDAO(desc,Integer.parseInt(mecanico),
            dueno,vehiculo));
        
            tEnProceso.fireTableDataChanged();
            tablaEnProceso.removeAll();
            flashPoint();
            filtrarEnProceso("","","","");
            filtroResult = false;
        }else{
            JOptionPane.showMessageDialog(null, "LLene todos los campos antes de continuar");                   
            Color ca = new Color(255,160,160);
            if(txtDescripcionProceso.getText().equals("")){
                txtDescripcionProceso.setBackground(ca);
            }
            if(txtNombreCliente.getText().equals("")){
                txtNombreCliente.setBackground(ca);
            }
            if(txtVehiculo.getText().equals("")){
                txtVehiculo.setBackground(ca);
            }
            
        
        }
        
    }
    
    /**
     * Separa una cadena en 2 partes
     * @param cadena
     * @param op
     * @return cadena
     */
    private String separarCadena(String cadena, int op){
        String[] partes = cadena.split(" - ");
        
        if (partes.length >= 2) {
            if(op==0){
                //Retorna la parte 1 de la cadena separada, en este caso, un numero
                return partes[0].trim(); 
            }else{
                //Retorna la parte 2 de la cadena separada, en este caso, un nombre de mecanico
                return partes[1].trim(); 
            }            
        }
        return "";
    }
    
    /**
     * Eliminar un registro de la base de datos
     * @param evt
     * @param tabla
     * @param pestana 
     */
    private void eliminarDB(KeyEvent evt,JTable tabla, String pestana){
        if(evt.getExtendedKeyCode() == KeyEvent.VK_DELETE){           
            int tempID = (int)tabla.getValueAt(tabla.getSelectedRow(), 0);
            int response = JOptionPane.showConfirmDialog(null,"¿Esta seguro de eliminar este registro?" ,"Alerta",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
            if(response == JOptionPane.YES_OPTION){
                String strResponse = JOptionPane.showInputDialog(null, "Ingrese su contraseña para confirmar", "Autenticacion", 0);              
                if(strResponse.equals("admin")){
                    boolean aux = false;
                    if(pestana.equals("SERVICIOS")){
                       ServicioImplementacion im = new ServicioImplementacion();
                       aux = im.eliminar(tempID); 
                    }else if(pestana.equals("PRODUCTOS")){
                       ProductoImplementacion im = new ProductoImplementacion();
                       aux = im.eliminar(tempID); 
                    }else if(pestana.equals("ENPROCESO")){
                        EnProcesoImplementacion im = new EnProcesoImplementacion();
                        aux = im.eliminar(tempID);
                    }                    
                    if(aux){
                        JOptionPane.showMessageDialog(null, "Eliminado Correctamente", "Succesful", JOptionPane.INFORMATION_MESSAGE);
                        if(pestana.equals("SERVICIOS")){
                            filtrarN("","","","");
                        }else if(pestana.equals("PRODUCTOS")){
                            filtrarProducto("","","","");
                        }else if(pestana.equals("ENPROCESO")){
                            filtrarEnProceso("","","","");
                        }                                   
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Error al eliminar", "FATAL ERROR", JOptionPane.ERROR_MESSAGE);
                    }                    
                }else{
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }              
            }
            
        }
    }
    
    
    /**
     * Modificar registro de la base de datos
     * @param <T>
     * @param pestana
     * @param t
     * @param btn
     * @param objeto 
     */
    private <T> void modificar(String pestana, DefaultTableModel t,JButton btn, T objeto){
        DAO im = null;
        if(pestana.equals("SERVICIO")){
            im = new ServicioImplementacion();
        }else if(pestana.equals("PRODUCTOS")){
            im = new ProductoImplementacion();
        }else if(pestana.equals("ENPROCESO")){
            im = new EnProcesoImplementacion();
        }
        
        im.modificar(objeto);
        t.fireTableDataChanged();
        btn.setVisible(false);
        flashPoint();
        
        if(pestana.equals("SERVICIO")){
            filtrarN("","","","");            
        }else if(pestana.equals("PRODUCTOS")){
            filtrarProducto("","","","");
        }else if(pestana.equals("ENPROCESO")){
            filtrarEnProceso("", "", "", "");
        }      
        
        btn.setVisible(false);
        
    }
    
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
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnModificarPro;
    private javax.swing.JButton btnModificarTrabajo;
    private javax.swing.JComboBox<String> cbMecanico;
    private javax.swing.JButton crearProducto;
    private javax.swing.JButton crearServicio;
    private javax.swing.JButton crearTarea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel jpInventario;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JPanel jpProductos;
    private javax.swing.JPanel jpServicios;
    private javax.swing.JMenuBar mbar;
    private javax.swing.JTable tablaEnProceso;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTable tablaServicios;
    private javax.swing.JTextField txtContacto;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDescripcionProceso;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtPrecioPro;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtVehiculo;
    // End of variables declaration//GEN-END:variables

    
}

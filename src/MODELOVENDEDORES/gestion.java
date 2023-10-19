package MODELOVENDEDORES;



import MODELO.conexion;
import MODELO.productoDAO;
import POO.clientesPOO;
import POO.hisventasPOO;
import POO.listarFacturasPOO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;
/**
 * 
 * @author rodri
 * Clase que gestiona todo lo relacionada a las interacciones de los usuarios que 
 * no soon administradores
 * 
 */
public class gestion{
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    conexion acceso = new conexion();

     /**
      * Lista los datos de los clientes de la tabla clientes mediante un filtro, 
      * mediante su nombre, nit, correo o genero
      * @param nombre
      * @param nit
      * @param correo
      * @param genero
      * @return LinkedList<clientesPOO> 
      */
    public LinkedList<clientesPOO> listarClientes(String nombre, String nit, String correo, String genero) {
        
        String sql = "SELECT * FROM clientes WHERE nombre LIKE '%"+nombre+"%' AND correo LIKE '%"+correo+"%' "
                + "AND nit LIKE '%"+nit+"%' AND genero LIKE '%"+genero+"%';";
        
      
        try {
            LinkedList<clientesPOO> listar = new LinkedList<>();
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();    
     
            while (rs.next()) {
                
                listar.add(new clientesPOO(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
                 
            }
            return listar;
   
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Busca un producto en especifico en la tabla productos basado en el codigo
     * recibido en los parametros
     * @param codigo
     * @return productoDAO 
     */
    public productoDAO obtenerPro(int codigo) {
        String sql = "select * from productos where idProducto= ?;";

        try {
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                productoDAO ah = new productoDAO(
                        rs.getInt("idProducto"),
                        rs.getString("nombreProducto"),
                        rs.getInt("stockProducto"),
                        rs.getDouble("precioProducto"),
                        rs.getString("marca"));
                return ah;
            } else {
              JOptionPane.showMessageDialog(null,"Algo ha salido mal");  
            }
            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Algo ha salido mal: \n"+e+" \n Contactese con el desarrollador");
        }
        return null;
    }

    /**
     * Lista todos los productos de la tabla productos mediante su codigo 
     * @param cod
     * @return LinkedList<productoDAO>
     */
    public LinkedList<productoDAO> listarProducto(int cod) {
        String sql = "select * from productos where idProducto= ?";
        
      
        try {
            LinkedList<productoDAO> listar = new LinkedList<>();
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
           ps.setInt(1, cod);
            rs = ps.executeQuery();    
     
            while (rs.next()) {
                
                listar.add(new productoDAO(
                        rs.getInt("idProducto"),
                        rs.getString("nombreProducto"),
                        rs.getInt("stockProducto"),
                        rs.getDouble("precioProducto"),
                        rs.getString("marca")));
                 return listar;
            }
            
   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Articulo no encontrado");
        }
        return null;
    }
   
   /**
    * crea una nueva venta que se guardara en la tabla hisventa
    * @param ah 
    */
    public void crearhisVentas(hisventasPOO ah) {
        
        String sql = "insert into hisventa(id_cliente,fecha,total,accion) values (?,?,?,?);";
        try{
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ah.getId_cliente());
            ps.setString(2, ah.getFecha());
            ps.setString(3, ah.getTotal());
            ps.setString(4, ah.getAccion());
            ps.executeUpdate();
        }catch(Exception e){JOptionPane.showMessageDialog(null,"Algo ha salido mal: \n"+e+" \n Contactese con el desarrollador");}
    }
    
    /**
     * Resta la cantidad ed productos que el usuario ha comprado a un producto 
     * en especifico
     * @param codigo
     * @param cantidad 
     */
    public void modificarProducto(int codigo, int cantidad) {
        String sql = "update productos set stockProducto=stockProducto-? WHERE idProducto =?;";
        try {
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            
            ps.setInt(2, codigo);
            ps.setInt(1, cantidad);
            ps.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Algo ha salido mal: \n"+e+" \n Contactese con el desarrollador");
        }
    }

    /**
     * Muestra un cliente en especifico basado en su numero de nit
     * @param nit
     * @return clientesPOO
     */
    public clientesPOO mostrarCliente(String nit) {
        String sql = "Select * from clientes where nit = ?";
        try{
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, nit);
            rs = ps.executeQuery();
            if(rs.next()){
                clientesPOO vp = new clientesPOO(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5));
                return vp;
            }
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, "ALgo salio mal: "+e);
        }
        return null;
    }

    /**
     * Lista todas las facturas creadas 
     * @return  LinkedList<listarFacturasPOO>
     */
    public LinkedList<listarFacturasPOO> listarFacturas() {
        String sql = "SELECT hisventa.no_factura,clientes.nit,clientes.nombre,hisventa.fecha,"
                + "hisventa.total,hisventa.accion FROM hisventa INNER JOIN clientes ON "
                + "hisventa.id_cliente = clientes.id_cliente;";
         try {
            LinkedList<listarFacturasPOO> listar = new LinkedList<>();
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();    
     
            while (rs.next()) {
                
                listar.add(new listarFacturasPOO(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getBytes(6)));
                 
            }
            return listar;
   
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
   
    /**
     * Crea un nuevo cliente con los atributos del objeto recibido por parametros
     * @param ah 
     */
    public void crearClientes(clientesPOO ah) {
        String sql = "insert into clientes(nombre,nit,correo,genero) values (?,?,?,?);";
        try{
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, ah.getNombre());
            ps.setInt(2, ah.getNit());
            ps.setString(3, ah.getCorreo());
            ps.setString(4, ah.getGenero());
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getCause());
        }finally{
            acceso.Desconectar();
        }
    }
     
     /**
      * Lista lkas facturas mediante un filtro
      * @param factura
      * @param nit
      * @param nombre
      * @param fecha
      * @return 
      */
    public LinkedList<listarFacturasPOO> listarfacturasU(String factura,String nit, String nombre, String fecha) {
        
        String sql = "SELECT hisventa.no_factura,clientes.nit,clientes.nombre,hisventa.fecha, "
                + "hisventa.total,hisventa.accion FROM hisventa INNER JOIN clientes ON hisventa.id_cliente ="
                + " clientes.id_cliente WHERE hisventa.no_factura LIKE '%"+factura+"%' AND clientes.nombre LIKE '%"+nombre+"%' AND "
                + "clientes.nit LIKE '%"+nit+"%' AND hisventa.fecha LIKE '%"+fecha+"%';";
        
      
        try {
            LinkedList<listarFacturasPOO> listar = new LinkedList<>();
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();    
     
            while (rs.next()) {
                
                listar.add(new listarFacturasPOO(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getBytes(6)));
                 
            }
            return listar;
   
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
        
}

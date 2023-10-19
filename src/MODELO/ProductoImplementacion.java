package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Esta clase permite la interaccion con metodos que interactuan con datos de la 
 * base de datos y la tabla Productos
 * Implementa la interfaz DAO<T>
 */
public class ProductoImplementacion implements DAO<productoDAO>{

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    conexion acceso = new conexion();
    
    /**
     * Metodo para listar datos de la tabla productos en ls DB
     * @return LinkedList<productoDAO> en caso de listar correctamente los datos
     * @return null en caso de no poder listarlos
     */
    @Override
    public LinkedList<productoDAO> listar() {
        String sql = "SELECT * FROM productos;";
        LinkedList<productoDAO> listar = new LinkedList<>();
        try {
            
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listar.add(new productoDAO(rs.getInt("idProducto"), 
                        rs.getString("nombreProducto"), 
                        rs.getInt("stockProducto"), 
                        rs.getDouble("precioProducto"),
                        rs.getString("marca")));
                
            }
            return listar;
        } catch (SQLException e) {
            System.out.println(e.getCause());
        }finally{
            acceso.Desconectar();
        }
        return null;
    }  

    /**
     * Crea un nuevo registro en la tabla productos tomando los datos del objeto 
     * recibido productoDAO
     * @param generico 
     */
    @Override
    public void crear(productoDAO generico) {
        String sql = "INSERT INTO productos(nombreProducto,marca,precioProducto,stockProducto) "
                + "VALUES (?,?,?,?);";
        try{
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, generico.getStrnombreProducto());
            ps.setString(2, generico.getStrMarca());
            ps.setDouble(3, generico.getDblPrecioProducto());
            ps.setInt(4, generico.getIntStock());
            ps.executeUpdate();
        }catch(SQLException e){        
            System.out.println(e.getCause());
        }finally{
            acceso.Desconectar();
        }
    }
    
    /**
     * Metodo que hace un filtro de datos utilizando la sentencia LIKE y 
     * los atributos del objeto de tipo productoDAO recibido
     * @param generico
     * @return LinkedList<productoDAO> en caso de ejecutar correctamente el filtro 
     * @return null en caso de no listarlos correctamente
     */
    @Override
    public LinkedList<productoDAO> filtrar(productoDAO generico) {
        String sql = "SELECT * FROM productos WHERE "
                + "nombreProducto LIKE '%"+generico.getStrnombreProducto()+"%' "
                + "AND precioProducto LIKE '%"+generico.getDblPrecioProducto()+"%' "
                + "AND marca LIKE '%"+generico.getStrMarca()+"%' "
                + "AND stockProducto LIKE '%"+generico.getIntStock()+"%';";
        try {
            LinkedList<productoDAO> listar = new LinkedList<>();
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();    
                      
            while(rs.next()){                                 
                listar.add(new productoDAO(
                        rs.getInt("idProducto"), 
                        rs.getString("nombreProducto"), 
                        rs.getInt("stockProducto"), 
                        rs.getDouble("precioProducto"),
                        rs.getString("marca")
                ));
            } 
                
            
            
            return listar;
   
        } catch (SQLException e) {
            System.out.println(e.getCause());
        }finally{
            acceso.Desconectar();
        }
        return null;
    }

    /**
     * Modifica un registro existente en la tabla productos de la DB
     * Toma los datos del objeto de tipo productoDAO recibido
     * @param generico 
     */
    @Override
    public void modificar(productoDAO generico) {
        String sql = "UPDATE productos set nombreProducto=?,"
                                        + "stockProducto=?,"
                                        + "precioProducto=?,"
                                        + "marca=? "
                                        + "WHERE idProducto =?";
        try{
            
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, generico.getStrnombreProducto());
            ps.setInt(2, generico.getIntStock());
            ps.setDouble(3, generico.getDblPrecioProducto());
            ps.setString(4, generico.getStrMarca());
            ps.setInt(5, generico.getIntID());
            ps.executeUpdate();
            
        }catch(SQLException e){
            //System.out.println(e.getCause().toString());
            System.out.println(e.getMessage());
        }finally{
            acceso.Desconectar();
        }
    }

    /**
     * Elimina un registro de la tabla productos con el id del parametro recibido
     * @param id
     * @return true en caso de borrarlo correctamente
     * @return false en caso de no poder borrar el dato
     */
    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM productos WHERE idProducto = ?";
        try{
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            
        }finally{
            acceso.Desconectar();
        }
        return false;
    }
    
}

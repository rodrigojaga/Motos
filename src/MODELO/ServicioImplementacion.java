
package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author rodri
 * Clase que interactua con la tabla servicios en la DB
 * Implementa la interfaz generica DAO<T> y le asigna el tipo de dato
 * ServicioDAO
 */
public class ServicioImplementacion implements DAO<servicioDAO>{
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    conexion acceso = new conexion();

    /**
     * Metodo que lista todo lo que hay en la tabla servicios
     * @return LinkedList<servicioDAO> en caso de listar los datos correctamente
     * @return null en caso de no poder listar los datos
     */
    @Override
    public LinkedList<servicioDAO> listar() {
        String sql = "select * from servicios";
        LinkedList<servicioDAO> listar = new LinkedList<>();
        try {
            
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listar.add(new servicioDAO(rs.getInt("idServicio"), 
                        rs.getString("nombreServicio"), 
                        rs.getString("Descripcion"), 
                        rs.getDouble("precioServicio"),
                        rs.getString("contacto")));
                
            }
            return listar;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            acceso.Desconectar();
        }
        return null;
    } 
    

    /**
     * Crea un nuevo registro en la tabla servicios tomando los datos del objeto
     * de tipo ServiciODAO que obtiene por parametros 
     * @param generico 
     */
    @Override
    public void crear(servicioDAO generico) {
        String sql = "insert into servicios(nombreServicio,precioServicio,Descripcion,contacto) values (?,?,?,?);";
        try{
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, generico.getNombreServicio());
            ps.setDouble(2, generico.getPrecio());
            ps.setString(3, generico.getDescripcion());
            ps.setString(4, generico.getContacto());
            ps.executeUpdate();
        }catch(Exception e){        
            System.out.println(e.getMessage());
        }finally{
            acceso.Desconectar();
        }
    }

    /**
     * Busca los datos en la tabla servicios en base a los atributos obtenidos del
     * objeto servicioDAO recibido en los parametros 
     * @param generico
     * @return LinkedList<servicioDAO>  en caso de realizar correctamente el filtro 
     * @return null en caso de no poder haber listado correctamente el filtro
     */
    @Override
    public LinkedList<servicioDAO> filtrar(servicioDAO generico) {
        String sql = "SELECT * FROM servicios WHERE "
                + "nombreServicio LIKE '%"+generico.getNombreServicio()+"%' "
                + "AND precioServicio LIKE '%"+generico.getPrecio()+"%' "
                + "AND Descripcion LIKE '%"+generico.getDescripcion()+"%' "
                + "AND contacto LIKE '%"+generico.getContacto()+"%';";
        try {
            LinkedList<servicioDAO> listar = new LinkedList<>();
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();    
                      
            while(rs.next()){                                 
                listar.add(new servicioDAO(rs.getInt("idServicio"), 
                        rs.getString("nombreServicio"), 
                        rs.getString("Descripcion"), 
                        rs.getDouble("precioServicio"),
                        rs.getString("contacto")));
            } 
                
            
            
            return listar;
   
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            acceso.Desconectar();
        }
        return null;
    }

    /**
     * Modifica un registro en la tabla servicios poniendole los atributos del objeto
     * de tipo servicioDAO obtenido en los parametros 
     * @param generico 
     */
    @Override
    public void modificar(servicioDAO generico) {
        
        String sql = "UPDATE servicios set nombreServicio=?,precioServicio=?,Descripcion=?,contacto=? WHERE idServicio=?";
        try{
            
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, generico.getNombreServicio());
            ps.setDouble(2, generico.getPrecio());
            ps.setString(3, generico.getDescripcion());
            ps.setString(4, generico.getContacto());
            ps.setInt(5, generico.getId());
            ps.executeUpdate();
            
        }catch(SQLException e){
            //System.out.println(e.getCause().toString());
            System.out.println(e.getMessage());
        }finally{
            acceso.Desconectar();
        }
    }

    /**
     * Elimina un registro de la tbala servicios en base al id recibido en los parametros
     * @param id
     * @return true en caso de eliminarlo correctamente
     * @return false en caso de no eliminarlo
     */
    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM servicios WHERE idServicio = ?";
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

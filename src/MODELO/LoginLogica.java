
package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author rodri
 * Clase que tiene los metodos que interatuan con la tabla Usuarios en la DB
 * Clase hecha para la interaccion del Login con la base de datos
 */
public class LoginLogica {
    //Clases de biblioteca de conexion a base de datos
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    //clase conexion
    conexion acceso = new conexion();
    
    /**
     * Recibe usuario y contraseña del login para verificar su existencia en la base de datos
     * @param usuario
     * @param password
     * en caso de encontrar un usuario existente, devuelve un objeto tipo usuarioDAO
     * @return usuariioDAO 
     * si no encuentra nada @return usuariioDAO
     * 
     * hecho de esta manera para evitar inyecciones de SQL
     */
    public usuarioDAO login(String usuario, String password) {                
        
        String sql = "SELECT * from usuarios WHERE nombreUsuario=? and contrasena = ?";
        try{
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()){
                usuarioDAO vp = new usuarioDAO(rs.getString("nombreUsuario"),rs.getString("contrasena"),rs.getInt("idRol"));
                System.out.println(vp.toString());
                return vp;
            }
        }catch(SQLException e){
            System.out.println(e.getCause()+" qwqwqw");
        }finally{
            acceso.Desconectar();
        }
        return null;
    }
    
    /**
     * Crea un usuario nuevo en la tabla usuarios de la DB
     * @param usuario
     * c  true en caso de haber hecho correctamente la creacion de un 
     * nuevo usuario en la aplicacion
     * @return false en caso de no poder crear el usuario
     */
    public boolean registrar(usuarioDAO usuario) {
        String sql = "insert into usuarios(nombreUsuario,contrasena,idRol) values (?,?,?);";
        try{
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombreUsuario());            
            ps.setString(2, usuario.getPasswordUsuario());
            ps.setInt(3, usuario.getRol());
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            acceso.Desconectar();
        }
        return false;
    }
    
    
}

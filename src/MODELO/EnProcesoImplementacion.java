/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author rodri
 * Clase que hace todas las interacciones con la tabla "enproceso" de la base de 
 * datos
 * 
 * Implementa la Interfaz DAO<T> sustituyendo el dato generico T por la clase 
 * EnProcesoDAO, lo que hace que los objetos y listas que devuelven los metodos
 * sean de tipo EnProcesoDAO
 */
public class EnProcesoImplementacion implements DAO<EnProcesoDAO>{
    //Clase de biblioteca
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    //Clase conexion, hace la conexion a la base de datos
    conexion acceso = new conexion();
    
    /**
     * Metodo que Lista los datos de la tabla enproceso y hace un INNER JOIN con
     * la tabla usuarios uniendolos mediante la clave Foranea idUsuario
     * @return LinkedList<EnProcesoDAO>
     * @return null en caso de que no pueda listar correctamente los datos
     */
    @Override    
    public LinkedList<EnProcesoDAO> listar() {
        String sql = "SELECT e.*, u.nombreUsuario\n" +
                    "FROM enproceso AS e\n" +
                    "INNER JOIN usuarios AS u ON e.idUsuario = u.idUsuario;";
        LinkedList<EnProcesoDAO> listar = new LinkedList<>();
        try {
            
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                EnProcesoDAO sa = new EnProcesoDAO(
                        rs.getInt("idProceso"), 
                        rs.getString("descripcionProceso"), 
                        rs.getInt("idUsuario"),                        
                        rs.getString("nombreDueno"),
                        rs.getString("vehiculo"),
                        rs.getString("nombreUsuario"));
                listar.add(sa);                                                
                
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
     * Crea un registro en la tabla enproceso 
     * 
     * Utiliza un objeto de tipo EnProcesoDAO para tomar los datos que enviara a
     * la base de datos
     * @param generico 
     */
    @Override
    public void crear(EnProcesoDAO generico) {
        String sql = "INSERT INTO enproceso(descripcionProceso,idUsuario,nombreDueno,vehiculo) "
                + "VALUES (?,?,?,?);";
        try{
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, generico.getStrDescripcionProceso());
            ps.setInt(2, generico.getIntIdUsuario());
            ps.setString(3, generico.getStrNombreDueno());
            ps.setString(4, generico.getStrVehiculo());
            ps.executeUpdate();
        }catch(SQLException e){        
            System.out.println(e.getCause());
        }finally{
            acceso.Desconectar();
        }
    }

    /**
     * Hace una peteticion select con un INNER JOIN a las tablas enproceso y 
     * usuarios. Va filtrando los resultados dependiendo del objeto recibido
     * @param generico
     * 
     * @return LinkedList<EnProcesoDAO> en caso de haber implementado correctamente
     * el metodo 
     * @return null en caso de que no haya podido ejecutar correctamente la peticion
     */
    @Override
    public LinkedList<EnProcesoDAO> filtrar(EnProcesoDAO generico) {
        String sql = "SELECT e.*, u.nombreUsuario\n" +
                    "FROM enproceso AS e\n" +
                    "INNER JOIN usuarios AS u ON e.idUsuario = u.idUsuario\n" +
                    "WHERE e.descripcionProceso LIKE '%"+generico.getStrDescripcionProceso()+"%' \n" +
                    "   AND u.nombreUsuario LIKE '%"+generico.getStrNombreMecanico()+"%'\n" +
                    "   AND e.nombreDueno LIKE '%"+generico.getStrNombreDueno()+"%'\n" +
                    "   AND e.vehiculo LIKE '%"+generico.getStrVehiculo()+"%';";
        try{
            LinkedList<EnProcesoDAO> listar = new LinkedList<>();
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);           
            rs = ps.executeQuery();
            
            while(rs.next()){                                 
                listar.add(new EnProcesoDAO(
                        rs.getInt("idProceso"), 
                        rs.getString("descripcionProceso"), 
                        rs.getInt("idUsuario"), 
                        rs.getString("nombreDueno"),
                        rs.getString("vehiculo"),
                        rs.getString("nombreUsuario")
                ));
            } 
            return listar;
        }catch(SQLException e){ 
            System.out.println(e.getCause());
        }finally{
            acceso.Desconectar();
        }
        return null;
    }

    /**
     * Metodo que modifica los datos de un registro en la tabla enproceso  reeemplazandolos
     * con los datos del objeto EnProcesoDAO 
     * @param generico 
     */
    @Override
    public void modificar(EnProcesoDAO generico) {
        String sql = "UPDATE enproceso set "
                + "descripcionProceso=?, "
                + "idUsuario=?,"
                + "nombreDueno=?, "
                + "vehiculo =?"
                + "WHERE idProceso = ?";
        try{            
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, generico.getStrDescripcionProceso());
            ps.setInt(2, generico.getIntIdUsuario());
            ps.setString(3, generico.getStrNombreDueno());
            ps.setString(4, generico.getStrVehiculo());
            ps.setInt(5, generico.getIntID());
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getCause());
        }finally{
            acceso.Desconectar();
        }
    }

    /**
     * 
     * @param id
     * @return true en caso de haber eliminado correctamente el registro
     * @return false en caso de no haberlo borrado 
     */
    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM enproceso WHERE idProceso=?";
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
    
    /**
     * Metodo para llenar el combobox de Mecanicos 
     * @return LinkedList<String> con el id del Mecanico y nombre del mecanico 
     * @return null en caso de no poder listar correctamente los datos 
     */
    public LinkedList<String> llenarComboBoxPuesto(){
        String sql = "SELECT idUsuario, nombreUsuario FROM usuarios WHERE idRol = 3;";
        LinkedList<String> listar = new LinkedList<>();
        try {
            
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                
                listar.add(rs.getInt("idUsuario")+" - "+rs.getString("nombreUsuario"));                                                
                
            }
            return listar;
        } catch (SQLException e) {
            System.out.println(e.getCause());
        }finally{
            acceso.Desconectar();
        }
        return null;
    }
    
    
    
}

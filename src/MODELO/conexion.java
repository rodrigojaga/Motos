package MODELO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class conexion {
   
    Connection con;
    String url="jdbc:mysql://localhost:3306/motos";
    String user="root";
    String pass="";
    /**
     * Realiza la conexion con la base de datos 
     * @return Connection en caso de poder conectarse a la base de datos
     */
    public Connection Conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {
            System.out.println("error:");
            System.out.println(e);
        }
        return con;
    }
    
    /**
     * Cierra la conexion previamente abierta con la base de datos 
     */
    public void Desconectar() {
        try {
            if (con != null) {
                con.close();
                //System.out.println("Desconexión exitosa.");
            }
        } catch (SQLException e) {
            //System.out.println("Error al desconectar: " + e.getMessage());
        }
    }
}

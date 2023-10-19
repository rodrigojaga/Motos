
package MODELO;
import java.util.LinkedList;

/**
 *
 * @author rodri
 * Interfaz que utiliza datos genericos para adaptarse de mejor manera
 * a las clases que necesiten implementarla
 * Ejemplo: public class Ejemplo<ClaseConPOO>
 */
public interface DAO<T> {
    /*
    Devuelve Una LinkedList de tipo generico, que luego sera cambiada en la implementacion
    Este metodo se utiliza para listar los datos obtenidos de la base de datos
    */
    LinkedList<T> listar ();   
    /*
    Utiliza un tipo de dato generico como un objeto, sera cambiado con la implementacion
    Este sera el metodo para crear un nuevo registro en la base de datos
    */   
    void crear(T generico);
    /*
    Devuelve otra LinkedList generica la cual es el resultado de un 
    filtro invocado por el usuario
    */
    LinkedList<T> filtrar(T generico);
    //Metodo que se utilizara para modificar un dato en la base de datos
    void modificar(T generico);
    /*
    Metodo que se utilizara para eliminar registros de la base de datos
    Devuelve un booleano para saber si la peticion se realizo correctamenre
    */
    boolean eliminar(int id);
}

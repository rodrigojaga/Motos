
package MODELO;

/**
 *
 * @author rodri
 * Clase con programacion orientada a Objetos 
 */
public class EnProcesoDAO {
    
    private int intID;
    private String strDescripcionProceso;
    private int intIdUsuario;
    private String strNombreDueno;
    private String strVehiculo;
    
    private String strNombreMecanico;

    public EnProcesoDAO(int intID, String strDescripcionProceso, int intIdUsuario, String strNombreDueno, String strVehiculo) {
        this.intID = intID;
        this.strDescripcionProceso = strDescripcionProceso;
        this.intIdUsuario = intIdUsuario;
        this.strNombreDueno = strNombreDueno;
        this.strVehiculo = strVehiculo;
    }

    public EnProcesoDAO(String strDescripcionProceso, int intIdUsuario, String strNombreDueno, String strVehiculo) {
        this.strDescripcionProceso = strDescripcionProceso;
        this.intIdUsuario = intIdUsuario;
        this.strNombreDueno = strNombreDueno;
        this.strVehiculo = strVehiculo;
    }

    public EnProcesoDAO(int intID, String strDescripcionProceso, int intIdUsuario, String strNombreDueno, String strVehiculo, String strNombreMecanico) {
        this.intID = intID;
        this.strDescripcionProceso = strDescripcionProceso;
        this.intIdUsuario = intIdUsuario;
        this.strNombreDueno = strNombreDueno;
        this.strVehiculo = strVehiculo;
        this.strNombreMecanico = strNombreMecanico;
    }

    public EnProcesoDAO(String strDescripcionProceso, String strNombreDueno, String strVehiculo, String strNombreMecanico) {
        this.strDescripcionProceso = strDescripcionProceso;        
        this.strNombreDueno = strNombreDueno;
        this.strVehiculo = strVehiculo;
        this.strNombreMecanico = strNombreMecanico;
    }
    
//Inicio Metodos get usados para obtener un atributo de un Objeto
    
    public String getStrNombreMecanico() {
        return strNombreMecanico;
    }

    public int getIntID() {
        return intID;
    }
    
     public String getStrDescripcionProceso() {
        return strDescripcionProceso;
    }
     
    public int getIntIdUsuario() {
        return intIdUsuario;
    }
     
    public String getStrNombreDueno() {
        return strNombreDueno;
    }
    
    public String getStrVehiculo() {
        return strVehiculo;
    }
   
//FIn metodos get
    

//Inicio metodos set utilizados para "ponerle" un valor a un atributo de un objeto
    
    public void setStrNombreMecanico(String strNombreMecanico) {
        this.strNombreMecanico = strNombreMecanico;
    }
           
    public void setIntID(int intID) {
        this.intID = intID;
    }

    public void setStrDescripcionProceso(String strDescripcionProceso) {
        this.strDescripcionProceso = strDescripcionProceso;
    }    

    public void setIntIdUsuario(int intIdUsuario) {
        this.intIdUsuario = intIdUsuario;
    }    

    public void setStrNombreDueno(String strNombreDueno) {
        this.strNombreDueno = strNombreDueno;
    }    

    public void setStrVehiculo(String strVehiculo) {
        this.strVehiculo = strVehiculo;
    }

//Fin metodos get
    
    //Sobreescritura del metodo toString() que hace mas facil la lectura de Objetos
    @Override
    public String toString() {
        return "EnProcesoDAO{" + "intID=" + intID + ", strDescripcionProceso=" + strDescripcionProceso + ", intIdUsuario=" + intIdUsuario + ", strNombreDueno=" + strNombreDueno + ", strVehiculo=" + strVehiculo + ", strNombreMecanico=" + strNombreMecanico + '}';
    }            
    
}

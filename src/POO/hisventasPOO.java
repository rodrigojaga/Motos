package POO;

/**
 * 
 * @author rodri
 * Clase con atributos necesarios para crear registros en la tabla hisventas
 * =====POO=====
 */
public class hisventasPOO {
    private int no_factura;   
    private int id_cliente;
    private String fecha;
    private String total;
    private String accion;   
    private int nit;
    private String nombreC;

    public hisventasPOO(int id_cliente, String fecha, String total, String accion) {
        this.id_cliente = id_cliente;
        this.fecha = fecha;
        this.total = total;
        this.accion = accion;
    }

    public hisventasPOO(int id_cliente, String fecha, String total) {
        this.id_cliente = id_cliente;
        this.fecha = fecha;
        this.total = total;
    }

//  
    

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

 

    
    
    
    
    public hisventasPOO() {
    }

  
   
   

   
    
    
    
    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }
   
    
    public int getNo_factura() {
        return no_factura;
    }

    public void setNo_factura(int no_factura) {
        this.no_factura = no_factura;
    }

  

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

   
    
    
}

package POO;
/**
 * 
 * @author rodri
 * Clase con atributos necesarios para listar las facturas
 * =====POO=====
 */
public class listarFacturasPOO {
    private int no_factura;
    private int nit;
    private String nombreC;
    private String fecha;
    private String total;
    private byte[] accion;

    public listarFacturasPOO(int no_factura, int nit, String nombreC, String fecha, String total, byte[] accion) {
        this.no_factura = no_factura;
        this.nit = nit;
        this.nombreC = nombreC;
        this.fecha = fecha;
        this.total = total;
        this.accion = accion;
    }

    
    
    
    
    public byte[] getAccion() {
        return accion;
    }

    public void setAccion(byte[] accion) {
        this.accion = accion;
    }

    public int getNo_factura() {
        return no_factura;
    }

    public void setNo_factura(int no_factura) {
        this.no_factura = no_factura;
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

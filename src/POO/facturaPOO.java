package POO;
/**
 * 
 * @author rodri
 * Clase necesario para crear facturas 
 * =====POO=====
 */
public class facturaPOO {
    private int cajaAtendio;
    private String noFactura;
    private String nit;
    private String nombreCliente;
    private String fechaV;
    private String totalCompra;

    private String CodigoProducto; 
    private String NombreProducto; 
    private String CantidadProducto; 
    private String PrecioProducto; 
    private String SubtotalProducto;

    public facturaPOO(int cajaAtendio, String noFactura, String nit, String nombreCliente, String fechaV, String totalCompra) {
        this.cajaAtendio = cajaAtendio;
        this.noFactura = noFactura;
        this.nit = nit;
        this.nombreCliente = nombreCliente;
        this.fechaV = fechaV;
        this.totalCompra = totalCompra;
    }

    public facturaPOO(String CodigoProducto, String NombreProducto, String CantidadProducto, String PrecioProducto, String SubtotalProducto) {
        this.CodigoProducto = CodigoProducto;
        this.NombreProducto = NombreProducto;
        this.CantidadProducto = CantidadProducto;
        this.PrecioProducto = PrecioProducto;
        this.SubtotalProducto = SubtotalProducto;
    }
    
    

    public int getCajaAtendio() {
        return cajaAtendio;
    }

    public void setCajaAtendio(int cajaAtendio) {
        this.cajaAtendio = cajaAtendio;
    }

    public String getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(String noFactura) {
        this.noFactura = noFactura;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getFechaV() {
        return fechaV;
    }

    public void setFechaV(String fechaV) {
        this.fechaV = fechaV;
    }

    public String getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(String totalCompra) {
        this.totalCompra = totalCompra;
    }

    public String getCodigoProducto() {
        return CodigoProducto;
    }

    public void setCodigoProducto(String CodigoProducto) {
        this.CodigoProducto = CodigoProducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    public String getCantidadProducto() {
        return CantidadProducto;
    }

    public void setCantidadProducto(String CantidadProducto) {
        this.CantidadProducto = CantidadProducto;
    }

    public String getPrecioProducto() {
        return PrecioProducto;
    }

    public void setPrecioProducto(String PrecioProducto) {
        this.PrecioProducto = PrecioProducto;
    }

    public String getSubtotalProducto() {
        return SubtotalProducto;
    }

    public void setSubtotalProducto(String SubtotalProducto) {
        this.SubtotalProducto = SubtotalProducto;
    }

  

}

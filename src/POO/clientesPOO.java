package POO;
public class clientesPOO {

    private int id;

    private String nombre;

    private int nit;

    private String correo;
    
    private String genero;

    
    //clientes
    public clientesPOO(int id, String nombre, int nit, String correo, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.nit = nit;
        this.correo = correo;
        this.genero = genero;
    }
    
    //clientes 
    public clientesPOO(String nombre, int nit, String correo, String genero) {
        this.nombre = nombre;
        this.nit = nit;
        this.correo = correo;
        this.genero = genero;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    
    
    
}

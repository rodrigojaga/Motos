/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

/**
 * Atributos necesarios para los metodos que utilizan productoDAO
 * Objeto producto
 *  ========POO========
 */
public class productoDAO {
    private int intID;
    private String strnombreProducto;
    private int intStock;
    private double dblPrecioProducto;
    private String strMarca;
    
    /**
     * Constructor que contiene Id, nombre, Stock, Marca y Precio
     * @param intID
     * @param strnombreProducto
     * @param intStock
     * @param dblPrecioProducto 
     * @param strMarca
     */
    public productoDAO(int intID, String strnombreProducto, int intStock, double dblPrecioProducto, String strMarca) {
        this.intID = intID;
        this.strnombreProducto = strnombreProducto;
        this.intStock = intStock;
        this.dblPrecioProducto = dblPrecioProducto;
        this.strMarca = strMarca;
    }       

    /**
     * Constructor que contiene nombre, Stock, Marca y Precio
     * @param strnombreProducto
     * @param intStock
     * @param dblPrecioProducto 
     * @param strMarca

     */        
    public productoDAO(String strnombreProducto, int intStock, double dblPrecioProducto, String strMarca) {
        this.strnombreProducto = strnombreProducto;
        this.intStock = intStock;
        this.dblPrecioProducto = dblPrecioProducto;
        this.strMarca = strMarca;
    }

//Inicio de metodos get que permiten obtener los atributos de un objeto
    public String getStrMarca() {
        return strMarca;
    }

    public int getIntID() {
        return intID;
    }
    
    public String getStrnombreProducto() {
        return strnombreProducto;
    }
    
    public int getIntStock() {
        return intStock;
    }
    
    public double getDblPrecioProducto() {
        return dblPrecioProducto;
    }
    
//Fin metodos get

//Inicio metodos set, que permiten "ponerle" un atributo diferente a un objeto 
    public void setIntID(int intID) {
        this.intID = intID;
    }    

    public void setStrnombreProducto(String strnombreProducto) {
        this.strnombreProducto = strnombreProducto;
    }

    public void setIntStock(int intStock) {
        this.intStock = intStock;
    }
    
    public void setDblPrecioProducto(double dblPrecioProducto) {
        this.dblPrecioProducto = dblPrecioProducto;
    }    
    
    public void setStrMarca(String strMarca) {
        this.strMarca = strMarca;
    }
//Fin metodos set 
    
}

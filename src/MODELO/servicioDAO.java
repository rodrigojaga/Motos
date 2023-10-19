/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

/**
 *
 * @author rodri
 * Atributos necesarios para los casos en donde se utiliza la clase servicioDAO
 *  ========POO========
 */
public class servicioDAO {
    private int id;
    private String nombreServicio;
    private String descripcion;
    private double precio;
    private String contacto;

    /**
     * COnstructor con id, nombreServicio, descripcion, precio
     * @param id
     * @param nombreServicio
     * @param descripcion
     * @param precio 
     */
    public servicioDAO(int id, String nombreServicio, String descripcion, double precio) {
        this.id = id;
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    /**
     * Constructor con id, nombreServicio. descripcion, precio, contacto
     * @param id
     * @param nombreServicio
     * @param descripcion
     * @param precio
     * @param contacto 
     */
    public servicioDAO(int id, String nombreServicio, String descripcion, double precio, String contacto) {
        this.id = id;
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.precio = precio;
        this.contacto = contacto;
    }

    /**
     * constructor con nombreServicio. descripcion, precio, contacto
     * @param nombreServicio
     * @param descripcion
     * @param precio
     * @param contacto 
     */
    public servicioDAO(String nombreServicio, String descripcion, double precio, String contacto) {
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.precio = precio;
        this.contacto = contacto;
    }
    
//Inicio metodos get que permiten obtener los atributos de un objeto 
    public String getContacto() {
        return contacto;
    }
        

    public double getPrecio() {
        return precio;
    }
    

    public int getId() {
        return id;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }
//Fin metodos get
    
}

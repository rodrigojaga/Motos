/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

/**
 *
 * @author rodri
 * Atributos necesarios para las clases que utilizan la clase usuarioDAO 
 * ========POO========
 */
public class usuarioDAO {
    private String nombreUsuario;
    private String passwordUsuario;
    private int rol;
    private int ID;

    /**
     * constructor con nombreUsuario, passwordUsuario, rol
     * @param nombreUsuario
     * @param passwordUsuario
     * @param rol 
     */
    public usuarioDAO(String nombreUsuario, String passwordUsuario, int rol) {
        this.nombreUsuario = nombreUsuario;
        this.passwordUsuario = passwordUsuario;
        this.rol = rol;
    }
    
    /**
     * constructor con nombreUsuario y passwordUsuario
     * @param nombreUsuario
     * @param passwordUsuario 
     */
    public usuarioDAO(String nombreUsuario, String passwordUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.passwordUsuario = passwordUsuario;
    }
    
    /**
     * COnstructor con nombreUsuario,passwordUsuario,rol, ID
     * @param nombreUsuario
     * @param passwordUsuario
     * @param rol
     * @param ID 
     */
    public usuarioDAO(String nombreUsuario, String passwordUsuario, int rol, int ID) {
        this.nombreUsuario = nombreUsuario;
        this.passwordUsuario = passwordUsuario;
        this.rol = rol;
        this.ID = ID;
    }

//Inicio metodos get que permiten obtener los atributos de un objeto 
    public int getID() {
        return ID;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public int getRol() {
        return rol;
    }    

//FIn metodos get
    
    /**
     * Metodo que faciliata la lectura de un objeto
     * @return String 
     */
    @Override
    public String toString() {
        return "usuarioDAO{" + "nombreUsuario=" + nombreUsuario + ", passwordUsuario=" + passwordUsuario + ", rol=" + rol + '}';
    }
    
    
}

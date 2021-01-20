/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Rod
 */
public class Usuario extends Modelo<Usuario>{
    protected int id;
    protected String nombre;
    protected String correo;
    protected String password;
    
    public Usuario(){
        //Definimos un arreglo que contiene el nombre de las columnas que existen en nuestra BD
        String[] fillable = {"id", "nombre", "email", "correo", "password"};
        this.setTable("usuarios");
        this.setFillable(fillable);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFillable(String[] fillable) {
        this.fillable = fillable;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Rod
 */
public class Cliente extends Modelo<Cliente> {
    protected int id;
    protected String nombre;
    protected String apellidos;
    protected String correo;
    protected String telefono;
    
    public Cliente(){
        String[] fillable = {"id", "nombre", "apellidos", "correo", "telefono"};
        this.setTable("clientes");
        this.setFillable(fillable);
    }
}

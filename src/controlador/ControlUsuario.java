/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;
import vista.LoginVista;

/**
 *
 * @author Rod
 */
public class ControlUsuario implements ActionListener {
    private Usuario usuario;
    private LoginVista loginVista;
   
    
    public ControlUsuario(LoginVista loginVista){
        this.usuario = new Usuario();
        this.loginVista = loginVista;
        
        this.loginVista.getConfirmar().addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent evento){
        if(this.loginVista.getConfirmar() == evento.getSource()){
            System.out.println(this.loginVista.getCorreo().getText());
            System.out.println(this.loginVista.getPassword().getText());
            try {
                ArrayList<Usuario> usuarios = this.usuario.where("correo", loginVista.getCorreo().getText()).where("password", loginVista.getPassword().getText()).get();
                if(usuarios.toArray().length > 0){
                    this.usuario = usuarios.get(0);
                    System.out.println("Usuario encontrado");
                    System.out.println("Nombre: " + this.usuario.getNombre());
                    System.out.println("Correo: " + this.usuario.getCorreo());
                }else{
                    System.out.println("Usuario invalido");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControlUsuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(ControlUsuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(ControlUsuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ControlUsuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ControlUsuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ControlUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

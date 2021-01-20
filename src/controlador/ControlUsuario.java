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
import javax.swing.JLabel;
import modelo.Usuario;
import vista.HomeVista;
import vista.LoginVista;

/**
 *
 * @author Rod
 */
public class ControlUsuario implements ActionListener {
    private Usuario usuario;
    private LoginVista loginVista;
    private HomeVista homeVista;
   
    
    public ControlUsuario(LoginVista loginVista, HomeVista homeVista){
        this.usuario = new Usuario();
        this.loginVista = loginVista;
        this.homeVista = homeVista;
        
        this.loginVista.getConfirmar().addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent evento){
        if(this.loginVista.getConfirmar() == evento.getSource()){
            String correo = this.loginVista.getCorreo().getText();
            String password = this.loginVista.getPassword().getText();
            try {
                if(this.usuario.authenticate(correo, password) != null){
                    //Logeado
                    this.loginVista.setVisible(false); //Ocultamos vista para mostrar la vista que require autenticacion
                    this.homeVista.setLocationRelativeTo(this.loginVista);
                    this.homeVista.setVisible(true);
                    //Actualizamos los textos que necesitan mostrar los datos del usuario
                    this.mostrarInfoUsuario();
                }else{
                    this.loginVista.getAlert().setTitle("Error");
                    this.loginVista.getAlert().setLocationRelativeTo(null);
                    this.loginVista.getAlert().setVisible(true);
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
    
    public void mostrarInfoUsuario(){
        this.homeVista.getNombre_usuario().setText(this.usuario.getNombre());
    }
}

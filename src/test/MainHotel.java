/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controlador.ControlUsuario;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import modelo.Usuario;
import vista.LoginVista;

/**
 *
 * @author Rod
 */
public class MainHotel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        LoginVista loginVista = new LoginVista();
        ControlUsuario controlUsuario = new ControlUsuario(loginVista);
        loginVista.setVisible(true);


//          Usuario usuario = new Usuario();
//          usuario = usuario.find(1);
//          usuario.setNombre("Rodrigo");
//          usuario.setCorreo("admin@email.com");
//          usuario.setPassword("123456");
//          usuario.update();
//          
//          System.out.println(usuario.getNombre());
        
//        Usuario user = new Usuario();
//        ArrayList<Usuario> usuarios = user.where("correo", "admin@email.com").get();
//        //Object[] array = usuarios.toArray(); 
//        for(int i = 0; i < usuarios.toArray().length; i++){
//            System.out.println(usuarios.get(i).getNombre());
//            System.out.println(usuarios.get(i).getCorreo());
//            System.out.println(usuarios.get(i).getPassword());
//        }
//        System.out.println(user.getNombre());
//        System.out.println(user.getCorreo());
//        System.out.println(user.getPassword());
//        System.out.println(user.getCreated_at());
//        System.out.println(user.getUpdated_at());
    }
    
}

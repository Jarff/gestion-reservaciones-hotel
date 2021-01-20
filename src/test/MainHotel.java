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
import vista.HomeVista;
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
        HomeVista homeVista = new HomeVista();
        ControlUsuario controlUsuario = new ControlUsuario(loginVista, homeVista);
        loginVista.setLocationRelativeTo(null);
        loginVista.setVisible(true);
    }
    
}

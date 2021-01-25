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
import modelo.Usuario;
import vista.CheckInVista;
/**
 *
 * @author PC
 */
public class ControlCheckIn implements ActionListener{
    private Cliente modeloCliente;
    private CheckInVista vistaCheckIn;
    
    public ControlCheckIn(Usuario modeloUsuario, CheckInVista vistaCheckin){
        this.modeloCliente = modeloCliente;
        this.vistaCheckIn = vistaCheckIn;
        
        this.vistaCheckIn.getjButton1().addActionListener(this);
        this.vistaCheckIn.getjButton2().addActionListener(this);
        this.vistaCheckIn.getjButton3().addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent evento){
        //Hacer Check In
        if(vistaCheckIn.getjButton1() == evento.getSource()){
            
        }
        
        //Consultar reservaciones
        if(vistaCheckIn.getjButton2() == evento.getSource()){
            ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
            
            String habitacion;
            String cliente;
            String fechaEntrada;
            String fechaSalida;
            double total;
            
        if(vistaCheckIn.getjButton3() == evento.getSource()){
            
        }    
        }
    }
}

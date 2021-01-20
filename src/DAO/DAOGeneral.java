/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author lbojor
 */
public abstract class DAOGeneral<T> {
    private static String host="localhost:3306";
    private static String bd="hotel_db";
    private static String login="root";
    private static String password="root";
    private boolean cargadoDriver;

    public DAOGeneral() {
        cargarDriver();
   }

    public void cargarDriver(){
        try {
            if ( !cargadoDriver ) {
                //Class.forName("org.postgresql.Driver");
                Class.forName("com.mysql.jdbc.Driver");
                cargadoDriver = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConeccion(String host, String bd, String login, String password){
        Connection conexion=null;
        String urlConexion = "jdbc:mysql://"+ host +"/" + bd +
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try {
            conexion=DriverManager.getConnection(urlConexion, login, password);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }

     public static Connection getConeccion(){
        return getConeccion(host, bd, login, password );
    }

    public void cerrarConeccion(Connection con){
        try {
            if ( con != null )
                if ( !con.isClosed() )    // Si no esta cerrada, se cierra
                    con.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }


    public abstract int create(ArrayList values)throws SQLException;

    public abstract int destroy()throws SQLException;

    //public abstract int update(ArrayList values)throws SQLException;

    //public abstract ArrayList<T> get()throws SQLException;
    
    //public static abstract Object find(int id)throws SQLException;

      public String getHost() {
        return host;
    }

    public String getBd() {
        return bd;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCargadoDriver() {
        return cargadoDriver;
    } 
}    
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import DAO.DAOGeneral;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Rod
 */
public class Modelo<T> extends DAOGeneral<Modelo> {
    protected String table;
    protected String[] fillable;
    protected String created_at;
    protected String updated_at;
    private String query;
    private boolean firstWhere = true;
    

    public void setFillable(String[] fillable) {
        this.fillable = fillable;
    }
    
    public void setTable(String table){
        this.table = table;
        this.resetQuery();
    }
    
    private void resetQuery(){
        this.query = "SELECT * FROM " + this.table;
        this.firstWhere = true;
    }
    
    /*
    Esta funcion busca una fila mediante su id
    */
    public T find(int id) throws SQLException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException{
        Connection con = getConeccion();
        String query = this.query + " WHERE id=" + id;
        Statement sentencia = con.createStatement();
        ResultSet rs = sentencia.executeQuery(query);
        while(rs.next()){
             //Obtenemos los atributos definidos en la clase hija
            Field[] attributes = getAttributes();
            for(int i = 0; i < attributes.length; i++) {
                if(this.contains(this.fillable, attributes[i].getName())){
                    if(attributes[i].getType() == String.class){
                        attributes[i].set(this, this.rowValue(rs, attributes[i].getName()));
                    }else if(attributes[i].getType() == int.class){
                        attributes[i].set(this, rs.getInt(attributes[i].getName()));
                    }
                }
            }
            this.setCreated_at(rs.getString("created_at"));
            this.setUpdated_at(rs.getString("updated_at"));
            
        }
        sentencia.close();
        this.cerrarConeccion(con);
        this.resetQuery();
        return (T) this;
    }
    
    /*
    Esta funcion ayuda a concatenar condiciones a consultas 
    */
    public T where(String condition, String value) throws SQLException{
        if(!this.firstWhere){
            this.query += " AND " + condition + "=" + "'" + value + "'";
        }else{
            this.query += " WHERE " + condition + "=" + "'" + value + "'";
        }
        this.firstWhere = false;
        return (T) this;
    }
    
    /*
    Ejecuta la consulta con todo y las concatenaciones que haya tenido
    */
    public ArrayList<T> get() throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        ArrayList<T> lista = new ArrayList<T>();
        Connection con = this.getConeccion();
        Statement sentencia = con.createStatement();
        ResultSet rs = sentencia.executeQuery(this.query);
        while(rs.next()){
            Constructor<?> cons;
            cons = this.getClass().getConstructors()[0];
            T obj = (T) cons.newInstance();
            //Recuperamos los metodos de la clase
            Field[] attributes = obj.getClass().getDeclaredFields();
            for(int i = 0; i < attributes.length; i++) {
                if(this.contains(this.fillable, attributes[i].getName())){
                    if(attributes[i].getType() == String.class){
                        attributes[i].set(obj, this.rowValue(rs, attributes[i].getName()));
                    }else if(attributes[i].getType() == int.class){
                        attributes[i].set(this, rs.getInt(attributes[i].getName()));
                    }
                }
                //TODO: Mostrar los atributos de la clase padre
//                if(attributes[i].getName() == "created_at"){
//                    attributes[i].set(obj, this.rowValue(rs, "created_at"));
//                }
//                if(attributes[i].getName() == "updated_at"){
//                    attributes[i].set(obj, this.rowValue(rs, "created_at"));
//                }
            }
            lista.add(obj);
        }
        sentencia.close();
        this.cerrarConeccion(con);
        this.resetQuery();
        return lista;
    }
    
    public int update() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SQLException{
        int numFilas = 0;
        Connection con = this.getConeccion();
        String query = "UPDATE " + this.table + " SET ";
        
        Field[] attributes = getAttributes();
        for(int i = 0; i < attributes.length; i++) {
            //Ignoramos el campo id, pues ese no lo necesitamos actualizar
            if(attributes[i].getName().toString() != "id"){
                if(this.contains(this.fillable, attributes[i].getName())){
                    query += attributes[i].getName().toString() + " = '" + attributes[i].get(this).toString() + "'";
                    //Si se cumple la condicion se agrega un espacio a la consulta de lo contrario agregaremos una coma, de tal forma que quede:
                    // UPDATE FROM nombre_tabla SET columna = "valor", columna_2 = "valor_2";
                    query += (i + 1) == attributes.length ? " " : " , ";
                }
            }
        }
        //Especificamos la fila que deseamos actualizar
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        query += ", updated_at = '" + timestamp.toString() + "' WHERE id = '"+this.getClass().getDeclaredField("id").get(this).toString() + "'";
        System.out.println(query);
        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(query);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }
    
    public int destroy(){
        return 0;
    }
    
    public int create(ArrayList values){
        return 1;
    }
    
    
    public Field[] getAttributes() {
        Field[] attributes = this.getClass().getDeclaredFields();
        return attributes;
    }
    
    public boolean contains(String[] array, final String v) {
        boolean contains = false;
        for(int i = 0; i < array.length; i++){
            if(array[i] == v){
                contains = true;
            }
        }
        return contains;
    }
    
    public T rowValue(ResultSet rs, String columnName) throws SQLException{
        T value = null;
        switch(rs.getType()){
            case 1003:
                value = (T) rs.getString(columnName);
            break;
        }
        return value;
    }
    
    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

}

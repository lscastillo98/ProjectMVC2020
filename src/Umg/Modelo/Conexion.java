/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Umg.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * @author @lscas98
 */
public class Conexion {
    String url="jdbc:mysql://localhost:3306/bd_mvc?useTimezone=true&serverTimezone=UTC";
    String user="root";
    String pass="";
    Connection con;
    
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            System.out.println("ERROR EN LA CONEXION:  "+ex+"\n");
        }
    return con;
    }
    
    

}

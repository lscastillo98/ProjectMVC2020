/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Umg.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author @lscas98
 */
public class PersonaDAO {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Persona p = new Persona();
    
    /*LISTAR PERSONAS*/
    public List ToList(){
    List<Persona> datos = new ArrayList<>();
        try {
            con= conectar.getConnection();
            ps= con.prepareStatement("SELECT * FROM persona");
            rs=ps.executeQuery();
            while(rs.next()){
            Persona p = new Persona();
            p.setId(rs.getInt(1));
            p.setNombre(rs.getString(2));
            p.setCorreo(rs.getString(3));
            p.setTelefono(rs.getString(4));
            datos.add(p);
            }
        } catch (Exception exListar) {
            System.out.println("ERROR AL EN LISTAR "+ exListar);
        }
    return datos;
    }
    
    /*AGREGAR PERSONA*/
    public int Add(Persona per){
    int r=0;
    String sql="INSERT into persona(Nombres,Correo,Telefono)VALUES(?,?,?)";
        try {
            con= conectar.getConnection();
            ps= con.prepareStatement(sql);
            ps.setString(1,per.getNombre());
            ps.setString(2,per.getCorreo());
            ps.setString(3,per.getTelefono());
            r=ps.executeUpdate();
            if(r==1){return 1;}
            else{return 0;}
        } catch (Exception exAdd) {
            System.out.println("ERROR AL EN LISTAR "+ exAdd);
        }
    return r;
    }
    
    /*ACTUALIZAR PERSONA*/
    public int Update(Persona per){
    int r=0;
    String sql="UPDATE persona SET Nombres=?,Correo=?,Telefono=? WHERE Id=?";
        try {
            con= conectar.getConnection();
            ps= con.prepareStatement(sql);
            ps.setString(1,per.getNombre());
            ps.setString(2,per.getCorreo());
            ps.setString(3,per.getTelefono());
            ps.setInt(4,per.getId());
            r=ps.executeUpdate();
            if(r==1){return 1;}
            else{return 0;}
        } catch (Exception exUp) {
            System.out.println("ERROR AL EN LISTAR "+ exUp);
        }
    return r;
    }

    /*DELETE PERSONA*/
    public int Delete(int id){
    int r=0;
    String sql="DELETE FROM persona WHERE Id="+id;
        try {
            con= conectar.getConnection();
            ps= con.prepareStatement(sql);
            r=ps.executeUpdate();
        } catch (Exception exDe) {
            System.out.println("ERROR AL EN LISTAR "+ exDe);
        }
    return r;
    }
}

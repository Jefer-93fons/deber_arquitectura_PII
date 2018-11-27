/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.hilos;

import ec.edu.espe.arquitectura.deber_arquitectura_software.StarterPostgres;
import ec.edu.espe.arquitectura.modelo.rgCivil;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jefferson
 */
public class CiudadanoHilosPostgres implements Runnable {
    
    private rgCivil ciudadano;
    private List<rgCivil> ciudadanos;
    private int rango;
    private String servidor;
    private String usuarioDB;
    private String passwordDB;
    protected Connection conn = null;
    protected Statement stmt = null;
    private String rutaArchivo;
    String[] parts;
    
    public CiudadanoHilosPostgres(List<rgCivil> ciudadanos, int rango){
        this.ciudadanos = ciudadanos;
        this.rango = rango;
    }
    
    
    public CiudadanoHilosPostgres(){
        
    }

    @Override
    public void run() {
        
        servidor = "jdbc:postgresql://localhost:5432/deber";
        usuarioDB = "espe";
        passwordDB = "espe";
        //passwordDB = "root";
        try{
            this.conn = DriverManager.getConnection(servidor, usuarioDB, passwordDB);
            this.stmt = conn.createStatement();
        }catch(Exception e){
            System.out.println("Conexiion error:"+e.toString());
        }
        
        try {
            
            for(int i=rango-100000; i <rango; i++){
                try{
                    PreparedStatement st = conn.prepareStatement("INSERT INTO registrocivil (CEDULA,APELLIDO,NOMBRE,FECHANACIMIENTO,CODPROVINCIA,GENERO,ESTADOCIVIL) VALUES (?,?,?,?,?,?,?)");   
                    st.setString(1,ciudadanos.get(i).getCedu());
                    st.setString(2,ciudadanos.get(i).getApel());
                    st.setString(3,ciudadanos.get(i).getNomb());
                    st.setString(4,ciudadanos.get(i).getFecN());
                    st.setString(5,ciudadanos.get(i).getCodP());
                    st.setString(6,ciudadanos.get(i).getGene());
                    st.setString(7,ciudadanos.get(i).getEstC());
            
                st.executeUpdate();
                }catch(Exception e){
                    
                }
                
            }
            conn.close();
            
        } catch (SQLException ex) {
          //  Logger.getLogger(CiudadanoHilosPostgres.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    public List<rgCivil> ObtenerRegistros(){
        List<rgCivil> lst = new ArrayList();
        
        servidor = "jdbc:postgresql://localhost:5432/deber";
        usuarioDB = "espe";
        passwordDB = "espe";
        
        try{
            this.conn = DriverManager.getConnection(servidor, usuarioDB, passwordDB);
            this.stmt = conn.createStatement();
        }catch(Exception e){
            System.out.println("Conexiion error:"+e.toString());
        }
        
        try {
            PreparedStatement csl = conn.prepareStatement("SELECT CEDULA, APELLIDO, NOMBRE, FECHANACIMIENTO, CODPROVINCIA, GENERO, ESTADOCIVIL FROM REGISTROCIVIL;");
            ResultSet result1 = csl.executeQuery();
            while(result1.next()){
                rgCivil rcg = new rgCivil();
                rcg.setCedu(result1.getString("CEDULA"));
                rcg.setApel(result1.getString("APELLIDO"));
                rcg.setNomb(result1.getString("NOMBRE"));
                rcg.setFecN(result1.getString("FECHANACIMIENTO"));
                rcg.setCodP(result1.getString("CODPROVINCIA"));
                rcg.setGene(result1.getString("GENERO"));
                rcg.setEstC(result1.getString("ESTADOCIVIL"));
                lst.add(rcg);
            }
            
        } catch (SQLException ex) {
            //Logger.getLogger(StarterPostgres.class.getName()).log(Level.SEVERE, null, ex);
        } 
       
        return lst;
    }
    
}

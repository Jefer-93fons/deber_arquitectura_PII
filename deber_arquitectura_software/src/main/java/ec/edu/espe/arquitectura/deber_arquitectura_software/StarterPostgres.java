/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.deber_arquitectura_software;

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
 * @author jorge
 */
public class StarterPostgres implements Runnable {
    private String rutaArchivo;
    //private String linea;
    String[] parts;
    String cedu;
    String apel;
    String nomb;
    String fecN;
    String codP;
    String gene;
    String estC;
    String servidor;
    String usuarioDB;
    String passwordDB;
    protected Connection conn = null;
    protected Statement stmt = null;

    public StarterPostgres() {
        
    }

    public void conectar() throws Exception {
       // Class.forName("com.mysql.jdbc.Driver");
       // servidor = "jdbc:mysql://localhost:3306/deber";
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
        
        System.out.println("Conexion establecida");
    }

    @Override
    public void run() {
        int i = 0;
        List<rgCivil> lst = new ArrayList();
        
        
        try {
            this.rutaArchivo = "/Users/jefferson/Documents/Espe_2018/registroCivil.txt";
            //this.rutaArchivo = "c:/registroCivil.txt";
            conectar();
        } catch (Exception ex) {
            Logger.getLogger(StarterPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            FileReader fr = new FileReader(rutaArchivo);
            BufferedReader entradaArchivo = new BufferedReader(fr);
            String linea;
            
            while ((linea = entradaArchivo.readLine()) != null) {
                rgCivil registro = new rgCivil();
                parts = linea.split(",");
                cedu = parts[0];
                apel = parts[1];
                nomb = parts[2];
                fecN = parts[3];
                codP = parts[4];
                gene = parts[5];
                estC = parts[6];
                registro.setCedu(cedu);
                registro.setApel(apel);
                registro.setNomb(nomb);
                registro.setFecN(fecN);
                registro.setCodP(codP);
                registro.setGene(gene);
                registro.setEstC(estC);
                
                lst.add(registro);
                
                
//            PreparedStatement st = conn.prepareStatement("INSERT INTO registrocivil VALUES (?,?,?,?,?,?,?)");   
//                st.setString(1, cedu);
//                st.setString(2, apel);
//                st.setString(3, nomb);
//                st.setString(4, fecN);
//                st.setString(5, codP);
//                st.setString(6, gene);
//                st.setString(7, estC);
//                st.executeUpdate();
               // Thread.sleep((long) 0.0000000000001);
               // linea = entradaArchivo.readLine();
            }
            
            for (rgCivil r : lst){
                PreparedStatement st = conn.prepareStatement("INSERT INTO registrocivil (CEDULA,APELLIDO,NOMBRE,FECHANACIMIENTO,CODPROVINCIA,GENERO,ESTADOCIVIL) VALUES (?,?,?,?,?,?,?)");   
                st.setString(1, r.getCedu());
                st.setString(2, r.getApel());
                st.setString(3, r.getNomb());
                st.setString(4, r.getFecN());
                st.setString(5, r.getCodP());
                st.setString(6, r.getGene());
                st.setString(7, r.getEstC());
                st.executeUpdate();
               // System.out.println(r.getNomb());
            }
            conn.close();
        } catch (IOException ex) {
            System.out.println("Error en la apertura del archivo " + ex.toString());
        } catch (SQLException ex) {
            Logger.getLogger(StarterPostgres.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public List<rgCivil> ObtenerRegistros(){
    //public List<rgCivil> ObtenerRegistros(){
        List<rgCivil> lst = new ArrayList();
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
        Logger.getLogger(StarterPostgres.class.getName()).log(Level.SEVERE, null, ex);
        } 
       
        return lst;
    }
    
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        StarterMariadb rg = new StarterMariadb();
//        try {
//            rg.conectar();
//            rg.leerArchivo();
//            rg.ObtenerRegistros();
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//        System.out.println("Ok!");
//    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.deber_arquitectura_software;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jorge
 */
public class StarterMariadb {
    private String rutaArchivo;
    private String linea;
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

    public void conectar() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        servidor = "jdbc:mysql://localhost:3306/deber";
        usuarioDB = "root";
        passwordDB = "root";
        try{
            this.conn = DriverManager.getConnection(servidor, usuarioDB, passwordDB);
            this.stmt = conn.createStatement();
        }catch(Exception e){
            System.out.println("Conexiion error:"+e.toString());
        }
        
        System.out.println("Conexion establecida");
    }

    public void leerArchivo() throws SQLException {
        try {
            this.rutaArchivo = "c:/tmp/registroCivil.txt";
            FileReader fr = new FileReader(rutaArchivo);
            BufferedReader entradaArchivo = new BufferedReader(fr);
            linea = entradaArchivo.readLine();
            while (linea != null) {
                parts = linea.split(",");
                cedu = parts[0];
                apel = parts[1];
                nomb = parts[2];
                fecN = parts[3];
                codP = parts[4];
                gene = parts[5];
                estC = parts[6];
                PreparedStatement st = conn.prepareStatement("INSERT INTO registrocivil VALUES (?,?,?,?,?,?,?)");
                st.setString(1, cedu);
                st.setString(2, apel);
                st.setString(3, nomb);
                st.setString(4, fecN);
                st.setString(5, codP);
                st.setString(6, gene);
                st.setString(7, estC);
                st.executeUpdate();

                linea = entradaArchivo.readLine();
            }
            conn.close();
        } catch (IOException ex) {
            System.out.println("Error en la apertura del archivo " + ex.toString());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        StarterMariadb rg = new StarterMariadb();
        try {
            rg.conectar();
            rg.leerArchivo();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("Ok!");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.deber_arquitectura_software;

import ec.edu.espe.arquitectura.hilos.CiudadanoHilosPostgres;
import ec.edu.espe.arquitectura.modelo.rgCivil;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class StarterPostgresql {
    private rgCivil ciudadano;
    private List<rgCivil> ciudadanos;
    private String rutaArchivo;
    String[] parts;
    
    public StarterPostgresql() {
    }
    
    
    public void iniciarIngreso (){
        
        //System.out.println("Conectandose a Postgres");

        List<rgCivil> lst = new ArrayList();
        
        try {
            this.rutaArchivo = "/Users/jefferson/Documents/Espe_2018/data.txt";
        } catch (Exception ex) {
            Logger.getLogger(CiudadanoHilosPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            FileReader fr = new FileReader(rutaArchivo);
            BufferedReader entradaArchivo = new BufferedReader(fr);
            String linea;
            
            while ((linea = entradaArchivo.readLine()) != null) {
                rgCivil registro = new rgCivil();
                parts = linea.split(",");
                registro.setCedu(parts[0]);
                registro.setApel(parts[1]);
                registro.setNomb(parts[2]);
                registro.setFecN(parts[3]);
                registro.setCodP(parts[4]);
                registro.setGene(parts[5]);
                registro.setEstC(parts[6]);
                
                lst.add(registro);
                
            }
        } catch (IOException ex) {
            System.out.println("Error en la apertura del archivo " + ex.toString());
        } 
        
        
        Runnable r1 = new CiudadanoHilosPostgres(lst,100000);
        Runnable r2 = new CiudadanoHilosPostgres(lst,200000);
        Runnable r3 = new CiudadanoHilosPostgres(lst,300000);
        Runnable r4 = new CiudadanoHilosPostgres(lst,400000);
        Runnable r5 = new CiudadanoHilosPostgres(lst,500000);
        Runnable r6 = new CiudadanoHilosPostgres(lst,600000);
        Runnable r7 = new CiudadanoHilosPostgres(lst,700000);
        Runnable r8 = new CiudadanoHilosPostgres(lst,800000);
        Runnable r9 = new CiudadanoHilosPostgres(lst,900000);
        Runnable r10 = new CiudadanoHilosPostgres(lst,1000000);

        
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        Thread t4 = new Thread(r4);
        Thread t5 = new Thread(r5);
        Thread t6 = new Thread(r6);
        Thread t7 = new Thread(r7);
        Thread t8 = new Thread(r8);
        Thread t9 = new Thread(r9);
        Thread t10 = new Thread(r10);
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        
        do {
            try{
                Thread.sleep(100);
            }catch (InterruptedException exc){
                 //System.out.println("Hilo principal interrumpido.");
            }
        } while (
                t1.isAlive()||
                t2.isAlive()||
                t3.isAlive()||
                t4.isAlive()||
                t5.isAlive()||
                t6.isAlive()||
                t7.isAlive()||
                t8.isAlive()||
                t9.isAlive()||
                t10.isAlive()
                );
    }
    
    public List<rgCivil> ObtenerRegistros(){
        CiudadanoHilosPostgres datosBase = new CiudadanoHilosPostgres();
        return datosBase.ObtenerRegistros();
    }
}

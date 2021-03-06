/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.deber_arquitectura_software;

import ec.edu.espe.arquitectura.hilos.CiudadanoHilosMongo;
import ec.edu.espe.arquitectura.modelo.CiudadanoMongo;
import java.util.List;

/**
 *
 * @author jefferson
 */
public class StarterMongo {

    List<CiudadanoMongo> ciudadanos;
    
    public StarterMongo(List<CiudadanoMongo> ciudadanos) {
        this.ciudadanos = ciudadanos;
    }
    
    
    public void iniciarIngreso (){
        
        Runnable r1 = new CiudadanoHilosMongo(ciudadanos, 100000);
        Runnable r2 = new CiudadanoHilosMongo(ciudadanos, 200000);
        Runnable r3 = new CiudadanoHilosMongo(ciudadanos, 300000);
        Runnable r4 = new CiudadanoHilosMongo(ciudadanos, 400000);
        Runnable r5 = new CiudadanoHilosMongo(ciudadanos, 500000);
        Runnable r6 = new CiudadanoHilosMongo(ciudadanos, 600000);
        Runnable r7 = new CiudadanoHilosMongo(ciudadanos, 700000);
        Runnable r8 = new CiudadanoHilosMongo(ciudadanos, 800000);
        Runnable r9 = new CiudadanoHilosMongo(ciudadanos, 900000);
        Runnable r10 = new CiudadanoHilosMongo(ciudadanos, 1000000);
        
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
}

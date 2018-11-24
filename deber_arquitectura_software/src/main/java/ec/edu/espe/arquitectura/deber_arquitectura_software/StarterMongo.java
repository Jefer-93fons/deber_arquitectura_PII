/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.deber_arquitectura_software;

import ec.edu.espe.arquitectura.hilos.CiudadanoHilos;
import ec.edu.espe.arquitectura.modelo.CiudadanoMongo;

/**
 *
 * @author jefferson
 */
public class StarterMongo {
    public static void main (String args[]){
        CiudadanoMongo ciudadano = new CiudadanoMongo();
        
        Runnable r = new CiudadanoHilos(ciudadano);
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        Thread t4 = new Thread(r);
        Thread t5 = new Thread(r);
        Thread t6 = new Thread(r);
        Thread t7 = new Thread(r);
        Thread t8 = new Thread(r);
        Thread t9 = new Thread(r);
        Thread t10 = new Thread(r);
        
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


        
//        System.out.println("Hola Taller Mongo");
//        System.out.println("Conectandose a Mongo");
//        Morphia morphia = new Morphia();
//        morphia.mapPackage("ec.edu.espe.arquitectura.deber_bases.mongo.modelo");
//        
//        Datastore ds = morphia.createDatastore(new MongoClient(), "local_base_arquitectura");
//        
//        System.out.println("Conexión Establecida");
//       
//        for(int i=0; i <10000; i++){
//            Ciudadano ciud = new Ciudadano();
//            ciud.setCedula("1718258393");
//            ciud.setNombre("Jefferson");
//            ciud.setApellido("Fonseca");
//            ciud.setFechaNacimiento(new Date());
//
//            ds.save(ciud);
//        }
    }
}

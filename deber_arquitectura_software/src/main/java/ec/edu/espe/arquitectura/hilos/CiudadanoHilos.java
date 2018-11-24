/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.hilos;

import com.mongodb.MongoClient;
import ec.edu.espe.arquitectura.modelo.CiudadanoMongo;
import java.util.Date;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author jefferson
 */
public class CiudadanoHilos implements Runnable{
    
    private CiudadanoMongo ciudadano;
    
    public CiudadanoHilos(CiudadanoMongo ciudadanoclass){
        this.ciudadano = ciudadano;
    }

    @Override
    public void run() {
        System.out.println("Hola Taller Mongo");
        System.out.println("Conectandose a Mongo");
        Morphia morphia = new Morphia();
        morphia.mapPackage("ec.edu.espe.arquitectura.deber_bases.mongo.modelo");
        
        Datastore ds = morphia.createDatastore(new MongoClient(), "local_base_arquitectura");
        
        System.out.println("Conexión Establecida");
       
        for(int i=0; i <1000; i++){
            CiudadanoMongo ciud = new CiudadanoMongo();
            ciud.setCedula("1718258393");
            ciud.setNombre("Jefferson");
            ciud.setApellido("Fonseca");
            ciud.setFechaNacimiento(new Date());

            ds.save(ciud);
        }
    }
    
}

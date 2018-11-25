/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.deber_arquitectura_software;

import com.mongodb.MongoClient;
import ec.edu.espe.arquitectura.modelo.CiudadanoMongo;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author jefferson
 */
public class StarterPrincipal {
    public static void main (String args[]){
        System.out.println("Ya termino");
        int i=0;
        
        Morphia morphia = new Morphia();
        morphia.mapPackage("ec.edu.espe.arquitectura.taller.mongo.modelo");
        
        Datastore ds = morphia.createDatastore(new MongoClient(), "local_base_arquitectura");
        
        List<CiudadanoMongo> ciudadanos = ds.createQuery(CiudadanoMongo.class).asList();
        
        for (CiudadanoMongo u: ciudadanos){
            //System.out.println(u);
            i++;
        }
        System.out.println("Total: "+ i);
        
        
        StarterMongo starmongo =  new StarterMongo(ciudadanos);
        starmongo.main();
        
    }
}

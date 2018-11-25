/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.deber_arquitectura_software;

import com.mongodb.MongoClient;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import ec.edu.espe.arquitectura.modelo.CiudadanoMongo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author jefferson
 */
public class StarterPrincipal {
    public static void main (String args[]){
        
        int i=0;
        
        Morphia morphia = new Morphia();
        morphia.mapPackage("ec.edu.espe.arquitectura.taller.mongo.modelo");
        
        Datastore ds = morphia.createDatastore(new MongoClient(), "local_base_arquitectura");
        
        //Datastore d2s = morphia.createDatastore(new MongoClient(), "local_base_arquitectura");
        
        List<CiudadanoMongo> ciudadanos = ds.createQuery(CiudadanoMongo.class).asList();
        
        for (CiudadanoMongo u: ciudadanos){
            i++;
        }
        System.out.println("Total: "+ i);
        
//        for (CiudadanoMongo u: ciudadanos){
//            CiudadanoMongo ciud = new CiudadanoMongo();
//            ciud.setCedula(u.getCedula());
//            ciud.setNombre(u.getNombre());
//            ciud.setApellido(u.getApellido());
//            ciud.setFechaNacimiento(u.getFechaNacimiento());
//            ds.save(ciud);
//            
//        }
        
        StarterMongo starmongo =  new StarterMongo(ciudadanos);
        starmongo.main();
        System.out.println("Ya termino");
        
        StarterMariadb starmariadb = new StarterMariadb();
        try {
            starmariadb.conectar();
            starmariadb.leerArchivo();
        } catch (Exception ex) {
            Logger.getLogger(StarterPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

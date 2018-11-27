/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.hilos;

import com.mongodb.MongoClient;
import ec.edu.espe.arquitectura.modelo.CiudadanoMongo;
import java.util.Date;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author jefferson
 */
public class CiudadanoHilosMongo implements Runnable{
    
    private CiudadanoMongo ciudadano;
    private List<CiudadanoMongo> ciudadanos;
    private int rango;
    
    public CiudadanoHilosMongo(List<CiudadanoMongo> ciudadanos, int rango){
        this.ciudadanos = ciudadanos;
        this.rango = rango;
    }

    @Override
    public void run() {
        //System.out.println("Conectandose a Mongo");
        Morphia morphia = new Morphia();
        morphia.mapPackage("ec.edu.espe.arquitectura.deber_bases.mongo.modelo");
        
        Datastore ds = morphia.createDatastore(new MongoClient(), "local_base_arquitectura");
        
        System.out.println("Conexión Establecida");
       
        for(int i=rango-100000; i <rango; i++){
            try{
                CiudadanoMongo ciud = new CiudadanoMongo();
                ciud.setCedula(ciudadanos.get(i).getCedula());
                ciud.setApellido(ciudadanos.get(i).getApellido());
                ciud.setNombre(ciudadanos.get(i).getNombre());
                ciud.setFechaNacimiento(ciudadanos.get(i).getFechaNacimiento());
                ciud.setCodprovincia(ciudadanos.get(i).getCodprovincia());
                ciud.setGenero(ciudadanos.get(i).getGenero());
                ciud.setEstadocivil(ciudadanos.get(i).getEstadocivil());

                ds.save(ciud);
               // ds.ensureIndexes();
            }catch(Exception e){
                
            }
            
        }
    }
    
}

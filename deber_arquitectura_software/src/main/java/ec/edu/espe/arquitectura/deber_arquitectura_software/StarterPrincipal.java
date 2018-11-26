/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.deber_arquitectura_software;

import com.mongodb.MongoClient;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import ec.edu.espe.arquitectura.modelo.CiudadanoMongo;
import ec.edu.espe.arquitectura.modelo.rgCivil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public static void main (String args[]) throws ParseException{
        
        int i=0;
        List<rgCivil> lst = new ArrayList();
        List<CiudadanoMongo> ciudadanos = new ArrayList();
//        

//        
//        for (CiudadanoMongo u: ciudadanos){
//            i++;
//        }
//        System.out.println("Total: "+ i);
        

        StarterMariadb starmariadb = new StarterMariadb();
        System.out.println("Comenzo lectura en Postgres");
        try {
            
            //starmariadb.conectar();
            Runnable r1 = new StarterMariadb();
            
            Thread t1 = new Thread(r1);
            
            t1.start();
            
            do {
            try{
                Thread.sleep(100);
            }catch (InterruptedException exc){
                 //System.out.println("Hilo principal interrumpido.");
            }
            
            
        } while (
                t1.isAlive()
                );
      
            starmariadb.conectar();
            lst = starmariadb.ObtenerRegistros();
            
        } catch (Exception ex) {
            Logger.getLogger(StarterPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        

            
      
        
        
//        ciudadanos = (List<CiudadanoMongo>) (CiudadanoMongo) lst;
        //Función para guardar en una lista los datos de Mariadb
        
        System.out.println("Comenzo la escritura en MongoDb");
        Morphia morphia = new Morphia();
        morphia.mapPackage("ec.edu.espe.arquitectura.taller.mongo.modelo");
        Datastore ds = morphia.createDatastore(new MongoClient(), "local_base_arquitectura");
        
        SimpleDateFormat formatear = new SimpleDateFormat("yy-MM-dd");
       
        for (rgCivil u: lst){
            CiudadanoMongo ciud = new CiudadanoMongo();
            Date date = formatear.parse(u.getFecN());
            ciud.setCedula(u.getCedu());
            ciud.setApellido(u.getApel());
            ciud.setNombre(u.getNomb());
            ciud.setFechaNacimiento(date);
            ciud.setCodprovincia(u.getCodP());
            ciud.setGenero(u.getGene());
            ciud.setEstadocivil(u.getEstC());
            
            ciudadanos.add(ciud);
        }
        
        
        StarterMongo starmongo =  new StarterMongo(ciudadanos);
        starmongo.iniciarIngreso();
//        
//        
//        List<CiudadanoMongo> ciudadanosLecturaMongo = ds.createQuery(CiudadanoMongo.class).asList();
        
        
        
        //Escritura en la base Mongo
//        for (CiudadanoMongo u: ciudadanosLecturaMongo){
//            CiudadanoMongo ciud = new CiudadanoMongo();
//            ciud.setCedula(u.getCedula());
//            ciud.setApellido(u.getApellido());
//            ciud.setNombre(u.getNombre());
//            ciud.setFechaNacimiento(u.getFechaNacimiento());
//            ciud.setCodprovincia(u.getCodprovincia());
//            ciud.setGenero(u.getGenero());
//            ciud.setEstadocivil(u.getEstadocivil());
//            
//            ds.save(ciud);
//            
//        }

        System.out.println("Ya termino");
//        
        
    }
}

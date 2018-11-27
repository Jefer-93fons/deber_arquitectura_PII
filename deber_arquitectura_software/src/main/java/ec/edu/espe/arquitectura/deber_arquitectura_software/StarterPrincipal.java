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
        
       // StarterPostgres starpostgres = new StarterPostgres();
        //Inicio de marca de tiempo
        long startTime = System.currentTimeMillis( ) ;

//        try {
//            
//            //starpostgres.conectar();
//            Runnable r1 = new StarterPostgres();
//            
//            Thread t1 = new Thread(r1);
//            
//            t1.start();
//            
//            do {
//            try{
//                Thread.sleep(100);
//            }catch (InterruptedException exc){
//                 System.out.println("Hilo principal interrumpido.");
//            }
//            
//            
//        } while (
//                t1.isAlive()
//                );
      
           // starpostgres.conectar();
           // lst = starpostgres.ObtenerRegistros();
            
//        } catch (Exception ex) {
//            Logger.getLogger(StarterPrincipal.class.getName()).log(Level.SEVERE, null, ex);
//        }        
        
        
        System.out.println("Comenzo lectura en PostgreSQL");
        StarterPostgresql starpostgres =  new StarterPostgresql();
        starpostgres.iniciarIngreso();
        lst = starpostgres.ObtenerRegistros();
        
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
        
        Morphia morphia = new Morphia();
        morphia.mapPackage("ec.edu.espe.arquitectura.taller.mongo.modelo");
        Datastore ds = morphia.createDatastore(new MongoClient(), "local_base_arquitectura");
        
        System.out.println("Comenzo la escritura en MongoDb");
        StarterMongo starmongo =  new StarterMongo(ciudadanos);
        starmongo.iniciarIngreso();
        
        List<CiudadanoMongo> ciudadanosLecturaMongo = ds.createQuery(CiudadanoMongo.class).asList();
        
//        System.out.println("Comenzo la escritura en Redis");
//        StarterRedis starredis =  new StarterRedis(ciudadanosLecturaMongo);
//        starredis.main();

        System.out.println("Proceso Terminado"); 
//        //Fin de marca de tiempo.
        long endTime = System.currentTimeMillis( ) ;
        System.out.println( "El tiempo de demora en la realización del deber es de: " + (( endTime - startTime )/1000)/60 + "m" + " y " + (( endTime - startTime )/1000)%60 +"s") ;
//        
    }
}

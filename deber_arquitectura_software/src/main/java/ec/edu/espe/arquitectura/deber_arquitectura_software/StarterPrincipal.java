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
        List<CiudadanoMongo> ciudadanosLecturaMongo = new ArrayList();

        long startTime = System.currentTimeMillis( ) ;
        
        
        try{
            System.out.println("Comenzo lectura en PostgreSQL");
            StarterPostgresql starpostgres =  new StarterPostgresql();
            starpostgres.iniciarIngreso();
            lst = starpostgres.ObtenerRegistros();
        }catch(Exception e){
            
        }
        
        
        SimpleDateFormat formatear = new SimpleDateFormat("yy-MM-dd");
        try{
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
        }catch(Exception e){
            System.out.println("Error Mapeo");
        }
        
        Morphia morphia = new Morphia();
        morphia.mapPackage("ec.edu.espe.arquitectura.taller.mongo.modelo");
        Datastore ds = morphia.createDatastore(new MongoClient(), "local_base_arquitectura");
        
        try{
            System.out.println("Comenzo la escritura en MongoDb");
            StarterMongo starmongo =  new StarterMongo(ciudadanos);
            starmongo.iniciarIngreso();
            ciudadanosLecturaMongo = ds.createQuery(CiudadanoMongo.class).asList();
            
        }catch(Exception e){
            System.out.println("Error Mongo");
        }
        
        try{
            System.out.println("Comenzo la escritura en Redis");
            StarterRedis starredis =  new StarterRedis(ciudadanosLecturaMongo);
            starredis.iniciarIngreso();
        }catch(Exception e){
            System.out.println("Error Redis");
        }
        
        

        System.out.println("Proceso Terminado"); 
        long endTime = System.currentTimeMillis( ) ;
        System.out.println( "El tiempo de demora en la realización del deber es de: " + (( endTime - startTime )/1000)/60 + "m" + " y " + (( endTime - startTime )/1000)%60 +"s") ;
        
    }
}

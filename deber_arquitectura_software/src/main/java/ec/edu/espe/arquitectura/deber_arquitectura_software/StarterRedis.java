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
import redis.clients.jedis.Jedis;

/**
 *
 * @author jonathan
 */
public class StarterRedis{
    List<CiudadanoMongo> ciudadano;
    
    public StarterRedis(List<CiudadanoMongo> ciudadano) {
        this.ciudadano = ciudadano;
    }
    
    public void ingresoRedis() {        
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.auth("root");
        jedis.connect();
        System.out.println("Conexion exitosa");
        System.out.println("Server ping " + jedis.ping());
        //jedis.set("foo", "bar");
        //jedis.set("prueba1", "prueba2");
        //String value = jedis.get("foo");
    
        /*System.out.println("Conectandose a MongoDB ....");*/
        final Morphia morphia = new Morphia();
        morphia.mapPackage("ec.edu.espe.earquitectura.modelo");
        final Datastore ds = morphia.createDatastore(new MongoClient(), "local_base_arquitectura");
        ciudadano = ds.find(CiudadanoMongo.class).retrievedFields(true).asList();
        for (CiudadanoMongo cm : ciudadano) {
            //System.out.println(cm);
            String dato = cm.toString();
            String[] codigo = dato.split(", apellido");
            int aux=0;
            String[] cedula = codigo[aux].split("cedula=");
            //System.out.println(dato);
            //System.out.println(codigo[0]); 
            //System.out.println(codigo[1]);            
            //System.out.println(cedula[1]);  
            jedis.set("" + cedula[1], "apellido"+codigo[1]);
            aux++;
        }     
    }
}
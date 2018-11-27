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
    
    public void iniciarIngreso() {        
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.auth("root");
        jedis.connect();
        System.out.println("Conexion exitosa");
        System.out.println("Server ping " + jedis.ping());

        for (CiudadanoMongo cm : ciudadano) {
            String dato = cm.toString();
            String[] codigo = dato.split(", apellido");
            int aux=0;
            String[] cedula = codigo[aux].split("cedula=");
            jedis.set("" + cedula[1],cm.getApellido()+","+cm.getNombre()+","+cm.getFechaNacimiento()+","+cm.getCodprovincia()+","+cm.getGenero()+","+cm.getEstadocivil());
            aux++;
        }     
    }
}
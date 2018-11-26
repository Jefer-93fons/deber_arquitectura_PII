/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.deber_arquitectura_software;

import redis.clients.jedis.Jedis;

/**
 *
 * @author jonathan
 */
public class StarterRedis {

    public static void main(String[] args) {        
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.auth("root");
        jedis.connect();
        System.out.println("Conexion exitosa");
        System.out.println("Server ping " + jedis.ping());
        jedis.set("foo", "bar");
        //jedis.set("prueba1", "prueba2");
        String value = jedis.get("foo");
    }
}

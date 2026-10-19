package com.krakdev.jdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.krakdev.jdbc.Conexion;
import com.krakdev.videojuegos.entidades.Videojuego;

public class VideojuegoJdbc {
	
	private static final Logger Log = LogManager.getLogger(VideojuegoJdbc.class);
	
	public static Videojuego insertar(String codigo, String nombre, String plataforma, double precio, boolean disponible, String genero) {
		
		Connection con = null;
		PreparedStatement ps = null;
		Videojuego videojuego = null;
    	
        try {
        	con = Conexion.getConnection();
        	String sqlInsert = """
        			INSERT INTO videojuegos(codigo, nombre, plataforma, precio, disponible, genero)
        			VALUES(?, ?, ?, ?, ?, ?)
        			""";
            ps = con.prepareStatement(sqlInsert);
            
			ps.setString(1, codigo);
			ps.setString(2, nombre);
			ps.setString(3, plataforma);
			ps.setDouble(4, precio);
			ps.setBoolean(5, disponible);
			ps.setString(6, genero);
        	
			videojuego = new Videojuego(codigo, nombre, plataforma, precio, disponible, genero);
			
        	int filas = ps.executeUpdate();
        	
        	Log.info("Filas insertadas: " + filas);
            
        } catch (Exception e) {

            Log.error("Error al conectar con la base de datos PostgreSQL.", e);
            
        } finally {
        	try {
        		con.close();        	
            } catch (Exception e) {
        	    e.printStackTrace();
            }
	    }
		return videojuego;
	}	
}

package com.krakdev.jdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
        	
        	Log.info("Videojuegos insertados: " + filas);
            
        } catch (Exception e) {

            Log.error("Error al insertar.", e);
            
        } finally {
        	try {
        		con.close();        	
            } catch (SQLException e) {
        	    e.printStackTrace();
            }
	    }
		return videojuego;
	}	
	
	public static List<Videojuego> listar(){
		
		List<Videojuego> videojuegos = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		
		try {
        	con = Conexion.getConnection();
        	String sqlList = """
        			SELECT * FROM videojuegos
        			""";
            ps = con.prepareStatement(sqlList);
            rs = ps.executeQuery();
            
            while(rs.next()) {
            	
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                String plataforma = rs.getString("plataforma");
                double precio = rs.getDouble("precio");
                boolean disponible = rs.getBoolean("disponible");
                String genero = rs.getString("genero");
            	
            	Videojuego videojuego = new Videojuego(
                        codigo,
                        nombre,
                        plataforma,
                        precio,
                        disponible,
                        genero
                );
            	
            	videojuegos.add(videojuego);
            	
            	Log.info("Videojuego: Codigo: " + codigo + " Nombre: " + nombre + " Plataforma: " + plataforma + " Precio: " + precio + " Disponible: " + disponible + " Genero: " + genero);
            }
            
		} catch (Exception e) {
			
			Log.error("Error al listar los videojuegos.", e);
			
		} finally {
        	try {
        		con.close();        	
            } catch (SQLException e) {
        	    e.printStackTrace();
            }
	    }
		return videojuegos;
	}
	
	public static Videojuego buscar(String codigo) {
		Connection con = null;
		PreparedStatement ps = null;
    	String sqlSearch = "SELECT * FROM videojuegos WHERE codigo = ?";
		ResultSet rs = null; 
		Videojuego videojuego = null;
		
		try {
        	con = Conexion.getConnection();
            ps = con.prepareStatement(sqlSearch);
            
            ps.setString(1, codigo);
            
            rs = ps.executeQuery();
            
            if(rs.next()) {
            	videojuego = new Videojuego(rs.getString("codigo"), rs.getString("nombre"), rs.getString("plataforma"), rs.getDouble("precio"), rs.getBoolean("disponible"), rs.getString("genero"));
            	
            	Log.info("Videojuegos buscado");
       
            }
		} catch (Exception e) {
			Log.error("Error al buscar el videojuego.", e);
			
		} finally {
        	try {
        		con.close();        	
            } catch (SQLException e) {
        	    e.printStackTrace();
            }
	    }
		
		return videojuego;
	}
}

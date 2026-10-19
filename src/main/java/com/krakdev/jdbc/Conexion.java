package com.krakdev.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Conexion {

	private static final Logger Log = LogManager.getLogger(Conexion.class);
	private static final String URL = "jdbc:postgresql://localhost:5432/apijdbc";
	private static final String USER = "postgres";
	private static final String PASSWORD = "carbot28";
	
	public static Connection getConnection() {
		
		
        try {
        	Connection conexion = DriverManager.getConnection(URL,USER,PASSWORD);

            Log.info("Conexión a PostgreSQL establecida correctamente.");
            
            return conexion;

        } catch (SQLException e) {

            Log.error( "Error al conectar con la base de datos PostgreSQL.", e);
            
            throw new RuntimeException("No se pudo conectar", e);
        }

	}
}

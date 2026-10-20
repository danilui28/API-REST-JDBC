package com.krakdev.jdbc.videojuegos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krakdev.jdbc.videojuegos.VideojuegoJdbc;
import com.krakdev.videojuegos.entidades.Videojuego;

@Service
public class ServicioVideojuegoJdbc {

	public Videojuego insertar(Videojuego videojuego) {
		return VideojuegoJdbc.insertar(videojuego.getCodigo(), videojuego.getNombre(), 
				videojuego.getPlataforma(), videojuego.getPrecio(), videojuego.isDisponible(), videojuego.getGenero());
	}
	
	public List<Videojuego> listar() {
		return VideojuegoJdbc.listar();
	}
	
	public Videojuego buscarPorCodigo(String codigo) {
		return VideojuegoJdbc.buscar(codigo);
	}
	
	public Videojuego actualizar(String codigo, Videojuego videojuegoActualizado) {
		return VideojuegoJdbc.actualizar(codigo, videojuegoActualizado.getNombre(), videojuegoActualizado.getPlataforma(), 
				videojuegoActualizado.getPrecio(), videojuegoActualizado.isDisponible(), videojuegoActualizado.getGenero());
	}
	
	public boolean eliminar(String codigo) {
		return VideojuegoJdbc.eliminar(codigo);
	}
}

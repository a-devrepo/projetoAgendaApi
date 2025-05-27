package br.com.cotiinformatica.dtos;

import java.util.UUID;

public class TarefaRequestDTO {
	private String titulo;
	private String data;
	private String hora;
	private Boolean finalizado;
	private UUID categoriaID;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Boolean getFinalizado() {
		return finalizado;
	}
	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}
	public UUID getCategoriaID() {
		return categoriaID;
	}
	public void setCategoriaID(UUID categoriaID) {
		this.categoriaID = categoriaID;
	}
}

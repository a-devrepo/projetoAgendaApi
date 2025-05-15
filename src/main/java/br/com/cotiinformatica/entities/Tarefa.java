package br.com.cotiinformatica.entities;

import java.util.Date;
import java.util.UUID;

public class Tarefa {

	private UUID id;
	private String titulo;
	private Date dataHora;
	private Boolean finalizado;
	private Categoria categoria;

	public Tarefa() {
	}

	public Tarefa(UUID id, String titulo, Date dataHora, Boolean finalizado, Categoria categoria) {
		this.id = id;
		this.titulo = titulo;
		this.dataHora = dataHora;
		this.finalizado = finalizado;
		this.categoria = categoria;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Tarefa [id=" + id + ", titulo=" + titulo + ", dataHora=" + dataHora + ", finalizado=" + finalizado
				+ ", categoria=" + categoria.getNome() + "]";
	}
}
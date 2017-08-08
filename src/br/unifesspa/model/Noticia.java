package br.unifesspa.model;

import java.util.Date;

public class Noticia {

	private int id;
	private String titulo;
	private String descricao;
	private Date data;
	
	public Noticia(){}
	
	public Noticia(int id, String titulo, String descricao, Date data)
	{
		this.setId(id);
		this.setDescricao(descricao);
		this.setTitulo(titulo);
		this.setData(data);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
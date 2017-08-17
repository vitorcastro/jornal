package br.unifesspa.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
	@NamedQuery(name="noticia.porId", query="SELECT n from Noticia n WHERE n.id = :id")
})
@Entity
public class Noticia {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private String descricao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Categoria categoria;
	
	@OneToOne(mappedBy="noticia", cascade=CascadeType.ALL)
	private NoticiaDetalhes detalhes;
	
	public NoticiaDetalhes getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(NoticiaDetalhes detalhes) {
		this.detalhes = detalhes;
	}

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
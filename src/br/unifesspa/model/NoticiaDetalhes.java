package br.unifesspa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class NoticiaDetalhes {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="criado_em")
	private Date criadoEm;
	
	@OneToOne
	private Noticia noticia;
	
	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}

	public NoticiaDetalhes(){}
	
	public NoticiaDetalhes(Date criadoEm)
	{
		this.criadoEm = criadoEm;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}
	
	
}

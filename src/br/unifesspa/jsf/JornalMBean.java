package br.unifesspa.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.unifesspa.model.Noticia;

@ManagedBean
@RequestScoped
public class JornalMBean {

	@ManagedProperty(value="#{noticiaMBean}")
	private NoticiaMBean noticiaMBean;
	
	private Iterable<Noticia> noticias;
	
	public Iterable<Noticia> getUltimasNoticias(){
		
		if (this.noticias == null)
			this.noticias = noticiaMBean.getNoticias();
		
		return this.noticias;
	}

	public NoticiaMBean getNoticiaMBean() {
		return noticiaMBean;
	}

	public void setNoticiaMBean(NoticiaMBean noticiaMBean) {
		this.noticiaMBean = noticiaMBean;
	}
	
}

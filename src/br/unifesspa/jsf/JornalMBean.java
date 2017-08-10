package br.unifesspa.jsf;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.unifesspa.model.Noticia;

@ManagedBean
@RequestScoped
public class JornalMBean {

	
	@ManagedProperty(value="#{noticiaMBean}")
	private NoticiaMBean noticiaMBean;
	
	public Collection<Noticia> getUltimasNoticias(){
		return noticiaMBean.getNoticias();
	}

	public NoticiaMBean getNoticiaMBean() {
		return noticiaMBean;
	}

	public void setNoticiaMBean(NoticiaMBean noticiaMBean) {
		this.noticiaMBean = noticiaMBean;
	}
	
}

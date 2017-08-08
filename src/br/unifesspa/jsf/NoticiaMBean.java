package br.unifesspa.jsf;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.sql.DataSource;

import br.unifesspa.model.Noticia;
import br.unifesspa.persistence.NoticiaRepository;

@ManagedBean
@RequestScoped
public class NoticiaMBean {

	private Noticia noticia;
	
	public String visualizarNoticia()
	{
		NoticiaRepository repository = new NoticiaRepository();
		
		try {
			this.noticia = repository.findById(this.noticia.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "noticia";
	}
	
	public Collection<Noticia> getNoticias()
	{
		NoticiaRepository repository = new NoticiaRepository();
		
		List<Noticia> noticias = repository.findAll();
		
		return noticias;
	}

	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}
	
}

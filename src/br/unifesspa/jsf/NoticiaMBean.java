package br.unifesspa.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.unifesspa.model.Noticia;
import br.unifesspa.persistence.NoticiaRepository;

@ManagedBean
@RequestScoped
public class NoticiaMBean {

	private Noticia noticia;
	
	private List<Noticia> allNoticias = new ArrayList<Noticia>();

	public NoticiaMBean(){
		this.noticia = new Noticia();
	}
	
	public String form()
	{
		return "/app/noticia/form";
	}
	
	public String salvar()
	{
		NoticiaRepository repository = new NoticiaRepository();
		
		this.noticia.setData(new Date());
		
		if (this.noticia.getId() == 0)
			repository.persist(noticia);
		else 
			repository.alterar(noticia);
		
		return "/app/index?faces-redirect=true";
	}
	
	public String visualizarNoticia()
	{
		NoticiaRepository repository = new NoticiaRepository();
		
		this.noticia = repository.findByIdNamedQuery(this.noticia.getId());
		
		return "noticia";
	}
	
	public Collection<Noticia> getNoticias()
	{
		NoticiaRepository repository = new NoticiaRepository();
		List<Noticia> noticias = repository.findAll();
		
		return noticias;
	}
	
	public String remover()
	{
		NoticiaRepository repository = new NoticiaRepository();
		repository.remover(this.noticia);
		
		this.noticia = new Noticia();
		
		HelperJSF.adicionarMensagemSucesso("Remoção realizada com sucesso");
		
		this.allNoticias = new ArrayList<Noticia>();
		
		return "app/index?faces-redirect=true";
		
	}
	
	public List<Noticia> getAllNoticias()
	{
		if (this.allNoticias.isEmpty())
		{
			NoticiaRepository repository = new NoticiaRepository();
			this.allNoticias = repository.findAllTypedQuery();
		}
		
		return this.allNoticias;
	}

	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}
	
}

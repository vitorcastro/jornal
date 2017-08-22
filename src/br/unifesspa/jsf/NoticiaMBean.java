package br.unifesspa.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.unifesspa.model.Categoria;
import br.unifesspa.model.Noticia;
import br.unifesspa.model.NoticiaDetalhes;
import br.unifesspa.persistence.NoticiaRepository;
import br.unifesspa.persistence.PersistenceUtil;

@ManagedBean
@RequestScoped
public class NoticiaMBean {

	private Noticia noticia;
	private List<Noticia> allNoticias = new ArrayList<Noticia>();

	public NoticiaMBean(){
		this.inicializar();
	}
	
	private void inicializar()
	{
		this.noticia = new Noticia();
		this.noticia.setCategoria(new Categoria());
		NoticiaDetalhes noticiaDetalhes = new NoticiaDetalhes(new Date()); 
		this.noticia.setDetalhes(noticiaDetalhes);
		noticiaDetalhes.setNoticia(this.noticia);
	}
	
	public String form()
	{
		return "/app/noticia/form";
	}
	
	public String paginaInicial()
	{
		return "/app/index?faces-redirect=true";
	}
	
	public String salvar()
	{
//		NoticiaRepository repository = new NoticiaRepository(PersistenceUtil.getEntityManager());
		NoticiaRepository repository = new NoticiaRepository(HelperJSF.getEntityManagerInView());
		
		if (this.noticia.getId() == 0)
			repository.persist(noticia);
		else 
			repository.alterar(noticia);
		
		HelperJSF.adicionarMensagemSucesso("Atualização realizada com sucesso");
		
		return null;
	}
	
	public String visualizarNoticia()
	{
		NoticiaRepository repository = new NoticiaRepository(PersistenceUtil.getEntityManager());
		
		this.noticia = repository.findByIdNamedQuery(this.noticia.getId());
		
		return "noticia";
	}
	
	public Collection<Noticia> getNoticias()
	{
		NoticiaRepository repository = new NoticiaRepository(PersistenceUtil.getEntityManager());
		List<Noticia> noticias = repository.findAll();
		
		return noticias;
	}
	
	public String remover()
	{
		NoticiaRepository repository = new NoticiaRepository(PersistenceUtil.getEntityManager());
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
			NoticiaRepository repository = new NoticiaRepository(HelperJSF.getEntityManagerInView());
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

package br.unifesspa.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import br.unifesspa.model.Categoria;
import br.unifesspa.persistence.CategoriaRepository;
import br.unifesspa.persistence.PersistenceUtil;

@ManagedBean
@RequestScoped
public class CategoriaMBean {

	private Categoria categoriaBusca;
	
	private List<SelectItem> categorias;
	
	public CategoriaMBean()
	{
		this.setCategoriaBusca(new Categoria());
		this.categorias = new ArrayList<SelectItem>();
	}
	
	public String buscar()
	{
		CategoriaRepository repository = new CategoriaRepository(PersistenceUtil.getEntityManager());
		this.categoriaBusca = repository.findById(this.categoriaBusca.getId());
		this.categoriaBusca.getNoticias().iterator();
		repository.close();
		return null;
	}
	
	public String entrarRelatorio()
	{
		return "/app/categoria/index";
	}
	
	private void popularCombo()
	{
		if (this.categorias.isEmpty())
		{
			CategoriaRepository repository = new CategoriaRepository(PersistenceUtil.getEntityManager());
			List<Categoria> categorias = repository.findAll();
			repository.close();
			
			for (Categoria categoria : categorias) {
				this.categorias.add(new SelectItem(categoria.getId(), categoria.getNome()));
			}
		}
		
	}
	
	public List<SelectItem> getComboCategoria()
	{
		popularCombo();
		return this.categorias;
	}

	public Categoria getCategoriaBusca() 
	{
		return categoriaBusca;
	}

	public void setCategoriaBusca(Categoria categoriaBusca) {
		this.categoriaBusca = categoriaBusca;
	}
	
}

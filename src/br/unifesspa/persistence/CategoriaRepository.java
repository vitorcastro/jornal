package br.unifesspa.persistence;

import java.util.List;

import javax.persistence.EntityManager;

import br.unifesspa.model.Categoria;

public class CategoriaRepository {

	private EntityManager manager;

	public CategoriaRepository(EntityManager manager)
	{
		this.manager = manager;
	}
	
	public List<Categoria> findAll()
	{
		return this.manager.createQuery("FROM Categoria", Categoria.class).getResultList();
	}
	
	public Categoria findById(int id)
	{
		return this.manager.find(Categoria.class, id);
	}
	
	public void close()
	{
		this.manager.close();
	}


}

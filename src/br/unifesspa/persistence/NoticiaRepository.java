package br.unifesspa.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.unifesspa.model.Noticia;

public class NoticiaRepository {

	private EntityManager manager;
	
	public NoticiaRepository(EntityManager manager)
	{
		this.manager = manager;
	}
	
	public void remover(Noticia noticia)
	{
		Noticia n = manager.merge(noticia);
		
		manager.getTransaction().begin();  
		manager.remove(n);
		manager.getTransaction().commit();

		manager.close();
	}
	
	public void alterar(Noticia noticia)
	{
		//manager.getTransaction().begin();  
		manager.merge(noticia);
		//manager.getTransaction().commit();
		
		//manager.close();
	}
	
	public Noticia persist(Noticia noticia)
	{
//		manager.getTransaction().begin();    
		manager.persist(noticia);
//		manager.getTransaction().commit();
		
		manager.close();
		
		return noticia;
	}
	
	public List<Noticia> findAllTypedQuery()
	{
		TypedQuery<Noticia> typedQuery = manager.createQuery("SELECT n FROM Noticia n JOIN FETCH n.categoria", Noticia.class);
		List<Noticia> allNoticias = typedQuery.getResultList();
	
		//manager.close();
		
		return allNoticias;
	}
	
	public List<Noticia> findAllNoticias()
	{
		Query query = manager.createQuery("SELECT n FROM Noticia n");
	   
		List<Noticia> resultado = query.getResultList();
	    
		manager.close();
		
	    return resultado;
	}
	
	public List<Noticia> findAllCriteria()
	{
		CriteriaBuilder builder = manager.getCriteriaBuilder();
	    CriteriaQuery<Noticia> criteria = builder.createQuery(Noticia.class);
	    criteria.from(Noticia.class);
	    return manager.createQuery(criteria).getResultList();
	}
	
	public Noticia findByIdNamedQuery(int id)
	{
		Noticia noticia = manager.createNamedQuery("noticia.porId", Noticia.class)
				.setParameter("id", id)
				.getSingleResult();
		
		manager.close();
		
		return noticia;
	}
	
	public Noticia findById(int id) throws SQLException
	{
		Noticia noticia = null;
			
			String sqlBusca = "SELECT * FROM noticia WHERE id = ?";
			
			ConnectionFactory factory = new ConnectionFactory();
			PreparedStatement statement = factory.byDataSource().prepareStatement(sqlBusca);
			statement.setInt(1,id);
			
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				noticia = new Noticia();
				noticia.setId(result.getInt("id"));
				noticia.setTitulo(result.getString("titulo"));
				noticia.setDescricao(result.getString("descricao"));
				noticia.setData(result.getDate("data"));
			}

		return noticia;
	}
	
	public List<Noticia> findAll()
	{
		List<Noticia> noticias = new ArrayList<Noticia>();
		
		ConnectionFactory factory = new ConnectionFactory();
		PreparedStatement statement = null;
		String sqlBusca = "SELECT id,titulo,descricao,data FROM noticia";
		
		try {
			statement = factory.build().prepareStatement(sqlBusca);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				
				Noticia noticia = new Noticia();
				noticia.setId(result.getInt("id"));
				noticia.setTitulo(result.getString("titulo"));
				noticia.setDescricao(result.getString("descricao"));
				noticia.setData(result.getDate("data"));
				
				noticias.add(noticia);
			}
			statement.close();
			factory.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return noticias;
	}

}

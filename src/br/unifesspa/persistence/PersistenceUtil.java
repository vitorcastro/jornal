package br.unifesspa.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
	
	private static EntityManagerFactory factory;
	
	public static EntityManager getEntityManager()
	{
		if (factory == null)
			factory = Persistence.createEntityManagerFactory("jornal");
		
		return factory.createEntityManager();
	}
}

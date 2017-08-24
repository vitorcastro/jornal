package br.unifesspa.persistence;
import java.util.Collection;

import br.unifesspa.model.Noticia;

public interface IRepository {

	public Collection<Noticia> findAll();
	
}

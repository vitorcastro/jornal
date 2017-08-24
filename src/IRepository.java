import java.util.List;

import br.unifesspa.model.Noticia;

public interface IRepository {

	public List<Noticia> findAll();
	
}

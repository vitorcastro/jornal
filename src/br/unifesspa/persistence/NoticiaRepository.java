package br.unifesspa.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unifesspa.model.Noticia;

public class NoticiaRepository {
	
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
		String sqlBusca = "SELECT id,tituto,descricao,data FROM noticia";
		
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
			throw new RuntimeException();
		}
		
		return noticias;
	}

}

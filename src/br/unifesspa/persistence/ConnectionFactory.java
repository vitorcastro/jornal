package br.unifesspa.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {
	
	public ConnectionFactory() {}
	
	private Connection connection;
	
	public Connection byDataSource() throws SQLException
	{
		try {
			Context initialContext = new InitialContext();
			DataSource datasource = (DataSource)initialContext.lookup("java:/TestDS");
			
			if (datasource != null)
				return datasource.getConnection();
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Connection build()
	{
		String serverName = "localhost:8889";    
		String nameDataBase ="jornal";        
		String url = "jdbc:mysql://" + serverName + "/" + nameDataBase;
		String username = "root";
		String password = "root";   

		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Criando conexão JDBC" + connection);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return connection;
	}
	
	public void close() 
	{
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

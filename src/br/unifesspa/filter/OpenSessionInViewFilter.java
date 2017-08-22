package br.unifesspa.filter;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.transaction.Transaction;

import br.unifesspa.persistence.PersistenceUtil;

@WebFilter(urlPatterns="*")
public class OpenSessionInViewFilter implements Filter {

	@Override
	public void destroy() {	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		EntityManager session = PersistenceUtil.getEntityManager();
		EntityTransaction transaction = null;
		
		try {
			transaction = session.getTransaction();

			transaction.begin();
			
			System.out.println("Iniciando transaction InView");
			
			request.setAttribute("session", session);
			
			chain.doFilter(request, response);
			
			transaction.commit();
			System.out.println("Finalizando transaction InView");
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			
			if (session.isOpen())
				session.close();
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	

}

package br.unifesspa.jsf;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

public class HelperJSF {

	public static void adicionarMensagemErro(String mensagem)
	{
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, null));
	}
	
	public static void adicionarMensagemSucesso(String mensagem)
	{
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful", mensagem));
	}
	
	public static String getParameter(String name)
	{
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		return params.get(name);
	}
	
	public static Object getRequestAttribute(String name) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		return request.getAttribute(name);
	}
	
	public static EntityManager getEntityManagerInView()
	{
		return (EntityManager) getRequestAttribute("session");
	}
	
}

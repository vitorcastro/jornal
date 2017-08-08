package br.unifesspa.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UsuarioMBean {
	
	private String login;
	
	public String logar()
	{
		if (this.login.equals("JSF"))
		{
			HelperJSF.adicionarMensagemErro("O login não pode ser JSF");
			this.login = null;
		}
		
		return null;
	}
	
	public String logout()
	{
		this.login = null;
		return null;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public Boolean getLoginAtivo()
	{
		return (this.login != null);
	}

}

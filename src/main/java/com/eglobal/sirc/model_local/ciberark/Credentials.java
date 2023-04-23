package com.eglobal.sirc.model_local.ciberark;


public class Credentials {

	private String url;
	private String user;
	private String pass;
	private String driver;
	private String script;
	private String descripcion;
	public Credentials() {
		super();
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Credentials [url=" + url + ", user=" + user + ", pass=" + pass + ", driver=" + driver + ", script="
				+ script + ", descripcion=" + descripcion + "]";
	}
	public Credentials(String url, String user, String pass, String driver, String script) {
		super();
		this.url = url;
		this.user = user;
		this.pass = pass;
		this.driver = driver;
		this.script = script;
	}
	
	


}


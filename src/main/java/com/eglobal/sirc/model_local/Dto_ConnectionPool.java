package com.eglobal.sirc.model_local;

import java.io.Serializable;



public class Dto_ConnectionPool implements Serializable {

	private String idConnectionPool;
	private String IdAdquirente;
	private String pais;
	private String conexionJson;
	private String script;
	private String descripcion;

	public String getIdConnectionPool() {
		return idConnectionPool;
	}

	public void setIdConnectionPool(String idConnectionPool) {
		this.idConnectionPool = idConnectionPool;
	}

	public String getIdAdquirente() {
		return IdAdquirente;
	}

	public void setIdAdquirente(String idAdquirente) {
		IdAdquirente = idAdquirente;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getConexionJson() {
		return conexionJson;
	}

	public void setConexionJson(String conexionJson) {
		this.conexionJson = conexionJson;
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
		return "Dto_ConnectionPool [idConnectionPool=" + idConnectionPool + ", IdAdquirente=" + IdAdquirente + ", pais="
				+ pais + ", conexionJson=" + conexionJson + ", script=" + script + ", descripcion=" + descripcion + "]";
	}

	public Dto_ConnectionPool(String idConnectionPool, String idAdquirente, String pais, String conexionJson,
			String script, String descripcion) {
		this.idConnectionPool = idConnectionPool;
		IdAdquirente = idAdquirente;
		this.pais = pais;
		this.conexionJson = conexionJson;
		this.script = script;
		this.descripcion = descripcion;
	}

	public Dto_ConnectionPool() {
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;
}

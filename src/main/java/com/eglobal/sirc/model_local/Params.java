package com.eglobal.sirc.model_local;

public class Params {

	private String feInicio;
	private String feFin;
	
	private int ventana;

	public Params() {
	}

	public String getFeInicio() {
		return feInicio;
	}

	public void setFeInicio(String feInicio) {
		this.feInicio = feInicio;
	}

	public String getFeFin() {
		return feFin;
	}

	public void setFeFin(String feFin) {
		this.feFin = feFin;
	}

	public int getVentana() {
		return ventana;
	}

	public void setVentana(int ventana) {
		this.ventana = ventana;
	}

	@Override
	public String toString() {
		return "Params [feInicio=" + feInicio + ", feFin=" + feFin + ", ventana=" + ventana + "]";
	}

	public Params(String feInicio, String feFin, int ventana) {
		super();
		this.feInicio = feInicio;
		this.feFin = feFin;
		this.ventana = ventana;
	}
	

	
}

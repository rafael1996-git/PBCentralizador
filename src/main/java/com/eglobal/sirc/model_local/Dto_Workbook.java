package com.eglobal.sirc.model_local;

import java.io.Serializable;
import java.util.Date;



public class Dto_Workbook implements Serializable {

	
	private Integer  id_workbook;
	private int adquirente;
	private String plataforma;
	private Date fecha_proceso;
	private Date fecha_aplicacion;
	private String tipo_txn_srv;
	private String descripcion_tipo_txn_srv;
	private String numero_autorizacion;
	private String giro_comercio_mcc;
	private String emisor;
	private String numero_afiliacion;
	private String nombre_comercio;
	private String numero_contrato;
	private String cuenta_recaudadora;
	private String tipo_producto;
	private Integer ventana_abono;
	private int moneda;
	private double importe_transacción_srv;
	private String tipo_cobro_abono;
	private String descripcion_tipo_cobro_abono;
	private String numero_archivo;
	private String cuenta_cheques;
	private String caja_contable;
	private double importe_cobro_abono;
	private Date fecha_deposito;
	private String cargo_abono;
	private Integer no_txns ; 
	
	
	public Integer getId_workbook() {
		return id_workbook;
	}

	public void setId_workbook(Integer id_workbook) {
		this.id_workbook = id_workbook;
	}

	public Integer getNo_txns() {
		return no_txns;
	}

	public void setNo_txns(Integer no_txns) {
		this.no_txns = no_txns;
	}

	public int getAdquirente() {
		return adquirente;
	}

	public void setAdquirente(int adquirente) {
		this.adquirente = adquirente;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public Date getFecha_proceso() {
		return fecha_proceso;
	}

	public void setFecha_proceso(Date fecha_proceso) {
		this.fecha_proceso = fecha_proceso;
	}

	public Date getFecha_aplicacion() {
		return fecha_aplicacion;
	}

	public void setFecha_aplicacion(Date fecha_aplicacion) {
		this.fecha_aplicacion = fecha_aplicacion;
	}

	public String getTipo_txn_srv() {
		return tipo_txn_srv;
	}

	public void setTipo_txn_srv(String tipo_txn_srv) {
		this.tipo_txn_srv = tipo_txn_srv;
	}

	public String getDescripcion_tipo_txn_srv() {
		return descripcion_tipo_txn_srv;
	}

	public void setDescripcion_tipo_txn_srv(String descripcion_tipo_txn_srv) {
		this.descripcion_tipo_txn_srv = descripcion_tipo_txn_srv;
	}

	public String getNumero_autorizacion() {
		return numero_autorizacion;
	}

	public void setNumero_autorizacion(String numero_autorizacion) {
		this.numero_autorizacion = numero_autorizacion;
	}

	public String getGiro_comercio_mcc() {
		return giro_comercio_mcc;
	}

	public void setGiro_comercio_mcc(String giro_comercio_mcc) {
		this.giro_comercio_mcc = giro_comercio_mcc;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public String getNumero_afiliacion() {
		return numero_afiliacion;
	}

	public void setNumero_afiliacion(String numero_afiliacion) {
		this.numero_afiliacion = numero_afiliacion;
	}

	public String getNombre_comercio() {
		return nombre_comercio;
	}

	public void setNombre_comercio(String nombre_comercio) {
		this.nombre_comercio = nombre_comercio;
	}

	public String getNumero_contrato() {
		return numero_contrato;
	}

	public void setNumero_contrato(String numero_contrato) {
		this.numero_contrato = numero_contrato;
	}

	public String getCuenta_recaudadora() {
		return cuenta_recaudadora;
	}

	public void setCuenta_recaudadora(String cuenta_recaudadora) {
		this.cuenta_recaudadora = cuenta_recaudadora;
	}

	public String getTipo_producto() {
		return tipo_producto;
	}

	public void setTipo_producto(String tipo_producto) {
		this.tipo_producto = tipo_producto;
	}

	public Integer getVentana_abono() {
		return ventana_abono;
	}

	public void setVentana_abono(Integer ventana_abono) {
		this.ventana_abono = ventana_abono;
	}

	public int getMoneda() {
		return moneda;
	}

	public void setMoneda(int moneda) {
		this.moneda = moneda;
	}

	public double getImporte_transacción_srv() {
		return importe_transacción_srv;
	}

	public void setImporte_transacción_srv(double importe_transacción_srv) {
		this.importe_transacción_srv = importe_transacción_srv;
	}

	public String getTipo_cobro_abono() {
		return tipo_cobro_abono;
	}

	public void setTipo_cobro_abono(String tipo_cobro_abono) {
		this.tipo_cobro_abono = tipo_cobro_abono;
	}

	public String getDescripcion_tipo_cobro_abono() {
		return descripcion_tipo_cobro_abono;
	}

	public void setDescripcion_tipo_cobro_abono(String descripcion_tipo_cobro_abono) {
		this.descripcion_tipo_cobro_abono = descripcion_tipo_cobro_abono;
	}

	public String getNumero_archivo() {
		return numero_archivo;
	}

	public void setNumero_archivo(String numero_archivo) {
		this.numero_archivo = numero_archivo;
	}

	public String getCuenta_cheques() {
		return cuenta_cheques;
	}

	public void setCuenta_cheques(String cuenta_cheques) {
		this.cuenta_cheques = cuenta_cheques;
	}

	public String getCaja_contable() {
		return caja_contable;
	}

	public void setCaja_contable(String caja_contable) {
		this.caja_contable = caja_contable;
	}

	public double getImporte_cobro_abono() {
		return importe_cobro_abono;
	}

	public void setImporte_cobro_abono(double importe_cobro_abono) {
		this.importe_cobro_abono = importe_cobro_abono;
	}

	public Date getFecha_deposito() {
		return fecha_deposito;
	}

	public void setFecha_deposito(Date fecha_deposito) {
		this.fecha_deposito = fecha_deposito;
	}

	public String getCargo_abono() {
		return cargo_abono;
	}

	public void setCargo_abono(String cargo_abono) {
		this.cargo_abono = cargo_abono;
	}

	
	public Dto_Workbook(int adquirente, String plataforma, Date fecha_proceso,
			Date fecha_aplicacion, String tipo_txn_srv, String descripcion_tipo_txn_srv, String numero_autorizacion,
			String giro_comercio_mcc, String emisor, String numero_afiliacion, String nombre_comercio,
			String numero_contrato, String cuenta_recaudadora, String tipo_producto, Integer ventana_abono, int moneda,
			double importe_transacción_srv, String tipo_cobro_abono, String descripcion_tipo_cobro_abono,
			String numero_archivo, String cuenta_cheques, String caja_contable, double importe_cobro_abono,
			Date fecha_deposito, String cargo_abono, Integer no_txns) {
		this.adquirente = adquirente;
		this.plataforma = plataforma;
		this.fecha_proceso = fecha_proceso;
		this.fecha_aplicacion = fecha_aplicacion;
		this.tipo_txn_srv = tipo_txn_srv;
		this.descripcion_tipo_txn_srv = descripcion_tipo_txn_srv;
		this.numero_autorizacion = numero_autorizacion;
		this.giro_comercio_mcc = giro_comercio_mcc;
		this.emisor = emisor;
		this.numero_afiliacion = numero_afiliacion;
		this.nombre_comercio = nombre_comercio;
		this.numero_contrato = numero_contrato;
		this.cuenta_recaudadora = cuenta_recaudadora;
		this.tipo_producto = tipo_producto;
		this.ventana_abono = ventana_abono;
		this.moneda = moneda;
		this.importe_transacción_srv = importe_transacción_srv;
		this.tipo_cobro_abono = tipo_cobro_abono;
		this.descripcion_tipo_cobro_abono = descripcion_tipo_cobro_abono;
		this.numero_archivo = numero_archivo;
		this.cuenta_cheques = cuenta_cheques;
		this.caja_contable = caja_contable;
		this.importe_cobro_abono = importe_cobro_abono;
		this.fecha_deposito = fecha_deposito;
		this.cargo_abono = cargo_abono;
		this.no_txns = no_txns;
	}
	
	

	@Override
	public String toString() {
		return "Dto_Workbook [adquirente=" + adquirente + ", plataforma=" + plataforma + ", fecha_proceso="
				+ fecha_proceso + ", fecha_aplicacion=" + fecha_aplicacion + ", tipo_txn_srv=" + tipo_txn_srv
				+ ", descripcion_tipo_txn_srv=" + descripcion_tipo_txn_srv + ", numero_autorizacion="
				+ numero_autorizacion + ", giro_comercio_mcc=" + giro_comercio_mcc + ", emisor=" + emisor
				+ ", numero_afiliacion=" + numero_afiliacion + ", nombre_comercio=" + nombre_comercio
				+ ", numero_contrato=" + numero_contrato + ", cuenta_recaudadora=" + cuenta_recaudadora
				+ ", tipo_producto=" + tipo_producto + ", ventana_abono=" + ventana_abono + ", moneda=" + moneda
				+ ", importe_transacción_srv=" + importe_transacción_srv + ", tipo_cobro_abono=" + tipo_cobro_abono
				+ ", descripcion_tipo_cobro_abono=" + descripcion_tipo_cobro_abono + ", numero_archivo="
				+ numero_archivo + ", cuenta_cheques=" + cuenta_cheques + ", caja_contable=" + caja_contable
				+ ", importe_cobro_abono=" + importe_cobro_abono + ", fecha_deposito=" + fecha_deposito
				+ ", cargo_abono=" + cargo_abono + ", no_txns=" + no_txns + "]";
	}

	public Dto_Workbook() {

	}

	private static final long serialVersionUID = 1L;

}

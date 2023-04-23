package com.eglobal.sirc.util;

public class Queries {
	/* Datos de
	" conexion  " +
	" scrsch.c_connectionpool";	*/
	public static String queryConnectionPoolID= "SELECT id_connection_pool, cod_adquirente_pais,  conexion_json, script, descripcion FROM eglobal_sicrsch.c_connection_pool where cod_adquirente_pais=?";
			
	/* Datos de
	" centralizacion " +
	" scrsch.ds_workbook";	*/
	public static String QuerySave="INSERT INTO eglobal_sicrsch.ds_workbook(\r\n"
			+ "cod_adquirente_pais, cod_plataforma, fecha_proceso, fecha_aplicacion, tipo_txn_srv, "
			+ "descripcion_tipo_txn_srv, numero_autorizacion, giro_comercio_mcc, emisor, numero_afiliacion, nombre_comercio,"
			+ " numero_contrato, cuenta_recaudadora, tipo_producto, ventana_abono, cod_moneda, tipo_cobro_abono, descripcion_tipo_cobro_abono,"
			+ " numero_archivo, cuenta_cheques, caja_contable, fecha_deposito, cargo_abono, no_txns, importe_cobro_abono, importe_transaccion_srv)\r\n"
			+ "	VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE, ?, ?, ?, ?)";
	
	public static String queryIDFECHA= "select fecha_proceso FROM eglobal_sicrsch.ds_workbook where fecha_proceso= ?";
	
	public static String queryDeleteIdFecha= "DELETE FROM eglobal_sicrsch.ds_workbook where fecha_proceso= ?";
	
}
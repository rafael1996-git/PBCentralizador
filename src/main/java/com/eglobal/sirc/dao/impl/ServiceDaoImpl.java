package com.eglobal.sirc.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.eglobal.sirc.config.db.PostgreDataSource;
import com.eglobal.sirc.credentials.db.DataBaseCiberArk;
import com.eglobal.sirc.credentials.db.DataBaseProperties;
import com.eglobal.sirc.dao.IServiceDao;
import com.eglobal.sirc.mapper.ConnectinMapper;
import com.eglobal.sirc.model_local.Dto_ConnectionPool;
import com.eglobal.sirc.model_local.Dto_Workbook;
import com.eglobal.sirc.model_local.ciberark.Credentials;
import com.eglobal.sirc.util.ErrorCodes;
import com.eglobal.sirc.util.Queries;
import com.eglobal.sirc.util.SingletonConnection;
import com.eglobal.sirc.util.Util;

@Component
public class ServiceDaoImpl implements IServiceDao {

	private static final Logger log = LogManager.getLogger(ServiceDaoImpl.class);

	@Value(value = "${local.postgres.driverClassName}")
	String drive;

	@Value(value = "${local.postgres.url}")
	String url;
	@Value(value = "${local.file.sicr}")
	String file;
	@Autowired
	private DataBaseProperties baseProperties;
	@Autowired
	private DataBaseCiberArk arkCredentials;
	long time_start;
	long time_end;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	static {
        try {
            if (PostgreDataSource.DB_CONFIG == null) {
            	log.info("[{}]", ErrorCodes.cod03);
    			log.error("ERROR conexion database instancia");
            }
            SingletonConnection.initSingletonConnectionCredentials(PostgreDataSource.DB_CONFIG.getUrl(), 
            		PostgreDataSource.DB_CONFIG.getUser(), PostgreDataSource.DB_CONFIG.getPass());

            System.out.println("prueba de conexion statica ");
            
        } catch (Exception e) {
        	log.info("[{}]", ErrorCodes.cod03);
			log.error("ERROR conexion database instancia",e);
        }
    }
	
	@Override
	public List<Dto_Workbook> getList(Credentials credential) throws Exception {
		PreparedStatement ps = null;
		Connection con = null;
		List<Dto_Workbook> listado = new ArrayList<>();

		try {
			Class.forName(credential.getDriver());
			con = DriverManager.getConnection(credential.getUrl(), credential.getUser(), credential.getPass());

			time_start = System.currentTimeMillis();
			log.info(
					"======== INICIO DE TIEMPO getList:  " + time_start);
			if (Util.params.getVentana() != 1 || Util.params.getVentana() >1) {
				Date date_inicio = Date.valueOf(Util.params.getFeInicio());
				Date date_fin = Date.valueOf(Util.params.getFeFin());
				ps = con.prepareStatement(credential.getScript());
				ps.setDate(1, date_inicio);
				ps.setDate(2, date_fin);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					listado.add(new Dto_Workbook(Util.params.getVentana(), rs.getString(12) == null ? null :rs.getString(12).trim() , rs.getDate(3),
							rs.getDate(4), null, null, null, null, rs.getString(11) == null ? null : rs.getString(11).trim(), rs.getString(2)== null ? null :rs.getString(2).trim(),
							rs.getString(1) == null ? null : rs.getString(1).trim(), null, null, null, null,
							Integer.parseInt(rs.getString(13)), 0, rs.getString(9)== null ? null :rs.getString(9).trim() , rs.getString(10)== null ? null :rs.getString(10).trim(), 
							rs.getString(5)== null ? null :rs.getString(5).trim(),rs.getString(15)== null ? null :rs.getString(15).trim(), rs.getString(6)== null ? null :rs.getString(6).trim(), Double.parseDouble(rs.getString(14)), rs.getDate(3),
							rs.getString(8)== null ? null :rs.getString(8), Integer.parseInt(rs.getString(7))));
				}
				rs.close();

			} else if (Util.params.getVentana() == 1) {
				Date date_inicio = Date.valueOf(Util.params.getFeInicio());
				Date date_fin = Date.valueOf(Util.params.getFeFin());
				ps = con.prepareStatement(credential.getScript());
				ps.setDate(1, date_inicio);
				ps.setDate(2, date_fin);
				ps.setDate(3, date_inicio);
				ps.setDate(4, date_fin);
				ResultSet rs = ps.executeQuery();
				System.out.println("======= paso por aqui EVO_BBVA");
				while (rs.next()) {
					listado.add(new Dto_Workbook(Util.params.getVentana(), rs.getString(14)== null ? null :rs.getString(14).trim(), rs.getDate(1),
							rs.getDate(3), rs.getString(18)== null ? null :rs.getString(18).trim(), rs.getString(20) == null ? null : rs.getString(20).trim(),
							rs.getString(6) == null ? null :rs.getString(6).trim(), rs.getString(7) == null ? null :rs.getString(7).trim(), rs.getString(16)== null ? null :rs.getString(16).trim(),
							rs.getString(2)== null ? null :rs.getString(2).trim(),
							rs.getString(19) == null ? null : rs.getString(19).trim(), rs.getString(9)== null ? null :rs.getString(9).trim(),
							rs.getString(10)== null ? null :rs.getString(10).trim(), rs.getString(11)== null ? null :rs.getString(11).trim(),
							rs.getString(12) == null ? null : Integer.parseInt(rs.getString(12)),
							Integer.parseInt(rs.getString(13)), Double.parseDouble(rs.getString(4)), null, null, null,
							null, null, 0, rs.getDate(3), rs.getString(15) == null ? null :rs.getString(15).trim(),
							rs.getString(17) == null ? null : Integer.parseInt(rs.getString(17))));
				}
				rs.close();

			}
			time_end = System.currentTimeMillis();
			double tiempo = (double) ((time_start - time_end) / 1000);
			log.info("======= FIN DE TIEMPO getList  [{}] ",
					tiempo + " segundos");
		} catch (SQLException e) {
			log.error("ERROR  getList ");
			log.info("[{}]", ErrorCodes.cod03);
			log.error("ERROR getList: ",e);

		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				log.error("ERROR getList",e);
			}
		}

		return listado;

	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Dto_ConnectionPool> getPoolID(int ventana) {
		try {
			String query = Queries.queryConnectionPoolID;
			List<Dto_ConnectionPool> var = jdbcTemplate.query(query, new Object[] { ventana }, new ConnectinMapper());
			return var;
		} catch (InvalidResultSetAccessException e) {
			log.info("[{}]", ErrorCodes.cod03);
			log.error("ERROR getPoolID: ",e);
			throw new RuntimeException(e);
		} catch (DataAccessException e) {
			log.info("[{}]", ErrorCodes.cod03);
			log.error(e.getMessage());
			log.error("ERROR getPoolID no se encontro la VENTANA: ",e);
			throw new RuntimeException(e);
		}
	}



	@Override
	public void insert(List<Dto_Workbook> objet) throws Exception {
		PreparedStatement ps = null;
		Connection con = null;
		String query = Queries.QuerySave;
		Date date_aplicacion = null;
		try {
			Class.forName(drive);
			con=SingletonConnection.getSingletonConnection(7, query);
			con.setAutoCommit(false);
			ps = con.prepareStatement(query);
			DecimalFormat formateador = new DecimalFormat("#########.##");
			int cont = 1;
			time_start = System.currentTimeMillis();
			log.info("========= INICIO FOR: " + time_start);
			for (Dto_Workbook data : objet) {

				date_aplicacion = Date.valueOf(data.getFecha_aplicacion().toString());
				Date date_proceso = Date.valueOf(data.getFecha_proceso().toString());

				ps.setInt(1, data.getAdquirente());
				ps.setString(2, data.getPlataforma());
				ps.setDate(3, date_proceso);
				ps.setDate(4, date_aplicacion);
				ps.setString(5, data.getTipo_txn_srv());
				ps.setString(6, data.getDescripcion_tipo_txn_srv());
				ps.setString(7, data.getNumero_autorizacion());
				ps.setString(8, data.getGiro_comercio_mcc());
				ps.setString(9, data.getEmisor());
				ps.setString(10, data.getNumero_afiliacion());
				ps.setString(11, data.getNombre_comercio() == null ? null : data.getNombre_comercio());
				ps.setString(12, data.getNumero_contrato());
				ps.setString(13, data.getCuenta_recaudadora());
				ps.setString(14, data.getTipo_producto());
				ps.setInt(15, data.getVentana_abono() == null ? 0 : data.getVentana_abono());
				ps.setInt(16, data.getMoneda());
				ps.setString(17, data.getTipo_cobro_abono());
				ps.setString(18, data.getDescripcion_tipo_cobro_abono());
				ps.setString(19, data.getNumero_archivo());
				ps.setString(20, data.getCuenta_cheques());
				ps.setString(21, data.getCaja_contable());
//				ps.setDate(22,null);
				ps.setString(22, data.getCargo_abono());
				ps.setInt(23, data.getNo_txns() == null ? 0 : data.getNo_txns());
				ps.setString(24, formateador.format(data.getImporte_cobro_abono()));
				ps.setString(25, formateador.format(data.getImporte_transacción_srv()));
				ps.addBatch();
				log.info("# NUMERO DE INSERCIONES: " + cont++ + "\n DATA: " + data);
				ps.executeBatch();
			}
			time_end = System.currentTimeMillis();
			double tiempo2 = (double) ((time_start - time_end) / 1000);
			log.info("======= FIN FOR  TIEMPO TRASCURRIDO [{}] ",
					tiempo2 + " segundos");

			con.commit();
			ps.close();
		} catch (SQLException e) {
			log.info("[{}]", ErrorCodes.cod03);
			log.error("ERROR  DATABASE");
			log.error(e.getMessage());
			log.error("ERROR insert: ",e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				log.error("ERROR insert",e);
			}
		}

	}

	@Override
	public List<Dto_Workbook> getFile() throws Exception {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		List<Dto_Workbook> listado = new ArrayList<>();
		Dto_Workbook obj = new Dto_Workbook();
		try {
			String año = Util.params.getFeInicio().substring(0, 4);
			String mes = Util.params.getFeInicio().substring(5, 7);
			String dia = Util.params.getFeInicio().substring(8, 10);
//			archivo = new File("C:\\file\\SICR\\costos_mercurio_22_604_"+dia+mes+año+".txt");
			archivo = new File(file + dia + mes + año + ".txt");

			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			while ((linea = br.readLine()) != null) {
				Date date_proceso = Date.valueOf(linea.substring(0, 10));

				obj.setAdquirente(Util.params.getVentana());
				obj.setFecha_proceso(date_proceso);
				obj.setFecha_aplicacion(date_proceso);
				obj.setNumero_contrato(linea.substring(10, 30));
				obj.setNumero_afiliacion(linea.substring(30, 38));
				obj.setTipo_txn_srv(linea.substring(38, 41));
				obj.setDescripcion_tipo_txn_srv(linea.substring(41, 91).trim());
				if (linea.substring(93, 96).equals("PEN")) {
					obj.setMoneda(604);
				} else if (linea.substring(93, 96).equals("MXN")) {
					obj.setMoneda(484);
				} else if (linea.substring(93, 96).equals("CLP")) {
					obj.setMoneda(152);
				} else if (linea.substring(93, 96).equals("USD")) {
					obj.setMoneda(840);
				}

				obj.setImporte_transacción_srv(Double.parseDouble(linea.substring(96, 111)));
				listado.add(obj);

				System.out.println(listado + "\n");
			}
		}

		catch (Exception e) {
			log.info("[{}]", ErrorCodes.cod02);
			log.info("[NO SE ENCONTRO FILE OH TUBO PROBLEMA DE SETTEO]");
			log.error("ERROR getFile",e.getMessage());
			log.error("ERROR getFile",e);

		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				log.error("ERROR getFile ",e2.getMessage());
			}
		}
		return listado;
	}

	@Override
	public boolean getFechaById(String fecha) {
		boolean bandera=false;
		PreparedStatement ps = null;
		Connection con = null;
		String query = Queries.queryIDFECHA;
		try {
			Class.forName(drive);
			con=SingletonConnection.getSingletonConnection(7, query);
			ps= con.prepareStatement(query); 
			Date date_inicio = Date.valueOf(fecha);
			ps.setDate(1,date_inicio);
			ResultSet rs = ps.executeQuery();
			Date value=null;
			while (rs.next()) {
                value=rs.getDate(1);
                bandera=true;
            }
			log.info("=======validacion de fecha encontrada en base de datos :" + value +" : "+ bandera);

			rs.close();
		} catch (Exception e) {
			 bandera=false;
				log.error("ERROR  DATABASE getFechaById");
				log.error("ERROR",e);
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				log.error("ERROR",e.getMessage());
			}
		}
			
			
		
		return bandera;
	}

	@Override
	public boolean DeleteFechaById(String fecha) {
		boolean bandera=false;
		PreparedStatement ps = null;
		Connection con = null;
		Credentials credentials = new Credentials();
		credentials = baseProperties.getCredentials();
		String query = Queries.queryDeleteIdFecha;
		try {
			Class.forName(drive);
			con = DriverManager.getConnection(url, PostgreDataSource.DB_CONFIG.getUser(), PostgreDataSource.DB_CONFIG.getPass());
//			con=SingletonConnection.getSingletonConnection(7, query);
			ps= con.prepareStatement(query); 
			Date date_inicio = Date.valueOf(fecha);
			ps.setDate(1,date_inicio);
            int row = ps.executeUpdate();
			log.info(row);
			bandera=true;
		} catch (Exception e) {
			 bandera=false;
				log.error("ERROR  DATABASE DeleteFechaById");
			log.error("ERROR DeleteFechaById",e);
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				log.error("ERROR DeleteFechaById",e);
			}
		}
			
			
		
		return bandera;
	}
	
	public static void main(String[] args) {
		ServiceDaoImpl o=new ServiceDaoImpl();
		boolean bandera=o.DeleteFechaById("2023-10-22");
		
		System.out.println(bandera);
		
	}

}

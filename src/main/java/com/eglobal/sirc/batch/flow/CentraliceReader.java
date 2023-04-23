package com.eglobal.sirc.batch.flow;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.eglobal.sirc.dao.IServiceDao;
import com.eglobal.sirc.encryptdecrypt.EncryptDecrypt;
import com.eglobal.sirc.model_local.Dto_ConnectionPool;
import com.eglobal.sirc.model_local.Dto_Workbook;
import com.eglobal.sirc.model_local.ciberark.Credentials;
import com.eglobal.sirc.util.ErrorCodes;
import com.eglobal.sirc.util.Util;
import com.google.gson.Gson;

public class CentraliceReader implements ItemReader<Dto_Workbook> {

	public static Logger log = LogManager.getLogger(CentraliceReader.class);

	@Autowired
	private IServiceDao Service;

	List<Dto_ConnectionPool> listTerminados = new ArrayList<Dto_ConnectionPool>();
	Credentials param = null;
	List<Dto_Workbook> list = new ArrayList<>();
	List<Dto_Workbook> data_file = new ArrayList<>();
	Dto_Workbook lista = new Dto_Workbook();
	long time_start;
	long time_end;

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		EncryptDecrypt encrypt = new EncryptDecrypt();
		Gson gson = new Gson();

		/* Recupera de la BD la configuración necesaria */
		log.info("====== RECUPERACION DE INFORMACION  " + Util.params.getVentana());
		Service.getPoolID(Util.params.getVentana()).forEach(listTerminados::add);
		listTerminados.stream().forEach((p) -> {
			param = gson.fromJson(p.getConexionJson(), Credentials.class);
			Credentials opj = new Credentials(param.getUrl(), param.getUser(), encrypt.decryptBase64(param.getPass()),
					param.getDriver(), p.getScript());
			try {
				if (Service.getFechaById(Util.params.getFeInicio())==false) {
					list = Service.getList(opj);
					if (Util.params.getVentana() == 1) {
						time_start = System.currentTimeMillis();
						data_file = Service.getFile();
						Service.insert(data_file);
						log.info("======== RECUPERACION DE INFORMACION FILE: [{}] registros ",
								data_file);
						time_end = System.currentTimeMillis();
						double tiempo2 = (double) ((time_start - time_end) / 1000);
						log.info(
								"========= FIN RECUPERACION DE INFORMACION FILE  TIEMPO TRASCURRIDO [{}] ",
								tiempo2 + " segundos");
					}
					Service.insert(list);
				}else {
					log.info("========== LA FECHA INGRESADA YA ESTA CENTRALIZADA ");
					boolean bandera=Service.DeleteFechaById(Util.params.getFeInicio());
					log.info("========= REPROCESANDO............."+bandera);
					list = Service.getList(opj);
					if (Util.params.getVentana() == 1) {
						time_start = System.currentTimeMillis();
						data_file = Service.getFile();
						Service.insert(data_file);
						log.info("======= RECUPERACION DE INFORMACION FILE: [{}] registros ",
								data_file);
						time_end = System.currentTimeMillis();
						double tiempo2 = (double) ((time_start - time_end) / 1000);
						log.info(
								"======= FIN RECUPERACION DE INFORMACION FILE  TIEMPO TRASCURRIDO [{}] ",
								tiempo2 + " segundos");
					}
					Service.insert(list);
				}
				

			} catch (Exception e) {
				log.info("[{}]", ErrorCodes.cod02);
				log.error(e.getCause());

			}
		});

	}

	@Override
	public Dto_Workbook read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (list != null && list.iterator().hasNext()) {
			Dto_Workbook data;
			data = list.iterator().next();
			list.remove(data);
			return data;
		}

		return null;
	}
}

package com.eglobal.sirc.batch.flow;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.item.ItemWriter;

import com.eglobal.sirc.model_local.Dto_Workbook;
import com.eglobal.sirc.util.Util;


public class CentraliceWriter implements ItemWriter<Dto_Workbook> {

	private static final Logger log = LogManager.getLogger(CentraliceWriter.class);



	@AfterStep
	public void finalizando() {
		log.info("========== SE TERMINO LA CENTRALIZACION DE INFORMACION PARA  "+Util.params.getVentana());
	}


	@Override
	public void write(List<? extends Dto_Workbook> items) throws Exception {
		
		
	}
}

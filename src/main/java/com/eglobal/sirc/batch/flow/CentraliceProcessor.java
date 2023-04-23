package com.eglobal.sirc.batch.flow;

import org.springframework.batch.item.ItemProcessor;

import com.eglobal.sirc.model_local.Dto_Workbook;

public class CentraliceProcessor implements ItemProcessor<Dto_Workbook, Dto_Workbook> {

	@Override
	public Dto_Workbook process(Dto_Workbook item) throws Exception {
		return item;
	}

}
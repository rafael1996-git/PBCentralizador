package com.eglobal.sirc.dao;

import java.sql.SQLException;
import java.util.List;

import com.eglobal.sirc.model_local.Dto_ConnectionPool;
import com.eglobal.sirc.model_local.Dto_Workbook;
import com.eglobal.sirc.model_local.ciberark.Credentials;

public interface IServiceDao {
	
	public void  insert(List<Dto_Workbook>  data) throws Exception;
	public List<Dto_Workbook>  getFile() throws Exception;
	public List<Dto_Workbook> getList(Credentials credential) throws Exception;
	public List<Dto_ConnectionPool> getPoolID(int ventana)  ;
	public boolean getFechaById(String fecha) ;
	public boolean DeleteFechaById(String fecha) ;
}
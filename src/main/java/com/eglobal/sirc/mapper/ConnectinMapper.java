package com.eglobal.sirc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.eglobal.sirc.model_local.Dto_ConnectionPool;

public class ConnectinMapper implements RowMapper<Dto_ConnectionPool>{

	public Dto_ConnectionPool mapRow(ResultSet rs, int arg1) throws SQLException {
		Dto_ConnectionPool ps = new Dto_ConnectionPool();
		ps.setIdConnectionPool(rs.getString("id_connection_pool"));
		ps.setIdAdquirente(rs.getString("cod_adquirente_pais"));
		ps.setConexionJson(rs.getString("conexion_json"));
		ps.setScript(rs.getString("script"));
		ps.setDescripcion(rs.getString("descripcion"));
		return ps;
	}
}


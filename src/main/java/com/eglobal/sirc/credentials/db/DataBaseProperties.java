package com.eglobal.sirc.credentials.db;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.eglobal.sirc.encryptdecrypt.EncryptDecrypt;
import com.eglobal.sirc.model_local.ciberark.Credentials;

@Component
@PropertySource("classpath:db.properties")
public class DataBaseProperties {
	


	@Autowired
	private EncryptDecrypt encrypt;
	
	/* Credenciales locales  */

	@Value(value = "${local.postgres.pwd}")
	String pwd;
	
	@Value(value = "${local.postgres.usr}")
	String usr;
	
	@Value(value = "${local.postgres.driverClassName}")
	String drive;

	@Value(value = "${local.postgres.url}")
	String url;
	public Credentials getCredentials() {
		Credentials credentials = new Credentials();
		credentials.setUser(usr);
		credentials.setPass(encrypt.decryptBase64(pwd));
		credentials.setDriver(drive);
		credentials.setUrl(url);
		return credentials;
	}
}


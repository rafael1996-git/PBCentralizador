package com.eglobal.sirc.credentials.db;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.eglobal.sirc.model_local.ciberark.Credentials;

import javapasswordsdk.PSDKPassword;
import javapasswordsdk.PSDKPasswordRequest;
import javapasswordsdk.exceptions.PSDKException;

@Component
@PropertySource("classpath:ciberark.properties")
public class DataBaseCiberArk {
	
	private static final Logger log = LogManager.getLogger(DataBaseCiberArk.class);
	
	@Value(value = "${ciber.ark.appID}")
	String appID;
	
	@Value(value = "${ciber.ark.safe}")
	String safe;
	
	@Value(value = "${ciber.ark.folder}")
	String folder;
	
	@Value(value = "${ciber.ark.object}")
	String object;
	
	@Value(value = "${ciber.ark.reason}")
	String reason;
	
	@Value(value = "${local.postgres.driverClassName}")
	String drive;

	@Value(value = "${local.postgres.url}")
	String url;
	
	public Credentials getCredentials() {
	
		Credentials credentials = new Credentials();
		PSDKPassword password = null;
		String aux = "";
		char[] content = null;
		
		if(!appID.isEmpty() && !folder.isEmpty() && !object.isEmpty() && !reason.isEmpty()) {
			
			try {
				PSDKPasswordRequest passRequest = new PSDKPasswordRequest();
				log.info("Obteniendo credenciales de Ciber-Ark");

				// Set request properties
				passRequest.setAppID(appID);
				passRequest.setSafe(safe);
				passRequest.setFolder(folder);
				passRequest.setObject(object);
				passRequest.setReason(reason);
				
				// Get password object
				password = javapasswordsdk.PasswordSDK.getPassword(passRequest);
				
				// Get password content
				content = password.getSecureContent();
				for (int i = 0; i < content.length; i++) {
					aux += content[i];
				}
				credentials.setUser(password.getUserName());
				credentials.setPass(aux);
				credentials.setDriver(drive);
				credentials.setUrl(url);
//				credentials.setUrl(password.getAddress());
			} catch (PSDKException e) {
				log.error("Existe un error al obtener credenciales de Ciber-Ark", e);
				credentials=null;
			} finally {
				if (content != null) {
					// Clean the returned object
					Arrays.fill(content, (char) 0);
				}
				
				if (password != null) {
					try {
						// Dispose of resources used by this PSDKPassword object
						password.dispose();
					} catch (PSDKException ex) {
						log.error(DataBaseCiberArk.class.getName() + " ", ex);
					}
				}
			}
		} else {
			credentials = null;
		}
		
		return credentials;
	}
}

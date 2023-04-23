package com.eglobal.sirc.encryptdecrypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.springframework.stereotype.Component;

@Component
public class EncryptDecrypt {
	
	StandardPBEStringEncryptor stringEncryptor;
	EnvironmentStringPBEConfig eConfig;

	public EncryptDecrypt() {
		stringEncryptor = new StandardPBEStringEncryptor();
		eConfig = new EnvironmentStringPBEConfig();

		eConfig.setAlgorithm("PBEWithSHA1AndDESede");
		eConfig.setKeyObtentionIterations(100000);
		eConfig.setPassword("Aspis2014");

		stringEncryptor.setConfig(eConfig);
	}

	public String encryptBase64(String texto) {
		return stringEncryptor.encrypt(texto);
	}

	public String decryptBase64(String texto) {
		return stringEncryptor.decrypt(texto);
	}

//	public static void main(String[] args) {
//		EncryptDecrypt opj=new EncryptDecrypt();
//		System.out.println("encrip: "+opj.encryptBase64("ZM04nW*uosv5&v3p9"));
//		System.out.println("decrip: "+opj.decryptBase64("B5aNVy7YoPvyOlHd9B32HgK7U2y3AedES8OZdRFtkW0="));
//		
//	}
}

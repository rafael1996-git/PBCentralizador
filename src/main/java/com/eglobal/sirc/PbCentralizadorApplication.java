package com.eglobal.sirc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.eglobal.sirc.util.ErrorCodes;
import com.eglobal.sirc.util.Util;

@SpringBootApplication
@PropertySource("classpath:info.properties")
public class PbCentralizadorApplication implements CommandLineRunner {
	private static final Logger log = LogManager.getLogger(PbCentralizadorApplication.class);
	public static String finalError = "";
	
	@Value(value = "${version}")
	public String version;
	
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;
	

	
	public static void main(String[] args) {
		SpringApplication.run(PbCentralizadorApplication.class, args);
	}
	public void run(String... args) throws Exception {
		log.info("Version PB [{}]", version);
		log.info("Inicia la generación del reporte de PbCentralizado");
		try {			
			if(Util.validateParams(args)) {
				log.info("Se validaron los parametros. ");
				log.info("Inicia la ejecución del Proceso Batch");
				JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
				JobExecution execution = jobLauncher.run(job, jobParameters);
				log.info("El Job ha finalizado con un estatus [{}]", execution.getStatus());
			} else {
				log.info("SE ESPERA ARGUMENTOS [FECHA_INICIO],[FECHA_FIN],[VENTANA] REVALIDA Y VUELVE A INTENTAR: " + false);
				log.info("[{}]", ErrorCodes.cod01);
			}
		} catch (Exception e) {
			log.info("[{}]", ErrorCodes.cod02);
			log.error("ERROR main ",e);		
		}
		
		
		System.exit(0);

	}

}

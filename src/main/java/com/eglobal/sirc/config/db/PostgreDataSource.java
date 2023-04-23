package com.eglobal.sirc.config.db;

import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.eglobal.sirc.credentials.db.DataBaseCiberArk;
import com.eglobal.sirc.credentials.db.DataBaseProperties;
import com.eglobal.sirc.model_local.ciberark.Credentials;

@Configuration
@PropertySource("classpath:db.properties")
public class PostgreDataSource {
	public static Credentials DB_CONFIG = null;


	@Autowired
	private DataBaseProperties baseProperties;
	
	@Autowired
	private DataBaseCiberArk arkCredentials;
	
	/* Credenciales locales  */
	@Value(value = "${local.postgres.dialect}")
	String dialect;
	
	@Value(value = "${local.postgres.url}")
	String url;
	
	@Value(value = "${local.postgres.driverClassName}")
	String driver;	
	
	
	
	@Bean
	@Primary
	public DataSource geDataSource() {
		Credentials credentials = new Credentials();

		/* En caso de que utilizar ciberark  */
		credentials = arkCredentials.getCredentials();
//		new DatabaseConnection(credentials);
		DB_CONFIG=credentials;
		System.out.println("CREDENTIAL  CIBERARK: \n ");

		if (credentials == null) {
			/* En case de que utilizar properties  */
			credentials = baseProperties.getCredentials();
//			new DatabaseConnection(credentials);
			DB_CONFIG=credentials;
			System.out.println("CREDENTIAL LOCAL: \n ");


		}
		DriverManagerDataSource postgreDataSource = new DriverManagerDataSource();
		postgreDataSource.setDriverClassName(driver);
		postgreDataSource.setUrl(url);
		postgreDataSource.setUsername(credentials.getUser());
		postgreDataSource.setPassword(credentials.getPass());
		return postgreDataSource;
	}
	
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean emOracle() throws SQLException {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(geDataSource());
		em.setPackagesToScan(new String[] { com.eglobal.sirc.constants.DataSourceConstants.PACKAGE_ENTITIES_POSTGRE });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.dialect", dialect);
		
		em.setJpaPropertyMap(properties);
		em.afterPropertiesSet();

		return em;
	}

	@Bean
	public PlatformTransactionManager tmOracle() throws SQLException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emOracle().getObject());
		return transactionManager;
	}
}

package com.eglobal.sirc.config.batch;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.repeat.policy.DefaultResultCompletionPolicy;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.eglobal.sirc.batch.flow.CentraliceProcessor;
import com.eglobal.sirc.batch.flow.CentraliceReader;
import com.eglobal.sirc.batch.flow.CentraliceWriter;
import com.eglobal.sirc.config.db.PostgreDataSource;
import com.eglobal.sirc.model_local.Dto_Workbook;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class BatchConfig implements BatchConfigurer {
	
	/* Configuración por defecto para persistencia en memoria */
	PlatformTransactionManager transactionManager;
	JobRepository jobRepository;
	JobLauncher jobLauncher;
	JobExplorer jobExplorer;
	PostgreDataSource dataSource;
	
	@Override
	public JobRepository getJobRepository() {
		return jobRepository;
	}

	@Override
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	@Override
	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}

	@Override
	public JobExplorer getJobExplorer() {
		return jobExplorer;
	}

	@PostConstruct
	void initialize() throws Throwable {
		if (this.transactionManager == null) {
			this.transactionManager = new ResourcelessTransactionManager();
		}

		MapJobRepositoryFactoryBean jobRepositoryFactory = new MapJobRepositoryFactoryBean(this.transactionManager);
		jobRepositoryFactory.afterPropertiesSet();
		this.jobRepository = jobRepositoryFactory.getObject();

		MapJobExplorerFactoryBean jobExplorerFactory = new MapJobExplorerFactoryBean(jobRepositoryFactory);
		jobExplorerFactory.afterPropertiesSet();
		this.jobExplorer = jobExplorerFactory.getObject();
		this.jobLauncher = createJobLauncher();
	}

	private JobLauncher createJobLauncher() throws Throwable {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}

	@Bean
	public StepBuilderFactory stepBuilderFactory() {
		return new StepBuilderFactory(jobRepository, transactionManager);
	}

	@Bean
	public JobBuilderFactory jobBuilderFactory() {
		return new JobBuilderFactory(jobRepository);
	}
	
	/**
	 * Permite que toda la información obtenida en el reader se procese
	 * completamente en el processor y writer 
	 * @return
	 */
	@Bean
	public DefaultResultCompletionPolicy completionPolicy() {
		return new DefaultResultCompletionPolicy(); 
	}
	
	/* Configuración por cada spring batch */
	
	@Autowired
	public BatchConfig(PostgreDataSource dataSource) throws SQLException {
		this.dataSource = dataSource;

	}
	
	@Bean
	public Step step() {
		Step step = stepBuilderFactory()
				.get("saveLoad")
				.<Dto_Workbook, Dto_Workbook>chunk(completionPolicy())
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
		return step;
	}
	
	@Bean
	public Job job() {
		return jobBuilderFactory()
				.get("saveJob")
				.flow(step())
				.end()
				.build();
	}
	
	@Bean
	CentraliceReader reader() {
		return new CentraliceReader();
	}

	@Bean
	CentraliceProcessor processor() {
		return new CentraliceProcessor();
	}

	@Bean
	CentraliceWriter writer() {
		return new CentraliceWriter();
	}
}

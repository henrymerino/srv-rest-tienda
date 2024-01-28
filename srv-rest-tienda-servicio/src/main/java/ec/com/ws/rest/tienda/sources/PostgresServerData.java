package ec.com.ws.rest.tienda.sources;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ec.com.ws.rest.tienda.util.ApplicationUtil;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "ec.com.ws.rest.tienda.persistence.postgres.service" })
@EnableJpaRepositories(basePackages = "ec.com.ws.rest.tienda.persistence.postgres.repository", entityManagerFactoryRef = "postgresEntityManager", transactionManagerRef = "postgresTransactionManager")
public class PostgresServerData {

	@Autowired
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean postgresEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(sqlDataSource());
		em.setPackagesToScan("ec.com.ws.rest.tienda.persistence.postgres.entity");

		HashMap<String, Object> properties = BaseData.entityProperties(em, env);
		properties.put("hibernate.dialect", ApplicationUtil.getString("postgres.hibernate.dialect"));
		properties.put("hibernate.ddl-auto", ApplicationUtil.getString("hibernate.ddl-auto"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	public DataSource sqlDataSource() {
		AtomicReference<JndiDataSourceLookup> dataSourceLookup = new AtomicReference<>(new JndiDataSourceLookup());
		return dataSourceLookup.get()
				.getDataSource(Objects.requireNonNull(ApplicationUtil.getString("spring.datasource.primary.jndi-name")));
	}

	
	/*
	    @Bean
	    public DataSource sqlDataSource() {
	        return DataSourceBuilder.create()
	          .driverClassName("org.postgresql.Driver")
	          .url("jdbc:postgresql://localhost:5432/store")
	          .username("postgres")
	          .password("root")
	          .build();	
	    }
*/
	@Bean
	public PlatformTransactionManager postgresTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(postgresEntityManager().getObject());
		return transactionManager;
	}

}

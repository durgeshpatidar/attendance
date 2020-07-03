package com.incture.attendance.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.incture.attendance.*" })
@PropertySource(value = { "classpath:application.properties" })
public class DataSourceConfiguration {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment environment;

	private String url;
	private String user;
	private String password;

	public DataSourceConfiguration() {

		try {
			JSONObject jsonObj = new JSONObject(System.getenv("VCAP_SERVICES"));
			System.err.println("ENV : " + jsonObj);
			JSONArray jsonArr = jsonObj.getJSONArray("hana");
			JSONObject credentials = jsonArr.getJSONObject(0).getJSONObject("credentials");
			url = credentials.getString("url");
			user = credentials.getString("user");
			password = credentials.getString("password");
		} catch (JSONException e) {
			logger.error("[HibernateConfiguration] reading environmental variables failed:" + e.getMessage());
		}

	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.incture.attendance.entities" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("url"));
		dataSource.setUsername(environment.getRequiredProperty("user"));
		dataSource.setPassword(environment.getRequiredProperty("password"));
		return dataSource;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.jdbc.batch_size", environment.getRequiredProperty("hibernate.jdbc.batch_size"));
		properties.put("hibernate.cache.use_second_level_cache",
				environment.getRequiredProperty("hibernate.cache.use_second_level_cache"));
		properties.put("hibernate.order_inserts", Boolean.TRUE);
		properties.put("hibernate.order_updates", Boolean.TRUE);

		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}

}

package org.kmrn.spring.batch.cpc.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DbConfiguration {
	
	@Bean
	@Primary
	public DataSource dataSource() throws SQLException {
		final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriver(new org.hsqldb.jdbcDriver());
		//dataSource.setUrl("jdbc:hsqldb:mem:mydb");
		dataSource.setUrl("jdbc:hsqldb:file:/tmp/db/springbatch");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}
}

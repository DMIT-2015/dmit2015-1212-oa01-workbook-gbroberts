package common.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.annotation.sql.DataSourceDefinitions;
import jakarta.enterprise.context.ApplicationScoped;

@DataSourceDefinitions({

	@DataSourceDefinition(
		name="java:app/datasources/oracleHrDs",
		className="oracle.jdbc.xa.client.OracleXADataSource",
		url="jdbc:oracle:thin:@localhost:1521/xepdb1",
		user="HR",
		password="Password2015"),

})

@ApplicationScoped
public class ApplicationConfig {

}
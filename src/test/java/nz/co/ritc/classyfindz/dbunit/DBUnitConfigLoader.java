package nz.co.ritc.classyfindz.dbunit;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.oracle.OracleConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class DBUnitConfigLoader {

	@Autowired
	DataSource dataSource;
	
	@Bean(name="oracleConnection")
	@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public IDatabaseConnection oracleConnection() throws DatabaseUnitException, SQLException {
		final OracleConnection oracleConnection = new OracleConnection(dataSource.getConnection(), "M1CWDEV1");
		return oracleConnection;
	}
}

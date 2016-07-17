package nz.co.ritc.classyfindz.dbunit;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
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
	private DBUnitPrimaryKeyFilter advertRegistryHasLCategoryPrimaryKeyFilter;

	@Autowired
	DataSource dataSource;
	
	@Bean(name="oracleConnection")
	@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public IDatabaseConnection oracleConnection() throws DatabaseUnitException, SQLException {
		final OracleConnection oracleConnection = new OracleConnection(dataSource.getConnection(), "M1CWDEV1");
		final DatabaseConfig databaseConfig = oracleConnection.getConfig();
		//databaseConfig.setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, Boolean.TRUE);
		//databaseConfig.setProperty(DatabaseConfig.FEATURE_SKIP_ORACLE_RECYCLEBIN_TABLES, Boolean.TRUE);
		//databaseConfig.setProperty(DatabaseConfig.PROPERTY_PRIMARY_KEY_FILTER, advertRegistryHasLCategoryPrimaryKeyFilter);
		return oracleConnection;
	}
}

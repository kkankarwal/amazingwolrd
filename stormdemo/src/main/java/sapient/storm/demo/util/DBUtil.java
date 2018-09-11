package sapient.storm.demo.util;

import static sapient.storm.demo.constant.DemoConstants.DB_DRIVER2;
import static sapient.storm.demo.constant.DemoConstants.DB_URL;
import static sapient.storm.demo.constant.DemoConstants.PASS;
import static sapient.storm.demo.constant.DemoConstants.USER;

import java.util.Map;

import org.apache.storm.jdbc.common.HikariCPConnectionProvider;

import com.google.common.collect.Maps;

public class DBUtil {

	/**
	 * This method provides HikariCP for Hibernet Database Connection.
	 * @return HikariCPConnectionProvider
	 */
	public static HikariCPConnectionProvider configureDBConnectionProvider() {
		Map hikariConfigMap = Maps.newHashMap();
		hikariConfigMap.put("dataSourceClassName", DB_DRIVER2);
		hikariConfigMap.put("dataSource.url", DB_URL);
		hikariConfigMap.put("dataSource.user", USER);
		hikariConfigMap.put("dataSource.password", PASS);
		return new HikariCPConnectionProvider(hikariConfigMap);

	}
}

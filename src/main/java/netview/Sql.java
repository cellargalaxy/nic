package netview;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.beanutils.BeanUtils;
import util.JDBCMethod;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

/**
 * Created by cellargalaxy on 2017/4/20.
 */
public class Sql {
	private static final String ipTableName = Configuration.getIpTableName();
	
	private static DataSource dataSource;
	
	static {
		try {
			Properties properties = new Properties();
			File confFile = new File(new File(JDBCMethod.class.getResource("").getPath()).getParentFile().getAbsolutePath() + "/netviewSql.properties");
			properties.load(new FileInputStream(confFile));
			dataSource = DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Connection createConnection() throws SQLException {
		if (dataSource != null) {
			return dataSource.getConnection();
		} else {
			return null;
		}
	}
	
	/**
	 * 添加一个host
	 */
	protected static boolean addAddress(Host host) {
		Connection connection = null;
		try {
			connection = createConnection();
			connection.setAutoCommit(false);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("address", host.getAddress());
			map.put("building", host.getBuilding());
			map.put("floor", host.getFloor());
			map.put("model", host.getModel());
			map.put("name", host.getName());
			int i = JDBCMethod.insert(connection, ipTableName, map);
			if (i > 0) {
				connection.commit();
				return true;
			} else {
				connection.rollback();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return false;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 删除某个host
	 */
	protected static boolean delleteAddress(String address) {
		Connection connection = null;
		try {
			connection = createConnection();
			connection.setAutoCommit(false);
			int i = JDBCMethod.delete(connection, ipTableName, "address", address);
			if (i > -1) {
				connection.commit();
				return true;
			} else {
				connection.rollback();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return false;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 读取数据库全部的host
	 */
	protected static LinkedList<Host> findAllHosts(int times) {
		Connection connection = null;
		try {
			connection = createConnection();
			String sql = "select * from " + ipTableName;
			Map<String, Object>[] maps = JDBCMethod.selectTable(connection, sql);
			LinkedList<Host> hosts = new LinkedList<Host>();
			if (maps != null) {
				for (Map<String, Object> map : maps) {
					Host host = new Host(times);
					BeanUtils.populate(host, map);
					hosts.add(host);
				}
			}
			return hosts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	
}

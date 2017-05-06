package nic;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.beanutils.BeanUtils;
import util.BASE64;
import util.BeanMapJson;
import util.JDBCMethod;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

/**
 * Created by cellargalaxy on 2017/5/2.
 */
public class Sql {
	private static final String nicerTableName = Configuration.getNicerTableName();
	
	private static DataSource dataSource;
	
	static {
		try {
			Properties properties = new Properties();
			File confFile = new File(new File(JDBCMethod.class.getResource("").getPath()).getParentFile().getAbsolutePath() + "/nicSql.properties");
//			System.out.println(confFile.getAbsolutePath());
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
	 * 添加一个Nicer
	 */
	protected static boolean addNicer(Nicer nicer) {
		Connection connection = null;
		try {
			connection = createConnection();
			connection.setAutoCommit(false);
			Map map = BeanMapJson.beanToMap(nicer);
			map.put("password", BASE64.encryptBASE64((String) map.get("password")));
			int i = JDBCMethod.insert(connection, nicerTableName, map);
			if (i > 0) {
				connection.commit();
				return true;
			} else {
				connection.rollback();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} finally {
			if (connection != null) try {
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取某Nicer
	 */
	protected static Nicer findNicer(long id) {
		Connection connection = null;
		try {
			connection = createConnection();
			String sql = "select * from " + nicerTableName + " where id=?";
			Map<String, Object> map = JDBCMethod.selectRow(connection, sql, id);
			map.put("password", BASE64.decryptBASE64((String) map.get("password")));
			Nicer nicer = new Nicer();
			BeanUtils.populate(nicer, map);
			return nicer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) try {
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/**
	 * 更改一个Nicer
	 */
	protected static boolean changeNicer(Nicer nicer) {
		Connection connection = null;
		try {
			connection = createConnection();
			connection.setAutoCommit(false);
			Map map = BeanMapJson.beanToMap(nicer);
			map.put("password", BASE64.encryptBASE64((String) map.get("password")));
			int i = JDBCMethod.set(connection, nicerTableName, "id", nicer.getId(), map);
			if (i > 0) {
				connection.commit();
				return true;
			} else {
				connection.rollback();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} finally {
			if (connection != null) try {
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/**
	 * 删除一个Nicer
	 */
	protected static boolean deleteNicer(long id) {
		Connection connection = null;
		try {
			connection = createConnection();
			connection.setAutoCommit(false);
			int i = JDBCMethod.delete(connection, nicerTableName, "id", id);
			if (i > -1) {
				connection.commit();
				return true;
			} else {
				connection.rollback();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			if (connection != null) try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} finally {
			if (connection != null) try {
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取全部Nicer
	 */
	protected static LinkedList<Nicer> findAllNicer() {
		Connection connection = null;
		try {
			connection = createConnection();
			String sql = "select * from " + nicerTableName;
			Map<String, Object>[] maps = JDBCMethod.selectTable(connection, sql);
			LinkedList<Nicer> nicers = new LinkedList<Nicer>();
			if (maps != null) {
				for (Map<String, Object> map : maps) {
					map.put("password", BASE64.decryptBASE64((String) map.get("password")));
					Nicer nicer = new Nicer();
					BeanUtils.populate(nicer, map);
					nicers.add(nicer);
				}
			}
			return nicers;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) try {
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
}

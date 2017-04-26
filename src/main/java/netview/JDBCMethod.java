package netview;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by cellargalaxy on 2017/4/20.
 */
public class JDBCMethod {
	/*
	connection.setAutoCommit(false);
	connection.commit();
	try { connection.rollback(); } catch (Exception e1) { e1.printStackTrace(); }
	 */
	private static DataSource dataSource;
	
	static {
		try {
			Properties properties = new Properties();
			File confFile = new File(new File(JDBCMethod.class.getResource("").getPath()).getParentFile().getAbsolutePath() + "/netviewSql.conf");
			System.out.println(confFile.getAbsolutePath());
			properties.load(new FileInputStream(confFile));
			dataSource = DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection createConnection() throws SQLException {
		if (dataSource != null) {
			return dataSource.getConnection();
		} else {
			return null;
		}
	}
	
	//增删改
	public static int update(Connection connection, String sql, Object... objects) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = creratPreparedStatement(connection, sql, objects);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (preparedStatement != null) try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static PreparedStatement creratPreparedStatement(Connection connection, String sql, Object... objects) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		for (int i = 0; i < objects.length; i++) preparedStatement.setObject(i + 1, objects[i]);
		return preparedStatement;
	}
	
	//查所需的整个表
	public static Map<String, Object>[] selectTable(Connection connection, String sql, Object... objects) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = creratPreparedStatement(connection, sql, objects);
			resultSet = preparedStatement.executeQuery();
			resultSet.last();
			Map<String, Object>[] maps = new Map[resultSet.getRow()];
			resultSet.beforeFirst();
			int num = 0;
			String[] columnNames = selectColumnLabels(resultSet);
			while (resultSet.next()) {
				maps[num] = new HashMap<String, Object>();
				for (String columnName : columnNames) maps[num].put(columnName, resultSet.getObject(columnName));
				num++;
			}
			return maps;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (preparedStatement != null) try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (resultSet != null) try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//查所需行
	public static Map<String, Object> selectRow(Connection connection, String sql, Object... objects) {
		try {
			return selectTable(connection, sql, objects)[0];
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//查所需属性或者情况
	public static Object selectValue(Connection connection, String sql, Object... objects) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = creratPreparedStatement(connection, sql, objects);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getObject(1);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (preparedStatement != null) try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (resultSet != null) try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//查表列别名，当没有指定别名时自动用回原名
	public static String[] selectColumnLabels(ResultSet resultSet) throws SQLException {
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		String[] columnLabels = new String[resultSetMetaData.getColumnCount()];
		for (int i = 0; i < columnLabels.length; i++) {
			columnLabels[i] = resultSetMetaData.getColumnLabel(i + 1);
		}
		return columnLabels;
	}
}

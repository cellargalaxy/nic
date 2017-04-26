package netview;

import jdk.nashorn.internal.scripts.JD;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by cellargalaxy on 2017/4/20.
 */
public class Sql {
	private static final String ipTableName="ipaddress2";
	
	
	/**
	 * 添加一个host
	 */
	protected static int addAddress(Host host) {
		Connection connection = null;
		try {
			connection = JDBCMethod.createConnection();
			connection.setAutoCommit(false);
			String sql = "insert "+ipTableName+" (Building,floor,name,address,addDate) value(?,?,?,?,?)";
			int i = JDBCMethod.update(connection, sql, host.getBuilding(), host.getFloor(), host.getName(), host.getAddress(), new Date());
			if (i > -1) connection.commit();
			else connection.rollback();
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return -1;
		} finally {
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 删除某个host
	 */
	protected static int delleteAddress(String address) {
		Connection connection = null;
		try {
			connection = JDBCMethod.createConnection();
			connection.setAutoCommit(false);
			String sql = "delete from "+ipTableName+" where address=?";
			int i = JDBCMethod.update(connection, sql, address);
			if (i > -1) connection.commit();
			else connection.rollback();
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return -1;
		} finally {
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 读取数据库全部的host
	 */
	protected static LinkedList<Host> findAllHosts(int times) {
		Connection connection = null;
		try {
			connection = JDBCMethod.createConnection();
			String sql = "select * from "+ipTableName;
			Map<String, Object>[] maps = JDBCMethod.selectTable(connection, sql);
			LinkedList<Host> hosts = new LinkedList<Host>();
			if (maps != null) {
				for (Map<String, Object> map : maps) {
					hosts.add(new Host((String) map.get("building"), new Integer( map.get("floor").toString()), (String) map.get("name"), (String) map.get("address"), times));
				}
			}
			return hosts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}

package netview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cellargalaxy on 2017/4/20.
 */
public class JDBCMethod {
	
	//增删改
	public static int update(Connection connection,String sql,Object...objects) {
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) preparedStatement.setObject(i+1, objects[i]);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			if(preparedStatement!=null) try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
	
	public static ResultSet query(Connection connection, String sql, Object...objects) {
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) preparedStatement.setObject(i+1, objects[i]);
			return preparedStatement.executeQuery();
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}finally {
			if(preparedStatement!=null) try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
	}
	
	//查所需的整个表
	public static Map<String, Object>[] selectTable(Connection connection,String sql,Object...objects) {
		ResultSet resultSet= query(connection,sql,objects);
		try {
			if (resultSet!=null) {
				resultSet.last();
				Map<String,Object>[] maps=new Map[resultSet.getRow()];
				resultSet.beforeFirst();
				int num=0;
				String[] columnNames=getColumnLabels(resultSet);
				while (resultSet.next()) {
					maps[num]=new HashMap<String, Object>();
					for(String columnName:columnNames) maps[num].put(columnName, resultSet.getObject(columnName));
					num++;
				}
				return maps;
			}else {
				return null;
			}
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}finally {
			if(resultSet!=null) try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
	}
	//查所需行
	public static Map<String, Object> getRow(Connection connection,String sql,Object...objects) throws SQLException {
		return selectTable(connection, sql, objects)[0];
	}
	
	//查所需属性或者情况
	public static Object getValue(Connection connection,String sql,Object...objects) {
		ResultSet resultSet= query(connection,sql,objects);
		try {
			if (resultSet!=null&&resultSet.next()) {
				return resultSet.getObject(1);
			}else {
				return null;
			}
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}finally {
			if(resultSet!=null) try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
	
	
	//查表列别名，当没有指定别名时自动用回原名
	public static String[] getColumnLabels(ResultSet resultSet) throws SQLException {
		ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
		String[] columnLabels=new String[resultSetMetaData.getColumnCount()];
		for (int i = 0; i < columnLabels.length; i++) {
			columnLabels[i]=resultSetMetaData.getColumnLabel(i+1);
		}
		return columnLabels;
	}
}

package netview;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cellargalaxy on 2017/4/20.
 */
public class Sql {
	private Connection connection;
	
	public Sql(String user,String password) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://202.116.150.40:3306/netview?user="+user+"&password="+password+"&useUnicode=true&characterEncoding=UTF8";
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(url);
	}
	
	
	
	/**
	 * 读取数据库全部的host
	 */
	//查表列别名
	public static String[] getColumnLabels(ResultSet resultSet) {
		try {
			ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
			String[] columnLabels=new String[resultSetMetaData.getColumnCount()];
			for (int i = 0; i < columnLabels.length; i++) {
				columnLabels[i]=resultSetMetaData.getColumnLabel(i+1);
			}
			return columnLabels;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) throws Exception{
//		Connection connection=new Sql("root","root").connection;
//		String sql="select address,host from ipaddress";
//		PreparedStatement preparedStatement=connection.prepareStatement(sql);
//		ResultSet resultSet=preparedStatement.executeQuery();
//		Map<String,Object>[] maps;
//		resultSet.last();
//		maps=new Map[resultSet.getRow()];
//		resultSet.beforeFirst();
//		int num=0;
//		String[] columnNames=getColumnLabels(resultSet);/////////////////////////////////////////////////
//		while (resultSet.next()) {
//			maps[num]=new HashMap<String, Object>();
//			for(String columnName:columnNames){
//				maps[num].put(columnName, resultSet.getObject(columnName));
//			}
//			num++;
//		}
//		for (Map<String, Object> map : maps) {
//			System.out.println(map);
//		}
		
		
	}
}

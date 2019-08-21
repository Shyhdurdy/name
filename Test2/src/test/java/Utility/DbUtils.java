package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DbUtils {

	private static Connection conn;
	private static Statement statement;
	private static ResultSet rs;
	
	

	public static void createConnection() {
		try {
			conn = DriverManager.getConnection(ConfigsReader.getProperty("dbUrl"),
					ConfigsReader.getProperty("dbUsername"), ConfigsReader.getProperty("dbPassword"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*@param String
	 * 
	 * Method will return you List of maps with your data from DB
	 * 
	 * @return List
	 * 
	 */
	
	
	public static List<Map<String, String>> getResultSetData(String sqlQuary) {

		List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(sqlQuary);
			ResultSetMetaData rsMetaData = rs.getMetaData();

			while (rs.next()) {
				Map<String, String> map = new LinkedHashMap<>();

				for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
					map.put(rsMetaData.getColumnName(i), rs.getObject(i).toString());
				}
				rsList.add(map);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rsList;
		

	}
	/*
	 * 
	 * Method will close all db connections
	 */
	
	public static void closeConnection() {
		
		try {
			
			if(rs!=null) {rs.close();}
			if(statement!=null) {statement.close();}
			if(conn!=null) {conn.close();}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	

}

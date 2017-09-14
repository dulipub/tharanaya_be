package tharanaya.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DbHandler {
	
	private ConnDB conDB;
	private Connection con;
	
	public DbHandler(){
		conDB = new ConnDB();
		con = conDB.getDBConnection();
	}

	public boolean setData(String query, Object... values){
		
		try {
			PreparedStatement statement = con.prepareStatement(query);
			int index = 1;
			
			for(Object obj : values){
				statement.setObject(index, obj);
				index++;
			}
			int check = statement.executeUpdate();
			if(check >=0){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet getData(String query, Object... objects){
		ResultSet rs = null;
		try {
			PreparedStatement statement = con.prepareStatement(query);
			int index = 1;
			for(Object a : objects){
				statement.setObject(index, a);
				index++;
			}
			rs = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	//overload for getData in case it's select all
	public ResultSet getData(String query){
		ResultSet rs = null;
		try {
			PreparedStatement statement = con.prepareStatement(query);
			rs = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean runQuery(String query, Object...objects){
		boolean isExe = false;
		try {
			PreparedStatement statement = con.prepareStatement(query);
			int index = 1;
			for(Object a : objects){
				statement.setObject(index, a);
				index++;
			}
			isExe = statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExe;
	}
	//overloading of runQuery in case no values are passed
	public boolean runQuery(String query){
		boolean isExe = false;
		try {
			PreparedStatement statement = con.prepareStatement(query);
			isExe = statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExe;
	}
}

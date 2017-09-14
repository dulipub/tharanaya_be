package tharanaya.logic;

import tharanaya.dto.Tour;
import tharanaya.utils.DbHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDB extends CrudLogic{
	
	private String tableName="tour"; //table information
	private String[] fields; //table fields
	private DbHandler handle; //the db-handler which we send raw queries to make prepared statements and operate on db

	/*
	 * (non-Javadoc)
	 * In here we implement all the database related activities
	 * therefore we need to know about the table we are going to use crud queries
	 * so we need field info and table name
	 */
	public TourDB() {
		setFields();
		handle = new DbHandler();//all the database functions are passed to this object
	}
	
	@Override
	protected void setFields() {
		String[] newfields = {"tourID","tName","location","ndays"};//the table fields in order
		fields = newfields;
	}
	
	//we do not need to set data in mvc, clear separation of data and logic. If the variable types
	//change we will have to change two places
	/*private void setData(Tour tour){
		this.tourID = tour.getTourID();
		this.tName = tour.getTourName();
		this.nDays = tour.getnDays();
		this.location = tour.getLocation();
	}
	*/
	private ArrayList setList(ResultSet rs) throws SQLException{
		ArrayList<Tour> tours = new ArrayList<Tour>();
		while(rs.next()){
			Tour rt = new Tour();
			rt.setTourID(rs.getInt("tourID"));
			rt.setTourName(rs.getString("tName"));
			rt.setLocation(rs.getString("location"));
			rt.setnDays(rs.getInt("ndays"));
			tours.add(rt);
		}	
		return tours;
	}

	//select all data(limit to 200 rows) of the tour table
	public ArrayList<Tour> selectAll() throws SQLException {
		ResultSet rs = null;
		ArrayList<Tour> tourList = new ArrayList<Tour>();// result set of tours
		String query = "SELECT * FROM "+tableName+" LIMIT 200;";
		rs = handle.getData(query);
		tourList = setList(rs);
		return tourList;
	}

	public ArrayList<Tour> selectRow(int id) throws SQLException {
		ResultSet rs = null;
		ArrayList<Tour> tourList = new ArrayList<Tour>();// result set of tours
		String query = "SELECT * FROM "+tableName+" WHERE id=? LIMIT 200;";
		rs = handle.getData(query,id);
		tourList = setList(rs);
		return tourList;
	}
	
	//insert data into tour table
	public boolean insert(Tour data) {
		//tour name is unique now therefore cannot insert tours with same name
			boolean check = false;
			//mysql statement
			String query = "INSERT INTO "+tableName+"(tName,location,ndays) VALUES(?,?,?);";
			//generic handling of inserting data to a table
			check = handle.setData(query,data.getTourName(),data.getLocation(),data.getnDays());
			return check;
	}

	public boolean update(Tour data) {
		//as the tourID is auto increment same row can be inserted twice
		boolean check = false;
		//mysql statement
		String query = "UPDATE "+tableName+" SET tName=?,location=?,ndays=? WHERE tourID=?;";
		//generic handling of updating table
		check = handle.setData(query,data.getTourName(),data.getLocation(),data.getnDays(),data.getTourID());
		return check;
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	
}

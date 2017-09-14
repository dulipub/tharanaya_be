package tharanaya.dto;

public class Tour {
	
	private String errorMsg = "";
	private int tourID;
	private String tName;
	private int nDays;
	private String location;
	
	//for use in result sets, ie select query
	public Tour() {}
	
	//used to set data from the servlet
	public boolean setAll(String id, String name, String days, String loc){
		boolean isSet = false;
		int num_id,num_days;
		
		if(id==null||id.equals("")){
			errorMsg = "Tour id is not set";
		} else if(name==null||name.equals("")){
			errorMsg = "Tour Name is not set";
		}else if(days==null||days.equals("")){
			errorMsg = "Tour days is not set";
		}else if(loc==null||loc.equals("")){
			errorMsg = "Tour location is not set";
		}else{
			try{
				num_id = Integer.parseInt(id);
				num_days = Integer.parseInt(days);
				setTourID(num_id);
				setTourName(name);
				setnDays(num_days);
				setLocation(loc);
				isSet = true;
			}catch (Exception e) {
				errorMsg = "Values are not of correct type";
			}
		}
		return isSet;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public int getTourID() {
		return tourID;
	}
	public void setTourID(int tourID) {
		this.tourID = tourID;
	}
	
	public String getTourName() {
		return tName;
	}
	public void setTourName(String tourName) {
		tourName.toLowerCase();
		tourName.trim();//remove whitespace at beginning or end
		this.tName = tourName;
	}
	public int getnDays() {
		return nDays;
	}
	public void setnDays(int nDays) {
		this.nDays = nDays;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String loc) {
		loc.toLowerCase();
		loc.trim();//remove whitespace at beginning or end
		this.location = loc;
	}
}

package solutions.egen.model;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Reservation {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Date date;
	private Time startTime;
	private Time endTime;
	private short partySize;
	private String specialOccasion;
	private int tableNumber;
	
	Calendar currentDate = Calendar.getInstance(); //Get the current date
	SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss"); //format it as per your requirement
	String dateNow = formatter.format(currentDate.getTime());
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public int getTableNumber() {
		return tableNumber;
	}
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	public short getPartySize() {
		return partySize;
	}
	public void setPartySize(short partySize) {
		this.partySize = partySize;
	}
	public String getSpecialOccasion() {
		return specialOccasion;
	}
	public void setSpecialOccasion(String specialOccasion) {
		this.specialOccasion = specialOccasion;
	}
	
	

}

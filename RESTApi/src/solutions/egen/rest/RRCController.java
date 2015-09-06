package solutions.egen.rest;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import solutions.egen.model.Employee;
import solutions.egen.model.Reservation;

@Api(tags = {"eatery"})
public class RRCController {

	private String page_status= "Customer";
	private int manager_id;
	//Login as manager
	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Login", notes = "Login as manager")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public boolean managerLogin(String user, String pwd){
		List <Employee> emp= null;
		EmployeeController ec = new EmployeeController();
		emp = ec.getAll();
		for(Employee elmt: emp)
			if (user==elmt.getLogin()){
				if (pwd==elmt.getPassword()){
					manager_id=elmt.getId();
					page_status="Manager";
					return true;
				}
			}
		return false;
	}
	
	//Logout
	@Path("/logout")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Logout", notes = "Logout from manager page")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public boolean managerLogout(String user, String pwd){
		if (page_status.equals("Manager")){
			manager_id=0;
			page_status = "Customer";
			return true;
		}
		return false;

	}
	
	//Reserve a table as customer
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Reserve a table", notes = "Reservation form by customer")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Reservation CustomerReservation(Reservation res){
		ReservationController rc = new ReservationController();
			res = rc.create(res);
		return res;
	}
	
	//Reserve a table as Manager
	@POST
	@Path("/reserve")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Reserve a table", notes = "Reservation form by customer")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Reservation CustomerReservation(Reservation res, int empId){
		EmployeeController ec = new EmployeeController();
		Employee emp = ec.get(empId);
		System.out.println("Reservation Created by Manager "+ emp.getFirstName()+" "+emp.getLastName() );
		ReservationController rc = new ReservationController();
			Reservation rest = rc.create(res);
		return rest;
	}
	
	//Get all reservations
	@GET
	@Path("/reserve/all")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Reservation Table", notes = "Finds all Reservations in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List <Reservation> resTable() {
		if (page_status.equals("Manager")){
			List<Reservation> reservations = null;
			ReservationController rc = new ReservationController();
			reservations = rc.getAll();
			return reservations;
		}
		return null;
	}

	//Get Seating Arrangement
	@GET
	@Path("/seating/all")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Seating Table", notes = "Finds Seating arrangement")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void seatingTable(Date date, Time start_time, Time end_time) {
		if (page_status.equals("Manager")){
			List<Reservation> reservations = null;
			ReservationController rc = new ReservationController();
			reservations = rc.getAll();
			for (Reservation elmt:reservations){
				//if ((date==elmt.getDate())&&(start_time.isBefore(elmt.getStartTime()))&&(end_time.isAfter(elmt.getEndTime())))
				System.out.println(elmt.getTableNumber()+" "+elmt.getFirstName()+" "+elmt.getLastName()+" "+elmt.getPhone());
			}
			
		}
	}
	
	//Manage Profile UPdate
	@GET
	@Path("/profile")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update", notes = "Updates an Reservation")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Employee managerProfile(){
		if (page_status.equals("Manager")){
			EmployeeController ec = new EmployeeController();
			return ec.get(manager_id);
		}
		return null;
	}

	//Manage Profile UPdate
	@PUT
	@Path("profile/change")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update", notes = "Updates an Reservation")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void update(){
		if (page_status.equals("Manager")){
			EmployeeController ec = new EmployeeController();
			ec.update(manager_id, ec.get(manager_id));
		}
	}
		
	//Manage Settings

}

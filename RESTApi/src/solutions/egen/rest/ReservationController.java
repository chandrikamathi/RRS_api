package solutions.egen.rest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import solutions.egen.dao.ReservationDAO;
import solutions.egen.exception.AppException;
import solutions.egen.model.Reservation;

@Path("/reservations")
@Api(tags = { "reservations" })
public class ReservationController {

	
	//R GET ALL
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find All Reservations", notes = "Finds all Reservations in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List <Reservation> getAll() {
		List<Reservation> reservations = null;
		try {
			ReservationDAO rdao = new ReservationDAO();
			reservations = rdao.getAllReservations();
		} catch (AppException ae) {
			// TODO Auto-generated catch block
			ae.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return reservations;
		
	}
	
	//R GET
	
	@GET
	@Path("/{id}")	
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find One", notes = "Finds an Reservation with its id in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	
	public Reservation get(@PathParam("id") int resId){
		Reservation res;
		
		try {
			ReservationDAO rdao = new ReservationDAO();
			res = rdao.getOne(resId);
			if (res == null) {
				throw new WebApplicationException(Status.NOT_FOUND);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		return res;
	}
	
	//C POST
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create", notes = "Creates an Reservation")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Reservation create(Reservation res){
		try {
			ReservationDAO rdao = new ReservationDAO();
			res = rdao.createOne(res);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return res;
	}
	
	//U PUT
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update", notes = "Updates an Reservation")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void update(@PathParam("id") int resId, Reservation res){
		try {
			ReservationDAO rdao = new ReservationDAO();
			rdao.updateOne(resId, res);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	//D DELETE
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Delete", notes = "Deletes an Reservation")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void delete(@PathParam("id") int resId){
		try {
			ReservationDAO rdao = new ReservationDAO();
			rdao.deleteOne(resId);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}

}


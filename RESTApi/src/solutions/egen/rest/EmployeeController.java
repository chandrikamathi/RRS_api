package solutions.egen.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

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

import solutions.egen.dao.EmployeeDAO;
import solutions.egen.exception.AppException;
import solutions.egen.model.Employee;

@Path("/employees")
@Api(tags = { "employees" })
public class EmployeeController {
	
	
	//R GET ALL
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find All Employees", notes = "Finds all employees in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Employee> getAll() {
		List<Employee> employees = null;
		try {
			EmployeeDAO dao = new EmployeeDAO();
			employees = dao.getAllEmployees();
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return employees;
	}
	
	//R GET
	@GET
	@Path("/{id}")	
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find Employee by ID", notes = "Finds an employee with its id in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	
	public Employee get(@PathParam("id") int empId){
		Employee emp;
		
		try {
			EmployeeDAO edao = new EmployeeDAO();
			emp = edao.getOne(empId);
			if (emp == null) {
				throw new WebApplicationException(Status.NOT_FOUND);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		return emp;
	}
	
	//C POST
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create", notes = "Creates an employee")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Employee create(Employee emp){
		try {
			EmployeeDAO edao = new EmployeeDAO();
			emp = edao.createOne(emp);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return emp;
	}
	
	//U PUT
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update", notes = "Updates an employee")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void update(@PathParam("id") int empId, Employee emp){
		try {
			EmployeeDAO edao = new EmployeeDAO();
			edao.updateOne(empId, emp);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	//D DELETE
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Delete", notes = "Deletes an employee")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void delete(@PathParam("id") int empId){
		try {
			EmployeeDAO edao = new EmployeeDAO();
			edao.deleteOne(empId);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}

}

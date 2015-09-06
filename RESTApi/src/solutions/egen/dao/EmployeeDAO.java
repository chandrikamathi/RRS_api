package solutions.egen.dao;
import solutions.egen.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import solutions.egen.exception.AppException;
import solutions.egen.model.Employee;

public class EmployeeDAO{
	public List<Employee> getAllEmployees() throws AppException {
		List<Employee> emp_list = new ArrayList<Employee>();
		Connection conn = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		

		try {
			ps = conn.prepareStatement("SELECT * FROM employee");
			rs = ps.executeQuery();
			
			while(rs.next()){
				Employee emp = new Employee();
				emp.setId(rs.getInt("ID"));
				emp.setFirstName(rs.getString("FIRST_NAME"));
				emp.setLastName(rs.getString("LAST_NAME"));
				emp.setPhone(rs.getString("PHONE"));
				emp.setEmail(rs.getString("EMAIL"));
				emp.setAddress1(rs.getString("ADDRESS1"));
				emp.setAddress2(rs.getString("ADDRESS2"));
				emp.setCity(rs.getString("CITY"));
				emp.setState(rs.getString("STATE"));
				emp.setZip(rs.getInt("ZIP"));
				emp.setLogin(rs.getString("LOGIN_USR"));
				emp.setPassword(rs.getString("LOGIN_PWD"));
				
				emp_list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		finally {
			DBUtils.closeResource(ps, rs, conn);
		}
	
		return emp_list;
	}
	
	public Employee getOne(int id) throws AppException {
		Connection conn = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee emp = null;		

		try {
			ps = conn.prepareStatement("SELECT * FROM employee WHERE ID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			
			if (rs.next()){
				emp = new Employee();
				emp.setId(rs.getInt("ID"));
				emp.setFirstName(rs.getString("FIRST_NAME"));
				emp.setLastName(rs.getString("LAST_NAME"));
				emp.setPhone(rs.getString("PHONE"));
				emp.setEmail(rs.getString("EMAIL"));
				emp.setAddress1(rs.getString("ADDRESS1"));
				emp.setAddress2(rs.getString("ADDRESS2"));
				emp.setCity(rs.getString("CITY"));
				emp.setState(rs.getString("STATE"));
				emp.setZip(rs.getInt("ZIP"));
				emp.setLogin(rs.getString("LOGIN_USR"));
				emp.setPassword(rs.getString("LOGIN_PWD"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		finally {
			DBUtils.closeResource(ps, rs, conn);
		}
	
		return emp;
	}
	
	public Employee createOne(Employee emp) throws AppException {

		Connection conn = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("INSERT INTO employee (FIRST_NAME, LAST_NAME, EMAIL, ADDRESS1, ADDRESS2, CITY, ZIP, PHONE, STATE, LOGIN_USR, LOGIN_PWD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, emp.getFirstName());
			ps.setString(2,  emp.getLastName());
			ps.setString(3, emp.getEmail());
			ps.setString(4,  emp.getAddress1());
			ps.setString(5,  emp.getAddress2());
			ps.setString(6,  emp.getCity());
			ps.setInt(7, emp.getZip());
			ps.setString(8, emp.getPhone());
			ps.setString(9, emp.getState());
			ps.setString(10, emp.getLogin());
			ps.setString(11, emp.getPassword());
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				emp.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		finally {
			DBUtils.closeResource(ps, rs, conn);
		}

		return emp;
	}
	
	public void updateOne(int id, Employee emp) throws AppException {

		Connection conn = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("UPDATE employee SET FIRST_NAME=?, LAST_NAME=?, EMAIL=?, ADDRESS1=?, ADDRESS2=?, CITY=?, ZIP=?, PHONE=?, STATE=? WHERE ID=?",  PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, emp.getFirstName());
			ps.setString(2,  emp.getLastName());
			ps.setString(3, emp.getEmail());
			ps.setString(4,  emp.getAddress1());
			ps.setString(5,  emp.getAddress2());
			ps.setString(6,  emp.getCity());
			ps.setInt(7, emp.getZip());
			ps.setString(8, emp.getPhone());
			ps.setString(9, emp.getState());
			ps.setString(10, emp.getLogin());
			ps.setString(11, emp.getPassword());
			ps.setInt(12, id);
		
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				emp.setId(rs.getInt(1));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		finally {
			DBUtils.closeResource(ps, rs, conn);
		}
	}
	public void deleteOne(int id) throws AppException {

		Connection conn = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("DELETE FROM employee WHERE ID =?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		finally {
			DBUtils.closeResource(ps, rs, conn);
		}

	}
}


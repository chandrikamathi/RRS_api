package solutions.egen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import solutions.egen.exception.AppException;
import solutions.egen.model.Reservation;
import solutions.egen.util.DBUtils;

public class ReservationDAO {
	public List<Reservation> getAllReservations() throws AppException{
		
		List<Reservation> res_list = new ArrayList<Reservation>();
		Connection conn = DBUtils.resConnect();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM reservation");
			rs = ps.executeQuery();
			
			while(rs.next()){
				Reservation res = new Reservation();
				res.setId(rs.getInt("ID"));
				res.setFirstName(rs.getString("FIRST_NAME"));
				res.setLastName(rs.getString("LAST_NAME"));
				res.setPhone(rs.getString("PHONE"));
				res.setEmail(rs.getString("EMAIL"));
				res.setDate(rs.getDate("DATE1"));
				res.setStartTime(rs.getTime("START_TIME"));
				res.setEndTime(rs.getTime("END_TIME"));
				res.setTableNumber(rs.getInt("TABLE_NUMBER"));
				res.setPartySize(rs.getShort("PARTY_SIZE"));
				res.setSpecialOccasion(rs.getString("SPECIAL_NEED"));

				
				res_list.add(res);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		
		finally {
			DBUtils.closeResource(ps, rs, conn);
		}
		
		return res_list;
	}
	
	public Reservation getOne(int id) throws AppException {
		Connection conn = DBUtils.resConnect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Reservation res = null;		

		try {
			ps = conn.prepareStatement("SELECT * FROM Reservation WHERE ID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			
			if (rs.next()){
				res = new Reservation();
				res.setId(rs.getInt("ID"));
				res.setFirstName(rs.getString("FIRST_NAME"));
				res.setLastName(rs.getString("LAST_NAME"));
				res.setPhone(rs.getString("PHONE"));
				res.setEmail(rs.getString("EMAIL"));
				res.setDate(rs.getDate("DATE1"));
				res.setStartTime(rs.getTime("START_TIME"));
				res.setEndTime(rs.getTime("END_TIME"));
				res.setTableNumber(rs.getInt("TABLE_NUMBER"));
				res.setPartySize(rs.getShort("PARTY_SIZE"));
				res.setSpecialOccasion(rs.getString("SPECIAL_NEED"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		finally {
			DBUtils.closeResource(ps, rs, conn);
		}
	
		return res;
	}
	
	public Reservation createOne(Reservation res) throws AppException {

		Connection conn = DBUtils.resConnect();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("INSERT INTO reservation (FIRST_NAME, LAST_NAME, EMAIL, PHONE, DATE1, START_TIME, END_TIME, TABLE_NUMBER, PARTY_SIZE, SPECIAL_NEED) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, res.getFirstName());
			ps.setString(2,  res.getLastName());
			ps.setString(3, res.getEmail());
			ps.setString(4, res.getPhone());
			ps.setDate(5, res.getDate());
			ps.setTime(6, res.getStartTime());
			ps.setTime(7, res.getEndTime());
			ps.setInt(8, res.getTableNumber());
			ps.setShort(9, res.getPartySize());
			ps.setString(10, res.getSpecialOccasion());
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				res.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		finally {
			DBUtils.closeResource(ps, rs, conn);
		}

		return res;
	}
	
	public void updateOne(int id, Reservation res) throws AppException {

		Connection conn = DBUtils.resConnect();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("UPDATE reservation SET FIRST_NAME=?, LAST_NAME=?, EMAIL=?, PHONE=?, DATE1=?, START_TIME=?, END_TIME=?, TABLE_NUMBER=?, PARTY_SIZE=?, SPECIAL_NEED=? WHERE ID=?",  PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, res.getFirstName());
			ps.setString(2,  res.getLastName());
			ps.setString(3, res.getEmail());
			ps.setString(4, res.getPhone());
			ps.setDate(5, res.getDate());
			ps.setTime(6, res.getStartTime());
			ps.setTime(7, res.getEndTime());
			ps.setInt(8, res.getTableNumber());
			ps.setShort(9, res.getPartySize());
			ps.setString(10, res.getSpecialOccasion());
			ps.setInt(11, id);
		
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				res.setId(rs.getInt(1));
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

		Connection conn = DBUtils.resConnect();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("DELETE FROM reservation WHERE ID =?");
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




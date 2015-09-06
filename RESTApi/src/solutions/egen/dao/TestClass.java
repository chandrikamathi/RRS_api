package solutions.egen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import solutions.egen.exception.AppException;
import solutions.egen.model.Reservation;
import solutions.egen.util.DBUtils;

public class TestClass {
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
	public static void main(String[] args){
		TestClass tc = new TestClass();
		Reservation res = new Reservation();
	    try {
			tc.updateOne(1011, res);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package hms.dao;

import hms.bean.Patient;
import hms.credentials.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class PatientDao {
	public Integer addPatient(Patient p)
	{
		int cnt = 0;
		try {
			Connection con = DbConn.getConnection();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date1 = p.getDoj();
			String strDate = sdf.format(date1);
			Date d1 = (Date) sdf.parse(strDate);
			String query = "insert into patient(ws_ssn,ws_pat_name,address,ws_age,ws_doj,ws_rtype,ws_city,ws_state,ws_status) values (?,?,?,?,?,?,?,?,?);";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setInt(1, p.getSsn_id());
			pStmt.setString(2, p.getPat_name());
			pStmt.setString(3, p.getAddress());
			pStmt.setInt(4, p.getAge());
			pStmt.setString(5, p.getDoj());
			//pStmt.setDate(5, d1);
			pStmt.setString(6, p.getTob());
			pStmt.setString(7, p.getCity());
			pStmt.setString(8, p.getState());
			pStmt.setString(9, "Active");
			
			
			cnt = pStmt.executeUpdate();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cnt;
	}
	
	public Patient searchId(Integer pat_id)
	{
		Patient c = new Patient();
		try {
			Connection con = DbConn.getConnection();
			String query = "select ws_ssn,ws_pat_id,ws_pat_name,ws_age,ws_doj,ws_rtype,address,ws_city,ws_state from patient where ws_ssn = ?;";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setInt(1, pat_id);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				c.setSsn_id(rs.getInt(1));
				c.setPat_id(rs.getInt(2));
				c.setPat_name(rs.getString(3));
				c.setAge(rs.getInt(4));
				c.setDoj(rs.getString(5));
				c.setTob(rs.getString(6));
				c.setAddress(rs.getString(7));
				c.setCity(rs.getString(8));
				c.setState(rs.getString(9));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return c;
		
	}
	
	public Integer deletePatient(Integer ssn_id)
	{
		int cnt = 0;
		try {
			
			Connection con = DbConn.getConnection();
			String query1 = "delete from patient where ws_ssn = ?;";
			PreparedStatement pStmt = con.prepareStatement(query1);
			pStmt.setInt(1, ssn_id);
			cnt = pStmt.executeUpdate();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return cnt;
		
	}
	
	public Integer updatePatient(Patient p)
	{
		int cnt = 0;
		try {
			Connection con = DbConn.getConnection();
			
			System.out.println("Updte Patient Details");
			String query1 = "update patient set ws_pat_name = ?, ws_age = ?, ws_doj = ?, ws_rtype = ?, address = ?, ws_city = ?, ws_state = ?  where ws_ssn = ? ;";
			PreparedStatement pStmt = con.prepareStatement(query1);
			pStmt.setString(1, p.getPat_name());
			pStmt.setInt(2, p.getAge());
			pStmt.setString(3, p.getDoj());
			pStmt.setString(4, p.getTob());
			pStmt.setString(5, p.getAddress());
			pStmt.setString(6, p.getCity());
			pStmt.setString(7, p.getState());
			pStmt.setInt(8, p.getSsn_id());
			
			cnt = pStmt.executeUpdate();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{ 
			e.printStackTrace();
		}
		return cnt;
	}
	
	public Patient viewId(String ssn_id)
	{
		Patient c = new Patient();
		try {
			Connection con = DbConn.getConnection();
			PreparedStatement pStmt;
			Integer ssn_id1 = Integer.parseInt(ssn_id);
			String query = "select ws_ssn,ws_pat_id,ws_pat_name,ws_age,ws_doj,ws_rtype,address,ws_city,ws_state from patient where ws_ssn = ? and ws_status='Active';";
			pStmt = con.prepareStatement(query);
			pStmt.setInt(1,ssn_id1);				
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				c.setSsn_id(rs.getInt(1));
				c.setPat_id(rs.getInt(2));
				c.setPat_name(rs.getString(3));
				c.setAge(rs.getInt(4));
				c.setDoj(rs.getString(5));
				c.setTob(rs.getString(6));
				c.setAddress(rs.getString(7));
				c.setCity(rs.getString(8));
				c.setState(rs.getString(9));
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return c;
	}
}

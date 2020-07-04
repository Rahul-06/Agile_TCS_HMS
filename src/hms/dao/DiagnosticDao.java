package hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hms.bean.Diagnostic;
import hms.bean.Lab;
import hms.bean.Medicine;
import hms.bean.Patient;
import hms.credentials.DbConn;

public class DiagnosticDao {
	
	public List<Diagnostic> viewId(String ssn_id)
	{
		List<Diagnostic> list = new ArrayList<Diagnostic>();
		try {
			Connection con = DbConn.getConnection();
			PreparedStatement pStmt;
			Integer ssn_id1 = Integer.parseInt(ssn_id);
			String query = "select * from diagnostic where ws_ssn = ?;";
			pStmt = con.prepareStatement(query);
			pStmt.setInt(1,ssn_id1);				
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				Diagnostic d = new Diagnostic();
				d.setWs_ssn(rs.getInt(1));
				d.setWs_pat_id(rs.getInt(2));
				d.setWs_test_id(rs.getInt(3));
				d.setWs_test_name(rs.getString(4));
				d.setWs_test_amt(rs.getInt(5));
				list.add(d);
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
		
		return list;
		
	}
	
	
	public List<Lab> conductTest(String test_name)
	{
		Lab l = new Lab();
		List<Lab> list = new ArrayList<Lab>();
		try {
			Connection con = DbConn.getConnection();
			PreparedStatement pStmt;
			String query = "select * from lab_test where ws_test_name = ?;";
			pStmt = con.prepareStatement(query);
			pStmt.setString(1, test_name);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next())
			{
				l.setWs_test_id(rs.getInt(1));
				l.setWs_test_name(rs.getString(2));
				l.setWs_test_amt(rs.getInt(3));
			}
			
			String query2 = "insert into temp_lab_test (ws_test_id, ws_test_name, ws_test_amt) values (?,?,?);";
				pStmt = con.prepareStatement(query2);
				pStmt.setInt(1, l.getWs_test_id());
				pStmt.setString(2, l.getWs_test_name());
	            pStmt.setInt(3, l.getWs_test_amt());
			    pStmt.executeUpdate();
	            
			    String query3 = "select * from temp_lab_test;";
			    Statement stmt = con.createStatement();
	            ResultSet rs1 = stmt.executeQuery(query3);
	            while(rs1.next())
	            {
	            	Lab d1 = new Lab();
	            	d1.setWs_test_id(rs1.getInt(1));
	            	d1.setWs_test_name(rs1.getString(2));
	            	d1.setWs_test_amt(rs1.getInt(3));
	            	list.add(d1);
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
		
		return list;	
	}
	
	public int updateTest(List<Lab> test_list, Patient p)
	{
		int cnt =0, cnt1 =0;
		try {
			Connection con = DbConn.getConnection();
			PreparedStatement pStmt;
			Integer ws_ssn = p.getSsn_id();
			Integer ws_pat_id = p.getPat_id();
			String query = "insert into diagnostic (ws_ssn, ws_pat_id, ws_test_id, ws_test_name, ws_test_amt) values (?,?,?,?,?);";
			pStmt = con.prepareStatement(query);
			for(Lab itr : test_list)
			{
				pStmt.setInt(1, ws_ssn);
				pStmt.setInt(2, ws_pat_id);
				pStmt.setInt(3, itr.getWs_test_id());
				pStmt.setString(4, itr.getWs_test_name());
				pStmt.setInt(5, itr.getWs_test_amt());
				cnt = pStmt.executeUpdate();
			}
			
			if(cnt > 0)
			{
				String query1 = "truncate table temp_lab_test;";
				pStmt = con.prepareStatement(query1);
				cnt1 = pStmt.executeUpdate();
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
		
		return cnt;
		
	}
	
	public Integer addTest(Lab l)
	{
		int cnt = 0,cnt1=0,cnt2=0;
		try {
			Connection con = DbConn.getConnection();
			String query ="create procedure insert_or_select()\r\n"
					+ "BEGIN\r\n"
					+ "if exists (select * from lab_test where ws_test_name=?) THEN\r\n"
					+ "update lab_test set ws_test_amt=? where ws_test_name=?;\r\n"
					+ "ELSE\r\n"
					+ "insert into lab_test (ws_test_name,ws_test_amt) values (?,?);\r\n"
					+ "END IF;\r\n"
					+ "END;";					
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setString(1, l.getWs_test_name());
			pStmt.setInt(2,l.getWs_test_amt());	
			pStmt.setString(3, l.getWs_test_name());
			pStmt.setString(4, l.getWs_test_name());
			pStmt.setInt(5, l.getWs_test_amt());
			cnt = pStmt.executeUpdate();
			System.out.println(cnt);
			String query1 = "call insert_or_select();";
			pStmt = con.prepareStatement(query1);
			cnt1 = pStmt.executeUpdate();
			System.out.println(cnt1);
			
			if(cnt1 > 0)
			{
				String query2 = "drop procedure insert_or_select;";
				pStmt = con.prepareStatement(query2);
				cnt2 = pStmt.executeUpdate();
				System.out.println(cnt2);
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
		return cnt1;
	}
	

}

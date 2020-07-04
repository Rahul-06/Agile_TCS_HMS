package hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import hms.bean.Medicine;
import hms.bean.Patient;
import hms.bean.Pharmacy;
import hms.credentials.DbConn;

public class PharmacyDao {

	public List<Pharmacy> viewId(String ssn_id)
	{
		//Pharmacy ph = new Pharmacy();
		List<Pharmacy> list = new ArrayList<Pharmacy>();
		try {
			Connection con = DbConn.getConnection();
			PreparedStatement pStmt;
			Integer ssn_id1 = Integer.parseInt(ssn_id);
			String query = "select ws_ssn,ws_pat_id,ws_med_id,ws_med_name,sum(ws_med_qty),ws_med_rate from pharmacy where ws_ssn = ? group by ws_med_name;";
			pStmt = con.prepareStatement(query);
			pStmt.setInt(1,ssn_id1);				
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				Pharmacy ph = new Pharmacy();
				ph.setWs_ssn(rs.getInt(1));
				ph.setWs_pat_id(rs.getInt(2));
				ph.setWs_med_id(rs.getInt(3));
				ph.setWs_med_name(rs.getString(4));
				ph.setWs_med_qty(rs.getInt(5));
				ph.setWs_med_rate(rs.getInt(6));
				list.add(ph);
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
	
	public List<Medicine> issueMedicine(String med_name, Integer qty)
	{
		Medicine m = new Medicine();
		//Medicine[] m1 = null;
		List<Medicine> list = new ArrayList<Medicine>();
		try {
			Connection con = DbConn.getConnection();
			PreparedStatement pStmt;
			String query = "select * from medicine where ws_med_name = ?;";
			pStmt = con.prepareStatement(query);
			pStmt.setString(1, med_name);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next())
			{
				m.setWs_med_id(rs.getInt(1));
				m.setWs_med_name(rs.getString(2));
				m.setWs_med_qty(rs.getInt(3));
				m.setWs_med_rate(rs.getInt(4));
			}
			
			/* Quantity Update */
			if(m.getWs_med_qty() >= qty)
			{
				String query1 = "update medicine set ws_med_qty = ? where ws_med_name = ?;";
				pStmt = con.prepareStatement(query1);
				int qty1 = m.getWs_med_qty() - qty;
				pStmt.setInt(1, qty1);
				pStmt.setString(2, m.getWs_med_name());
				int cnt = pStmt.executeUpdate();
				System.out.println("hi "+cnt);
			//}*/
			
			/* Insert into temp table and get list of medicine form temp table */
			//if(m.getWs_med_qty() >= qty)
			//{
				String query2 = "insert into temp_medicine (ws_med_id, ws_med_name, ws_qty, ws_rate) values (?,?,?,?);";
				pStmt = con.prepareStatement(query2);
				pStmt.setInt(1, m.getWs_med_id());
				pStmt.setString(2, m.getWs_med_name());
	            pStmt.setInt(3, qty);
	            pStmt.setInt(4, m.getWs_med_rate());
			    pStmt.executeUpdate();
	            
			    String query3 = "select * from temp_medicine;";
			    Statement stmt = con.createStatement();
	            ResultSet rs1 = stmt.executeQuery(query3);
	            while(rs1.next())
	            {
	            	Medicine m1 = new Medicine();
	            	m1.setWs_med_id(rs1.getInt(1));
	            	m1.setWs_med_name(rs1.getString(2));
	            	m1.setWs_med_qty(rs1.getInt(3));
	            	m1.setWs_med_rate(rs1.getInt(4));
	            	list.add(m1);
	            }
	            
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
	
	public int updateMedicine(List<Medicine> med_list, Patient p)
	{
		int cnt =0, cnt1 =0;
		try {
			Connection con = DbConn.getConnection();
			PreparedStatement pStmt;
			Integer ws_ssn = p.getSsn_id();
			Integer ws_pat_id = p.getPat_id();
			String query = "insert into pharmacy (ws_ssn, ws_pat_id, ws_med_id, ws_med_name, ws_med_qty, ws_med_rate) values (?,?,?,?,?,?);";
			pStmt = con.prepareStatement(query);
			for(Medicine itr : med_list)
			{
				pStmt.setInt(1, ws_ssn);
				pStmt.setInt(2, ws_pat_id);
				pStmt.setInt(3, itr.getWs_med_id());
				pStmt.setString(4, itr.getWs_med_name());
				pStmt.setInt(5, itr.getWs_med_qty());
				pStmt.setInt(6, itr.getWs_med_rate());
				cnt = pStmt.executeUpdate();
			}
			
			if(cnt > 0)
			{
				String query1 = "truncate table temp_medicine;";
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
	
	public Integer addMedicine(Medicine m)
	{
		int cnt = 0,cnt1=0,cnt2=0;
		try {
			Connection con = DbConn.getConnection();
			String query ="create procedure select_or_insert()\r\n"
					+ "BEGIN\r\n"
					+ "if exists (select * from medicine where ws_med_name=?) THEN\r\n"
					+ "update medicine set ws_med_qty=?, ws_med_rate=? where ws_med_name=?;\r\n"
					+ "ELSE\r\n"
					+ "insert into medicine (ws_med_name,ws_med_qty,ws_med_rate) values (?,?,?);\r\n"
					+ "END IF;\r\n"
					+ "END;";					
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setString(1, m.getWs_med_name());
			pStmt.setInt(2, m.getWs_med_qty());
			pStmt.setInt(3,m.getWs_med_rate());	
			pStmt.setString(4, m.getWs_med_name());
			pStmt.setString(5, m.getWs_med_name());
			pStmt.setInt(6, m.getWs_med_qty());
			pStmt.setInt(7,m.getWs_med_rate());
			cnt = pStmt.executeUpdate();
			System.out.println(cnt);
			String query1 = "call select_or_insert();";
			pStmt = con.prepareStatement(query1);
			cnt1 = pStmt.executeUpdate();
			System.out.println(cnt1);
			
			if(cnt1 > 0)
			{
				String query2 = "drop procedure select_or_insert;";
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

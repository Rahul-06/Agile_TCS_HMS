package hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import hms.bean.Patient;
import hms.credentials.DbConn;

public class BillDao {

	public int dischargePatient(Patient p)
	{
		int ws_pat_id = p.getPat_id();
		int cnt = 0;
		try {
			Connection con = DbConn.getConnection();
			String query = "update patient set ws_status = ? where ws_pat_id = ?;";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setString(1, "Discharged");
			pStmt.setInt(2, ws_pat_id);
			cnt = pStmt.executeUpdate();
			System.out.println(cnt);
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

}

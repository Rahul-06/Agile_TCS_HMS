package hms.service;

import hms.bean.Patient;
import hms.dao.BillDao;

public class BillService {

	public int dischargePatient(Patient p)
	{
		BillDao bd = new BillDao();
		return bd.dischargePatient(p);
	}

}

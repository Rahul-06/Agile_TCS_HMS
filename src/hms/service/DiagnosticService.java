package hms.service;

import java.util.List;


import hms.bean.Diagnostic;
import hms.bean.Lab;
import hms.bean.Medicine;
import hms.bean.Patient;
import hms.dao.DiagnosticDao;
import hms.dao.PharmacyDao;

public class DiagnosticService {
	public List<Diagnostic> viewDiagnostic(String ssn_id)
	{
		DiagnosticDao dd = new DiagnosticDao();
		return dd.viewId(ssn_id);
	}

	public List<Lab> conductTest(String test_name)
	{
		DiagnosticDao dd = new DiagnosticDao();
		return dd.conductTest(test_name);
	}
	
	public int updateTest(List<Lab> test_list, Patient p)
	{
		DiagnosticDao dd = new DiagnosticDao();
		return dd.updateTest(test_list, p);
	}
	public Integer createTest(Lab l)
	{
		DiagnosticDao dm = new DiagnosticDao();
		return dm.addTest(l);
	}
	

}

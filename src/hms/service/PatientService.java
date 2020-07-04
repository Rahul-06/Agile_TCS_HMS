package hms.service;

import hms.bean.Patient;
import hms.dao.PatientDao;

public class PatientService {
	public Integer createPatient(Patient p)
	{
		PatientDao cp = new PatientDao();
		return cp.addPatient(p);
	}
	public Patient searchId(Integer pat_id)
	{
		PatientDao pd = new PatientDao();
		return pd.searchId(pat_id);
	}
	public Integer updatePatient(Patient p)
	{
		PatientDao pd = new PatientDao();
		return pd.updatePatient(p);
	}
	public Patient viewPatient(String ssn_id)
	{
		PatientDao pd = new PatientDao();
		return pd.viewId(ssn_id);
		
	}
	public Integer deletePatient(Integer ssn_id)
	{
		PatientDao pd = new PatientDao();
		return pd.deletePatient(ssn_id);
	}
}

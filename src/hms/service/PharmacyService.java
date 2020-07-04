package hms.service;

import java.util.List;

import hms.bean.Medicine;
import hms.bean.Patient;
import hms.bean.Pharmacy;
import hms.dao.PatientDao;
import hms.dao.PharmacyDao;

public class PharmacyService {
	
	public List<Pharmacy> viewPharmacy(String ssn_id)
	{
		PharmacyDao phd = new PharmacyDao();
		return phd.viewId(ssn_id);
	}

	public List<Medicine> issueMedicine(String med_name, Integer qty)
	{
		PharmacyDao phd = new PharmacyDao();
		return phd.issueMedicine(med_name, qty);
	}
	
	public int updateMedicine(List<Medicine> med_list, Patient p)
	{
		PharmacyDao phd = new PharmacyDao();
		return phd.updateMedicine(med_list, p);
	}
	public Integer createMedicine(Medicine m)
	{
		PharmacyDao cm = new PharmacyDao();
		return cm.addMedicine(m);
	}
	
}

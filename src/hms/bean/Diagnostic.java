package hms.bean;

public class Diagnostic {
	
	private Integer ws_ssn;
	private Integer ws_pat_id;
	private Integer ws_test_id;
	private String ws_test_name;
	private Integer ws_test_amt;
	
	public Diagnostic()
	{
		
	}	
	public Diagnostic(Integer ws_ssn, Integer ws_pat_id, Integer ws_test_id, String ws_test_name, Integer ws_test_amt) {
		this.ws_ssn = ws_ssn;
		this.ws_pat_id = ws_pat_id;
		this.ws_test_id = ws_test_id;
		this.ws_test_name = ws_test_name;
		this.ws_test_amt = ws_test_amt;
	}
	public Integer getWs_ssn() {
		return ws_ssn;
	}
	public void setWs_ssn(Integer ws_ssn) {
		this.ws_ssn = ws_ssn;
	}
	public Integer getWs_pat_id() {
		return ws_pat_id;
	}
	public void setWs_pat_id(Integer ws_pat_id) {
		this.ws_pat_id = ws_pat_id;
	}
	public Integer getWs_test_id() {
		return ws_test_id;
	}
	public void setWs_test_id(Integer ws_test_id) {
		this.ws_test_id = ws_test_id;
	}
	public String getWs_test_name() {
		return ws_test_name;
	}
	public void setWs_test_name(String ws_test_name) {
		this.ws_test_name = ws_test_name;
	}
	public Integer getWs_test_amt() {
		return ws_test_amt;
	}
	public void setWs_test_amt(Integer ws_test_amt) {
		this.ws_test_amt = ws_test_amt;
	}

}

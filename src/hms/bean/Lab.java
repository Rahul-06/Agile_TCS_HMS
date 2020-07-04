package hms.bean;

public class Lab {
	private Integer ws_test_id;
	private String ws_test_name;
	private Integer ws_test_amt;
	
	public Lab()
	{
		
	}
	
	
	
	public Lab(String ws_test_name, Integer ws_test_amt) {
		this.ws_test_name = ws_test_name;
		this.ws_test_amt = ws_test_amt;
	}



	public Lab(Integer ws_test_id, String ws_test_name, Integer ws_test_amt) {
		this.ws_test_id = ws_test_id;
		this.ws_test_name = ws_test_name;
		this.ws_test_amt = ws_test_amt;
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

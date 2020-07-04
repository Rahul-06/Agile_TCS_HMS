package hms.bean;

public class Pharmacy {
	
	private Integer ws_ssn;
	private Integer ws_pat_id;
	private Integer ws_med_id;
	private String ws_med_name;
	private Integer ws_med_qty;
	private Integer ws_med_rate;
	
	public Pharmacy()
	{
		
	}
	
	public Pharmacy(Integer ws_ssn, Integer ws_pat_id, Integer ws_med_id, String ws_med_name, Integer ws_med_qty, Integer ws_med_rate) {
		this.ws_ssn = ws_ssn;
		this.ws_pat_id = ws_pat_id;
		this.ws_med_id = ws_med_id;
		this.ws_med_name = ws_med_name;
		this.ws_med_qty = ws_med_qty;
		this.ws_med_rate = ws_med_rate;
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
	public Integer getWs_med_id() {
		return ws_med_id;
	}
	public void setWs_med_id(Integer ws_med_id) {
		this.ws_med_id = ws_med_id;
	}
	public String getWs_med_name() {
		return ws_med_name;
	}
	public void setWs_med_name(String ws_med_name) {
		this.ws_med_name = ws_med_name;
	}
	public Integer getWs_med_qty() {
		return ws_med_qty;
	}
	public void setWs_med_qty(Integer ws_med_qty) {
		this.ws_med_qty = ws_med_qty;
	}
	public Integer getWs_med_rate() {
		return ws_med_rate;
	}
	public void setWs_med_rate(Integer ws_med_rate) {
		this.ws_med_rate = ws_med_rate;
	}
	

}

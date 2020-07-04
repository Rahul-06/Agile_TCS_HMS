package hms.bean;

public class Patient {

	private String pat_name;
	private Integer pat_id;
    private Integer age;
    private String address;
    private String city;
    private String state;
    private String doj;
    private String tob;
    private Integer ssn_id;
    
    public Patient()
    {
    	
    }
    
    public Patient(Integer ssn_id,String pat_name, Integer age, String doj, String tob, String address, String city, String state) {
		this.pat_name = pat_name;
		this.age = age;
		this.address = address;
		this.city = city;
		this.state = state;
		this.doj = doj;
		this.tob = tob;
		this.ssn_id = ssn_id;
	}
	public Integer getSsn_id() {
		return ssn_id;
	}
	public void setSsn_id(Integer ssn_id) {
		this.ssn_id = ssn_id;
	}
	public void setPat_id(Integer pat_id) {
		this.pat_id = pat_id;
	}
	public Integer getPat_id() {
		return pat_id;
	}
	public String getPat_name() {
		return pat_name;
	}
	public void setPat_name(String pat_name) {
		this.pat_name = pat_name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getTob() {
		return tob;
	}
	public void setTob(String tob) {
		this.tob = tob;
	}

    

}

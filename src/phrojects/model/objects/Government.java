package phrojects.model.objects;

public class Government extends Project{
	private String project_cost;
	private String fund_source;
	private String sector;
	private String icc_tb_date;
	private String icc_cc_date;	
	private String neda_date;
	private String implement_period;
	private String project_status;
	private String compliance_status;
	private String update_status;

	public String getCost() {
		return project_cost;
	}
	
	public void setCost(String project_cost) {
		this.project_cost = project_cost;
	}

	public String getSource() {
		return fund_source;
	}
	
	public void setSource(String fund_source) {
		this.fund_source = fund_source;
	}

	public String getSector() {
		return sector;
	}
	
	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getTBDate() {
		return icc_tb_date;
	}
	
	public void setTBDate(String icc_tb_date) {
		this.icc_tb_date = icc_tb_date;
	}

	public String getCCDate() {
		return icc_cc_date;
	}
	
	public void setCCDate(String icc_cc_date) {
		this.icc_cc_date = icc_cc_date;
	}

	public String getNedaDate() {
		return neda_date;
	}
	
	public void setNedaDate(String neda_date) {
		this.neda_date = neda_date;
	}

	public String getPeriod() {
		return implement_period;
	}
	
	public void setPeriod(String implement_period) {
		this.implement_period = implement_period;
	}

	public String getProjStatus() {
		return project_status;
	}
	
	public void setProjStatus(String project_status) {
		this.project_status = project_status;
	}

	public String getCompStatus() {
		return compliance_status;
	}
	
	public void setCompStatus(String compliance_status) {
		this.compliance_status = compliance_status;
	}

	public String getUpdate() {
		return update_status;
	}
	
	public void setUpdate(String update_status) {
		this.update_status = update_status;
	}
}
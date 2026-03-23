package traning;

public class Employee {
	private String emp_ID;
	private String emp_name;
	
	public Employee(String emp_ID, String emp_name) {
		super();
		this.emp_ID = emp_ID;
		this.emp_name = emp_name;
	}


	public String getEmp_ID() {
		return emp_ID;
	}


	public String getEmp_name() {
		return emp_name;
	}
	
}
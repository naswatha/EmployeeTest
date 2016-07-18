package insticator.emp;

public class EmployeeFactory {
	
	public static Employee getEmployee(String empType){
		
		if(empType.equalsIgnoreCase("intern")){
			return new Intern();
		}
		else if(empType.equalsIgnoreCase("fulltime")){
			return new FullTime();
		}
		else if(empType.equalsIgnoreCase("parttime")){
			return new PartTime();
		}
		return null;
	}

}

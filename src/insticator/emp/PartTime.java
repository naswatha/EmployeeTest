package insticator.emp;

public class PartTime extends Employee {
	
	private double hours;
	
	@Override
	public double pay() {
		// implement the total salary based on the yearly pay.
		// any computation 
		return 0;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

}

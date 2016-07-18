package insticator.emp;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

import insticator.db.DBConnection;


public class EmployeeTester {

	public static void main(String[] args) {
		// test employee data here
	}
		
	public static void createEmployeeTable() throws SQLException{
		
		Connection conn = DBConnection.getDBConnection();
		Statement stmt = null;
		try{
			String sql = "CREATE TABLE EMPLOYEE " +
	                "(SSN VARCHAR(15) not NULL, " +
	                " NAME VARCHAR(255), " + 
	                " ADDRESS VARCHAR(255), " + 
	                " AGE INTEGER, " + 
	                " SALARY DECIMAL(6,2)"+
	                " EMPTYPE VARCHAR(10)"+
	                " PRIMARY KEY ( SSN ))"; 
			
			stmt.executeUpdate(sql);
		}
		finally{
			stmt.close();
			conn.close();
		}
	}
	
	public static void insertEmployee(String ssn, String name, String address, int age, double salary,String empType) throws SQLException{
		
		Connection conn = DBConnection.getDBConnection();
		Statement stmt = null;
		
		try{
			stmt = conn.createStatement();
			String sql = "INSERT INTO EMPLOYEE " +
	                   "VALUES (" + 
	                   ssn + "," +
	                   name + "," +
	                   address + "," +
	                   age + "," + 
	                   salary + "," +
	                   empType + ")";
			stmt.executeUpdate(sql);
	                   
		}
		finally{
			stmt.close();
			conn.close();
		}
	}
	
	public static void writeJSON(ArrayList<Employee> employees) throws FileNotFoundException{
		
		Gson gson = new Gson();
		String json = gson.toJson(employees);
		PrintWriter out = new PrintWriter("employee.json");
	    out.println(json);
	    out.close();
	}
	
	
	
	public static ArrayList<Employee> retrieveEmployee() throws SQLException{
		
		Connection conn = DBConnection.getDBConnection();
		Statement stmt = null;
		ArrayList<Employee> empCollection = new ArrayList<Employee>();
		
		try{
			stmt = conn.createStatement();
			String sql = "SELECT SSN, NAME, ADDRESS, AGE, SALARY, EMPTYPE FROM EMPLOYEE";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
		         
				String type = rs.getString("emptype");
				Employee emp = EmployeeFactory.getEmployee(type);
				emp.setSsn(rs.getString("ssn"));
				emp.setName(rs.getString("name"));
				emp.setAddress(rs.getString("address"));
				emp.setSalary(rs.getDouble("salary"));
				empCollection.add(emp);
			} 
		}
		finally{
			stmt.close();
			conn.close();
		}
		return empCollection;
	}
	
	public static void updateEmployeeAge(String ssn, int age) throws SQLException{
		
		Connection conn = DBConnection.getDBConnection();
		Statement stmt = null;
				
		try{
			stmt = conn.createStatement();
			String sql = "UPDATE EMPLOYEE " +
	                   		"SET age = "+ age +"WHERE SSN ="+ ssn +"";
			stmt.executeUpdate(sql);
		}
		finally{
			stmt.close();
			conn.close();
		}
	}
}

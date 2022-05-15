package com;

import java.sql.*;

public class Item {

    public Connection connect(){
    	
        //database connection details
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        String dbName = "Employee_management";
        String dbUsername = "root";
        String dbPassword = "";
        
        Connection conn = null;
        
        try {
        	//connecting the database
        	Class.forName(dbDriver);
        	conn = DriverManager.getConnection(dbURL+dbName, dbUsername, dbPassword);
        	
        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        return conn;
    }
    
    
    //method to insert data
    public String insertEmployee(String code, String name, String price, String desc) {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "INSERT INTO items (EmployeeID,employeeName,employeeAge,emloyeeaddress) VALUES (?,?,?,?)";
        	
        	//binding data to SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setString(1, ID);
        	preparedStatement.setString(2, name);
        	preparedStatement.setDouble(3, Double.parseDouble(Age));
        	preparedStatement.setString(4, address);
        	
        	//execute the SQL statement
        	preparedStatement.execute();
        	conn.close();

			String newItems = readItems(); 
			Output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}";
        	
    	} catch(Exception e) {
			Output = "{\"status\":\"error\", \"data\": \"Failed to insert the Emloyee\"}";
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    //method to update data
    public String updateItem(String emloyeeID, (EmployeeID, String name, String age, String address) {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "UPDATE emloyee SET employeeID = ?,employeeName = ?,employeeAge = ?,employeeAddress = ? WHERE emloyeeID = ?";
        	
        	//binding data to SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setString(1, ID);
        	preparedStatement.setString(2, name);
        	preparedStatement.setDouble(3, Double.parseDouble(Age));
        	preparedStatement.setString(4, address);
        	preparedStatement.setInt(5, Integer.parseInt(employeeID));
        	
        	//execute the SQL statement
        	preparedStatement.executeUpdate();
        	conn.close();
        	
        	String newItems = reademployee(); 
      		Output = "{\"status\":\"success\", \"data\": \"" + newemployee + "\"}";
        	
    	} catch(Exception e) {
    		Output = "{\"status\":\"error\", \"data\":\"Failed to update the employee.\"}"; 
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    
    //method to read data
    public String reademployee() {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "SELECT * FROM emloyee";
        	
        	//executing the SQL query
        	Statement statement = conn.createStatement();
        	ResultSet resultSet = statement.executeQuery(query);
        	
        	// Prepare the HTML table to be displayed
    		Output = "<table border='1'><tr><th>Employee ID</th>" +"<th>Employee Name</th><th>Employee Age</th>"
    		+ "<th>Employee Address</th>"
    		+ "<th>Update</th><th>Remove</th></tr>";
        	
        	while(resultSet.next()) {
        		String itemID = Integer.toString(resultSet.getInt("employeeID"));
        		String itemCode = resultSet.getString("employeeID");
        		String itemName = resultSet.getString("employeeName");
        		String itemPrice = Double.toString(resultSet.getDouble("employeeAge"));
        		String itemDesc = resultSet.getString("employeeAddress");
        		
        		// Add a row into the HTML table
        		Output += "<tr><td>" + employeeCode + "</td>"; 
        		Output += "<td>" + employeeName + "</td>"; 
        		Output += "<td>" + employeeAge + "</td>"; 
        		Output += "<td>" + employeeAddress + "</td>";
        		
        		// buttons
        		Output += "<td>"
						+ "<input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-sm btn-secondary' data-itemid='" + itemID + "'>"
						+ "</td>" 
        				+ "<td>"
						+ "<input name='btnRemove' type='button' value='Remove' class='btn btn-sm btn-danger btnRemove' data-itemid='" + itemID + "'>"
						+ "</td></tr>";
        	}

        	conn.close();
        	
        	// Complete the HTML table
        	Output += "</table>";
        	
    	} catch(Exception e) {
    		Output = "Failed to read the items";
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    //method to delete data
    public String deleteEmployee(String itemID) {
    	String Output = "";
    	Connection conn = connect();
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "DELETE FROM items WHERE itemID = ?";
        	
        	//binding data to the SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setInt(1, Integer.parseInt(itemID));
        	
        	//executing the SQL statement
        	preparedStatement.execute();
        	conn.close();
        	
        	String newEmployee = readEmployee(); 
      		Output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}"; 
        	
    	} catch(Exception e) {
			Output = "{\"status\":\"error\", \"data\":\"Failed to delete the employee.\"}";
    		System.err.println(e.getMessage());
    	}
    	return Output;
    }
}

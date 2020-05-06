package com;
import java.sql.*;


public class Doc
{
	private Connection connect()
	{
		Connect con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertDoc(String code, String name, String fee, String desc)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
			
			//create a prepare statement
			String query = "insert into docs ('docID','docCode','docName','docFee','docDesc')"
					+" values (?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, Double.parseDouble(fee));
			preparedStmt.setString(5, desc);
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			String newDocs = readDocs();
			output = "{\"status\":\"success\",\"data\":\""+newDocs+"\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\",\"data\":\"Error while insering the doctor.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readDoc()
	{
		 String output = ""; 
		 
		 try
		 {
			 Connection con = connect(); 
			 
			 if (con == null)   
			 {    
				 return "Error while connecting to the database for reading.";
			 }
			 
			// Prepare the html table to be displayed   
			 output = "<table border=\"1\"><tr><th>Doc Code</th>       "
			 		+ "<th>Doc Name</th><th>Doc Fee</th>     <th>Doc Description</th>         <th>Update</th><th>Remove</th></tr>";
			 
			 String query = "select * from docs";   
			 Statement stmt = con.createStatement();   
			 ResultSet rs = stmt.executeQuery(query); 
					 
			// iterate through the rows in the result set 
			 
			 while (rs.next())   
			 {    
				 String docID = Integer.toString(rs.getInt("docID"));    
				 String docCode = rs.getString("docCode");    
				 String docName = rs.getString("docName");    
				 String docFee = Double.toString(rs.getDouble("docFee"));    
				 String docDesc = rs.getString("docDesc");  
				 
				// Add into the html table 
				 output += "<tr><td><input id= 'hidDocIDUpdate' name='hidDocIDUpdate' type='hidden' value='" + docID + "'>"     
						 + docCode + "</td>";
				   
				 output += "<td>" + docName + "</td>";    
				 output += "<td>" + docFee + "</td>";    
				 output += "<td>" + docDesc + "</td>"; 	 
				 
				// buttons 
				 
				 output += "<td><input name ='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						 + "<td><input name ='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-docid='"
						 + docID + "'>" + "</td></tr>";
			 }
			 
			 con.close(); 
			 
			  // Complete the html table  
			  output += "</table>"; 
		 }
		 
		 catch (Exception e)  
		 {   
			 output = "Error while reading the doctor.";   
			 System.err.println(e.getMessage());  
		 } 
		 
		 return output; 
		
	}
	
	public String updateDoc(String ID, String code, String name, String fee, String desc)
	{
		String output = "";
		
		try 
		{
			Connection con = connect(); 
			 
			 if (con == null)   
			 {    
				 return "Error while connecting to the database for updating.";
			 }
			 //create a prepared statement.
			 String query = "UPDATE docs SET docCode=?,docName=?,docFee?,docDesc=? WHERE docID=?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 //binding values
			 preparedStmt.setString(1, code);
			 preparedStmt.setString(2, name);
			 preparedStmt.setDouble(3, Double.parseDouble(fee));
			 preparedStmt.setString(4, desc);
			 preparedStmt.setInt(5, Integer.parseInt(ID));
			 
			 //execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 String newDocs = readDocs();
				output = "{\"status\":\"success\",\"data\":\""+newDocs+"\"}";
			 
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\",\"data\":\"Error while updating the doctor.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteDoc(String docID)
	{
		try
		{
			Connection con = connect(); 
			 
			 if (con == null)   
			 {    
				 return "Error while connecting to the database for deleting.";
			 }
			 //create a prepared statement.
			 String query = "delete from docs where docID=?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 //binding values
			 preparedStmt.setInt(1, Integer.parseInt(docID));
			 
			 //execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 String newItems = readDocs();
			 output = "{\"status\":\"success\", \"data\": \"" + newDocs + "\"}";
			 
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\",\"data\":\"Error while deleting the doctor.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
}

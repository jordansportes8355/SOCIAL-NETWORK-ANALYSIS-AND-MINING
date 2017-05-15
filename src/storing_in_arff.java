import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import weka.core.converters.ArffSaver;

import java.io.BufferedWriter;
import java.io.FileWriter;


public class storing_in_arff {
	
	public static void main(String[] args)  {
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter","root", "jokevin");
			//Connection 
			
			System.out.println("hello word");
			PreparedStatement st = conn.prepareStatement("SELECT text FROM tweet");
		    ResultSet r1 =st.executeQuery();
		    BufferedWriter writer = new BufferedWriter(new FileWriter("./data/test.arff"));
		    while ( r1.next() ) {
	           String text = r1.getString("text");
	           writer.write(text);
	           writer.flush();
	        }
		    writer.close();
			}
			catch(Exception variable){
				variable.printStackTrace();
			}
	}

}

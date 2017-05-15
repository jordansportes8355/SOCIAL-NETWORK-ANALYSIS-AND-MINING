import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;



public class part3 {
	
	public static void main(String[] args)  {

		Graph<String, String> graph = new SparseMultigraph<String, String>();
		graph = processDatabase(graph);
		
		System.out.println("Done. Total number of edges : "+graph.getEdgeCount()+" and total number of vertices : "+graph.getVertexCount());

	}

	public static Graph<String, String> processDatabase (Graph<String, String> graph) {
		// TODO Auto-generated method stub
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter","root", "jokevin");
			//Connection 
			
			System.out.println("hello word");
			PreparedStatement st = conn.prepareStatement("SELECT text FROM tweet");
		    ResultSet r1 =st.executeQuery();
		    while ( r1.next() ) {
	           String text = r1.getString("text");
			   graph = processTweet(graph, text);
	        }
		    
			}
			catch(Exception variable){
				variable.printStackTrace();
			}
		return graph;

	}
	
	
	public static Graph<String, String> processTweet (Graph<String, String> graph, String tweet){
		// TODO Auto-generated method stub
		String[] words = tweet.replaceAll("[^0-9A-Za-z]"," ").split("\\s+");
		
		//Adding the verteces
		for (String word : words){
			word.toLowerCase();
			if(!graph.containsVertex(word)){
				graph.addVertex((String) word);
			}
		}
		
		//Creation of the edges
		for(Integer i = 0 ; i<words.length; i++){
			for (Integer ite = i; ite<words.length; ite++){
				graph.addEdge(words[i]+" -> "+words[ite], words[i], words[ite]);
			}
			i++;
		}
		
		return graph;

	}

}

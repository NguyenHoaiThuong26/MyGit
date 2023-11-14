package student;

public class Test {
	 public static void main(String[] args) {
		 HillClimbingSearch hillClimbingSearch = new HillClimbingSearch();
	        Node initialState = new Node();
	        initialState.generateBoard(); 
	        Node result = hillClimbingSearch.execute(initialState);

	       result.displayBoard();
		
	}
}

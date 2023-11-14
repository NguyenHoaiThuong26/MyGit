package student;

public class HillClimbingSearch {
	
	
	//Task 3
	public Node execute(Node initialState) {
		// Enter your code here.
		// Enter your code here.
        Node current = initialState;

        while (true) {
            Node neighbor = current.getBestCandidate();

            if (neighbor.getH() >= current.getH()) {
                // If no better neighbor, return current state
                return current;
            }

            current = neighbor;
        }	}
	public Node executeHillClimbingWithRandomRestart(Node initialState) {
        int maxRestarts = 1000; // Adjust the maximum number of restarts as needed
        int restarts = 0;

        while (restarts < maxRestarts) {
            Node current = initialState;
            
            while (true) {
                Node neighbor = current.getBestCandidate();

                if (neighbor.getH() >= current.getH()) {
                    // If no better neighbor, break and restart
                    break;
                }

                current = neighbor;
            }

            // Generate a new random initial state
            initialState.generateBoard();
            restarts++;
        }

        return initialState;
    }
}



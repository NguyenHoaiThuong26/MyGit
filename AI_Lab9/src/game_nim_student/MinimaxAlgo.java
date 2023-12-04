package game_nim_student;

public class MinimaxAlgo {
	public void execute(Node node) {
		int v = minValue(node); // Task 3: Determine the value at the root node
        System.out.println("Minimax Value at the Root: " + v);

        // Task 4: Show the best move for MIN player at the root
        Node bestMove = getBestMove(node);
        System.out.println("Best Move for MIN Player: " + bestMove);
	}

	public int maxValue(Node node) {
        if (node.isTerminal()) {
            return 1; 
        }
        int v = Integer.MIN_VALUE;
        for (Node successor : node.getSuccessors()) {
            v = Math.max(v, minValue(successor));
        }
        return v;
    }

    public int minValue(Node node) {
        if (node.isTerminal()) {
            return -1; 
        }
        int v = Integer.MAX_VALUE;
        for (Node successor : node.getSuccessors()) {
            v = Math.min(v, maxValue(successor));
        }
        return v;
    }
    
 // Task 4
    private Node getBestMove(Node node) {
        int bestValue = Integer.MAX_VALUE;
        Node bestMove = null;
        for (Node successor : node.getSuccessors()) {
            int value = maxValue(successor);
            if (value < bestValue) {
                bestValue = value;
                bestMove = successor;
            }
        }
        return bestMove;
    }
}

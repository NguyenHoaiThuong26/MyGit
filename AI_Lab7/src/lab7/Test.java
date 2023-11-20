package lab7;

public class Test {
	public static void main(String[] args) {
		GA_NQueenAlgo gaNQueenAlgo = new GA_NQueenAlgo();
        gaNQueenAlgo.initPopulation();
        Node result = gaNQueenAlgo.execute();

        System.out.println("Testing execute:");
        System.out.println("Best Result Heuristic: " + result.getH());
        System.out.println("Best Result Board:");
        result.displayBoard();
        System.out.println();
	}
}

package lab2and3;

import java.util.List;

public class Test {
	public static void main(String[] args) {
		Node nodeS = new Node("S");
		Node nodeA = new Node("A"); Node nodeB = new Node("B");
		Node nodeC = new Node("C"); Node nodeD = new Node("D");
		Node nodeE = new Node("E"); Node nodeF = new Node("F");
		Node nodeG = new Node("G"); Node nodeH = new Node("H");
		nodeS.addEdge(nodeA, 5); nodeS.addEdge(nodeB, 2);
		nodeS.addEdge(nodeC, 4); nodeA.addEdge(nodeD, 9);
		nodeA.addEdge(nodeE, 4); nodeB.addEdge(nodeG, 6);
		nodeC.addEdge(nodeF, 2); nodeD.addEdge(nodeH, 7);
		nodeE.addEdge(nodeG, 6); nodeF.addEdge(nodeG, 1);

		ISearchAlgo algo1 = new BreadthFirstSearchAlgo();
		ISearchAlgo algo2 = new DepthFirstSearchAlgo();
		Node resultB = algo1.execute(nodeS, "E");
		  System.out.println("BFS from root to goal:");
		if (resultB != null) {
            List<String> path1 = NodeUtils.printPath(resultB);
            System.out.println("Path: " + path1);
        } else {
            System.out.println("Goal node not found.");
        }
		
		Node resultD = algo2.execute(nodeS, "G");
		  System.out.println("DFS from root to goal:");
		if (resultD != null) {
          List<String> path2 = NodeUtils.printPath(resultD);
          System.out.println("Path: " + path2);
      } else {
          System.out.println("Goal node not found.");
      }
	}
}

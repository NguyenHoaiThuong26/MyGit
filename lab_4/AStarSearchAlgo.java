package lab_4;
import java.util.*;

public class AStarSearchAlgo implements IInformedSearchAlgo{

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingDouble(Node::getH));
        Set<Node> explored = new HashSet<>();

        root.setG(0);
        root.setH(heuristic(root, goal));
        frontier.offer(root);

        while (!frontier.isEmpty()) {
            Node node = frontier.poll();

            if (node.getLabel().equals(goal)) {
                return node; // Return the goal node
            }

            explored.add(node);

            for (Edge edge : node.getChildren()) {
                Node childNode = edge.getEnd();
                double cost = edge.getWeight();
                double newG = node.getG() + cost;

                if (!explored.contains(childNode) && !frontier.contains(childNode)) {
                    childNode.setG(newG);
                    childNode.setH(heuristic(childNode, goal));
                    childNode.setParent(node);
                    frontier.offer(childNode);
                } else if (frontier.contains(childNode) && newG < childNode.getG()) {
                    childNode.setG(newG);
                    childNode.setH(heuristic(childNode, goal));
                    childNode.setParent(node);
                }
            }
        }

        return null; // Failure: no solution found
	}
	
	private double heuristic(Node node, String goal) {
		return Math.abs(node.getLabel().length() - goal.length());
	}

	@Override
	public Node execute(Node root, String start, String goal) {
	    PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingDouble(Node::getH));
	    Set<Node> explored = new HashSet<>();

	    // Find the start node
	    Node startNode = findNode(root, start);
	    if (startNode == null) {
	        return null; // Start node not found
	    }

	    frontier.offer(startNode);

	    while (!frontier.isEmpty()) {
	        Node node = frontier.poll();

	        if (node.getLabel().equals(goal)) {
	            return node; // Return the goal node
	        }

	        explored.add(node);

	        for (Edge edge : node.getChildren()) {
	            Node childNode = edge.getEnd();

	            if (!explored.contains(childNode) && !frontier.contains(childNode)) {
	                frontier.offer(childNode);
	            }
	        }
	    }

	    return null; // Failure: no solution found
	}

	private Node findNode(Node node, String label) {
	    if (node.getLabel().equals(label)) {
	        return node;
	    }

	    for (Edge edge : node.getChildren()) {
	        Node childNode = edge.getEnd();
	        Node foundNode = findNode(childNode, label);
	        if (foundNode != null) {
	            return foundNode;
	        }
	    }

	    return null;
	}

}

package lab2and3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo{

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub  
		Set<Node> visited = new HashSet<>();
        visited.add(root);

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();

            if (current.getLabel().equals(goal)) {
                return current;
            }

            for (Edge edge : current.getChildren()) {
                Node childNode = edge.getEnd();
                if (!visited.contains(childNode)) {
                    visited.add(childNode);
                    stack.push(childNode);
                }
            }
        }
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		 Node startNode = findNode(root, start);
	        if (startNode == null) {
	            return null;
	        }

	        Set<Node> visited = new HashSet<>();
	        visited.add(startNode);

	        Stack<Node> stack = new Stack<>();
	        stack.push(startNode);

	        while (!stack.isEmpty()) {
	            Node current = stack.pop();

	            if (current.getLabel().equals(goal)) {
	                return current;
	            }

	            for (Edge edge : current.getChildren()) {
	                Node childNode = edge.getEnd();
	                if (!visited.contains(childNode)) {
	                    visited.add(childNode);
	                    stack.push(childNode);
	                }
	            }
	        }
		return null;
	}
	

    private Node findNode(Node node, String label) {
        if (node == null) {
            return null;
        }

        if (node.getLabel().equals(label)) {
            return node;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.getLabel().equals(label)) {
                return current;
            }

            for (Edge edge : current.getChildren()) {
                Node childNode = edge.getEnd();
                queue.add(childNode);
            }
        }

        return null;
    }

}

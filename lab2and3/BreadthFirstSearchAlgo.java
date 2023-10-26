package lab2and3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class BreadthFirstSearchAlgo implements ISearchAlgo{

	
		// TODO Auto-generated method stub
//		List<Node> explore = new ArrayList<>();
//		Stack<Node> stack = new Stack<Node>();
//		
//		stack.add(root);
//		int i = 1;
//		while (!stack.isEmpty()) {
//			Node current = stack.pop();
//			if (current.getLabel().equals(goal)) {
//				return current;
//			}
//			explore.add(current);
//			
//			Collections.sort(current.getChildren(), new Comparator<Edge>() {
//
//				@Override
//				public int compare(Edge o1, Edge o2) {
//					// TODO Auto-generated method stub
//					return o2.getEnd().getLabel().compareTo(o1.getEnd().getLabel());
//				}
//			});
//			
//			for(Edge edge : current.getChildren()) {
//				Node child = edge.getEnd();
//				if (!explore.contains(child)) {
//					child.setParent(current);
//					explore.add(child);
//					stack.add(child);
//				}
//			}
//			System.out.println(i++ + " : " + current + " : " + stack);
//		}
		
	 @Override
	    public Node execute(Node root, String goal) {
	        Set<Node> visited = new HashSet<>();
	        Queue<Node> queue = new LinkedList<>();

	        queue.add(root);

	        while (!queue.isEmpty()) {
	            Node current = queue.poll();
	            visited.add(current);

	            if (current.getLabel().equals(goal)) {
	                return current;
	            }

	            for (Edge edge : current.getChildren()) {
	                Node childNode = edge.getEnd();
	                if (!visited.contains(childNode) && !queue.contains(childNode)) {
	                    queue.add(childNode);
	                    childNode.setParent(current); // Lưu parent để tái tạo đường đi
	                }
	            }
	        }

	        return null;
	    }

	    @Override
	    public Node execute(Node root, String start, String goal) {
	        Node startNode = findNode(root, start);

	        if (startNode == null) {
	            return null;
	        }

	        Set<Node> visited = new HashSet<>();
	        Queue<Node> queue = new LinkedList<>();

	        queue.add(startNode);

	        while (!queue.isEmpty()) {
	            Node current = queue.poll();
	            visited.add(current);

	            if (current.getLabel().equals(goal)) {
	                return current;
	            }

	            for (Edge edge : current.getChildren()) {
	                Node childNode = edge.getEnd();
	                if (!visited.contains(childNode) && !queue.contains(childNode)) {
	                    queue.add(childNode);
	                    childNode.setParent(current); // Lưu parent để tái tạo đường đi
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

package lab_4;
import java.util.*;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo{

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingDouble(Node::getH));
        Set<Node> explored = new HashSet<>();

        frontier.offer(root);

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

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		return null;
	}

}

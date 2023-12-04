package game_nim_student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {
	private List<Integer> data = new ArrayList<Integer>();

	public void add(Integer val) {
		this.data.add(val);
	}

	public void addAll(List<Integer> data) {
		this.data.addAll(data);
	}

	// Get children of the current nodes
	public List<Node> getSuccessors() {
		List<Node> successors = new ArrayList<>();
        for (int i = 0; i < this.data.size(); i++) {
            for (int j = 1; j <= this.data.get(i); j++) {
                Node successor = new Node();
                successor.addAll(this.data);
                successor.data.set(i, this.data.get(i) - j);
                successors.add(successor);
            }
        }
        return successors;
	}

	// Check whether a node is terminal or not
	public boolean isTerminal() {
		for (Integer pile : this.data) {
            if (pile > 1) {
                return false; // If any pile has more than 1 token, it's not terminal
            }
        }
        return true; // All piles have 1 or 0 tokens, so it's terminal
	}

	public static final Comparator<Integer> DESCOMPARATOR = new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	};

	@Override
	public String toString() {
		Collections.sort(this.data, DESCOMPARATOR);
		return this.data.toString();
	}

}

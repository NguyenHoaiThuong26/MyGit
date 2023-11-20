package lab7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo {
	public static final int POP_SIZE = 100;//Population size
	public static final double MUTATION_RATE = 0.03;
	public static final int MAX_ITERATIONS = 1000;
	List<Node> population = new ArrayList<Node>();
	Random rd = new Random();
	
	// initialize the individuals of the population
	public void initPopulation() {
		for (int i = 0; i < POP_SIZE; i++) {
			Node ni = new Node();
			ni.generateBoard();
			population.add(ni);
		}
	}
	
	public Node execute() {
		int generation = 0;
		while (generation < MAX_ITERATIONS) {
			List<Node> newGeneration = new ArrayList<Node>();
			for (int i = 0; i < POP_SIZE; i++) {
				Node parent_1 = getParentByTournamentSelection();
				Node parent_2 = getParentByTournamentSelection();
				Node child = reproduce(parent_1, parent_2);
				mutate(child);
				newGeneration.add(child);
			}
			
			population = newGeneration;
		
			generation++;
			
		}
		return Collections.min(population);
	}
	
	// Mutate an individual by selecting a random Queen and 
	//move it to a random row.
	public void mutate(Node node) {
		if (rd.nextDouble() < MUTATION_RATE) {
			Random random = new Random();
			int queenIndex = random.nextInt(Node.N);
			int newRow = random.nextInt(Node.N);
			node.setRow(queenIndex, newRow);
		}
	}

	//Crossover x and y to reproduce a child
	public Node reproduce(Node x, Node y) {
		Random rd = new Random();
		int point = rd.nextInt(Node.N);
		Queen[] childState = new Queen[Node.N];
		
		for (int i = 0; i < point; i++) {
			childState[i] = new Queen(x.getRow(i), x.getColumn(i));
		}
		for (int i = point; i < Node.N; i++) {
			childState[i] = new Queen(y.getRow(i), y.getColumn(i));
		}
		
		return new Node(childState);
	}
	
	// Select K individuals from the population at random and 
	//select the best out of these to become a parent.
	public Node getParentByTournamentSelection() {
		int tournamentSize = 5;
		Node best = null;
		for (int i = 0; i < tournamentSize; i++) {
			Node randomParent = getParentByRandomSelection();
			if (best == null || randomParent.getH() < best.getH()) {
				best = randomParent;
			}
		}
		
		return best;
	
	}
	//Select a random parent from the population
	public Node getParentByRandomSelection() {
		return population.get(rd.nextInt(POP_SIZE));
	}

}

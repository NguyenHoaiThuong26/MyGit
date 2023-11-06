package chapter2.agent_AB; 

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	//new action 
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	//new location
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;

	public Environment(LocationState locAState, LocationState locBState, LocationState locCState, LocationState locDState) {
		envState = new EnvironmentState(locAState, locBState, locCState, locDState);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		this.agent = agent;
		envState.setAgentLocation(location);
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		if (action.equals(SUCK_DIRT)) {
			envState.setLocationState(envState.getAgentLocation(), LocationState.CLEAN);
		}
		if (action.equals(MOVE_RIGHT)) {
			envState.setAgentLocation(LOCATION_B);
		}
		if (action.equals(MOVE_LEFT)) {
			envState.setAgentLocation(LOCATION_A);
		}
		return envState;
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		// TODO
		return new Percept(envState.getAgentLocation(), envState.getLocationState(envState.getAgentLocation()));
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
//		EnvironmentState es = executeAction(anAction);

//		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);

//		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
//				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN))
//			isDone = true;// if both squares are clean, then agent do not need to do any action
//		es.display();
		if (anAction.equals(SUCK_DIRT)) {
			LocationState currentLocationState = envState.getLocationState(agentLocation);
			if (currentLocationState.equals(LocationState.DIRTY) ) {
				envState.setLocationState(agentLocation, LocationState.CLEAN);
				System.out.println("Angent Location: " + agentLocation + "\tAction: " + anAction + " (+500 points)");
			} else {
				System.out.println("Angent Location: " + agentLocation + "\tAction: " + anAction + " (-10 points)");
			}
		} else if (anAction.equals(MOVE_LEFT) || anAction.equals(MOVE_RIGHT) || anAction.equals(MOVE_DOWN)
				|| anAction.equals(MOVE_UP)) {
			String newLocation = moveToNewLocation(agentLocation, anAction);
			if (newLocation != null) {
				envState.setAgentLocation(newLocation);
				System.out.println("Angent Location: " + agentLocation + "\tAction: " + anAction + " (-10 points)");
			} else {
				System.out.println("Angent Location: " + agentLocation + "\tAction: " + anAction + " (-100 points)");
			}
		}
		if (envState.isClean()) {
			isDone = true;
		}
		
		envState.display();
			
	}
	

	
	
	public String moveToNewLocation(String currentLocation, Action action) {
		switch(action.toString()) {
		case "UP":
			if (currentLocation.equals(LOCATION_A) || currentLocation.equals(LOCATION_B)) {
				return currentLocation;
			} else if (currentLocation.equals(LOCATION_C)) {
				return LOCATION_A;
			} else if (currentLocation.equals(LOCATION_D)) {
				return LOCATION_B;
			}
			break;
		case "DOWN":
			if (currentLocation.equals(LOCATION_C) || currentLocation.equals(LOCATION_D)) {
				return currentLocation;
			} else if (currentLocation.equals(LOCATION_A)) {
				return LOCATION_C;
			} else if (currentLocation.equals(LOCATION_B)) {
				return LOCATION_D;
			}
			break;
		case "RIGHT":
			if (currentLocation.equals(LOCATION_B) || currentLocation.equals(LOCATION_D)) {
				return currentLocation;
			} else if (currentLocation.equals(LOCATION_A)) {
				return LOCATION_B;
			} else if (currentLocation.equals(LOCATION_C)) {
				return LOCATION_D;
			}
			break;
		case "LEFT":
			if (currentLocation.equals(LOCATION_A) || currentLocation.equals(LOCATION_C)) {
				return currentLocation;
			} else if (currentLocation.equals(LOCATION_B)) {
				return LOCATION_A;
			} else if (currentLocation.equals(LOCATION_D)) {
				return LOCATION_C;
			}
			break;
		}
		return null;
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
}

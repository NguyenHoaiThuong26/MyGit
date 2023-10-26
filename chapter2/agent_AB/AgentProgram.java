package chapter2.agent_AB;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		String location = p.getAgentLocation();
		Environment.LocationState state = p.getLocationState();
		
		if (state.equals(Environment.LocationState.CLEAN)) {
			switch (location) {
			case Environment.LOCATION_A:
				return Environment.MOVE_RIGHT;
			case Environment.LOCATION_B:
				return Environment.MOVE_LEFT;
			}
		} else {
			return Environment.SUCK_DIRT;
		}
	
		return NoOpAction.NO_OP;
		
	}
}
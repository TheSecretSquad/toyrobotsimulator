package toyrobotsimulator;

public enum Direction {
	NORTH,
	SOUTH,
	EAST,
	WEST;
	
	public static Direction createFrom(final String stringValue) {
		switch(stringValue.toUpperCase()) {
		case "NORTH":
			return NORTH;
		case "SOUTH":
			return SOUTH;
		case "EAST":
			return EAST;
		case "WEST":
			return WEST;
		default:
			throw new InvalidDirectionException();
		}
	}
}
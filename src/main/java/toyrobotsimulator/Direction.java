package toyrobotsimulator;

public enum Direction {
	NORTH("NORTH"),
	SOUTH("SOUTH"),
	EAST("EAST"),
	WEST("WEST");
	
	private final String stringValue;
	
	private Direction(final String stringValue) {
		this.stringValue = stringValue;
	}
	
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
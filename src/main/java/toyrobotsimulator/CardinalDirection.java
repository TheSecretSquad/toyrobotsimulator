package toyrobotsimulator;

public enum CardinalDirection implements Direction {
	NORTH,
	SOUTH,
	EAST,
	WEST;
	
	public static CardinalDirection createFrom(final String stringValue) {
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

	public CardinalDirection clockwise() {
		CardinalDirection counterClockwise = this;
		
		switch(this) {
		case NORTH:
			counterClockwise = EAST;
			break;
		case EAST:
			counterClockwise = SOUTH;
			break;
		case SOUTH:
			counterClockwise = WEST;
			break;
		case WEST:
			counterClockwise = NORTH;
			break;
		}
		
		return counterClockwise;
	}
	
	public CardinalDirection counterClockwise() {
		CardinalDirection counterClockwise = this;
		
		switch(this) {
		case NORTH:
			counterClockwise = WEST;
			break;
		case WEST:
			counterClockwise = SOUTH;
			break;
		case SOUTH:
			counterClockwise = EAST;
			break;
		case EAST:
			counterClockwise = NORTH;
			break;
		}
		
		return counterClockwise;
	}

	@Override
	public Position moveFrom(final Position position) {
		// TODO Auto-generated method stub
		return null;
	}
}
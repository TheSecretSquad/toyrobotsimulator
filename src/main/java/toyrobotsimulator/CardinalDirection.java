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
	public void directDirectableFrom(final Directable directable, final Coordinate fromCoordinate) {
		switch(this) {
		case NORTH:
			fromCoordinate.translateByPositionTo(new Position(0, 1), directable);
			break;
		case SOUTH:
			fromCoordinate.translateByPositionTo(new Position(0, -1), directable);
			break;
		case EAST:
			fromCoordinate.translateByPositionTo(new Position(1, 0), directable);
			break;
		case WEST:
			fromCoordinate.translateByPositionTo(new Position(-1, 0), directable);
			break;
		}	
	}
}
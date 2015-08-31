package toyrobotsimulator;

import java.io.PrintStream;

public enum CardinalDirection implements Direction {
	NORTH("NORTH"),
	SOUTH("SOUTH"),
	EAST("EAST"),
	WEST("WEST");
	
	private final String stringValue;
	
	private CardinalDirection(final String stringValue) {
		this.stringValue = stringValue;
	}
	
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

	public void turnClockwise(final Turnable turnable) {
		switch(this) {
		case NORTH:
			turnable.face(EAST);
			return;
		case EAST:
			turnable.face(SOUTH);
			return;
		case SOUTH:
			turnable.face(WEST);
			return;
		case WEST:
			turnable.face(NORTH);
			return;
		default:
			turnable.face(this);
			return;
		}
	}
	
	public void turnCounterClockwise(final Turnable turnable) {
		switch(this) {
		case NORTH:
			turnable.face(WEST);
			return;
		case WEST:
			turnable.face(SOUTH);
			return;
		case SOUTH:
			turnable.face(EAST);
			return;
		case EAST:
			turnable.face(NORTH);
			return;
		default:
			turnable.face(this);
			return;
		}
	}

	@Override
	public void directDirectableFrom(final Directable directable, final Coordinate fromCoordinate) {
		switch(this) {
		case NORTH:
			fromCoordinate.translateByCoordinateTo(new Position(0, 1), directable);
			return;
		case SOUTH:
			fromCoordinate.translateByCoordinateTo(new Position(0, -1), directable);
			return;
		case EAST:
			fromCoordinate.translateByCoordinateTo(new Position(1, 0), directable);
			return;
		case WEST:
			fromCoordinate.translateByCoordinateTo(new Position(-1, 0), directable);
			return;
		}	
	}

	@Override
	public void printOn(final PrintStream printStream) {
		printStream.print(this.stringValue);		
	}
}
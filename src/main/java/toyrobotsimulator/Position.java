package toyrobotsimulator;

public class Position {
	
	private final int x;
	private final int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position(final String x, final String y) {
		try {
			this.x = Integer.parseInt(x);
			this.y = Integer.parseInt(y);
		}
		catch(final NumberFormatException numberFormatException) {
			throw new PositionInitializationException();
		}
	}
	
	public boolean isBetween(final Position position1, final Position position2) {
		return isHorizontallyBetween(position1, position2) &&
				isVerticallyBetween(position1, position2);
	}
	
	private boolean isHorizontallyBetween(final Position position1, final Position position2) {
		int minX = Math.min(position1.x, position2.x);
		int maxX = Math.max(position1.x, position2.x);
		return minX <= x && x <= maxX;
	}
	
	private boolean isVerticallyBetween(final Position position1, final Position position2) {
		int minY = Math.min(position1.y, position2.y);
		int maxY = Math.max(position1.y, position2.y);
		return minY <= y && y <= maxY;
	}
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
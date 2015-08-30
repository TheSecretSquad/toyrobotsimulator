package toyrobotsimulator;

public class RealToyRobot implements ToyRobot, EnvironmentObject, Directable, Turnable {

	private final Environment environment;
	private final ReportStream reportStream;
	private Position position;
	private Direction facingDirection;

	public RealToyRobot(final Environment environment, final ReportStream reportStream) {
		this.environment = environment;
		this.reportStream = reportStream;
		this.position = null;
		this.facingDirection = null;
	}
	
	@Override
	public void move() {
		ifActiveDo(() -> facingDirection.directDirectableFrom(this, position));
	}

	@Override
	public void turnLeft() {
		ifActiveDo(() -> facingDirection.turnCounterClockwise(this));
	}

	@Override
	public void turnRight() {
		ifActiveDo(() -> facingDirection.turnClockwise(this));
	}

	@Override
	public void report() {
		reportStream.report(position, facingDirection);
	}

	@Override
	public void place(final Position position, final Direction facingDirection) {
		environment.tryPlaceObjectAtPositionFacing(this, position, facingDirection);
	}

	@Override
	public void issueCommand(final Command command) {
		command.execute();
	}

	@Override
	public void placeAtPositionFacing(final Position position, final Direction direction) {
		placeAt(position);
		this.facingDirection = direction;
	}

	private void placeAt(final Position position) {
		this.position = position;
	}
	
	@Override
	public void moveTo(final Position position) {
		placeAt(position);
	}

	@Override
	public void directTo(final Position position) {
		environment.tryMoveObjectTo(this, position);
	}
	
	private void ifActiveDo(final Runnable action) {
		if(position != null && facingDirection != null)
			action.run();
	}

	@Override
	public void face(final Direction direction) {
		this.facingDirection = direction;		
	}
}
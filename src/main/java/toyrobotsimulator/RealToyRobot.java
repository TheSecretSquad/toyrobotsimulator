package toyrobotsimulator;
/*
 * This class is admittedly a bit of a self-indulgent design. The specification
 * for the project states that the robot is the only thing on the tabletop. Which means
 * there are no other aspects of the environment that will affect the robot's movement aside
 * from the size of the table.
 * I could have implemented this by just passing in the size of the environment to the
 * robot, but I chose to design the environment separately as an experiment.
 */
public class RealToyRobot implements ToyRobot, EnvironmentObject, Placeable, Directable, Turnable {

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
		ifPlacedDo(() -> facingDirection.directDirectableFrom(this, position));
	}

	@Override
	public void turnLeft() {
		ifPlacedDo(() -> facingDirection.turnCounterClockwise(this));
	}

	@Override
	public void turnRight() {
		ifPlacedDo(() -> facingDirection.turnClockwise(this));
	}

	@Override
	public void report() {
		ifPlacedDo(() -> reportStream.report(position, facingDirection));
	}

	@Override
	public void place(final Position position, final Direction facingDirection) {
		ifNotPlacedDo(() -> tryPlaceAtPositionFacingDirection(position, facingDirection));
	}

	private void tryPlaceAtPositionFacingDirection(final Position position,	final Direction facingDirection) {
		tryPlaceAtPositionOnSuccess(position, onSuccess(position, facingDirection));
	}

	private void tryPlaceAtPositionOnSuccess(final Position position, final PlaceSuccessHandler placeSuccessHandler) {
		environment.tryPlaceObjectAtPositionOnSuccess(this, position, placeSuccessHandler);
	}
	private ToyRobotPlaceSuccessfulHandler onSuccess(final Position position, final Direction facingDirection) {
		return new ToyRobotPlaceSuccessfulHandler(this, position, facingDirection);
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
	
	private void ifPlacedDo(final Runnable action) {
		if(position != null && facingDirection != null)
			action.run();
	}
	
	private void ifNotPlacedDo(final Runnable action) {
		if(position == null || facingDirection == null)
			action.run();
	}

	@Override
	public void face(final Direction direction) {
		this.facingDirection = direction;		
	}
}

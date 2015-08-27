package toyrobotsimulator;

public class RealToyRobot implements ToyRobot {

	private final Environment environment;
	private final EnvironmentObject environmentObject;
	private boolean isActive;
	private Direction facingDirection;
	private final ReportStream reportStream;

	public RealToyRobot(final Environment environment, final EnvironmentObject environmentObject, final ReportStream reportStream) {
		this.environment = environment;
		this.environmentObject = environmentObject;
		this.isActive = false;
		this.facingDirection = null;
		this.reportStream = reportStream;
	}
	
	@Override
	public void move() {
		ifActiveDo(() -> environment.moveInDirection(facingDirection));
	}

	@Override
	public void turnLeft() {
		ifActiveDo(() -> facingDirection = facingDirection.counterClockwise());
	}

	@Override
	public void turnRight() {
		ifActiveDo(() -> facingDirection = facingDirection.clockwise());
	}

	@Override
	public void report() {
		ifActiveDo(() -> doReport());
	}

	private void doReport() {
		reportStream.report(facingDirection);
		environment.reportTo(reportStream);
	}

	@Override
	public void place(final Position position, final Direction facingDirection) {
		environment.placeObjectAtPosition(environmentObject, position);
		isActive = true;
		this.facingDirection = facingDirection;
	}
	
	private void ifActiveDo(final Runnable action) {
		if(isActive)
			action.run();
	}

	@Override
	public void issueCommand(final Command command) {
		command.execute();
	}
}
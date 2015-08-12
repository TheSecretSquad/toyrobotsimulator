package toyrobotsimulator;

public class RealToyRobot implements ToyRobot {

	private final Environment environment;
	private final EdgeDetector edgeDetector;
	private boolean isActive;
	private Direction facingDirection;
	private final ReportStream reportStream;

	public RealToyRobot(final Environment environment, final EdgeDetector edgeDetector, final ReportStream reportStream) {
		this.environment = environment;
		this.edgeDetector = edgeDetector;
		this.isActive = false;
		this.facingDirection = null;
		this.reportStream = reportStream;
	}
	
	@Override
	public void move() {
		ifActiveDo(() -> environment.moveInDirectionWith(facingDirection, edgeDetector));
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
		environment.placeAtPositionWith(position, edgeDetector);
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
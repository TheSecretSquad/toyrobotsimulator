package toyrobotsimulator;

public class RealToyRobot implements ToyRobot {

	private final Environment environment;
	private boolean isActive;
	private Direction facingDirection;

	public RealToyRobot(final Environment environment) {
		this.environment = environment;
		this.isActive = false;
		this.facingDirection = null;
	}
	
	@Override
	public void move() {
		ifActiveDo(() -> environment.moveIn(facingDirection));
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void place(final Position position, final Direction facingDirection) {
		environment.placeAt(position);
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
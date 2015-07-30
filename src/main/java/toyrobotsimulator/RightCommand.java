package toyrobotsimulator;

public class RightCommand implements Command {

	private final RightAction rightAction;

	public RightCommand(final RightAction rightAction) {
		this.rightAction = rightAction;
	}

	@Override
	public void execute() {
		rightAction.turnRight();		
	}
}
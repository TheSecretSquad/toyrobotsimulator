package toyrobotsimulator;

public class LeftCommand implements Command {

	private final LeftAction leftAction;
	
	public LeftCommand(final LeftAction leftAction) {
		this.leftAction = leftAction;
	}

	@Override
	public void execute() {

	}
}
package toyrobotsimulator;

public class LeftCommand implements Command {

	private final LeftActionable leftActionable;
	
	public LeftCommand(final LeftActionable leftActionable) {
		this.leftActionable = leftActionable;
	}

	@Override
	public void execute() {

	}
}
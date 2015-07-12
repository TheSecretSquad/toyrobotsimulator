package toyrobotsimulator;

public class MoveCommand implements Command {

	private final MoveActionable moveActionable;
	
	public MoveCommand(final MoveActionable moveActionable) {
		this.moveActionable = moveActionable;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
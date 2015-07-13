package toyrobotsimulator;

public class MoveCommand implements Command {

	private final MoveAction moveAction;
	
	public MoveCommand(final MoveAction moveAction) {
		this.moveAction = moveAction;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
package toyrobotsimulator;

public class ToyRobotCommands implements Commands {

	private CommandSource commandSource;

	public ToyRobotCommands(final CommandSource commandSource) {
		this.commandSource = commandSource;
	}

	public void readTo(final CommandReceiver commandReceiver) {
		commandSource.parseTo(commandReceiver);
	}
}
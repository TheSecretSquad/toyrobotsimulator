package toyrobotsimulator;

import java.util.function.Consumer;

public class ToyRobotCommandInterpreter implements CommandInterpreter, CommandReceiver {

	private final InterpretedCommandReceiver interpretedCommandReceiver;
	private final ToyRobot toyRobot;
	private final CommandName moveCommandName;
	private final CommandName leftCommandName;
	private final CommandName rightCommandName;
	private final CommandName reportCommandName;
	private final CommandName placeCommandName;
	
	public ToyRobotCommandInterpreter(final InterpretedCommandReceiver interpretedCommandReceiver, final ToyRobot toyRobot) {
		this.interpretedCommandReceiver = interpretedCommandReceiver;
		this.toyRobot = toyRobot;
		this.moveCommandName = new CommandName("MOVE");
		this.leftCommandName = new CommandName("LEFT");
		this.rightCommandName = new CommandName("RIGHT");
		this.reportCommandName = new CommandName("REPORT");
		this.placeCommandName = new CommandName("PLACE");
	}
	
	public void interpretCommands(final Commands commands) {
		commands.readTo(this);
	}

	@Override
	public void sendCommandName(final CommandName commandName) {
		issueCommandForCommandNameWithParametersTo(commandName, null, interpretedCommandReceiver);
	}

	private void issueCommandForCommandNameWithParametersTo(final CommandName commandName, final CommandParameters commandParameters,
			final InterpretedCommandReceiver interpretedCommandReceiver) {
		interpretedCommandReceiver.issueCommand(commandFor(commandName, commandParameters));
	}

	private Command commandFor(final CommandName commandName, final CommandParameters commandParameters) {
		if(commandParameters != null)
			return commandWithParametersFor(commandName, commandParameters);
		
		if(commandName.equals(moveCommandName))
			return new MoveCommand(toyRobot);
		else if(commandName.equals(leftCommandName))
			return new LeftCommand(toyRobot);
		else if(commandName.equals(rightCommandName))
			return new RightCommand(toyRobot);
		else if(commandName.equals(reportCommandName))
			return new ReportCommand(toyRobot);
		
		return unknownCommand();
	}
	
	private Command commandWithParametersFor(final CommandName commandName, final CommandParameters commandParameters) {
		if(commandName.equals(placeCommandName))
			return new PlaceCommand(commandParameters, toyRobot);
		
		return unknownCommand();
	}

	private UnknownCommand unknownCommand() {
		return new UnknownCommand();
	}

	@Override
	public void sendCommandNameWithParameters(final CommandName commandName, final CommandParameters commandParameters) {
		issueCommandForCommandNameWithParametersTo(commandName, commandParameters, interpretedCommandReceiver);
	}
}
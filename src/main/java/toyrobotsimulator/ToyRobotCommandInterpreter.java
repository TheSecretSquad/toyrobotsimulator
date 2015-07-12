package toyrobotsimulator;

import java.util.function.Consumer;

public class ToyRobotCommandInterpreter implements CommandInterpreter, CommandReceiver {

	private final InterpretedCommandReceiver interpretedCommandReceiver;
	private final ToyRobot toyRobot;
	
	public ToyRobotCommandInterpreter(final InterpretedCommandReceiver interpretedCommandReceiver, final ToyRobot toyRobot) {
		this.interpretedCommandReceiver = interpretedCommandReceiver;
		this.toyRobot = toyRobot;
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
		ifHasCommandForCommandNameDo(commandName, commandParameters, (command) -> interpretedCommandReceiver.issueCommand(command));
	}

	private void ifHasCommandForCommandNameDo(final CommandName commandName, final CommandParameters commandParameters,
			final Consumer<Command> commandConsumer) {
		Command command = commandFor(commandName, commandParameters);
		
		commandConsumer.accept(command);
	}

	private Command commandFor(final CommandName commandName, final CommandParameters commandParameters) {
		if(commandParameters != null)
			return commandWithParametersFor(commandName, commandParameters);
		
		if(commandName.equals(new CommandName("MOVE")))
			return new MoveCommand(toyRobot);
		else if(commandName.equals(new CommandName("LEFT")))
			return new LeftCommand(toyRobot);
		else if(commandName.equals(new CommandName("RIGHT")))
			return new RightCommand(toyRobot);
		else if(commandName.equals(new CommandName("REPORT")))
			return new ReportCommand(toyRobot);
		
		return unknownCommand();
	}
	
	private Command commandWithParametersFor(final CommandName commandName, final CommandParameters commandParameters) {
		if(commandName.equals(new CommandName("PLACE")))
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
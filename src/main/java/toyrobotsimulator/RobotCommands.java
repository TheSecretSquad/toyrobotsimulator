package toyrobotsimulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RobotCommands {
	
	private static RobotCommand robotCommandFromString(final String command) {
		return new RobotCommand(command);
	}
	
	private static List<RobotCommand> robotCommandsFromStrings(final String ... commands) {
		return Arrays.asList(commands).stream()
				.map(RobotCommands::robotCommandFromString)
				.collect(Collectors.toList());
	}
	
	private List<RobotCommand> commands;
	
	public RobotCommands() {
		this(new ArrayList<>());
	}
	
	public RobotCommands(final String ... commands) {
		this(robotCommandsFromStrings(commands));
	}
	
	public RobotCommands(final RobotCommand ... commands) {
		this(Arrays.asList(commands));
	}
	
	public RobotCommands(final List<RobotCommand> commands) {
		this.commands = new ArrayList<RobotCommand>(commands);
	}

	public void eachDo(final RobotCommandAction robotCommandAction) {
		commands.stream()
			.forEachOrdered((command) -> robotCommandAction.actionWith(command));
	}

	public void add(final RobotCommand command) {
		commands.add(command);		
	}

	public void add(final RobotCommands addedCommands) {
		addedCommands.eachDo((command) -> add(command));
	}
}

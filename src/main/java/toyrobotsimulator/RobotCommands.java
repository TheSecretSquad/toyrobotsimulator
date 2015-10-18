package toyrobotsimulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RobotCommands {
	
	private static RobotCommand robotCommandFromString(final String command) {
		return new RobotCommand(command);
	}
	
	private List<RobotCommand> commands;
	
	public RobotCommands(final String ... commands) {
		this(Arrays.asList(commands).stream()
				.map(RobotCommands::robotCommandFromString)
				.collect(Collectors.toList()));
	}
	
	public RobotCommands(final RobotCommand ... commands) {
		this(Arrays.asList(commands));
	}
	
	public RobotCommands(final List<RobotCommand> commands) {
		this.commands = new ArrayList<RobotCommand>(commands);
	}
}
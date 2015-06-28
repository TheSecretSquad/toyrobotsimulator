package toyrobotsimulator;

public class ToyRobotCommandName implements CommandName {
	
	private final String commandName;
	
	public ToyRobotCommandName(final String commandName) {
		this.commandName = commandName.toUpperCase();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commandName == null) ? 0 : commandName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToyRobotCommandName other = (ToyRobotCommandName) obj;
		if (commandName == null) {
			if (other.commandName != null)
				return false;
		} else if (!commandName.equals(other.commandName))
			return false;
		return true;
	}
}
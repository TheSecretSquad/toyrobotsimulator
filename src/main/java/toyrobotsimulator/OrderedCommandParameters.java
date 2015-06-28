package toyrobotsimulator;

import java.util.Arrays;
import java.util.List;

public class OrderedCommandParameters implements CommandParameters {

	private final List<String> parameters;
	
	public OrderedCommandParameters(final String[] parameters) {
		this.parameters = Arrays.asList(parameters);
	}
	
	@Override
	public void listTo(final CommandParameterReceiver commandParameterReceiver) {
		parameters.forEach(s -> commandParameterReceiver.receive(s));
	}
}
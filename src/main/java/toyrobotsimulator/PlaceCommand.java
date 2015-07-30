package toyrobotsimulator;

import java.util.ArrayList;
import java.util.List;
/**
 * A lot more design can be done to make parameter extraction and verification
 * more reusable and flexible, such as creating some kind of descriptor that knows
 * about expected parameter count and has functions for parsing to the correct types.
 * 
 * Since the PLACE command is currently the only command that uses parameters, I'm saving
 * some time by not doing all that.
 */
public class PlaceCommand implements Command {

	private final CommandParameters commandParameters;
	private final PlaceAction placeAction;
	private final int expectedParameterCount;
	private final int positionXValueIndex;
	private final int positionYValueIndex;
	private final int directionIndex;

	public PlaceCommand(final CommandParameters commandParameters, final PlaceAction placeAction) {
		this.commandParameters = commandParameters;
		this.placeAction = placeAction;
		this.expectedParameterCount = 3;
		this.positionXValueIndex = 0;
		this.positionYValueIndex = 1;
		this.directionIndex = 2;
	}
	
	private void extractParametersTo(final List<String> parameters) {
		commandParameters.listTo(new CommandParameterReceiver() {
			@Override
			public void receive(final String parameter) {
				parameters.add(parameter);
			}
		});
	}
	
	private void verifyParameterCount(final List<String> parameters) {
		if(parameters.size() != expectedParameterCount)
			throw new CommandParametersCountException();
	}
	
	private Position positionFrom(final List<String> extractedParameters) {
		return new Position(positionXValueFrom(extractedParameters), positionYValueFrom(extractedParameters));
	}

	private String positionXValueFrom(final List<String> extractedParameters) {
		return extractedParameters.get(positionXValueIndex);
	}
	
	private String positionYValueFrom(final List<String> extractedParameters) {
		return extractedParameters.get(positionYValueIndex);
	}
	
	private Direction directionFrom(final List<String> extractedParameters) {
		return Direction.createFrom(extractedParameters.get(directionIndex));
	}

	@Override
	public void execute() {
		final List<String> extractedParameters = new ArrayList<>(3);
		extractParametersTo(extractedParameters);
		verifyParameterCount(extractedParameters);
		placeAction.place(positionFrom(extractedParameters), directionFrom(extractedParameters));
	}
}
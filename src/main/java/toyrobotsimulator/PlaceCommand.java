package toyrobotsimulator;

import java.util.ArrayList;
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
	private final ArrayList<String> extractedParameters;
	private final Position position;
	private final Direction direction;
	private final int expectedParameterCount;
	private final int positionXValueIndex;
	private final int positionYValueIndex;
	private final int directionIndex;

	public PlaceCommand(final CommandParameters commandParameters, final PlaceAction placeAction) {
		this.commandParameters = commandParameters;
		this.placeAction = placeAction;
		// Decided to do parameter extraction and verification in constructor instead of during execution
		// this way we don't have to wait until execution to find out the command will fail.
		this.extractedParameters = new ArrayList<>(3);
		this.expectedParameterCount = 3;
		this.positionXValueIndex = 0;
		this.positionYValueIndex = 1;
		this.directionIndex = 2;
		extractParametersTo(extractedParameters);
		verifyParameterCount(extractedParameters);
		this.position = createPositionFrom(extractedParameters);
		this.direction = createDirectionFrom(extractedParameters);
	}
	
	private void extractParametersTo(final ArrayList<String> parameters) {
		commandParameters.listTo(new CommandParameterReceiver() {
			@Override
			public void receive(final String parameter) {
				parameters.add(parameter);
			}
		});
	}
	
	private void verifyParameterCount(final ArrayList<String> parameters) {
		if(parameters.size() != expectedParameterCount)
			throw new CommandParametersCountException();
	}
	
	private Position createPositionFrom(final ArrayList<String> extractedParameters) {
		return new Position(positionXValueFrom(extractedParameters), positionYValueFrom(extractedParameters));
	}

	private String positionXValueFrom(final ArrayList<String> extractedParameters) {
		return extractedParameters.get(positionXValueIndex);
	}
	
	private String positionYValueFrom(final ArrayList<String> extractedParameters) {
		return extractedParameters.get(positionYValueIndex);
	}
	
	private Direction createDirectionFrom(final ArrayList<String> extractedParameters) {
		return Direction.createFrom(extractedParameters.get(directionIndex));
	}

	@Override
	public void execute() {
		placeAction.place(position, direction);
	}
}
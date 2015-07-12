package toyrobotsimulator;

public class PlaceCommand implements Command {

	private final CommandParameters commandParameters;
	private final PlaceActionable placeActionable;

	public PlaceCommand(final CommandParameters commandParameters, final PlaceActionable placeActionable) {
		this.commandParameters = commandParameters;
		this.placeActionable = placeActionable;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
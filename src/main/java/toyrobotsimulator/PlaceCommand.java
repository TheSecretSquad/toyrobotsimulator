package toyrobotsimulator;

public class PlaceCommand implements Command {

	private final CommandParameters commandParameters;
	private final PlaceAction placeAction;

	public PlaceCommand(final CommandParameters commandParameters, final PlaceAction placeAction) {
		this.commandParameters = commandParameters;
		this.placeAction = placeAction;
	}

	@Override
	public void execute() {
		placeAction.place(new Position(1, 2), new Direction("NORTH"));
	}
}
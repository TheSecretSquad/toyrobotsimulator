package toyrobotsimulator;

public class ToyRobotPlaceSuccessfulHandler implements PlaceSuccessHandler {

	private Placeable placeable;
	private Position position;
	private Direction direction;
	
	public ToyRobotPlaceSuccessfulHandler(final Placeable placeable, final Position position, final Direction direction) {
		this.placeable = placeable;
		this.position = position;
		this.direction = direction;
	}
	
	@Override
	public void placeSuccessful() {
		placeable.placeAtPositionFacing(position, direction);
	}
}
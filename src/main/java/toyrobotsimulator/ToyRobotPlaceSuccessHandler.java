package toyrobotsimulator;

public class ToyRobotPlaceSuccessHandler implements PlaceSuccessHandler {

	private Placeable placeable;
	private Position position;
	private Direction direction;
	
	public ToyRobotPlaceSuccessHandler(final Placeable placeable, final Position position, final Direction direction) {
		this.placeable = placeable;
		this.position = position;
		this.direction = direction;
	}
	
	@Override
	public void placeSuccessful() {
		placeable.placeAtPositionFacing(position, direction);
	}
}
public class Jumper extends Actor {

    public Jumper() {
	super();
    }

    public void act() {
	if (canMove()){
	    move();
	}
	else {
	    turn();
	}
    }

    public void turn() {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    public void move() {
	Grid<Actor> gr = getGrid();
	if (gr == null)
	    return;
	Location loc = getLocation();
	Location c = loc.getAdjacentLocation(getDirection());
	Location next = c.getAdjacentLocation(getDirection());
	if (gr.isValid(next)){
	    moveTo(next);
	}
	else {
	    removeSelfFromGrid();
	}
    }

    public boolean canMove(){
	Grid<Actor> gr = getGrid();
	if (gr == null){
	    return false;
	}
	Location loc = getLocation();
	Location c = loc.getAdjacentLocation(getDirection());
	if (!gr.isValid(c)){
	    return false;
	}
	Actor neighbor = gr.get(next);
	if ( !(neighbor == null || neighbor instanceof Flower || neighbor instanceof Rock ) ) {
	    return false;
	}
	Location next = c.getAdjacentLocation(getDirection());
	if (!gr.isValid(next)){
	    return false;
	}
	neighbor = gr.get(next);
	return (neighbor == null) || (neighbor instanceof Flower);
    }
}


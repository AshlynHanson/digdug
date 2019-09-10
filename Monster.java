import java.util.Random;

public class Monster {
	
	private Location loc;
	private int dir;
	
	
	public Monster(Location loc, int dir) {
		this.loc = loc;
		this.dir = dir;
	}

	public Location getLoc() {
		return this.loc;
	}
	
	public boolean isLocated(Location loc) {
		if (this.loc.getRow() == loc.getRow() && this.loc.getCol() == loc.getCol()) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getID() {
		return "M";
	}
	
	public void move(int dr, int dc) {
		this.loc.move(dr, dc);
	}
	
	public String toString() {
		return "*** Monster:  Loc = " + this.loc.toString() + "  Dir = " + this.dir + " ***";
	}

}

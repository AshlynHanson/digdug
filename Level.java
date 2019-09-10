
/**
 * 	Creates game objects and displays them
 * 	
 */
public class Level {

	public static final int ROWS = 13;
	public static final int COLS = 12;
	private static final int DIVIDER_LENGTH = 49;
	private static final String dirtID = "-";
	private Monster[] monsters;
	private Rock[] rocks;
	private boolean[][] hasDirt;
	
	/** Dig Dug player object **/
	private DigDug digDug;

	/** Display string representing the game world **/
	private String display;
	
	
	/**
	 *	Default constructor, nothing for Level to do 
	 *	until the game gives it a DigDug and Monster
	 *	object.
	 */
	public Level(int numMonsters, int numRocks )
	{
		monsters = new Monster[numMonsters];
		rocks = new Rock[numRocks];
		this.setUpDirt();
	}
	
	
	/**
	 *	Adds a DigDug object for the player
	 */
	public void addPlayer( DigDug dd )
	{
		this.digDug = dd;
	}
	
	/**
	 *	Adds a Monster object 
	 */
	public void addMonster( Monster m )
	{
		for (int i = 0; i <monsters.length; i++) {
			if ( monsters[i] == null) {
				this.monsters[i] = m;
				return;
			}
		}
	}
	
	public void addRock( Rock r ) {
		for (int i = 0; i < rocks.length; i++) {
			if (rocks[i] == null) {
				this.rocks[i] = r;
				return;
 			}
		}
	}
	
	/**
	 *	Returns true if the given location is in bounds of level
	 */
	public boolean inBounds(Location target) {
		if (target.getRow() > (ROWS -1) || target.getCol() > (COLS -1 ) || (target.getRow() < 0) || target.getCol() <0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean validLocation( Location target )
	{
		if (this.inBounds(target) && this.getRock(target) == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setUpDirt() {
		this.hasDirt = new boolean[ROWS][COLS];
		this.hasDirt = new boolean[][]  {
				{true, true, true, true, true, true, true, true, true, true, true, true,},
				{true, true, true, true, true, true, true, true, true, true, true, true,},
				{true, true, true, true, true, true, true, true, true, true, true, true,},
				{true, true, true, true, true, true, true, true, true, true, true, true,},
				{true, true, true, true, true, true, true, true, true, true, true, true,},
				{true, true, true, true, true, true, true, true, true, true, true, true,},
				{true, true, true, true, true, true, true, true, true, true, true, true,},
				{true, true, true, true, true, true, true, true, true, true, true, true,},
				{true, true, true, true, true, true, true, true, true, true, true, true,},
				{true, true, true, true, true, true, true, true, true, true, true, true,},
				{true, true, true, true, true, true, true, true, true, true, true, true,},
				{true, true, true, true, true, true, true, true, true, true, true, true,},
				{true, true, true, true, true, true, true, true, true, true, true, true,}
		};
		for ( int i = 0; i < this.hasDirt.length;i++) {
			for ( int j = 0; j<this.hasDirt[i].length; j++) {
				Location loc = new Location(i,j);
				if (this.getRock(loc) != null) {
					this.hasDirt[i][j] = false;
				}
			}
		}
	}
	
	public boolean containsDirt(Location loc) {
		return this.hasDirt[loc.getRow()][loc.getCol()];
	}
	
	public Rock getRock(Location loc) {
		Rock r = null;                                        
		for (int i = 0; i< this.rocks.length; i++) {
			if (this.rocks[i] != null) {
				if( this.rocks[i].isLocated(loc)) {
					r = this.rocks[i];
				}
			}
		}
		return r;
	}
	
	public Monster getMonster(Location loc) {
		Monster m = null;                                    
		for (int i = 0; i< this.monsters.length; i++) {
			if (this.monsters[i] != null) {
				if (this.monsters[i].isLocated(loc)) {
					m = this.monsters[i];
				}
			}
		}
		return m;
	}
	
	public void update() {
		Location temp = this.digDug.getLoc();
		if (this.containsDirt(temp)) {
			this.hasDirt[temp.getRow()][temp.getCol()] = false;
		}
	}
	

	/**
	 *	Displays the game world to the screen
	 */
	public void display()
	{		
		this.buildDisplay();		
		System.out.println(display);
		System.out.println("\t\t\t" + this.digDug);
	}
	
//==============================================================
//		Display helper methods
//==============================================================

	/**
	 *	In charge of building the string that
	 *	represents the game world
	 */
	private void buildDisplay()
	{
		this.display = "\n";
		for( int r = 0; r < Level.ROWS; r++ )
		{
			this.buildRow(r);
			this.buildDivider();			
		}
	}
	
	/**
	 *	Builds a given row for the game world
	 */
	private void buildRow(int r)
	{
		this.display += "\t";
		for( int c = 0; c < Level.COLS; c++ )			
			this.buildCell(r, c);
		this.display += "|\n";
	}
	
	/**
	 *	Builds a given cell in the row of the
	 *	game world.
	 */
	private void buildCell(int r, int c)
	{
		this.display += "| " + this.getDisplayID(r, c) + " ";
	}

	/**
	 *	Retrieves the display id of the player or
	 *	the monster, if they're at the given location.
	 */
	private String getDisplayID( int r, int c )       
	{
		Location loc = new Location(r, c);
		if( this.digDug.isLocated(loc) ) {
			return this.digDug.getID();
		}
		else if (this.getMonster(loc) != null) {   
			return this.getMonster(loc).getID();
		}
		else if (this.getRock(loc) != null ) {
			return this.getRock(loc).getID();
		}
		else if (this.hasDirt[r][c]) {             
			return dirtID;
		}
		else {
			return " ";
		}
		
	}
	
	/**
	 *	Row divider in the display to make it easier
	 *	to see where one row starts and ends.
	 */
	private void buildDivider()
	{
		this.display += "\t";
		for(int i = 0; i < Level.DIVIDER_LENGTH; i++)
			this.display += "-";
		this.display += "\n";
	}
}	
	
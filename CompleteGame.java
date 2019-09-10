import java.util.Random;
/**
 *	Complete DigDug game handles controlling the 
 *	objects and taking user inputs.
 */
public class CompleteGame {
	
	private static final int totalMonsters = 3;
	private static final int totalRocks = 3;
	/** Current level for game **/
	private Level level;
	/** Current player **/
	private DigDug digDug;
	private Monster[] monsters;
	private Rock[] rocks;
	
	
	/**
	 *	Initiates a new game which handles setting up
	 *	a new level, the player, and the monster.
	 */
	public CompleteGame()
	{
		this.level = new Level(totalMonsters, totalRocks);
		this.setupPlayer();
		this.setUpMonsters();
		this.setUpRocks();
	}
	
	/**
	 *	Returns true if game is running, which is 
	 *	always true in this version
	 */
	public boolean isRunning()
	{
		return true;
	}
	
	/**
	 *	Displays the game level, instructions to screen
	 */
	public void display()
	{
		System.out.println( "\n\t\t\t\t\t*** Dig Dug! ***" );
		this.level.display();
		this.displayControls();
	}
	
	/**	
	 *	Attempts to move Dig Dug the given number of rows
	 *	and columns, then checks to make sure Dig Dug's 
	 *	location is still in bounds. If not, moves 
	 *	Dig Dug back to the previous location.
	 */
	public void tryMove( int dr, int dc )
	{
		digDug.move(dr, dc);
		if (level.validLocation(digDug.getLoc()) == false) {
			digDug.move(-dr,-dc);
		}
	}
	
	/**
	 *	Updates the monster each game loop. The
	 *	monster will randomly choose a new direction
	 *	to move in, then will attempt to move one 
	 *	step in that direction.
	 */
	private void updateMonster(Monster m)
	{
			
		Monster monster = m;
			
		int[] directions = {0,90,180,270};
		Random r = new Random();
		int rand = r.nextInt((directions.length -1));
		int dir = directions[rand];
			
			
		if (dir == 0) {
			monster.move(0,1);
			if (level.validLocation(monster.getLoc()) == false) {
				monster.move(0,-1);
			}
		}
		else if (dir == 90) {
			monster.move(-1, 0);
			if (level.validLocation(monster.getLoc()) == false) {
				monster.move(1,0);
			}
		}
		else if (dir == 180) {
			monster.move(0,-1);
			if (level.validLocation(monster.getLoc()) == false) {
				monster.move(0,1);
			}
		}
		else  if (dir == 270) {
			monster.move(1, 0);
			if (level.validLocation(monster.getLoc()) == false) {
				monster.move(-1,0);
			}
		}
		                                                     
	}

	private void updateAllMonsters() {
		for (Monster m: this.monsters) {
			this.updateMonster(m);

		}
		                           
	}

	public void update() {
		this.updateAllMonsters();
		level.update();
		
	}
//==============================================================
//		Private setup methods
//==============================================================

	/**
	 *	Sets up the player's Dig Dug object. Dig Dug will
	 *	always start at the top-middle of the game level,
	 *	facing to the right. Then, adds Dig Dug to the level.
	 */
    private void setupPlayer()
    {
    	Location startingLoc = new Location(0,6);
    	this.digDug = new DigDug(startingLoc, 0);
    	level.addPlayer(this.digDug);
    	
    }
    
    private void setUpMonsters() {
    	this.monsters = new Monster[totalMonsters];
    	for (int i = 0; i< totalMonsters; i++) {
    		this.monsters[i] = this.createMonster();
    		level.addMonster(this.monsters[i]);
    	}
    }
    
    private void setUpRocks() {
    	this.rocks = new Rock[totalRocks];
    	for (int i = 0; i< totalRocks; i++) {
    		this.rocks[i] = this.createRock();
    		level.addRock(this.rocks[i]);
    	}
    }


    private Monster createMonster() {
    	return new Monster(this.getRandomLocation(), this.getRandomDirection());
    }
    
    private Rock createRock() {
    	Location l = this.getRandomLocation();
    	return new Rock(l);
    }
    
    
    private Location getRandomLocation() {
    	int r = (int) ((Math.random() * 12) + 0);
    	int c = (int) ((Math.random() * 11) + 0);
    	return new Location(r,c);
    }
    
    private int getRandomDirection() {
	    int[] directions = {0, 90, 180, 270};
	    Random random = new Random();
	    int i = random.nextInt(directions.length);
	    return directions[i];
	  
    }
    


//==============================================================
//		Private display methods
//==============================================================

	/**
	 *	Displays the controls for the player to move
	 */
	private void displayControls()
	{
		String controls = "\n\n\t\t\t\t(W) UP";
		controls += "\n\t(A) Left\t(S) Down\t(D) Right";
		System.out.println( controls );
	}
    
}


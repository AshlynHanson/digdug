
import java.util.Scanner;
/**
 *	Executes the DigDug game
 */
public class PlayGame {
	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		CompleteGame game = new CompleteGame();
		while( game.isRunning() )
		{
			//Display the game
			game.display();
			
			//Ask for player move
			System.out.print("Enter Move -> ");
			String choice = input.nextLine().toUpperCase();
			
			//Handle input
			if( choice.equals("W") )
				game.tryMove(-1, 0);
			else if( choice.equals("S") )
				game.tryMove(1, 0);
			else if( choice.equals("A") )
				game.tryMove(0, -1);
			else if( choice.equals("D") )
				game.tryMove(0, 1);
			
			//Update game objects (i.e. monster)
			game.update();
		}
		
	}
}

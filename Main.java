import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

interface Game 
{
	void start();
}
interface Player
{
	int guess();
}
interface NumberGenerator 
{
	int generateNumber();
}


class GuessTheNumberGame implements Game
{
	private final NumberGenerator numberGenerator;
	private final Player player;

	public GuessTheNumberGame(NumberGenerator numberGenerator, Player player) 
	{
		this.numberGenerator = numberGenerator;
		this.player = player;
	}

	@Override
	public void start()
	{
		int targetNumber = numberGenerator.generateNumber();
		int guess;

		do {
			guess = player.guess();
			if (guess < targetNumber) {
				System.out.println("You have guessed too low!");
			} else if (guess > targetNumber) {
				System.out.println("You have guessed too high!");
			} else {
				System.out.println("Bingo! You nailed it.");
			}
		} while (guess != targetNumber);
	}
}

class HumanPlayer implements Player
{
	@Override
	public int guess() 
	{	
		int userGuess = 0;		
		Scanner scanner = new Scanner(System.in);
		System.out.println("What is your guess?");
		boolean answer = false;
		while (!answer) {
		
			try {	
				userGuess = scanner.nextInt();
				System.out.println("You guessed " + userGuess + ".");
				answer = true;
			} catch (InputMismatchException e) {
				System.out.println("You must enter a whole number!");
				scanner.nextLine();	
			}

		}
	
	
		return userGuess;
	}		
}

class RandomNumberGenerator implements NumberGenerator
{
	@Override
	public int generateNumber()
	{
		Random randomNumber = new Random();
		int rn = randomNumber.nextInt(11);
		System.out.println(rn);
		return rn;	
	}	
}

public class Main 
{
	public static void main(String[] args) 
	{
		NumberGenerator numberGenerator = new RandomNumberGenerator();
		Player player = new HumanPlayer();
		Game game = new GuessTheNumberGame(numberGenerator, player);
		game.start();
	}
}
















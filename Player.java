import java.util.Scanner;
import java.util.HashMap;


public class Player {


	// public:
	public Player(String player_name) {
		points = 0;
		name = player_name;
	}

	public void move(String country, HashMap<String, String> data) {
		//System.out.println(Game.data);
		Scanner input = new Scanner(System.in);
		String answer = data.get(country); // this is the answer
		double initialTime = System.currentTimeMillis();
		String guess = input.nextLine().trim(); //user input
		double finalTime = System.currentTimeMillis();
		
		
		if((finalTime-initialTime)/1000>10){
			System.out.println("\nOh, you took too long. (-1)");
			if (points != 0) {
				points--;
			} else {
				System.out.println("How do you have zero points. SMH (-_-) \n");
			}
	
		}
		else if (guess.equalsIgnoreCase(answer)) {
			points++;
			System.out.println("\nYou are correct you loser (+1) :) \n");
		} else {
			System.out.println("\nYou are wrong, and you are still a loser (-1) :(");
			System.out.println("The correct answer is: " + answer + ":(");
			if (points != 0) {
				points--;
			} else {
				System.out.println("How do you have zero points. SMH (-_-) \n");
			}
		}
	}

	public void showScores() {
		System.out.println(String.format("%10s's score: %10d \n",name, points));
	}

	public int accessScore() {
		return (points);
	}

	public String getName() {
		return (name);
	}



	// private: 

	private int points;
	private String name;



}

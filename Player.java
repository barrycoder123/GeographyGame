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

        if (guess.equals("quit")) System.exit(0);
		
		
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
			if (points >= 7) {
				points--;
			} else if(points == 0) {
				System.out.println("How do you have zero points. SMH (-_-) \n");
			} else {
				System.out.println("LOSER: '\'\'(^*^)// \n");
            }
		}
	}
    public void guess(String country, String[][] borderingCountries) {
        //TODO:
        // change this crap to hashmap
        Scanner input = new Scanner(System.in);
		double initialTime = System.currentTimeMillis();
        String ans = input.nextLine().trim();
		double finalTime = System.currentTimeMillis();

        if (ans.equals("quit")) System.exit(0);

		if((finalTime-initialTime)/1000>10){
			System.out.println("\nOh, you took too long. (-1)");
			if (points != 0) {
				points--;
			} else {
				System.out.println("How do you have zero points. SMH (-_-) \n");
			}
	
		}
        
        boolean correct = false;

        for (int i = 0; i < borderingCountries.length; ++i) {
            if (borderingCountries[i][0].equals(country)) {
                /// found 
                for (int j = 1; j < borderingCountries[i].length; ++j) {
                    if (borderingCountries[i][j].trim().equalsIgnoreCase(ans.trim())) {
                        correct = true;
                    }
                }
            }
        }
        
        System.out.println("the value of correct is -> " + correct); 
        if (correct) {
            points++;
            System.out.println("\nHUZZAH!!! Brilliant my friend. You are not a bot");

        } else {
            System.out.println("THAT IS INCORRECT, MY FRIEND - Sabally 2019");
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

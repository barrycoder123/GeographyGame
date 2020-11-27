import java.util.Scanner;
import java.util.HashMap;


public class Player {


    // public:
    public Player(String player_name) {
        this.points = 0;
        this.name = player_name;
    }

    public void move(String country, HashMap<String, String> data) {
        //System.out.println(Game.data);
        Scanner input = new Scanner(System.in);
        String answer = data.get(country); // this is the answer
        String guess = input.nextLine().trim(); //user input
        if (guess == answer) {
            points++;
            System.out.println("You are correct you loser :) \n");
        } else {
            System.out.println("You are wrong, and you are still a loser :( \n");
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

    





    // private: 

    private int points;
    private String name;



}

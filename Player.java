import java.util.Scanner;
import java.util.HashMap;


public class Player {


    // public:

    public void move(String country, HashMap<String, String> data) {
        //System.out.println(Game.data);
        Scanner input = new Scanner(System.in);
        String answer = data.get(country); // this is the answer
        String guess = input.next(); //user input
        if (guess == answer) {
            points++;
            System.out.println("You are correct you loser :) \n");
        }
    }





    // private: 

    private int points = 0;









}

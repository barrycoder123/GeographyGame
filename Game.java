import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;
public class Game {


	public static void main(String[] args)  throws Exception { 
        readData();
        flagData();
        borders();
        contestants();
        playGame();
	}

    public static void readData() throws Exception { 

		Scanner file = new Scanner(new File("dataset.txt"));
		int i = 0;
		while (file.hasNext()) {

			String keys, values, line;

			line = file.nextLine(); // get the whole line - string
			countries[i] = line.substring(0, line.indexOf('-')).trim();
			capitals[i] = line.substring(line.indexOf('-') + 1, line.length()).trim();
			i++;

		}


		for (int j = 0; j < 196; ++j) {
			data.put(countries[j], capitals[j]);
		}
    }

    public static void flagData() throws Exception {

		Scanner file2 = new Scanner(new File("flagdata.txt"));

		int f = 0; 
		while (file2.hasNext()) {
			String values, line;

			line = file2.nextLine().trim();
			flagFileNames[f] = line;


			f++;
		}

		for (int l = 0; l < 196; ++l) {
			flagData.put(countries[l], flagFileNames[l]);
		}
    }

    public static void borders() throws Exception {
		//Establish Bordering Countries

		Scanner borderScanner = new Scanner(new File("Bordering.txt"));
		int counter = 0;

		while(borderScanner.hasNext()){
			String currentLine = borderScanner.nextLine().trim();

			int indexOne = 0;
			int counterTwo = 0;
			borderingCountries[counter] = new String[StringUtils.countMatches(currentLine,',')];
			for(int a=0; a<StringUtils.countMatches(currentLine,','); a++){
				int indexTwo = currentLine.indexOf(",",indexOne);
				//System.out.println(currentLine.substring(indexOne,indexTwo));
				borderingCountries[counter][counterTwo++] = currentLine.substring(indexOne,indexTwo);
				indexOne = indexTwo+1;	
			}
			counter++;
		}
        for (int i = 0; i < borderingCountries.length; ++i) {
            for (int j = 0; j < borderingCountries[i].length; ++j) {
                System.out.println(borderingCountries[i][j]);
            }
        }

    }

    public static void playGame() throws Exception {
		Random rn = new Random();

		// game loop
		boolean end = false;
		while (end == false) {
			//System.out.println(players[0].accessScore() + " " + players[1].accessScore());
			
			for(int j = 0; j < numPlayers; j++){
				String currentCountry = countries[rn.nextInt(196)]; 
				System.out.print("\n"+ players[j].getName() + ", it's your turn.  What is the capital of ");
				System.out.println(currentCountry + "?");
				players[j].move(currentCountry, data);

                System.out.println("\nName one country that borders " + currentCountry +".");
                players[j].guess(currentCountry, borderingCountries);
			}

			for (Player p: players) 
				p.showScores();
            for (Player p: players) {
                if (p.accessScore() == 10) {
                    end = true;
                }
            }
		}

    }

    public static void contestants() throws Exception {

		Scanner sc = new Scanner(System.in);
		String name;
		//Start Game
		System.out.println("Welcome To The Geography Game!");
		System.out.println("Enter the number of participants:");
        numPlayers = sc.nextInt();
		System.out.println("\nPlease enter the name of the contestants.");

        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; ++i) {
            name = sc.next();
            players[i] = new Player(name);
        }
    }

    private static String[] countries = new String[196];
    private static String[] capitals = new String[196];
	private static HashMap<String, String> data = new HashMap<>();
    private static HashMap<String, String> flagData = new HashMap<>();
    private static String[] flagFileNames = new String[196];
    private static String[][] borderingCountries = new String [148][];
    private static int numPlayers;
	private static Player[] players;
}






















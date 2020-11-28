import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Random;
import org.apache.commons.lang3.StringUtils; 
public class Game {


	public static void main(String[] args)  throws Exception { 

		// READ FROM THE FILE
		// TODO: 
		// open file 
		// create data stucture - hashmap
		// create two arrays - for countries and for capitals
		// read file and populate the two arrays
		// loop through the key array and add to hash map keys
		// loop through the value array and add to hash map values
		HashMap<String, String> data = new HashMap<>();
		String[] countries = new String[196];
		String[] capitals = new String[196];

		Scanner file = new Scanner(new File("dataset.txt"));
		int i = 0;
		while (file.hasNext()) {

			String keys, values, line;

			line = file.nextLine(); // get the whole line - string
			countries[i] = line.substring(0, line.indexOf('-')).trim();
			capitals[i] = line.substring(line.indexOf('-') + 1, line.length()).trim();
			i++;

		}


		for (int j = 0; j < 195; ++j) {
			data.put(countries[j], capitals[j]);
		}

		HashMap<String, String> flagData = new HashMap<>();
		String[] flagFileNames = new String[196];

		Scanner file2 = new Scanner(new File("flagdata.txt"));

		int f = 0; 
		while (file2.hasNext()) {
			String values, line;

			line = file2.nextLine().trim();
			flagFileNames[f] = line;

			f++;
		}

		for (int l = 0; l < 195; ++l) {
			flagData.put(countries[l], flagFileNames[l]);
		}

		Scanner sc = new Scanner(System.in);
		String name;
		//Start Game
		System.out.print("Welcome To The Geography Game!\nPlease enter the name");
		System.out.println("of the contestants. When you are done, enter \"done\".");

		Player[] players = new Player[3];
		int k = 0;
		do{
			name = sc.next();
			if (!name.equals("done")){
				players[k++] = new Player(name);
			} else if(k!=3){
				name = "";
			}

		} while(!name.equals("done")); 

		Random rn = new Random();

		//Establish Bordering Countries

		Scanner borderScanner = new Scanner(new File("Bordering.txt"));
		String[][] borderingCountries = new String [148][];
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
		//
		//		for(int a=0;a<borderingCountries.length;a++){
		//			for(int b=0;b<borderingCountries[a].length;b++){
		//				System.out.print(borderingCountries[a][b]);
		//			}	
		//			System.out.println();
		//		}

		// game loop
		boolean end = false;
		while (end == false) {

			for(int j = 0; j < 3; j++){
				String currentCountry = countries[rn.nextInt(196)]; 
				System.out.print("\n"+ players[j].getName() + ", it's your turn.  What is the capital of ");
				System.out.println(currentCountry + "?");
				players[j].move(currentCountry, data);

				System.out.println("\nName one country that borders " + currentCountry +".");
				player[j].guess(currentCountry, borderCountries);

			}

			for (Player p: players) 
				p.showScores();
			if(players[0].accessScore() == 10 || players[1].accessScore() == 10 || players[2].accessScore() == 10){
				end = true;
			}
		}




	}
}






















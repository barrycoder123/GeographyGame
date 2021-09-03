import java.util.Scanner;
import javax.swing.ImageIcon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {
	Player[] players;
	
    static String[] capitals = new String[196];
    
    static String[][] borderingCountries = new String [148][];

    public Game() {

    }

	public static String[] getCountries() {
		
		InputStream in = Game.class.getResourceAsStream("/dataset.txt");
		
		String[] countries = new String[196];
		
		try(Scanner file= new Scanner(in)) {
			int i = 0;
			while (file.hasNext()) {
				String line;
				
				line = file.nextLine(); // get the whole line - string
				countries[i] = line.substring(0, line.indexOf('-')).trim();
		
				i++;
			}	
		} 
		return countries;
	}
	
	public static HashMap<String,String> getCountryMap() {
		 HashMap<String, String> capMap = new HashMap<>();
		 String[] countries = getCountries();
		 
		 InputStream in = Game.class.getResourceAsStream("/dataset.txt");
		 
		 try(Scanner file= new Scanner(in)) {
				int i = 0;
				while (file.hasNext()) {
					String line;
			
					line = file.nextLine(); // get the whole line - string
					capitals[i] = line.substring(line.indexOf('-') + 1, line.length()).trim();
			
					i++;
				}

				for (int j = 0; j < 196; ++j) {
					capMap.put(countries[j], capitals[j]);
				}
				
			} 
		 return capMap;
	}
	
	public static ArrayList<String> getFlagsCountries() {
		 ArrayList<String> countries = new ArrayList<>();
		 InputStream in = Game.class.getResourceAsStream("/flagdata.txt");
		 
		 
		 try(Scanner file= new Scanner(in)) {
				
				while (file.hasNext()) {
					String line = file.nextLine().trim(); 
					String name = line.substring(0,line.indexOf('.'));
					name = name.replace('_', ' ');
					countries.add(name);
				}
			}
		 
		 return countries;
	}
	
	public static HashMap<String,ImageIcon> getFlagMap() {
		 HashMap<String, ImageIcon> flagMap = new HashMap<>();
		 ArrayList<String> countries = new ArrayList<>();
		 ArrayList<ImageIcon> images = new ArrayList<>();
		 
		 InputStream in = Game.class.getResourceAsStream("/flagdata.txt");
		 
		 try(Scanner file= new Scanner(in)) {
				
				while (file.hasNext()) {
					String line = file.nextLine().trim(); 
					String name = line.substring(0,line.indexOf('.'));
					name = name.replace('_', ' ');
					ImageIcon image = new ImageIcon("Flags/"+line);
					
					countries.add(name);
					images.add(image);
				}

				for (int j = 0; j < 196; ++j) {
					flagMap.put(countries.get(j), images.get(j));
				}
				
			} 
		 
		 return flagMap;
	}
	
	public static ArrayList<String> getBordCountries() {
		 ArrayList<String> bordMap = new ArrayList<>();
		 InputStream in = Game.class.getResourceAsStream("/Bordering.txt");
		 
		 try(Scanner borderScanner = new Scanner(in)) {
				while(borderScanner.hasNext()){

					String currentLine = borderScanner.nextLine().trim();
					Scanner lineScanner = new Scanner(currentLine).useDelimiter(",");
					String main = lineScanner.next();
					bordMap.add(main);
				}
			}
		 
		 return bordMap;
	}
	
	public static HashMap<String,String[]> getBordMap() {
		 HashMap<String, String[]> bordMap = new HashMap<>();
		 InputStream in = Game.class.getResourceAsStream("/Bordering.txt");
		 
		 try(Scanner borderScanner = new Scanner(in)) {
				while(borderScanner.hasNext()){
					int counter = 0;
					
					String currentLine = borderScanner.nextLine().trim();
					Scanner lineScanner = new Scanner(currentLine).useDelimiter(",");
					String main = lineScanner.next();
					ArrayList<String> bord = new ArrayList<>();
					
					while(lineScanner.hasNext()) bord.add(lineScanner.next());
					
					String[] arr = new String[bord.size()];
					for(String x:bord) {
						arr[counter]=bord.get(counter);
						counter++;
					}
					
					bordMap.put(main, arr);
				}
				
			} 
		 return bordMap;
	}
    
}






















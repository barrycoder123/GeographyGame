import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;

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
            System.out.println("this is the country we read in: " + countries[i]);
            capitals[i] = line.substring(line.indexOf('-') + 1, line.length()).trim();
            System.out.println("this is the capital we read in: " + capitals[i]);
            i++;

        }

        // populate the map
        //
        for (int j = 0; j < 195; ++j) {
            data.put(countries[j], capitals[j]);
        }

        
        System.out.println("size of the map is: " + data.size());
        System.out.println(data);

        

    }


}
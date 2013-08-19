import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class ArtistPairFinder {

	/**
	 * @author Xuan Li (xuanlinyc@gmail.com)
	 * 
	 */	
	
	public static HashMap<Pair, Integer> map =  new HashMap<Pair, Integer>();
	public static int THRESHOLD ;
	
	/*
	 * Need to input FineName and Threshold from command line
	 *  eg :
	 *  
	 *  Artist_lists_small
	 *  50
	 *  
	 *  Then fileName will be passed to method readFile. 
	 *  The result file is ArtistPair.txt
	 *  
	 */
	
	public static void main(String[] args) {
		
		System.out.println("Please input fileName and threshold");
		
		if(args.length != 2) {
			System.out.println(" Input format Error: String Integer");
		    System.exit(1);		
		}
	
		try {		
			THRESHOLD = Integer.parseInt(args[1]);		
			if(THRESHOLD < 0 ) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			System.out.println(" Threshold must be possitive number)");
		    System.exit(1);	
		}
		System.out.println(args[0]);
		readFile(args[0]);
		
	}
	
	
	/*
	 * Read the .txt file by line 
	 * For each line, split it into String[] 
	 * Sort String[] and call getArtPair methods to created Artist Pair.
	 * 
	 * Using sort String[] to avoid duplicate pairs (A,B) and (B,A). 
	 */
	
	public static void readFile(String fileName) {
			
		try{	
			
			FileReader fileReader = new FileReader(fileName + ".txt");			
			BufferedReader br = new BufferedReader(fileReader);	
			
			String templine = null;
			while((templine = br.readLine()) != null) {
				String line = new String(templine.getBytes(), "UTF-8");
				String[] artlist = line.split(",");
				Arrays.sort(artlist);
				getArtPair(artlist);			
			}
			
			br.close();
			fileReader.close();
			
		} catch (FileNotFoundException  e) {
			System.out.println("Unable to open file " + fileName );		
		} catch (IOException e) {
			System.out.println("Error reading file" + fileName );
		}
		
		printPairs();
		
	}
	
	/*
	 * Method getArtPair input a String[] 
	 * Find out all possible Artist pairs 
	 * Created Pair(art1, art2) , insert and count them in HashMap map
	 * 
	 * The sorted String[] garantee in each Pair(art1, art2), art1 <= art2 
	 * 
	 */
	
	public static void getArtPair(String[] artlist) {
		
		int len = artlist.length ;
		for(int i = 0 ; i < len -1; i++) {
			for(int j = i+1; j < len ; j++) {
				Pair pair = new Pair(artlist[i], artlist[j]);
				if(map.containsKey(pair)) {
					int value = map.get(pair);
					map.put(pair, value+1);
				} else {
					map.put(pair, 1);					
				}
			}
			
		}
		
	}
	
	
	/*
	 * Iterate every <Pair, Integer> set in HashMap map 
	 * print and save artist pair (Pair) which value is >= Threshold
	 * 
	 * The result file is ArtistPair.txt
	 */
	
	public static void printPairs() {
		
		try {
			File file = new File("./ArtistPair.txt");
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw =  new BufferedWriter(fw);
			
			Iterator<Map.Entry<Pair, Integer>> it = map.entrySet().iterator();
			while(it.hasNext()) {
				Entry<Pair, Integer> entry = it.next();
				if(entry.getValue() >= THRESHOLD) {
					String s = entry.getKey().printPair();
					bw.write(s);
					bw.newLine();
				}				
			}
			bw.close();
			fw.close();
					
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		
	}
	
	/*
	 * Pair class. contains two artist.
	 * Override equals and hashCode methods to use in HashMap 
	 */

	public static class Pair {
		 String art1 = null;
		 String art2 = null;
		 
		public Pair(String art1, String art2) {
			this.art1 = art1;
			this.art2 = art2;
		}		
		
		public String printPair() {
			System.out.println(art1 + "," + art2);
			return(art1 + "," + art2 ) ;
		}
		
		@Override 		
		public boolean equals(Object obj ) {
			if(obj instanceof Pair) {
				if( ((Pair)obj).art1.equals(this.art1) && ((Pair)obj).art2.equals(this.art2) ) {
					return(true);
				}				
			}			
			return(false);		
		}
		
		@Override
		public int hashCode() {
			String str =  art1 + art2;
			return(str.hashCode());
		}
		
	}
	
}

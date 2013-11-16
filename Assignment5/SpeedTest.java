import java.util.Date;
import java.util.List;

public class SpeedTest {
    
    public static void main(String[] args)
    {

	try{
	    //Change this to change whether you create a BST 
	    //or a skiplist
	    SortedMap<String,Integer> t = 
		new BSTMap<String,Integer>();
	    //	new SkiplistMap<String,Integer>();

	    //Try the different files here
	    FileParser fp = new FileParser("WarAndPeace.txt");
	    //  FileParser fp = new FileParser("RomeoAndJuliet.txt");
	    //	FileParser fp = new FileParser("TWL06.txt");

	    List<String> words = fp.getAllWords();
	    Date startTime1 = new Date();
	    //add all the words
	    for(String word : words){
		Integer i = t.get(word);
		if (i == null) {
		    t.put(word, 1);
		} else {
		    t.put(word,i+1);
		}
	    }

	    Date endTime1 = new Date();
	    System.out.println("Time to add all words in script: " +  
			       (endTime1.getTime() - startTime1.getTime()));


	    System.out.println("The statistics for the data-structure are:");
	    System.out.println(t.calculateStats());

	    //now, find the most common word in the script
	    //that is also in the scrabble dictionary
	    FileParser dict = new FileParser("TWL06.txt");
	    List<String> dictWords = dict.getAllWords();
	    int bestOccurances = 0;
	    String mostCommonWord = null;
	    Date startTime2 = new Date();
	    for (int i = 0; i < 100; i++){
		for(String word : dictWords){
		    Integer wordOccurances = t.get(word);
		    if(wordOccurances != null && wordOccurances > bestOccurances){
			bestOccurances = wordOccurances;
			mostCommonWord = word;
		    }
		}
	    }
	    Date endTime2 = new Date();
	    System.out.println("Time to find most common word: " +  
			       (endTime2.getTime() - startTime2.getTime()));

	    System.out.println("The most common Scrabble word in the script is: " 
			       + mostCommonWord);

	    Date startTime3 = new Date();
	    int size = 0;
	    for (int i = 0; i < 100; i++){
		for(String word : t){
		    size++;
		}
	    }
	    Date endTime3 = new Date();
	    System.out.println("Time to iterate through the set was " +  
			       (endTime3.getTime() - startTime3.getTime()));

	    System.out.println("The size value was:" + size/100);

	} catch (Exception e){e.printStackTrace();}
    }
}



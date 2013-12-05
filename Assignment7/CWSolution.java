//YOU SHOULD MODIFY THIS FILE.
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.regex.*;

public class CWSolution {
    public HashMap<Integer, StringBuilder> dict;
    public Matcher m;

    /**
     * Builds a storage unit for the list of words the user
     * wishes.
     *
     * @param allWords A List of String objects, each string is a word.
     */

    public CWSolution(List<String> allWords)
    {
      dict = new HashMap<Integer,StringBuilder>();
      int length;
      //Iterate over list
      for (String word : allWords) {
        length = word.length();
        if (dict.get(length) == null) {
          //If no stringbuilder exists, make a new one
          StringBuilder sb = new StringBuilder();
          dict.put(length, sb);
        }
        //add word + 2 spaces to stringbuilder in corresponding container.
       dict.get(length).append(" "+word+" ");
      }
    }

    /**
     * Returns a list of possible solutions where * is used as a single wildcard
     * for the search pattern.
     *
     * @param pattern String pattern, * is any non-whitespace.
     * @return List of possible solutions
     */

    public List<String> solutions(String pattern, int maxRequired)
    {
      ArrayList<String> result = new ArrayList<String>();
      int i = 0;
      String nextWord;
      int length = pattern.length();
      //Check to see if dict contains a string.
      if (dict.get(length) == null) {return result;}
      //Get String of all length long words
      String words = dict.get(length).toString();
      //Formatting for regex
      pattern = pattern.replaceAll("\\*","\\\\S");
      //Formatting search pattern
      Pattern pat = Pattern.compile(pattern);
      m = pat.matcher(words);
      //While loop will keep adding as long as there are words to return,
      //and max return hasn't been reached
       while(((nextWord = getNextWord()) != null) && (i < maxRequired)) {
          result.add(nextWord);
          i++;
      }
      return result;
    }
    /**
     * Helper function to check matcher for next word.
     */
    private String getNextWord() {
      while (true) {
        if (m.find()) {
          return m.group();
        }
        return null;
      }
    }
}





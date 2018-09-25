package encrypdecrypt;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EngDictionary {
	private Map<String, Integer> dicMap;
	
	public EngDictionary(int minLetterCount, int maxLetterCount) {
		dicMap = new HashMap<String, Integer>();
		int i = 0;
		try (BufferedReader br = Files.newBufferedReader(Paths.get(System.getProperty("user.home"),"Desktop", "COMP4109", "encrypdecrypt", "src", "ressources", "words.txt"))) {
		    for (String line = null; (line = br.readLine()) != null;) {
		    	if (line.length() <= maxLetterCount && line.length() >= minLetterCount) {
		    		
		    		Pattern p = Pattern.compile("[^a-zA-Z]");   // the pattern to search for
		    	    Matcher m = p.matcher(line);
		    		
		    		if(!m.find()) {
		    			i++;
		    			dicMap.put(line.toUpperCase(), line.length());
		    		}
		    	}
		    }
		} catch (IOException e) {
			System.out.println("error loading dictionary");
		}
		System.out.println(i);
	}
	
	public boolean containsDictionaryWord(String message, ArrayList<String> words) {
		return containsDictionaryWords(message, 1, words);
	}
	
	public boolean containsDictionaryWords(String message, int numToContain, ArrayList<String> words) {
		int numContains = 0;
		for(String dictWord : dicMap.keySet()) {
			if (message.contains(dictWord)) {
				words.add(dictWord);
				numContains++;
				if (numContains >= numToContain) {
					return true;
				}
			}
		}
		return false;
	}
}

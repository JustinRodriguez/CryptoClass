package encrypdecrypt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class Run {
	
	

	public static void main(String[] args) {
		/*
		String message = "ONEIGHBOURINGFARMSANDBEYONDTOTELLTHEANIMALSABOUTTHEREBELLIONNEWSOFTHEREBELLIONHASSPREADTOTHESURROUNDINGCOUNTYTHEFARMERSATFIRSTPR";
		System.out.println(message.length());
		String message40 = message.substring(0, 40);
		System.out.println(message40);
		System.out.println(message40.length());
		*/
		
		forceLenghtThree("DOG", 1000, 5, 9, 3);
	}
		
	private static void forceLenghtThree(String startKey, int maxNumFound, int dictWordMin, int dictWordMax, int numWordsValid) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/encrypdecrypt/test.txt"));
			EngDictionary engDic = new EngDictionary(dictWordMin, dictWordMax);
			Vignere vignere = new Vignere();
			Map<Integer, Character> map = vignere.getMap();
			
			int foundNum = 0;
			boolean found = false;
			String message = "";
			String key = startKey;
			while(true) {
				if(foundNum >= maxNumFound) {
					writer.close();
					if (found) {
						System.out.println("c:");
					} else {
						System.out.println(":c");
					}
					return;
				} 
				message = vignere.decode(key, "VCWAZLZFBUKZQKNYTCVGNPFMJGDOUBUOCNVSXSVFKPLKVZPAMAZEVZVRWWVTZSFWHMSOTYUQHPRSECORZSSZNTOPMGCPSVGNPABUKJVIQMZBMGULBLOKJCVQZSSNHXEVMFZWWUVZDCNCNJGQPGWZIJYCSAGXFQBHXPRBUKQIVQGXSVGGWTIOXTQWSSLHBRXDHZNOYSLGCTGBRJLBLOXZYMAZSSTNCDCNCUDGQOOWWBLGYRQZVZGAVHTZQGEMIBFZTZTGNPUZRGESAGKIQQGKXSVGUQOTYYPSURJECJRZZAMRZLAIACTHPNTZFIAMPGIFNCCCAJSWAAKNYIAUCOVTKDOAUCLGEUGEHPRVCSAVJPBBBLEVMTGWOFLZCOLVZTCVNRWMEBXPWBZORVBAUESDRTSODRSLRMZANVLVLQSZRTNSBBZSSUVLEVMLJVBWJTPLIPZWMPBCXIKUVZKMEZSSXEKDWLRTECNGNPUIYGIMIPZFOTYEHWMYJPRVBTPOBNRWCVYEDWFCKZDTROYHPRMLZIKEVBMJZSOBGNPXWOUQHPRMLZIPZTQXEKDWLRTEKIFTZHBBCTSTQVZKMEHFHBBGEHZNIEOBGKYHQBTLKILLCCUVZKOXUUOPMRHWSJEUIKIFGXOHVTRZGTUZRIGNTGRBHEVMPXZKLTGDDMQJLNHYKOPGFAYOVQYPSUNTDVQCGDHPRVCSAVJPBBVGWGXRKOPWNZKWXCKOFWHTOHPRNPOLYGYRQAZZHPRHLMQGLWOAUKOOVQYSCVRGDWBPGXSAXGEWVTUGSZGNPGMNOYKQQKDYQQJTBOGACBAVTQOKGOERQQTEBMRJECBBANVBUKHOBRXLHIYRMSKNADSQGCLGAHVACZGKOCVNNLNGPADVQBTZTQBTTNMQGECUFHFHRHYETWEKQTMPZTHENYQWBGKOKQGNEVQALTBJYGOSAJNTQPPUFZLOKWCERXPRQAZZHPRCLHMEZSSGFRLGPRJDVMRZDCNJGESZUODGQAMTBBBZSSIVXNOZ");
				
				ArrayList<String> words = new ArrayList<String>();
				if (engDic.containsDictionaryWords(message, numWordsValid, words)) {
					found = true;
					foundNum ++;
					writer.append("Key: "+key+ "\t words: ");
					for (String word : words) {
						writer.append(word + "\t");
					}
					writer.append( "message: "+message+ "\n");
				}
				
				key = incrementCharAtPos(key, key.length() - 1, map);
			}
		} catch (IOException e) {
			System.out.println("fail to open write file");
			return;
		}
		//System.out.println(vignere.decode("LEMON",vignere.encode("LEMON", "ATTACKATDAWN")));
	}
	
	private static String incrementCharAtPos(String key, int pos, Map<Integer, Character> aToZ) {
		char charAtPos = key.charAt(pos);
		if (charAtPos == 'Z') {
			if (pos == 0) {
				if (key.length() > 1) {
					key = "AA" + key.subSequence(1, key.length());
				} else {
					key = "AA";
				}
			} else {
				key = "" + key.subSequence(0, pos) + 'A' + key.subSequence(pos + 1, key.length());
				key = incrementCharAtPos(key, pos - 1, aToZ);
			}
		} else {
			for (Entry<Integer, Character> value : aToZ.entrySet()) {
				if (value.getValue() == charAtPos) {
					key = "" + key.subSequence(0, pos) + aToZ.get(value.getKey() + 1) + key.subSequence(pos + 1, key.length());
					break;
				}
			}
		}
		if (pos == 0) {
			System.out.println(key);
		}
		return key;
	}

}

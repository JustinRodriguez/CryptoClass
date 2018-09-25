package encrypdecrypt;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Vignere {
	
	private Map<Integer, Character> baseMap;
	
	public Vignere() {
		baseMap = new HashMap<Integer, Character>();
		baseMap.put(0, 'A');
		baseMap.put(1, 'B');
		baseMap.put(2, 'C');
		baseMap.put(3, 'D');
		baseMap.put(4, 'E');
		baseMap.put(5, 'F');
		baseMap.put(6, 'G');
		baseMap.put(7, 'H');
		baseMap.put(8, 'I');
		baseMap.put(9, 'J');
		baseMap.put(10, 'K');
		baseMap.put(11, 'L');
		baseMap.put(12, 'M');
		baseMap.put(13, 'N');
		baseMap.put(14, 'O');
		baseMap.put(15, 'P');
		baseMap.put(16, 'Q');
		baseMap.put(17, 'R');
		baseMap.put(18, 'S');
		baseMap.put(19, 'T');
		baseMap.put(20, 'U');
		baseMap.put(21, 'V');
		baseMap.put(22, 'W');
		baseMap.put(23, 'X');
		baseMap.put(24, 'Y');
		baseMap.put(25, 'Z');
	}
	
	public Map<Integer, Character> getMap() { return baseMap; }
	
	public String encode(String key, String plainMessaget) {
		
		String encodedMessage = "";
		for(int i = 0; i<plainMessaget.length();i++) {
			int plainCharPos = -1;
			int keyCharPos = -1;
			
			char plainChar = plainMessaget.charAt(i);
			char keyChar = key.charAt(i % key.length());
			for (Entry<Integer, Character> entry : baseMap.entrySet()) {
				if (entry.getValue() == keyChar) {
					keyCharPos = entry.getKey();
				}
				if (entry.getValue() == plainChar) {
					plainCharPos = entry.getKey();
				}
				
				if (plainCharPos != -1 && keyCharPos != -1) {
					break;
				}
			}
			int charValuePos = (plainCharPos + keyCharPos) % 26;
			encodedMessage += baseMap.get(charValuePos);
		}
		return encodedMessage;
	}
	
	public String decode(String key, String cipherMessage) {
		String plainMessage = "";
		for(int i = 0; i<cipherMessage.length();i++) {
			int cipherCharPos = -1;
			int keyCharPos = -1;
			
			char cipherChar = cipherMessage.charAt(i);
			char keyChar = key.charAt(i % key.length());
			for (Entry<Integer, Character> entry : baseMap.entrySet()) {
				if (entry.getValue() == keyChar) {
					keyCharPos = entry.getKey();
				}
				if (entry.getValue() == cipherChar) {
					cipherCharPos = entry.getKey();
				}
				
				if (cipherCharPos != -1 && keyCharPos != -1) {
					break;
				}
			}
			int charValuePos = (cipherCharPos - keyCharPos) % 26;
			if (charValuePos < 0) {
				charValuePos = 26 + charValuePos;
			}
			//System.out.println(cipherChar + "\t | " + keyChar + "\t | " + cipherCharPos + "\t | " + keyCharPos + "\t | " + ((cipherCharPos - keyCharPos) % 26) + "\t | " + charValuePos);
			plainMessage += baseMap.get(charValuePos);
		}
		return plainMessage;
	}

}

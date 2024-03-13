
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		String word = word1.toLowerCase();
		int dis = 0;
		if (Math.min (word1.length(), word2.length()) == 0) {
			dis = Math.max(word1.length(), word2.length());
		}
		else if (word.charAt(0) == word2.charAt(0)) {
			dis = levenshtein(tail(word), tail(word2));
		}
		else {
			dis = 1 + Math.min(levenshtein(tail(word), word2), Math.min(levenshtein(word, tail(word2)), levenshtein(tail(word), tail(word2))));
		}
		return dis;
	}

	public static String[] readDictionary(String fileName) {
		In in = new In(fileName);
		String[] dictionary = new String[3000];
		for (int i = 0; i < 3000; i++) {
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min = levenshtein(word, dictionary[0]);
		int place = 0;
		for (int i = 0; i < 3000; i++) {
			int current = levenshtein(word, dictionary[i]);
			if (current < min) {
				min = current;
				place = i;
			}
		}
		if (min <= threshold) 
		    return dictionary[place];
		else
		    return word;
	}

}

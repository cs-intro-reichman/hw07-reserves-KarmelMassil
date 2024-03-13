

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		In in = new In(fileName);
		String[] dictionary = new String[3000];
		for (int i = 0; i < 3000; i++) {
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		for (int i = 0; i < 3000; i++) {
			if (dictionary[i].equals(word)) {
				return true;
			}		
		}
		   return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
		String find = hashtag.toLowerCase();
        int N = find.length();
		int first = 0;
        for (int i = 1; i <= N; i++) {
			String word = find.substring(first, i);
			if (existInDictionary(word, dictionary)) {
				System.out.println(word);
			    first = i;
				breakHashTag(find.substring(first, i), dictionary);
			}
        }
    }
}

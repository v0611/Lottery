import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Twitter {
	static final int MAX_TWEET_LENGTH = 140;
	static final int PREFIX_LENGTH = 14;
	static final int CHARS_PER_TWEET = MAX_TWEET_LENGTH - PREFIX_LENGTH;

	public static void main(String[] args) throws IOException {

		String paragraph = readFile();
		System.out.println(splitMethod(paragraph));
	}

	private static String readFile() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("tweet.txt"));
		StringBuilder paragraph = new StringBuilder();
		String line = in.readLine();

		while (line != null) {
			paragraph.append(line);
			line = in.readLine();
		}
		in.close();
		splitMethod(paragraph.toString());
		return paragraph.toString();
	}

	private static String splitFile(String input) {
		String[] charArray = input.split("");
		String[] wordArray = input.split(" ");
		StringTokenizer token = new StringTokenizer(input, " ");
		StringBuilder output = new StringBuilder();
		int characterCount = 0;
		int tweetCount = 1;
		int totalTweet = (int) Math.ceil((double) charArray.length / CHARS_PER_TWEET);

		if (charArray.length <= MAX_TWEET_LENGTH) {
			return input;
		} else {
			while (token.hasMoreTokens()) {
				String word = token.nextToken();
				output.append(word);
				output.append(" ");
				characterCount += word.length() + 1;

				if (characterCount > CHARS_PER_TWEET) {
					output.append("(Tweet " + tweetCount + " of " + totalTweet + ")");
					output.append("\n");
					characterCount = 0;
					tweetCount += 1;
				}

			}
			output.append("(Tweet " + tweetCount + " of " + totalTweet + ")");
			return output.toString();

		}
	}

	private static String splitMethod(String input) {
		String[] wordArray = input.split(" ");
		StringBuilder output = new StringBuilder();

		int tweetCount = 1;
		int totalTweet = (int) Math.ceil((double) (input.split("").length / CHARS_PER_TWEET));
		int lineLen = 0;

		for (int index = 0; index < wordArray.length; index++) {
			while (lineLen + wordArray[index].length() < CHARS_PER_TWEET && index < wordArray.length-1) {
				output.append(wordArray[index]);
				output.append(" ");
				lineLen += wordArray[index].length() + 1;
				index++;

			}
			output.append("(Tweet " + tweetCount + " of " + totalTweet + ")");
			output.append("\n");
			tweetCount++;
			lineLen = 0;

		}

		return output.toString();

	}
}

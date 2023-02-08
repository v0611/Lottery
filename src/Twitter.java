import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Twitter {
	static final int MAX_TWEET_LENGTH = 140;
	static final int PREFIX_LENGTH = 14;
	static final int CHARS_PER_TWEET = MAX_TWEET_LENGTH - PREFIX_LENGTH;

	public static void main(String[] args) throws IOException {
		String paragraph = readFile();
		splitMethod(paragraph);

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
		return paragraph.toString();
	}

	private static List<String> splitMethod(String input) {
		List<String> tweets = new ArrayList<>();

		String[] wordArray = input.split(" ");
		String [] characterArray=input.split("");
		int currentTweetCount = 0;
		int totalTweet = (int) Math.ceil((double) characterArray.length / CHARS_PER_TWEET);
		int index = 0;

		while (index < wordArray.length) {
			StringBuilder output = new StringBuilder();
			while (index < wordArray.length && output.length() + wordArray[index].length() + 1 < CHARS_PER_TWEET) {
				output.append(wordArray[index]);
				output.append(" ");
				index++;

			}
			currentTweetCount++;
			tweets.add(output.toString() + String.format("(Tweet %d/%d)", currentTweetCount, totalTweet));

		}
		for (String tweet : tweets) {
			System.out.println(tweet);
		}
		return tweets;
	}
}

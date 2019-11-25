import java.lang.reflect.Array;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.Collection;

public class TweetParser {
    public static Map<Token, ArrayList<Token>> parseTweets(String filename) throws Exception {
        ArrayList<Token> tokens = new ArrayList<Token>();
        Twokenizer twokenizer = new Twokenizer();
        Scanner scanner = new Scanner(new File(filename), "UTF-8");
        String line = scanner.nextLine(); //the first line is field headers, we do not want that.
        Token prevToken = null;
        Map<Token, ArrayList<Token>> wordCollection = new TreeMap<>();
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] fields = line.split("\",\"");

            List<String> twokens = twokenizer.twokenize(fields[4]);


            for (int i = 0; i < twokens.size(); i++) {

                Token nt = new Token(twokens.get(i));
                if (i == 0) {
                    ArrayList<Token> values = new ArrayList<>();
                    wordCollection.put(nt, values);
                }
                else {
                    Token prev = new Token(twokens.get(i - 1));
                    if (!wordCollection.containsKey(nt)) {
                        ArrayList<Token> values = new ArrayList<>();
                        wordCollection.put(nt, values);
                    }
                    ArrayList<Token> list = wordCollection.get(prev);
                    list.add(nt);
                    wordCollection.replace(prev, list);
                }
            }

        }
        return wordCollection;
    }
}


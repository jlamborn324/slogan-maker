package home.DriverCode;

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class TweetParser {
    public static Map<Token, HashMap<Token, Integer>> parseTweets(String filename) throws Exception {
        ArrayList<Token> tokens = new ArrayList<Token>();
        Twokenizer twokenizer = new Twokenizer();
        Scanner scanner = new Scanner(new File(filename), "UTF-8");
        String line = scanner.nextLine(); //the first line is field headers, we do not want that.
        Token prevToken = null;
        Map<Token, HashMap<Token, Integer>> wordCollection = new TreeMap<>();


        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] fields = line.split("\",\"");

            List<String> twokens = twokenizer.twokenize(fields[4]);


            for (int i = 0; i < twokens.size(); i++) {

                Token nt = new Token(twokens.get(i));
                if (i == 0) {
                    wordCollection.put(nt, new HashMap<>());
                }
                else {
                    Token prev = new Token(twokens.get(i - 1));
                    if (!wordCollection.containsKey(nt)) {
                        wordCollection.put(nt, new HashMap<>());
                    }
                    HashMap<Token, Integer> prev_word_bigrams = wordCollection.get(prev);
                    prev_word_bigrams.put(nt, prev_word_bigrams.getOrDefault(nt, 0) + 1);
                    wordCollection.replace(prev, prev_word_bigrams);
                }
            }

        }
        return wordCollection;
    }


    /**
     * This method is special-made to parse the .csv file containing trump tweets. This file is a bit more tricky to
     * parse, as it contains fields with quotes, fields without quotes, and fields with line breaks.
     *
     * @param filename takes name of csv file to be parsed
     * @return returns a map where key is a token and value is all words that appear after that token
     * @throws Exception Using files
     */


    public static Map<Token, PriorityQueue<Token>> parseTrumpTweets(String filename) throws Exception {


        // Initializing all the data we need for the parsing

        Twokenizer twokenizer = new Twokenizer();
        Scanner scanner = new Scanner(new File(filename), "UTF-8");
        String line = scanner.nextLine(); //the first line is field headers, we do not want that.
        String nextline = scanner.nextLine();
        Map<Token, Map<Token, Integer>> wordCollection = new TreeMap<>(); // Initialize map to be returned

        int testcounter = 0 ; // Temporary counter to test some iterations


        while (scanner.hasNextLine()) {


            // There are data fields that contain multiple lines. This makes parsing a bit less pretty. To solve this,
            // I am checking ahead to the next line to see if its a new line of data or a line break within the same
            // data field. If the latter, add the next line to the current line.
//

                while (!nextline.startsWith("\"x") ||nextline.length() == 0) {
                    line = line + nextline;
                    if (scanner.hasNextLine())
                        nextline = scanner.nextLine();
                    else
                        break;
                }


            // Regex that accounts for quotations within data fields
            String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);



            List<String> twokens = twokenizer.twokenize(fields[4]); // "Twokenizes" the content of the tweet

            for (int i = 0; i < twokens.size(); i++) {

                Token nt = new Token(twokens.get(i));

                if (i == 0) {
                    wordCollection.put(nt, new HashMap<>());
                }
                else {
                    Token prev = new Token(twokens.get(i - 1));

                    if (!wordCollection.containsKey(nt)) {
                        wordCollection.put(nt, new HashMap<>());
                    }

                    Map<Token, Integer> bigram_freq = wordCollection.get(prev);
                    bigram_freq.put(nt, bigram_freq.getOrDefault(nt, 0) + 1);
                    wordCollection.replace(prev, bigram_freq);
                }
            }
            testcounter++;
            line = nextline;    // Shift line to the next line
            if (scanner.hasNextLine())
                nextline = scanner.nextLine(); // Shift nextline to the next line
        }
        Map<Token, PriorityQueue<Token>> freqmap = new HashMap<>();

        for (Token token: wordCollection.keySet()) {    // Convert each frequency dictionary into a priority queue"
//            System.out.println("Token: " + token);
//            for (Token x: wordCollection.get(token).keySet()) {
//                System.out.print(x + " : " + wordCollection.get(token).get(x) + " ");
//            }
//
//            System.out.println("\n---------------------");
            freqmap.put(token, mapToHeap(wordCollection.get(token)));

        }

        return freqmap;

    }
    public static PriorityQueue<Token> mapToHeap(Map<Token, Integer> map){
        PriorityQueue<Token> heap = new PriorityQueue<>((n1, n2) -> map.get(n2) - map.get(n1));
        for(Token token: map.keySet()){
            heap.add(token);
        }
        return heap;

    }
}


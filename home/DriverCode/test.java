package home.DriverCode;

import java.util.Map;
import java.util.PriorityQueue;

public class test {
    public static void main(String[] args) throws Exception{

        Map<Token, PriorityQueue<Token>> map = TweetParser.parseTrumpTweets("trumptweets-1515775693.tweets.csv");
        SloganMaker maker = new SloganMaker(map);
        System.out.println(maker.getSlogan("abcdef"));





    }


}

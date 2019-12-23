package home.DriverCode;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws Exception{

        Map<Token, PriorityQueue<Token>> map = TweetParser.parseTrumpTweets("trumptweets-1515775693.tweets.csv");
        SloganMaker maker = new SloganMaker(map);
        boolean stop = false;
        Scanner scanner = new Scanner(System.in);

        while(!stop) {
            System.out.print("Enter a slogan: ");
            maker = new SloganMaker(map);
            String userinput = scanner.nextLine();

            if (!maker.getSlogan(userinput, new PriorityQueue<>(map.keySet()))) {
                System.out.println("No solution can be found");
            } else {
                System.out.println(maker.solution);
            }
        }





    }


}

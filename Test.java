import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;


public class Test {
  public static void main(String[] args) throws Exception {
    Map<Token, ArrayList<Token>> wordCollection = TweetParser.parseTweets("full-corpus.csv");

    Scanner scanner = new Scanner(System.in);

    SloganMaker maker = new SloganMaker(wordCollection);
    System.out.println("Enter an acronym");
    String acronym = scanner.next();
    System.out.println("Slogan: " + maker.getSlogan(acronym));
    System.out.println("Another? (Y/N)");
    String choice = scanner.next();
    while (choice.toLowerCase().equals("y")){
      System.out.println("Enter an acronym");
      acronym = scanner.next();
      System.out.println("Slogan: " + maker.getSlogan(acronym));
      System.out.println("Another? (Y/N)");
      choice = scanner.next();

    }


  }
}

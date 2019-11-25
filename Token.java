import java.util.ArrayList;

public class Token implements Comparable<Token>{
  public String word;
  public ArrayList<Token> grams;

  public Token(String word){
    this.word = word;
    this.grams = new ArrayList<Token>();
  }

  public boolean addGram(Token t){
    return grams.add(t);
  }

  public int compareTo(Token t){
    return word.compareTo(t.toString());
  }

  public String toString(){
    return word;
  }
}
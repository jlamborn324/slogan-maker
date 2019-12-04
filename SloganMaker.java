import java.util.*;

public class SloganMaker{

  private Map<Token, ArrayList<Token>> bigrams;

  public SloganMaker(Map<Token, ArrayList<Token>> bigrams){
    this.bigrams = bigrams;
  }


  /**
   * Get a slogan for a given acronym. The slogan must satisfy the following
   * constraints:
   *   1. The first character of each String corresponds with a character in the acronym passed as a
   *   parameter to the method in the same order.
   *   2. Adjacent tokens must be bigrams in the corpus of text used to generate the list of tokens.
   * @param  acronym The acronym that will be used to create a slogan
   * @return         A list of Strings that satisfies the above constraints.
   */
  public ArrayList<Token> getSlogan(String acronym) {
    acronym = acronym.replaceAll(" ", "");
    ArrayList<Token> finalacronym = new ArrayList<>();
    ArrayList<Token> usedTokens = new ArrayList<>();
    ArrayList<Token> keysArray = new ArrayList<>(bigrams.keySet());

    if (recursiveSolution(usedTokens, keysArray, acronym, finalacronym)) {
      return finalacronym;
    }
    else
//      System.out.println("--------------------------------------------------------" + "\nerror error error no solution");
      return null;
  }


    /***
     *  Helper function to check if a given word has a bigram that begins with the next letter of the acronym. Checks
     *  a step ahead to see if there is a viable solution.
     * @param currentWordbigrams The list of bigrams associated with the current word
     * @param nextLetter The next letter in the acronym
     * @param alreadyusedtokens A list of the tokens already attempted but failed as solutions.
     * @return Returns true if the s
     */
  public boolean isSafe(ArrayList<Token> currentWordbigrams, String nextLetter, ArrayList<Token> alreadyusedtokens){

    for( int i = 0; i < currentWordbigrams.size(); i++) {
      Token testWord = currentWordbigrams.get(i);
      String firstletter = testWord.toString().substring(0, 1).toLowerCase();
      if (firstletter.equals(nextLetter) && !alreadyusedtokens.contains(testWord)) {
        return true;
      }
    }

    return false;
  }

  public boolean recursiveSolution( ArrayList<Token> usedTokens,
                                            ArrayList<Token> currentWordbigrams, String lettersLeft,
                                            ArrayList<Token> solution){
    /* BASE CASE : If all of the words have been found, return true */
    if (lettersLeft.length() <= 1)


      for(int i = 0; i < currentWordbigrams.size(); i++){
        String bigram_firstletter = currentWordbigrams.get(i).toString().substring(0, 1).toLowerCase();

        if (bigram_firstletter.equals(lettersLeft.substring(0, 1).toLowerCase())){
          solution.add(currentWordbigrams.get(i));
          return true;
        }
      }


    /*For the word we are currently on, try bigrams one by one */
    for(int i = 0; i < currentWordbigrams.size(); i++){

      /* Check if this word can be added */
      if (isSafe(bigrams.get(currentWordbigrams.get(i)), lettersLeft.substring(1, 2), usedTokens) &&
      currentWordbigrams.get(i).toString().substring(0, 1).equals(lettersLeft.substring(0, 1))) {

        /* Add the word to the solution list */
//        System.out.println("adding \"" + currentWordbigrams.get(i) + "\" to the solution list");
        solution.add(currentWordbigrams.get(i));
        /* Add this word to the used tokens list */
        usedTokens.add(currentWordbigrams.get(i));

        /*recursion to find the rest of the words */
//        System.out.println("Remaining Letters: \"" + lettersLeft.substring(1) + "\"");
        if(recursiveSolution(usedTokens, bigrams.get(currentWordbigrams.get(i)),
                lettersLeft.substring(1), solution)){
          return true;

        }
        solution.remove(currentWordbigrams.get(i)); // Backtracking here
//        System.out.println("This one's not gonna work! Deleting: \"" + currentWordbigrams.get(i) + "\"");

      }
    }
    return false;
  

  }

}
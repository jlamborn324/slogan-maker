package home.DriverCode;

import java.util.*;

public class SloganMaker{

  public Map<Token,PriorityQueue<Token>> bigrams;

  public ArrayList<Token> solution;

  public SloganMaker(Map<Token, PriorityQueue<Token>> bigrams){

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
    acronym = acronym.replaceAll(" ", ""); //user-inputted acronym
    ArrayList<Token> finalacronym = new ArrayList<>(); // answer that will be returned
    ArrayList<Token> usedTokens = new ArrayList<>();

    PriorityQueue<Token> keysArray = new PriorityQueue<>(bigrams.keySet());

    if (recursiveSolution(usedTokens, keysArray, acronym, finalacronym)) {
      return finalacronym;
    }
    else
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
  public boolean isSafe(PriorityQueue<Token> currentWordbigrams, String nextLetter, ArrayList<Token> alreadyusedtokens){

    for (Token testWord : currentWordbigrams) {
      String firstletter = testWord.toString().substring(0, 1).toLowerCase();
      if (firstletter.equals(nextLetter) && !alreadyusedtokens.contains(testWord)) {
        return true;
      }
    }

    return false;
  }

  /**
   *
   * @param usedTokens Keeps track of all tokens used so far
   * @param currentWordbigrams A list containing all bigrams for the current word
   * @param lettersLeft Remaining letters of the user's inputted acroynm
   * @param solution Running arraylist of the solutoin
   * @return
   */
  public boolean recursiveSolution(ArrayList<Token> usedTokens,
                                            PriorityQueue<Token> currentWordbigrams, String lettersLeft,
                                            ArrayList<Token> solution){
    /* BASE CASE : If all of the words have been found, return true */
    if (lettersLeft.length() <= 1)


      for (Token currentWordbigram : currentWordbigrams) {
        String bigram_firstletter = currentWordbigram.toString().substring(0, 1).toLowerCase();

          if (lettersLeft.startsWith(bigram_firstletter)){
            solution.add(currentWordbigram);
            return true;
          }
      }


    /*For the word we are currently on, try bigrams one by one */
    for (Token currentWordbigram : currentWordbigrams) {

      /* Check if this word can be added */
      if (isSafe(bigrams.get(currentWordbigram), lettersLeft.substring(1, 2), usedTokens) &&
              currentWordbigram.toString().substring(0, 1).equals(lettersLeft.substring(0, 1))) {

        /* Add the word to the solution list */
        System.out.println("adding \"" + currentWordbigram + "\" to the solution list");
        solution.add(currentWordbigram);
        /* Add this word to the used tokens list */
        usedTokens.add(currentWordbigram);

        /*recursion to find the rest of the words */
        System.out.println("Remaining Letters: \"" + lettersLeft.substring(1) + "\"");
        if (recursiveSolution(usedTokens, bigrams.get(currentWordbigram),
                lettersLeft.substring(1), solution)) {
          return true;

        }
        solution.remove(currentWordbigram); // Backtracking here
        System.out.println("This one's not gonna work! Deleting: \"" + currentWordbigram + "\"");

      }
    }
    return false;
  

  }

}
package home.DriverCode;

import java.util.*;

public class SloganMaker {

    public Map<Token, PriorityQueue<Token>> tokensandbigrams;
    public ArrayList<Token> solution = new ArrayList<>();

    public SloganMaker(Map<Token, PriorityQueue<Token>> bigrams) {
        this.tokensandbigrams = bigrams;
    }


    /***
     * Recursive funtion that finds a solution for the sloganmaker. Solution is made in the this.solution field
     * @param acronym String given by the user
     * @param tokens List of bigrams to choose from. First function call, this list contains every
     *               word used. After that, it contains a list of all bigrams that come after a word we are checking for
     *               solution
     * @return  true if a solution was found, false is a solution could not be found
     */
    public boolean getSlogan(String acronym, PriorityQueue<Token> tokens) {

        /** base case, the acronym is down to the last letter. Find the last word that
            finishes the solution and then return true. (There will always be a last
            word that finishes the solution because of the isSafe method that was used previously */
        if(acronym.length() == 1){
            String currentletter = acronym.substring(0, 1);
            for(Token token: tokens){
                if (token.toString().startsWith(currentletter)){
                    solution.add(token);
                    return true;
                }
            }
            return false;
        }


        String currentletter = acronym.substring(0, 1);
        String nextletter = acronym.substring(1, 2);

        for (Token token : tokens) {    // iterate through given lists of tokens to find a viable next step
            if (token.toString().startsWith(currentletter)) {
                if (isSafe(tokensandbigrams.get(token), nextletter)) {
                    solution.add(token);
//                    System.out.println("Adding " + token + " to solution");   //uncomment when testing
                    /**
                     * Recursive call to find a solution. If a recursive call returns false( If there is no viable next
                     * step for the word added to the solution), then the program will step a level back up, and change
                     * to the next word. This is where the backtracking is implemented.
                     */
                    if (getSlogan(acronym.substring(1), tokensandbigrams.get(token))) {
                        return true;
                    }

                    // If we reach a word that does not have a viable option to it, we will remove it and keep iterating
//                    System.out.println(solution.get(solution.size()-1) + " will not work, Deleting..."); // uncomment when testing
                    solution.remove(solution.size() - 1);
                }
            }
        }
        return false;


    }

    /**
     * Helpe function to determine if a word will work as a solution. Checks to see if a word that is being considered
     * has a word in its bigrams that beings with the next letter in the acronym.
     * 
     * @param currentwordbigrams All bigrams for the word we are checking, these are all of the possible
     *                           next steps to the solution.
     * @param nextletter The next letter that needs to be fulfilled in the user-inputted acronym
     * @return boolean determining whether or not a given word that is being considered for the solution is viable.
     * If the word doesn't have a word in its bigrams that begins with the next letter we need in the acronym, then
     * this word is not viable.
     */
    public boolean isSafe(PriorityQueue<Token> currentwordbigrams, String nextletter) {
        for (Token token : currentwordbigrams) {
            if (token.toString().startsWith(nextletter)) {
                return true;
            }
        }
        return false;
    }



}
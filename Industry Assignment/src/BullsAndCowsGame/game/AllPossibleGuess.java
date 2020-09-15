package BullsAndCowsGame.game;

import java.util.ArrayList;

public class AllPossibleGuess {

    private ArrayList<String> basicElement = new ArrayList<>();

    public AllPossibleGuess(){
        permutation("0123456789","", basicElement,4);
    }

    public ArrayList<String> getAllPossibleGuess(){

        return basicElement;
    }


    public static void permutation(String numberList, String code, ArrayList<String> basicElement, int len) {

        if (code.length() == len) {
            basicElement.add(code);
        } else {
            for (int i = 0; i < numberList.length(); i++) {
                //no repeated number
                if (code.indexOf(numberList.charAt(i)) < 0) {
                    permutation(numberList, code + numberList.charAt(i), basicElement, len);
                }
            }
        }
    }



}

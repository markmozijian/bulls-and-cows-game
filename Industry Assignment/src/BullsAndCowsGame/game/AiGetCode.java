package BullsAndCowsGame.game;

import java.util.ArrayList;

public class AiGetCode {



    public String getCode(ArrayList<String> possibleGuess){

        return possibleGuess.get((int)(Math.random() * possibleGuess.size()));

    }






}

package BullsAndCowsGame.game;


import java.util.ArrayList;
import java.util.Iterator;

public class AiGuess extends AiGetCode {

    private String aiGuess;
    private ArrayList<String> possibleGuess;


    public AiGuess() {

        AllPossibleGuess all = new AllPossibleGuess();
        possibleGuess = all.getAllPossibleGuess();
    }

    //calculate possible guess for hard mode
    public void setFeedBack(String lastGuess, int lastBulls, int lastCows) {




            Iterator<String> myIterator = possibleGuess.iterator();

            while (myIterator.hasNext()) {
                int bull;
                int cow;
                String element = myIterator.next();

                Compare elementCompare = new Compare(lastGuess, element);
                bull = elementCompare.getBulls();
                cow = elementCompare.getCows();

                if (lastBulls != bull || lastCows != cow) {
                    myIterator.remove();
                }


            }




    }


    //assign aiGuess
    public String run(int difficulty) {

        switch (difficulty) {
            case 1:
                easy();
                break;

            case 2:
                medium();
                break;
            case 3:
                hard();
                break;
        }

        return aiGuess;
    }

    private void easy() {

        aiGuess = getCode(possibleGuess);
    }

    private void medium() {

        aiGuess = getCode(possibleGuess);

        possibleGuess.remove(aiGuess);

    }

    private void hard() {


        aiGuess = getCode(possibleGuess);

    }


}





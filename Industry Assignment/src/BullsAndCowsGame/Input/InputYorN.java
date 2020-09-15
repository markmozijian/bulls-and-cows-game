package BullsAndCowsGame.Input;

import BullsAndCowsGame.Exception.InvalidException;

public class InputYorN {

    private boolean check;

    public boolean inputYorN(){

        boolean loopInput = true;
        while (loopInput) {
            try {
                String str = startInputYorN();
                if (str.toLowerCase().equals("y")){check = true;}
                else {check = false;}
                loopInput = false;
            } catch (InvalidException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }

        }
        return check;

    }

    public String startInputYorN() throws InvalidException {

        String str = Keyboard.readInput();
        if (!str.toLowerCase().equals("y") && !str.toLowerCase().equals("n"))
            throw new InvalidException("Wrong input! Please enter Y or N case insensitively.");
        return str;

    }
}

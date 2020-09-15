package BullsAndCowsGame.Input;

import BullsAndCowsGame.Exception.InvalidException;

public class InputDifficulty {

private int aiDifficulty;

    public int startInputDifficulty(){

        boolean loopInput = true;
        while (loopInput) {
            try {
                this.aiDifficulty = inputAiDifficulty();
                loopInput = false;
            } catch (InvalidException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }

        }
        return aiDifficulty;

    }

    public int inputAiDifficulty() throws InvalidException {
        System.out.println("Please select Ai Difficulty, 1 for easy, 2 for medium, 3 for hard:");
        String str = Keyboard.readInput();
        System.out.println("- - -");
        if (!str.equals("1")  && !str.equals("2") && !str.equals("3"))
            throw new InvalidException("Wrong input! Please enter 1 or 2 or 3 only.");
        return Integer.parseInt(str);


    }
}

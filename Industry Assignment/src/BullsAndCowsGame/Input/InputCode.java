package BullsAndCowsGame.Input;

import BullsAndCowsGame.Exception.BitException;
import BullsAndCowsGame.Exception.InvalidException;
import BullsAndCowsGame.Exception.SameNumberException;

public class InputCode {

    private String userInput;

    public String startInput(){

        boolean loopInput = true;
        while (loopInput) {
            try {
                userInput = inputCode();
                loopInput = false;
            } catch (BitException | InvalidException | SameNumberException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }

        }
        return userInput;
    }


    public String inputCode() throws BitException, InvalidException, SameNumberException{

        try {
            String str = Keyboard.readInput();
            System.out.println("- - -");
            int index = Integer.parseInt(str);
            if (str.length() != 4) throw new BitException("Code digit should be 4!");
            if(!sameNumber(str)) throw new SameNumberException("The digits must be all different!");

            return str;
        } catch (NumberFormatException e) {
            throw new InvalidException("Please enter number!!");
        }

    }


    private boolean sameNumber(String str){

        boolean flag = true;

        for(int i=0;i<4;i++){
            for(int j=i+1;j<4;j++)
           if(str.charAt(i)==str.charAt(j)){
               flag= false;
           }
        }
        return flag;
    }


}

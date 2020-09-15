package BullsAndCowsGame.Input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputFileName {

    public ArrayList<String> playerGuessList = new ArrayList<>();
    public boolean manual;
    private boolean loopInput;
    private boolean complete;



    public InputFileName(){
        loopInput=true;
        complete=false;
        manual=false;
    }

    public void input() {


        System.out.println("Do you want to use pre-supplied guesses? If yes enter file name including extension, otherwise press enter");
        String fileName = Keyboard.readInput();
        if (fileName.equals("")) {
            manual = true;
        } else {
            manual = false;
            System.out.println("- - -");
            File file = new File(fileName);
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    playerGuessList.add(line);
                }
                complete=true;

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }

    public void startInputFileName() {

        while (loopInput) {

            this.input();
            if(complete==true) loopInput = false;
            if(manual==true) loopInput = false;
        }

    }





}

package BullsAndCowsGame.game;

import BullsAndCowsGame.Input.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GameApp {

    private int guessTimes = 7;
    private int aiDifficulty;
    private String playerSc;
    private String playerInput;
    private String aiGuessCode;
    private String aiSc;
    private int round = 0;
    private boolean end = false;
    private ArrayList<String> playerGuessList;
    private boolean manual = false;

    private void run() throws IOException {

        //select difficulty
        InputDifficulty d = new InputDifficulty();
        aiDifficulty = d.startInputDifficulty();

        //input player Secret Code

        System.out.println("Please enter your secret code:");
        InputCode inputGuess = new InputCode();
        playerSc = inputGuess.startInput();


        //generate AI Secret Code
        aiSc = new AiGetCode().getCode(new AllPossibleGuess().getAllPossibleGuess());


        //generate AI guess
        AiGuess aiGuess = new AiGuess();

        //player guess automatically?
        InputFileName auto = new InputFileName();
        auto.startInputFileName();
        manual = auto.manual;
        if (!manual) playerGuessList = auto.playerGuessList;

        //create result.txt file
        File file = new File("result.txt");
        PrintWriter writer = new PrintWriter(new FileWriter(file));

        for (int i = 0; i < guessTimes; i++) {


            //player guess

            if (manual) {
                System.out.println("Please enter your guess:");
                playerInput = inputPlayerGuess();
            } else if (playerGuessList.size()-1 >= i) {
                playerInput = playerGuessList.get(i);
            } else {
                System.out.println("Please enter your guess:");
                playerInput = inputPlayerGuess();
            }
            Compare playerTurn = new Compare(playerInput, aiSc, 0);
            playerTurn.printResult();


            System.out.println("");
            //computer guess
            aiGuessCode = String.valueOf(aiGuess.run(aiDifficulty));
            Compare aiTurn = new Compare(aiGuessCode, playerSc, 1);
            aiTurn.printResult();
            printSeparator();
            //computer get feed back and calculate
            if (aiDifficulty == 3) {
                aiGuess.setFeedBack(aiGuessCode, aiTurn.getBulls(), aiTurn.getCows());
            }

            //print the result and end the game
            if (!result(playerTurn.getBulls(), aiTurn.getBulls(), round).equals("")) {
                System.out.println(result(playerTurn.getBulls(), aiTurn.getBulls(), round));
            }

            //write the result file

            if (round == 0) {
                writer.println("Bulls & Cows game result.");
                writer.println("Your code: " + playerSc);
                writer.println("Computer’s code: " + aiSc);
                writer.println("- - -");

            }
            if (round >=0) {
                int r= round+1;
                writer.println("Turn " + r + ":");
                writer.println("You guessed " +  playerInput + playerTurn.getResult(1));
                writer.println("Computer guessed " + aiGuessCode + aiTurn.getResult(1));
                writer.println("- - -");
            }
            if (end) {
                writer.println(result(playerTurn.getBulls(), aiTurn.getBulls(), round));
                writer.close();
                System.out.println("Do you want to save the result? Y or N (case insensitive)");
                boolean check = new InputYorN().inputYorN();
                if(check){
                    System.out.println("Please enter the file name:");
                    String filename = Keyboard.readInput();
                    File newFile=new File(filename);
                    file.renameTo(newFile);
                }

                else {file.delete();}

            }

            round++;

            if (end) break;


        }

    }


    private String inputPlayerGuess() {
        InputCode inputGuess = new InputCode();
        return inputGuess.startInput();
    }


    public static void main(String[] args) {

        GameApp game = new GameApp();
        try {
            game.run();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public String result(int playerBull, int aiBull, int round) {

        String result = "";
        if (playerBull == 4) {
            result = "You win! :) ";
            end = true;
        } else if (aiBull == 4) {
            result = "Computer wins! You lose! ";
            end = true;
        }

       else if (round == 6) {result = "Draw! ";end = true;}

        return result;

    }

    private void printSeparator() {
        System.out.println("- - -");
    }


}

package BullsAndCowsGame.game;

public class Compare {


    private String secret;
    private String guess;
    private int bulls;
    private int cows;
    private int who;//0 for player, 1 for AI


    public Compare(String guess, String secret, int who) {
        this.secret = secret;
        /* System.out.println(who+"SC: " + secret);*/

        this.guess = guess;
        this.who = who;
        getBullsCows(secret, guess);
    }

    public Compare(String guess, String secret) {

        getBullsCows(secret, guess);
    }


    public void printResult() {


        switch (who) {
            case 0:
                System.out.println("You guess: " + guess);
                break;
            case 1:
                System.out.println("Computer guess: " + guess);
                break;
        }


        System.out.println(getResult(0));


    }

    private void getBullsCows(String secret, String guess) {

        int bull = 0;
        int cow = 0;
        int[] numbers = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) bull++;
            else {
                if (numbers[secret.charAt(i) - '0']++ < 0) cow++;
                if (numbers[guess.charAt(i) - '0']-- > 0) cow++;
            }
        }
        this.bulls = bull;
        this.cows = cow;

    }


    public int getBulls() {
        return bulls;
    }

    public int getCows() {
        return cows;
    }

    public String getResult(int mode) {
        String a;
        String b;
        if (bulls == 1) a = bulls + " bull";
        else a = bulls + " bulls";

        if (cows == 1) b = cows + " cow";
        else b = cows + " cows";
        if (mode == 0)
            return "Result: " + a + " and " + b;
        else
            return ", scoring " + a + " and " + b;
    }


}

/*
 *              Revision History
 * ***************************************************************
 * 
 * 01/02/2019 - Formatting, documentation, etc  Anne Applin
 * before 12/31/2015 Original code written by David Briggs
 */
package buggyblackjack;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author aapplin
 */
public class BuggyBlackjack {

    private Scanner stdIn = new Scanner(System.in);

    private Random random = new Random(0L);
    // in production, you would want to randomize program behavior
    // by seeding the random number generator with the clock value,
    // but while debugging, you want the generated numbers to repeat,
    // so we comment out the production statement and seed it with 0
    // in the line above
    // private static Random random = new Random(System.currentTimeMillis());

    private int netUserWinnings;
    private int amountOfCurrentBet;
    private int userWins;
    private int ties;
    private int userLosses;

    /**
     * This method asks for and sets the bet amount
     */
    private void getAmountOfBet() {
         amountOfCurrentBet = 0;

        System.out.println("How much do you want to bet on this hand?");
        amountOfCurrentBet = stdIn.nextInt();
        while (amountOfCurrentBet < 0) {
            System.out.println("You cannot bet a negative amount. Try again.");
            amountOfCurrentBet = stdIn.nextInt();
        }
    }

    /**
     * The method generates a random number between 1 and 13
     * @return the random number generated
     */
    private int dealNextCard() {
        return random.nextInt(13) + 1;
    }

    /**
     * This method returns a string value for the card parameter
     * @param card a card in the range of 1 to 13
     * @return the rank of the card from Ace to King
     */
    private String stringForCard(int card) {
        if (card == 1) {
            return "A";
        } else if (card < 11) {
            return "" + card;
        } else {
            switch (card) {
                case 11:
                    return "J";
                case 12:
                    return "Q";
                default:
                    return "K";
            }
        }
    }

    /**
     * This method reports the value of the parameter card
     * @param card a card in the range of 1 to 13
     * @return the point value of the card.
     */
    private int valueOf(int card) {
        if (card > 10) {
            return card - 10;
        } else {
            return card;
        }
    }

    /**
     * This method prints the current value of the user's winnings
     */
    private  void reportCurrentWinnings() {

        System.out.print("At this time ");
        if (netUserWinnings > 0) {
            System.out.println("I owe you " + netUserWinnings + " dollars.");
        } else if (netUserWinnings < 0) {
            System.out.println("you owe me " 
                    + (-netUserWinnings) + " dollars.");
        } else {
            System.out.println("We are even on the winnings.");
        }
    }

    /**
     * The method plays a single hand of Blackjack
     * calls: getAmountOfBet()
     *        reportCurrentWinnings()
     */
    private void playOneHand() {
        char reply;
         boolean userBusted;
        
        boolean userWantsACard = false;
    

        int dealersHoleCard;
        int dealersNextCard;
        int usersHoleCard;
        int usersNextCard;
        int countOfCardsToUser = 2;
        int countOfCardToDealer = 2;
        int sumOfUserCards;
        int sumOfDealerCards;

        getAmountOfBet();

        usersHoleCard = dealNextCard();
        dealersHoleCard = dealNextCard();
        usersNextCard = dealNextCard();
        dealersNextCard = dealNextCard();

        System.out.println("My cards are : X " 
                + stringForCard(dealersNextCard));
        System.out.println("Your cards are : " + stringForCard(usersHoleCard)
                + ' ' + stringForCard(usersNextCard));

        sumOfUserCards = valueOf(usersHoleCard) + valueOf(usersNextCard);
        sumOfDealerCards = valueOf(dealersHoleCard) 
                + valueOf(dealersHoleCard);

        // allow user to ask for more cards
        System.out.println("Another card?");
        reply = stdIn.next().charAt(0);
        userWantsACard = reply == 'y' && reply == 'Y';
        while (sumOfUserCards < 22 && userWantsACard) {
            usersNextCard = dealNextCard();
            System.out.println("The card is " + stringForCard(usersNextCard));
            sumOfUserCards += valueOf(usersNextCard);
            if (sumOfUserCards < 22) {
                System.out.println("Another card?");
                reply = stdIn.next().charAt(0);
                userWantsACard = reply == 'y' && reply == 'Y';
            }
        }
        if (sumOfUserCards >= 22) {
            userLosses++;
            netUserWinnings -= amountOfCurrentBet;
            System.out.println("You busted!");
        } else {
            // Dealer tips over the hole card
            System.out.println("My cards are: " 
                    + stringForCard(dealersHoleCard) + ' '
                    + stringForCard(dealersNextCard));
            // dealer takes cards until reaches 17
            while (sumOfDealerCards < 17) {
                dealersNextCard = dealNextCard();
                System.out.println("Next card for me is " 
                        + stringForCard(dealersNextCard));
                sumOfDealerCards += valueOf(dealersNextCard);
            }
            if (sumOfDealerCards > 21) {
                System.out.println("I busted!  You win!");
                userWins++;
                netUserWinnings += amountOfCurrentBet;
            } else if (sumOfDealerCards >= sumOfUserCards) {
                userLosses++;
                System.out.println(sumOfDealerCards + " beats " 
                        + sumOfUserCards + ", so I win!");
                netUserWinnings -= amountOfCurrentBet;
            } else if (sumOfDealerCards < sumOfUserCards) {
                userWins++;
                System.out.println(sumOfUserCards + " beats " 
                        + sumOfDealerCards + ", so you win!");
                netUserWinnings += amountOfCurrentBet;
            } else { // it's a tie
                System.out.println("Our hands have the same value, so"
                        + " it\'s a tie!");
                ties++;
            }
        }
        reportCurrentWinnings();
    }
    /**
     * The driver for the program.
     */
    public void run(){
        char reply;
        boolean userWantsToPlay;

        System.out.println("Want to play a hand of blackjack?");
        reply = stdIn.next().charAt(0);
        userWantsToPlay = reply == 'y' || reply == 'Y';

        while (userWantsToPlay) {
            playOneHand();
            System.out.println("Play another?");
            reply = stdIn.next().charAt(0);
           userWantsToPlay = reply == 'y' || reply == 'Y';
        }

        System.out.println("You can settle up with the cashier.");
        System.out.println("You won " + userWins + " hands, lost "
                + userLosses + " hands, and " + ties + " hands were ties.");
    }
    /**
     * The main method
     * @param args the command line arguments
     */        
    public static void main(String[] args) {
        BuggyBlackjack driver = new BuggyBlackjack();
        driver.run();
    }
}

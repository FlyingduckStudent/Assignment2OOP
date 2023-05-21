import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//////Some instructions:
/////1. Change time from 00 to 500 in deck class, pause method to do slow dealing of cards. its set to 00 to speed up testing

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // start off with a value thats not in the range of the controls
        while (true) {
            int command = -1;
            List<Player> players = new ArrayList<>();
            Deck deck = new Deck();
            System.out.println("Deck: " + deck.getCards());
            System.out.println("Center Pile: " + deck.getCenterPile());
            System.out.println("\nEnter your choice:");
            System.out.println("1. Begin Game!");
            System.out.println("2. Reset Deck & All Hands!");
            System.out.println("0. Quit");
            System.out.println(">");

            command = input.nextInt();

            switch (command) {

                case 1:

                    startGame(players, deck);
                    break;

                case 2:
                    // start a clean deck with original arrangement (Instantiation of Deck class)
                    deck = new Deck();
                    // clear deck and all player hands
                    for (Player player : players) {
                        player.getHand().clear();
                    }
                    deck.getCenterPile().clear();
                    // clear the screen
                    Tool.clearScreen();
                    // This is to view the players hands after clearing, for testing purposes
                    for (Player player : players) {
                        System.out.println(player.getName() + "'s Hand: " + player.getHand());
                    }
                    break;

                case 0:
                    System.exit(0);
                    break; // exit loop and end program

                default:
                    System.out.println("Invalid choice, try again.");
                    break;

            }

        }

    }

    public static void startGame(List<Player> players, Deck deck) {
        Scanner input = new Scanner(System.in);
        deck.shuffle();
        String centerCard = deck.getCenterPile().toString();
        char x = centerCard.charAt(1);
        Tool.clearScreen();
        System.out.println("Center Pile: " + deck.getCenterPile());
        Tool.pause(1000);
        boolean flag = true;

        if (x == 'K' || x == 'A' || x == '9' || x == '5') {
            int n = 0;
            System.out.println("First Player will be Player A!");
            Tool.pause(1000);
            Player.CreatePlayersList(n, players);
        } else if (x == '2' || x == '6' || x == 'X') {
            int n = 3;
            System.out.println("First Player will be Player B!");
            Tool.pause(1000);
            Player.CreatePlayersList(n, players);
        } else if (x == '3' || x == '7' || x == 'J') {
            int n = 2;
            System.out.println("First Player will be Player C!");
            Tool.pause(1000);
            Player.CreatePlayersList(n, players);
        } else if (x == '4' || x == '8' || x == 'Q') {
            int n = 1;
            System.out.println("First Player will be Player D!");
            Tool.pause(1000);
            Player.CreatePlayersList(n, players);
        } else {
            int n = (int) (Math.random() * 3);
            Player.CreatePlayersList(n, players);
        }

        deck.dealCards(players);
        Tool.clearScreen();
        int counter = 0;

        while (flag) {
            counter = counter + 1;

            for (int i = 0; i < players.size(); i++) {
                System.out.println("Trick #" + counter);
                for (Player player : players) {
                    System.out.println(player.getName() + "'s Hand: " + player.getHand());
                }

                // This is to get the current player info
                Player player = players.get(i);

                for (int j = 0; j < players.size(); j++) {
                    Player player2 = players.get(i);
                    // if yes the while loop will be set to false and it will break from this
                    // current for loop
                    // refer to the first while loop to understand better
                    if (player.getHand().isEmpty()) {
                        System.out.println("Exit statement");
                        Tool.pause(1000);
                        System.out.println("The winner is player " + player2.getName());
                        System.exit(0);
                        break;

                    }
                }

                System.out.println("\nCenter Pile: " + deck.getCenterPile());
                System.out.println(player.getName() + "'s Move ");
                // default value is '0' because first round got centre deck, second round
                // onwards it will be different
                // so it will check
                char status = 'O';

                // need to put when centredeck is not empty cause later got error saying we are
                // going out of bounds
                if (!deck.getCenterPile().isEmpty()) {
                    // refer to Play.java it will compare and return the appropriate character
                    status = Play.CardPlay(player.getHand(), deck.getCenterPile().get(0));
                } else if (deck.getCenterPile().isEmpty()) {
                    status = 'O';
                }

                // if one of these were returned, then you have cards, if not it will draw cards
                // for you.
                if (status == 'P' || status == 'S' || status == 'O') {
                    System.out.println("Cards are available!");
                } else if (status == 'N') {
                    System.out.println("No Cards Available! Card(s) Will Be Drawn");
                    // i put it under a while loop cause it will draw until you get a card
                    // as usual p for precedence an s for suits
                    while (true) {
                        Play.DrawMain(player.getHand(), deck);
                        if (Play.CardPlay(player.getHand(),
                                deck.getCenterPile().get(0)) == 'S') {
                            System.out.println("Cards were drawn");
                            break;
                        } else if (Play.CardPlay(player.getHand(),
                                deck.getCenterPile().get(0)) == 'P') {
                            System.out.println("Cards were drawn!");
                            break;
                        }
                    }
                    // this is to show that cards have finished drawing
                    System.out.println("Card(s) Done Drawing");
                }

                // Player has to input the card they want to play
                System.out.println(
                        "Player's Deck : " + player.getHand() + "\n" + "Please select an appropriate card to play");
                String PlayedCard = input.nextLine();

                // this is to see whether the card they played are in their hands or not. if not
                // it will output
                // saying the card doesnt exist in thier deck
                if (Play.CardContains(PlayedCard, player.getHand())) {
                    // this is to see whether the card they played is equal in suits or precedence
                    if (!deck.getCenterPile().isEmpty()) {
                        status = Play.CardPlay(PlayedCard, deck.getCenterPile().get(0));
                    }

                    if (status == 'S') {

                        System.out.println("Main Deck : " + deck.getCards());
                        int HandIndex = Play.IndexOf(player.getHand(), PlayedCard);
                        player.getHand().remove(HandIndex);
                        deck.getCenterPile().add(PlayedCard);

                    } else if (status == 'P') {

                        System.out.println("Main Deck : " + deck.getCards());
                        int HandIndex = Play.IndexOf(player.getHand(), PlayedCard);
                        player.getHand().remove(HandIndex);
                        deck.getCenterPile().add(PlayedCard);

                    } else if (status == 'O') {
                        System.out.println("Main Deck : " + deck.getCards());
                        int HandIndex = Play.IndexOf(player.getHand(), PlayedCard);
                        player.getHand().remove(HandIndex);
                        deck.getCenterPile().add(PlayedCard);

                    } else if (status == 'N') {
                        // the i = i - 1 is to come back to this player
                        System.out.println("Please Choose a Right Card!");
                        i = i - 1;

                    }
                } else {
                    // the i = i - 1 is to come back to this player
                    System.out.println("Does not exist in your deck");
                    i = i - 1;
                }
            }

            int FirstPlayer;

            // to set the first player for the next round. this line will determine the
            // player index with the highest card
            if (deck.getCenterPile().size() == 5) {
                Tool.pause(1000);
                FirstPlayer = Play.HighestPlayerIndex(deck.getCenterPile(), false);
            } else {
                FirstPlayer = Play.HighestPlayerIndex(deck.getCenterPile(), true);
            }

            // this part if you want to understand rotate, watch youtube or ask chatgpt. but
            // this is the method for you to arrange it properly
            System.out.println(players.get(FirstPlayer).getName() + " is the winner of the round!");
            Tool.pause(2000);
            Collections.rotate(players, FirstPlayer);
            // This for loop over here to see if its arranged properly or not. Its commented
            // for now cause it was meant for debugging
            // for (Player player : players) {
            // System.out.println(player.getName());
            // }
            // this will clear the centerpile
            deck.getCenterPile().clear();
            // clear the console area to make it look clean
            Tool.clearScreen();
            // this will check whether got any player with empty hand or not
        }
        // closing the input method to save resources
        input.close();
    }

}

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Play {

    // This method is for specific card, meaning it will only take one card
    static char CardPlay(String CardInHand, String CenterDeck) {
        // eg : 'XK' it will take 'X' and convert it to int so that you can compare it
        // later on
        int ConvertedCardInHand = Tool.ConvertToComparables(CardInHand.charAt(0));
        int ConvertedCardInMainDeck = Tool.ConvertToComparables(CenterDeck.charAt(0));

        // If the card in hand is the same suit as the centre card. Takes the first
        // character from the string
        // eg : 'XK' it will take 'X' and compare the suit in the centerdeck. if same it
        // will return 'S'
        if (CardInHand.charAt(1) == CenterDeck.charAt(1)) {
            return 'S';
            // If the card in hand is higher in precedence than the card in centredeck
            // eg : 'XK' it will take 'X', convert it to int, then compare the card with
            // centredeck
        } else if (ConvertedCardInHand == ConvertedCardInMainDeck) {
            return 'P';
            // If nothing it will return 'N'
        } else {
            return 'N';
        }

    }

    // this overloaded method is to compare the player cards with centre deck. it
    // takes the entire card array not a specific card
    // others all the same ny
    static char CardPlay(List<Card> CardInHand, String CenterDeck) {
        int ConvertedCardInMainDeck = Tool.ConvertToComparables(CenterDeck.charAt(0));
        for (int i = 0; i < CardInHand.size(); i++) {
            int ConvertedCardInHand = Tool.ConvertToComparables(CardInHand.get(i).toString().charAt(0));
            if (CardInHand.get(i).toString().charAt(1) == CenterDeck.charAt(1)) {
                return 'S';
            } else if (ConvertedCardInHand == ConvertedCardInMainDeck) {
                return 'P';
            }
        }
        return 'N';
    }

    // this is to see whether the card inputted by the player is in the player deck
    // or not
    static boolean CardContains(String CardPlayed, List<Card> CardInHand) {
        for (int i = 0; i < CardInHand.size(); i++) {
            // from the array it will take element, must change it to string, and the
            // compare with the card inputted by the player
            if (CardInHand.get(i).toString().equals(CardPlayed)) {
                return true;
            }
        }
        return false;
    }

    // if the main deck is not empty this will be executed to draw cards from main
    // deck and add them to player
    // it will remove from maindeck byt the way
    static void DrawMain(List<Card> CardInHand, Deck deck) {
        if (!deck.getCards().isEmpty()) {
            // deck.getCards().size() - 1 <-- this is to get the top part of the array
            CardInHand.add(deck.getCards().get(deck.getCards().size() - 1));
            deck.getCards().remove(deck.getCards().size() - 1);
        }
    }

    // this is to get the index value of a card, from card array. Needed it to
    // remove the card from the player's hand
    // after they are done playing their round
    static int IndexOf(List<Card> Cards, String CardInHand) {
        int index = 0;
        for (int i = 0; i < Cards.size(); i++) {
            if (Cards.get(i).toString().equals(CardInHand.toString())) {
                index = i;
                break;
            }
        }
        return index;
    }

    // first must convert to number format because the cards are in alphanumeric
    // form
    // and then arrange them in an array. find the highest value card
    // then return back the highest
    // later must compare which player got the highest card in the main method

    public static int HighestPlayerIndex(List<String> Cards, boolean Size) {

        int LeadingCard = Tool.ConvertToComparables(Cards.get(0).toString().charAt(0));
        char LeadingSuit = Cards.get(0).toString().charAt(1);
        List<Integer> NumberFormat = new ArrayList<>();

        if (Size == false) {
            for (int i = 1; i < Cards.size(); i++) {
                if (Cards.get(i).toString().charAt(1) == LeadingSuit) {
                    if (LeadingCard <= Tool.ConvertToComparables(Cards.get(i).toString().charAt(0))) {
                        NumberFormat.add(Tool.ConvertToComparables(Cards.get(i).toString().charAt(0)));
                    } else {
                        NumberFormat.add(Tool.ConvertToComparables(Cards.get(i).toString().charAt(0)));
                    }
                } else {
                    NumberFormat.add(0);
                }
            }
        } else {
            for (int i = 0; i < Cards.size(); i++) {
                if (Cards.get(i).toString().charAt(1) == LeadingSuit) {
                    if (LeadingCard <= Tool.ConvertToComparables(Cards.get(i).toString().charAt(0))) {
                        NumberFormat.add(Tool.ConvertToComparables(Cards.get(i).toString().charAt(0)));
                    } else {
                        NumberFormat.add(Tool.ConvertToComparables(Cards.get(i).toString().charAt(0)));
                    }
                } else {
                    NumberFormat.add(0);
                }
            }
        }

        int Highest;
        if (!NumberFormat.isEmpty()) {
            Highest = NumberFormat.indexOf(Collections.max(NumberFormat));
        } else {
            Highest = 0;
        }
        // System.out.println(Highest);
        return Highest;
    }
}
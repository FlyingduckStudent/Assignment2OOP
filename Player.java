import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Player{
    private String name;
    private List<Card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void removeCard(Card card){
        hand.remove(card);
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public static List<Player> CreatePlayersList(int n, List<Player> players) {
        players.add(new Player("Player A"));
        players.add(new Player("Player B"));
        players.add(new Player("Player C"));
        players.add(new Player("Player D"));
        // Here we are using rotate() method to rotate the element by distance n
        Collections.rotate(players, n);
        return players;
    }
}
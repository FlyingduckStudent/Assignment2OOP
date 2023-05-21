import java.io.IOException;
public class Tool {
    // use this method to delay processes
    public static void pause(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // this method clears screen, much longer than C++'s system.cls()
    public static void clearScreen() {

        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            // fallback to clear screen for Unix-based systems
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

    }

    //This is to convert from letters to int. Easier to compare
    public static int ConvertToComparables(char Comparable){
        int ConvertCardInHand = 0;
        switch (Comparable) {
            case 'A':
                ConvertCardInHand = 14;
                break;
            case 'X':
                ConvertCardInHand = 10;
                break;
            case 'J':
                ConvertCardInHand = 11;
                break;
            case 'Q':
                ConvertCardInHand = 12;
                break;
            case 'K':
                ConvertCardInHand = 13;
                break;
            default:
                ConvertCardInHand = Character.getNumericValue(Comparable);
                break;
        }
        return ConvertCardInHand;
    }

}
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class SnapCardGame extends CardGame {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Scanner scanner;
    private Timer timer;

    public SnapCardGame(String name, Player player1, Player player2) {
        super(name);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.scanner = new Scanner(System.in);
        this.timer = new Timer();
    }

    public void playGame() {
        System.out.println("Iiiiittttt's SNAP!! Press enter to take your go!!");
        System.out.println("type 'snap' within 4 seconds to win:)");

        shuffleDeck();

        Card previousCard = null;
        while (true) {
            System.out.println(currentPlayer.getName() + "'s turn. Press Enter...");
            scanner.nextLine();

            Card currentCard = dealCard();
            if (currentCard == null) {
                System.out.println("You ran out of cards in the deck:( game over!");
                break;
            }


            if (previousCard != null && currentCard.getSymbol().equals(previousCard.getSymbol())) {
                System.out.println("quick!! say snap!!");

                startTimer();

                String input = scanner.nextLine();

                stopTimer();

                if (input.equalsIgnoreCase("snap")) {
                    System.out.println(currentPlayer.getName() + " says SNAP! They win this round!");

                    currentPlayer.getPlayerDeck().addCard(previousCard);
                    currentPlayer.getPlayerDeck().addCard(currentCard);

                    if (currentPlayer.getPlayerDeck().size() == getDeck().size()) {
                        currentPlayer.setWinner(true);
                        System.out.println(currentPlayer.getName() + " wins the game!");
                        break;
                    }
                } else {
                    System.out.println("Time's up! " + currentPlayer.getName() + " loses.");
                }
            }

            previousCard = currentCard;

            switchPlayer();
        }

        scanner.close();
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                stopTimer();
                System.out.println("Time's up! " + currentPlayer.getName() + " too slow:(");
            }
        }, 4000);
    }

    private void stopTimer() {
        timer.cancel();
        timer = new Timer();
    }

    public static void main(String[] args) {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        SnapCardGame snapGame = new SnapCardGame("Snap", player1, player2);
        snapGame.playGame();
    }
}
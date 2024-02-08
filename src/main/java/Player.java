class Player {
    private String name;
    private boolean isWinner;
    private Deck playerDeck;

    public Player(String name) {
        this.name = name;
        this.isWinner = false;
        this.playerDeck=new Deck();
    }

    public String getName() {
        return name;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public Deck getPlayerDeck() {
        return playerDeck;
    }
}
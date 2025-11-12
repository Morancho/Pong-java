package src.game;

public class Score {
    private int leftScore = 0;
    private int rightScore = 0;
    private final int MAX_SCORE = 10;

    public void leftPoint() { leftScore++; }
    public void rightPoint() { rightScore++; }

    public boolean hasWinner() {
        return leftScore >= MAX_SCORE || rightScore >= MAX_SCORE;
    }

    public String getWinner() {
        if (leftScore >= MAX_SCORE) return "Jugador esquerre";
        else if (rightScore >= MAX_SCORE) return "Jugador dret";
        else return null;
    }

    public int getLeft() { return leftScore; }
    public int getRight() { return rightScore; }
}

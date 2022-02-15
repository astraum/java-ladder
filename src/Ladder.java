import java.util.Random;

public class Ladder {
    private static final char RAIL = '|';
    private static final char STEP = '-';
    private static final char EMPTY = ' ';
    private static final int STEP_WIDTH = 1;

    private final char[][] ladderBars;
    private final Random random = new Random();

    public Ladder(int height, int persons) {
        this.ladderBars = drawLadderBars(height, persons);
    }

    public String render() {
        StringBuilder sb = new StringBuilder();

        for (char[] row : ladderBars) {
            sb.append(row);
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    private char[][] drawLadderBars(int height, int persons) {
        char[][] randomLadderBars = new char[height][getWidth(persons)];

        for (char[] row : randomLadderBars) {
            drawLadderRow(row);
        }

        return randomLadderBars;
    }

    private int getWidth(int persons) {
        return persons + STEP_WIDTH * (persons - 1);
    }

    private void drawLadderRow(char[] ladderRow) {
        ladderRow[0] = RAIL;

        for (int i = 1; i < ladderRow.length - 1; i += 2) {
            ladderRow[i] = drawRandomStep();
            ladderRow[i + 1] = RAIL;
        }
    }

    private char drawRandomStep() {
        return random.nextBoolean() ? STEP : EMPTY;
    }
}

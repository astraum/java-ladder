package ladder.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ladder {
    private static final String RAIL = "|";
    private static final String STEP = "-";
    private static final String EMPTY = " ";
    private static final int STEP_WIDTH = Group.NAME_LENGTH_LIMIT + 1;
    private static final String SECTION_WITH_STEP = RAIL + STEP.repeat(STEP_WIDTH);
    private static final String SECTION_WITHOUT_STEP = RAIL + EMPTY.repeat(STEP_WIDTH);

    private final int height;
    private final int width;
    private final List<LadderRow> ladderRowList;
    private final Climber climber = new Climber();

    protected Ladder(int height, int playerCount) {
        this.height = height;
        this.width = playerCount - 1;
        this.ladderRowList = new ArrayList<>(width);
        build();
    }

    public int getRewardIndex(int playerIndex) {
        climber.initialize(playerIndex);
        climber.climbDown();
        return climber.endPosition;
    }

    public String render() {
        return ladderRowList.stream()
                .map(this::renderRow)
                .collect(Collectors.joining());
    }

    private StringBuilder renderRow(LadderRow ladderRow) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < width; i++) {
            sb.append(ladderRow.hasStepAt(i) ? SECTION_WITH_STEP : SECTION_WITHOUT_STEP);
        }

        return sb.append(RAIL).append(System.lineSeparator());
    }

    private void build() {
        for (int i = 0; i < height; i++) {
            ladderRowList.add(new LadderRow(width));
        }
    }

    class Climber {
        int xPosition;
        int yPosition = 0;
        int endPosition = -1;

        void initialize(int xPosition) {
            this.xPosition = xPosition;
            yPosition = 0;
            endPosition = -1;
        }

        void climbDown() {
            while (yPosition < height) {
                moveLeftOrRightIfPossible();
                yPosition++;
            }

            endPosition = this.xPosition;
        }

        void moveLeftOrRightIfPossible() {
            if (canMoveLeft()) {
                xPosition--;
                return;
            }

            if (canMoveRight()) {
                xPosition++;
            }
        }

        boolean canMoveLeft() {
            if (xPosition == 0) {
                return false;
            }

            return ladderRowList.get(yPosition).hasStepAt(xPosition - 1);
        }

        boolean canMoveRight() {
            if (xPosition == width) {
                return false;
            }

            return ladderRowList.get(yPosition).hasStepAt(xPosition);
        }

    }
}

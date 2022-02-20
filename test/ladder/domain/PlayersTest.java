package ladder.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayersTest {

    private static final List<String[]> DUMMY_INPUT = List.of(
            new String[]{"ABC", "RATHALOS", "GOLEM", "BEAST"},
            new String[]{"HANNAH", "JK", "HONUX", "FLOWER", "GOAT"},
            new String[]{"HAHAHAHA", "MADNESS", "STRANGE"}
    );

    @Test
    @DisplayName("3가지 입력 시나리오에 대한 플레이어 수 카운트")
    void count() {
        assertAll(() -> assertEquals(new Players(DUMMY_INPUT.get(0)).count(), 4),
                () -> assertEquals(new Players(DUMMY_INPUT.get(1)).count(), 5),
                () -> assertEquals(new Players(DUMMY_INPUT.get(2)).count(), 3));
    }

    @Test
    @DisplayName("3가지 입력 시나리오에 대한 문자열 변환값")
    void testToString() {
        assertAll(
                () -> assertEquals(new Players(DUMMY_INPUT.get(0)).toString(), "*ABC   *RATHA *GOLEM *BEAST "),
                () -> assertEquals(new Players(DUMMY_INPUT.get(1)).toString(), "*HANNA *JK    *HONUX *FLOWE *GOAT  "),
                () -> assertEquals(new Players(DUMMY_INPUT.get(2)).toString(), "*HAHAH *MADNE *STRAN "));
    }
}

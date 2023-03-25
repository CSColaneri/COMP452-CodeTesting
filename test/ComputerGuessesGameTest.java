import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComputerGuessesGameTest {

    private ComputerGuessesGame game;

    @BeforeEach
    void init() {
        // reinint the game for each test.
        game = new ComputerGuessesGame();
    }

    @Test
    void makeFirstGuessTest() {
        assertEquals(500, game.makeNextGuess(true));
    }


    @Test
    void updateBoundsTest_tooLow() {
        game.makeNextGuess(true);
        assertEquals(1, game.getLowerBound());
        assertEquals(1000, game.getUpperBound());
        game.makeNextGuess(true);
        assertEquals(501, game.getLowerBound());
        assertEquals(1000, game.getUpperBound());
        for(int i = 0; i < 9; ++i) {
            game.makeNextGuess(true);
        }
        assertEquals(1000, game.getLastGuess());
    }

    @Test
    void updateBoundsTest_tooHigh() {
        game.makeNextGuess(true);
        assertEquals(1, game.getLowerBound());
        assertEquals(1000, game.getUpperBound());
        game.makeNextGuess(false);
        assertEquals(1, game.getLowerBound());
        assertEquals(500, game.getUpperBound());
        for(int i = 0; i < 9; ++i) {
            game.makeNextGuess(false);
        }
        assertEquals(1, game.getLastGuess());
    }
}
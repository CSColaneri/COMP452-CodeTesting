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
    void makeNextGuessTest() {
        // TODO: should this be 500 or 501?
        assertEquals(500, game.makeNextGuess(true));
    }

    @Test
    void updateBoundsTest() {
        // TODO: Depending on above answer, change this accordingly. (+1)
        game.makeNextGuess(true);
        assertEquals(1, game.getLowerBound());
        assertEquals(1000, game.getUpperBound());
        game.makeNextGuess(true);
        assertEquals(501, game.getLowerBound());
        assertEquals(1000, game.getUpperBound());
    }
}
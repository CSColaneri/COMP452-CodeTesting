import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class HumanGuessesGameTest {

    private MockHumanGuessesGame game;

    @BeforeEach
    public void init() {
        this.game = new MockHumanGuessesGame(250);
    }

    @Test
    public void HumanGuessesGameTest() {
        //using dependency injection
        MockHumanGuessesGame g = new MockHumanGuessesGame(1);
        assertEquals(1, g.getTarget());
        g = new MockHumanGuessesGame(500);
        assertEquals(500, g.getTarget());
        g = new MockHumanGuessesGame(MockHumanGuessesGame.UPPER_BOUND);
        assertEquals(MockHumanGuessesGame.UPPER_BOUND, g.getTarget());
        assertThrows(IllegalArgumentException.class,
                () -> {new MockHumanGuessesGame(1001);});
    }

    @Test
    public void MakeGuessTest_Correct() {
        //using dependency injection
        assertEquals(GuessResult.CORRECT, game.makeGuess(250));
        assert game.isDone();
    }

    @Test
    public void MakeGuessTest_Low() {
        //using dependency injection
        assertEquals(GuessResult.LOW, game.makeGuess(1));
        assertEquals(GuessResult.LOW, game.makeGuess(5));
        assertEquals(GuessResult.LOW, game.makeGuess(249));
    }

    @Test
    public void MakeGuessTest_High() {
        //using dependency injection
        assertEquals(GuessResult.HIGH, game.makeGuess(251));
        assertEquals(GuessResult.HIGH, game.makeGuess(900));
        assertEquals(GuessResult.HIGH, game.makeGuess(1000));
    }

    @Test
    public void MakeGuessTest_IncrementsNumGames() {
        //using dependency injection
        game.makeGuess(250);
        assertEquals(1, game.getNumGuesses());
        game.makeGuess(250);
        assertEquals(2, game.getNumGuesses());
        game.makeGuess(250);
        assertEquals(3, game.getNumGuesses());
    }

}

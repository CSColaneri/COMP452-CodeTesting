import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import java.time.format.DateTimeParseException;

public class StatsFileTest {

    private static final int [] BIN_EDGES = {1, 2, 4, 6, 8, 10, 12, 14};

    private static final String testInput1 = "\"2020-02-2Z4T20:10:29.11Z02785Z00\",\"3\" \n \"2020-02-24T20:15:28.750793100\",\"3\" \n \"2020-02-24T20:56:09.088002200\",\"9\"";
    private static final String testInput2 = "2020-02-24T20:10:29.110278500, apple \n \"2020-02-24T20:15:28.750793100\",\"3\" \n \"2020-02-24T20:56:09.088002200\",\"9\"";
    private static final String testInput3 = "2020-02-24T20:10:29.110278500,3\n2020-02-24T20:15:28.750793100,3\n2020-02-24T20:56:09.088002200,9\n";


    @Test
    void testNonNegativeNumGames()
    {
        StatsFile sf = new StatsFile();

        for(int binIndex=0; binIndex<BIN_EDGES.length; binIndex++){
            assert (sf.numGames(binIndex) >= 0);
        }
    }

    @Test
    void testNumGuessesNegative()
    {
        StatsFile sf = new StatsFile();

        assert (sf.numGames(-1) == 0);

        assert (sf.numGames(-10) == 0);

        assert (sf.numGames(-100) == 0);

        assert (sf.numGames(-1000) == 0);

    }

    @Test
    void testNonNegativeMaxGames()
    {
        StatsFile sf = new StatsFile();

        assert (sf.maxNumGuesses() >= 0);
    }

    @Test
    void testDataNotNegative()
    {
        StatsFile sf = new StatsFile();

        for(int binIndex=0; binIndex<BIN_EDGES.length; binIndex++){
            assert (sf.resultsPanelData(BIN_EDGES).get(binIndex) >= 0);
        }
    }

    @Test
    void testDataNotNull()
    {
        StatsFile sf = new StatsFile();
        assertNotNull(sf.resultsPanelData(BIN_EDGES));
    }


    @Test
    void testConstructorBadDate()
    {
        assertThrows(DateTimeParseException.class, () -> {
            MockStatsFile sf = new MockStatsFile(testInput1);
        });
    }

    @Test
    void testConstructorBadNumberFormat()
    {
        assertThrows(NumberFormatException.class, () -> {
            MockStatsFile sf = new MockStatsFile(testInput2);
        });
    }

    @Test
    void testConstructorGoodDate()
    {
            MockStatsFile sf = new MockStatsFile(testInput3);
    }

    @Test
    void testConstructorGoodNumberFormat()
    {
        MockStatsFile sf = new MockStatsFile(testInput3);
    }

}

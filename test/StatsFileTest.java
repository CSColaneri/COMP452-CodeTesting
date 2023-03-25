import org.junit.jupiter.api.*;

import javax.swing.*;

public class StatsFileTest {

    private static final int [] BIN_EDGES = {1, 2, 4, 6, 8, 10, 12, 14};

    @Test
    void testNonNegativeNumGames()
    {
        StatsFile sf = new StatsFile();

        for(int binIndex=0; binIndex<BIN_EDGES.length; binIndex++){
            assert (sf.numGames(binIndex) >= 0);
        }
    }

    @Test
    void testNonNegativeMaxGames()
    {
        StatsFile sf = new StatsFile();

        assert (sf.maxNumGuesses() >= 0);
    }

}

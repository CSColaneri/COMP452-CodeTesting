public class ComputerGuessesGame {
    private int numGuesses;
    private int lastGuess;
    private int upperBound;
    private int lowerBound;

    public ComputerGuessesGame() {
        numGuesses = 0;
        upperBound = 1000;
        lowerBound = 1;
        lastGuess = 0; // new
    }

    public int getNumGuesses() {
        return numGuesses;
    }

    public int getLastGuess() {
        return lastGuess;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    /**
     * Updates the bounds and makes the computer's next guess.
     *
     * @param tooHigh True if the computer's guess was too high, false if it was
     *               too low.
     * @return The computer's new guess.
     */
    public int makeNextGuess(boolean tooHigh) {
        updateBounds(tooHigh);
        this.lastGuess = (lowerBound + upperBound + 1) / 2;
        this.numGuesses += 1;
        return lastGuess;
    }

    /**
     * Updates the bounds based on whether the previous guess was
     * {@code higher} than the correct answer or not.
     *
     * @param tooHigh true if the computer's guess was too high, otherwise false.
     */
    public void updateBounds(boolean tooHigh) {
        if(tooHigh) {
            lowerBound = Math.max(lowerBound, this.lastGuess + 1);
        } else {
            upperBound = Math.min(upperBound, this.lastGuess);
        }
    }
}

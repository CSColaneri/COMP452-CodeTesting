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
     * @param tooLow True if the computer's guess was too low, false if it was
     *               too high.
     * @return The computer's new guess.
     */
    public int makeNextGuess(boolean tooLow) {
        updateBounds(tooLow);
        this.lastGuess = (lowerBound + upperBound + 1) / 2;
        this.numGuesses += 1;
        return lastGuess;
    }

    /**
     * Updates the bounds based on whether the previous guess was
     * {@code tooLow} than the correct answer or not.
     *
     * @param tooLow true if the computer's guess was too low, otherwise false.
     */
    public void updateBounds(boolean tooLow) {
        if(tooLow) {
            lowerBound = Math.max(lowerBound, this.lastGuess + 1);
        } else {
            upperBound = Math.min(upperBound, this.lastGuess);
        }
    }
}

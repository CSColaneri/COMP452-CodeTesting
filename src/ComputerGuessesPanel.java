import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * UI screen for when the computer is guessing a number
 *
 * Displays the computer's guesses and processes human's answers
 * Tracks the computer's guesses
 *
 * TODO: refactor this class
 */
public class ComputerGuessesPanel extends JPanel {

    private int numGuesses;
    private int lastGuess;

    // upperBound and lowerBound track the computer's knowledge about the correct number
    // They are updated after each guess is made
    private int upperBound; // correct number is <= upperBound
    private int lowerBound; // correct number is >= lowerBound

    public ComputerGuessesPanel(JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback){
        numGuesses = 0;
        upperBound = 1000;
        lowerBound = 1;
        lastGuess = 0; // new

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel guessMessage = new JLabel("I guess ___.");
        guessMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(guessMessage);
        guessMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0, 40)));

        JLabel prompt = new JLabel("Your number is...");
        this.add(prompt);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));

        JButton lowerBtn = new JButton("Lower");
        // TODO: Calculation, Repeated, get this from somewhere else
        lowerBtn.addActionListener(e -> {
//            upperBound = Math.min(upperBound, lastGuess);
//
//            lastGuess = (lowerBound + upperBound + 1) / 2;
//            numGuesses += 1;
//            guessMessage.setText("I guess " + lastGuess + ".");
            makeGuess(guessMessage, false);
        });
        this.add(lowerBtn);
        lowerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));

        JButton correctBtn = new JButton("Equal");
        correctBtn.addActionListener(e -> {
            guessMessage.setText("I guess ___.");

            // Send the result of the finished game to the callback
            GameResult result = new GameResult(false, lastGuess, numGuesses);
            gameFinishedCallback.accept(result);

            CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
            cardLayout.show(cardsPanel, ScreenID.GAME_OVER.name());
        });
        this.add(correctBtn);
        correctBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));

        JButton higherBtn = new JButton("Higher");
        higherBtn.addActionListener(e -> {
//            lowerBound = Math.max(lowerBound, lastGuess + 1);
//
//            lastGuess = (lowerBound + upperBound + 1) / 2;
//            numGuesses += 1;
//            guessMessage.setText("I guess " + lastGuess + ".");
            makeGuess(guessMessage, true);
        });
        this.add(higherBtn);
        higherBtn.setAlignmentX(Component.CENTER_ALIGNMENT);


        // TODO: Initial guess. Happens only once.
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                numGuesses = 0;
                upperBound = 1000;
                lowerBound = 1;
                lastGuess=0; // new
//
//                lastGuess = (lowerBound + upperBound + 1) / 2;
//                guessMessage.setText("I guess " + lastGuess + ".");
                makeGuess(guessMessage, true);
            }
        });
    }

    /**
     * Returns the current upper boundary for the computer's guesses.
     * @return Returns the current upper boundary for the computer's guesses.
     */
    public int getUpperBound() {return this.upperBound;}

    /**
     * Returns the current lower boundary for the computer's guesses.
     * @return Returns the current lower boundary for the computer's guesses.
     */
    public int getLowerBound() {return this.lowerBound;}

    private void makeGuess(JLabel guessMessage, boolean higher) {
        updateGuessLabel(guessMessage, updateBounds(higher));
    }

    /**
     * Updates the lowerBound or upperBound based on whether the computers last
     * guess was {@code higher} than the actual number or not.
     *
     * @param higher True if the computer's guess was too high, false if it was
     *               too low.
     * @return The computer's new guess.
     */
    private int updateBounds(boolean higher) {
        if(higher) {
            lowerBound = Math.max(lowerBound, this.lastGuess + 1);
        } else {
            upperBound = Math.min(upperBound, this.lastGuess);
        }
        this.lastGuess = (lowerBound + upperBound + 1) / 2;
        this.numGuesses += 1;
        return lastGuess;
    }

    /**
     * Updates the given JLabel {@param guessMessage} that displays
     * the computer's guess
     * @param guessMessage the JLabel to update with the given guess {@code lastGuess}
     * @return a reference to the given JLabel {@code guessMessage}
     */
    private JLabel updateGuessLabel(JLabel guessMessage, int lastGuess) {
        guessMessage.setText("I guess " + lastGuess + ".");
        return guessMessage;
    }
}

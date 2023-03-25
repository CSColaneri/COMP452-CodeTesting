public class MockHumanGuessesGame extends HumanGuessesGame {

    public MockHumanGuessesGame(int target) {
        this.target = target;

        numGuesses = 0;
        gameIsDone = false;
    }

    public int getTarget() {return this.target;}

    GuessResult makeGuess(int value){
        numGuesses += 1;

        if(value < target){
            return GuessResult.LOW;
        }
        if(value > target){
            return GuessResult.HIGH;
        }

        return GuessResult.CORRECT;
    }
    int getNumGuesses(){
        return numGuesses;
    }

    boolean isDone(){
        return gameIsDone;
    }
}

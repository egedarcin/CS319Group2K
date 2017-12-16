package Modal;

public class GameData {
    private int score1 = 0;
    private int score2 = 0;
    private int scoreLimit;
    private int TimeLimit ;



    private int CurrentTime;

    public void startTime(){}
    public void PauseTime(){}

    public void updateScore(int score1, int score2){this.score1 = score1; this.score2= score2;}
    public void resetScore(){}
    public void resetTime(){}



    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public int getScoreLimit() {
        return scoreLimit;
    }

    public void setScoreLimit(int scoreLimit) {
        this.scoreLimit = scoreLimit;
    }

    public int getTimeLimit() {
        return TimeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        TimeLimit = timeLimit;
    }

    public int getCurrentTime() {
        return CurrentTime;
    }
}

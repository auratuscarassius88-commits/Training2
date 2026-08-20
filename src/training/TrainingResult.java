package training;

public class TrainingResult {

    private Employee emp;
    private int score;
    private String judge;

    public TrainingResult(Employee emp, int score, String judge) {
        this.emp = emp;
        this.judge = judge;
        this.score = score;
    }

    public Employee getEmp() {
        return emp;
    }

    public int getScore() {
        return score;
    }

    public String getJudge() {
        return judge;
    }

}

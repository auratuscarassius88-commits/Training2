package traning;

public class TrainingResult {
	private Employee emp;
	int score;
	String judge;

    public TrainingResult(Employee emp, String judge, int score) {
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

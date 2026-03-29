package traning;

import java.util.ArrayList;
import java.util.List;

public class TrainingService {
	public TrainingResult registerTrainingResult(Employee employee,int score) {
		//研修結果を登録する。
		
		//合格判定Stringを宣言
		String judge = judgeResult(score);
		//TrainingResultインスタンスを作成
		TrainingResult result = new TrainingResult(employee, score, judge);

		
		return result;
	}
	
	public String judgeResult(int score) {
		//点数に基づいて合否を判定する。
		
		String discrimination = "不合格";
		if(score > 75) {
			discrimination = "合格";
		}
		return discrimination;
	}
	
	public List<TrainingResult> getResultList() {
		//研修結果一覧を取得する。
		TrainingRepository tr = new TrainingRepository();
		List<TrainingResult> resultList = new ArrayList<>();
		//findAllメソッドをつかう
		resultList = tr.findAll();
		
		return resultList;
	}
}

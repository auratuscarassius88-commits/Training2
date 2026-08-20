package training;

import java.util.List;

public class TrainingService {

    public TrainingResult registerTrainingResult(Employee employee, int score) {
        //研修結果を登録する。
        TrainingValidator validator = new TrainingValidator();
        TrainingRepository repository = new TrainingRepository();
        validator.validate(employee, score);//ここでエラーが出たらthrowされてmainでキャッチ
        
        String judge = judgeResult(score);
        //合否の結果をまとめたインスタンスを作成
        TrainingResult result = new TrainingResult(employee, score, judge);
        //ここで保存処理
        repository.save(result);
        //Mainに結果を表示するためにResultを返す
        return result; 
    }

    public String judgeResult(int score) {
        //点数に基づいて合否を判定する。

        String discrimination = "不合格";
        if (score >= 75) {
            discrimination = "合格";
        }
        return discrimination;
    }

    public List<TrainingResult> getResultList() {
        //研修結果一覧を取得する。
        TrainingRepository repository = new TrainingRepository();
        //研修結果をインスタンス化
        List<TrainingResult> resultList = repository.findAll();

        return resultList;
    }
}

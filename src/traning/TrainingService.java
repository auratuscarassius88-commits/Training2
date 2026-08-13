package traning;

import java.util.List;

public class TrainingService {

    public TrainingResult registerTrainingResult(Employee employee, int score) {
        //研修結果を登録する。
        TrainingValidator validator = new TrainingValidator();

        if (!validator.validate(employee, score)) {
            throw new IllegalArgumentException("入力内容が正しくありません");
        }

        String judge = judgeResult(score);
        return new TrainingResult(employee, score, judge);
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

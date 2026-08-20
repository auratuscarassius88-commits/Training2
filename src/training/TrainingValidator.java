package training;

import java.util.*;

public class TrainingValidator {

    public void validate(Employee employee, int score) {
        //受講者情報および研修点数の妥当性をチェックする

        //与えられたインスタンスが空でないか？
        if (employee == null) {
            throw new IllegalArgumentException("社員情報が入力されていません");
        }
        //IDはちゃんと入力されているか判別isEmpty()だともともと空か？しか対応していないみたいなのでisBlankを採用してスペースや空文字の場合でもはじけるようにした
        if (employee.getEmpId() == null || employee.getEmpId().isBlank()) {
            throw new IllegalArgumentException("社員IDが入力されていません");
        }
        //名前がちゃんと入力されているか判定
        if (employee.getEmpName() == null || employee.getEmpName().isBlank()) {
            throw new IllegalArgumentException("社員名が入力されていません");
        }

        // 点数が0点以上100点以下か判別
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("点数は0点以上100点以下で入力してください");
        }

        //受講者情報のIDが重複していないことを確認
        TrainingRepository repository = new TrainingRepository();
        List<TrainingResult> resultList = repository.findAll();

        for (TrainingResult result : resultList) {
            //IDの比較
            
            String id = result.getEmp().getEmpId();
            //引数で受け取ったものをStringに
            
            //保存されていたものと入力されていたもののIDの比較
            if (employee.getEmpId().equals(id)) {
                throw new IllegalArgumentException("IDが重複しています。");
            }
        }

        
    }

}

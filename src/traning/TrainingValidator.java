package traning;

import java.util.*;

public class TrainingValidator {

    public boolean validate(Employee employee, int score) {
        //受講者情報および研修点数の妥当性をチェックする

        //与えられたインスタンスが空でないか？
        if (employee == null) {
            return false;
        }
        //IDはちゃんと入力されているか判別
        if (employee.getEmpId() == null || employee.getEmpId().isEmpty()) {
            return false;
        }
        //名前がちゃんと入力されているか判定
        if (employee.getEmpName() == null || employee.getEmpName().isEmpty()) {
            return false;
        }

        //点数が0点以上、100点未満を判別
        if (score < 0 || score > 100) {
            return false;
        }

        //受講者情報のIDが重複していないことを確認
        TrainingRepository repository = new TrainingRepository();
        List<TrainingResult> resultList = repository.findAll();

        for (TrainingResult result : resultList) {
            //IDの比較
            //テキストファイルに保存されていたものをStringに
            Employee emp = result.getEmp();
            String id = emp.getEmpId();
            //引数で受け取ったものをStringに
            String inputId = employee.getEmpId();
            //保存されていたものと入力されていたもののIDの比較
            if (id.equals(inputId)) {
                return false;
            }
        }

        return true;
    }

}

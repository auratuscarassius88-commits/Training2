package traning;

import java.util.*;
public class TrainingValidator {
	public boolean validate(Employee employee,int score) {
		//受講者情報および研修点数の妥当性をチェックする
		
		//与えられたインスタンスが空でないか？
		if(employee == null){
			return false;
		}
		//IDはちゃんと入力されているか判別
		if(employee.getEmp_ID() == null || employee.getEmp_ID().isEmpty()){
			return false;
		}
		//名前がちゃんと入力されているか判定
		if(employee.getEmp_name() == null || employee.getEmp_name().isEmpty()){
			return false;
		}
		
		//点数が0点以上、100点未満を判別
		if(score < 0 || score > 100) {
			return false;
		}
		
		//受講者情報のIDが重複していないことを確認
		TrainingRepository tr = new TrainingRepository();
		List<TrainingResult> resultList = tr.findAll();
		
		for(TrainingResult ts : resultList){
			//IDの比較
			//テキストファイルに保存されていたものをStringに
			Employee emp = ts.getEmp();
			String id = emp.getEmp_ID();
			//引数で受け取ったものをStringに
			String now_Id = employee.getEmp_ID();
			//保存されていたものと入力されていたもののIDの比較
			if(id.equals(now_Id)){
				return false;
			}
		}
		
		return true;
	}

}

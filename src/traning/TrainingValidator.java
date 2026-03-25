package traning;

public class TrainingValidator {
	public boolean validate(Employee employee,int score) {
		//受講者情報および研修点数の妥当性をチェックする
		boolean hanbetu = false;
		//与えられたインスタンスが空でないか？
		if(employee != null){
			hanbetu = true;
		}
		//名前、IDはちゃんと入力されているか判別
		if(employee.getEmp_ID() != null && employee.getEmp_name() != null){
			hanbetu = true;
		}
		

		
		//点数が0点以上、100点未満を判別
		if(score > 0 && score < 101) {
			hanbetu = true;
		}
		
		//受講者情報のIDが重複していないことを確認
		
		
		return hanbetu;
	}

}

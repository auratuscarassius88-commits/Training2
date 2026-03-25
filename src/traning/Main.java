package traning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
	
		System.out.println("社員ID、名前、点数の順に入力してください");
		//入力用のスキャナーをインスタンス化
		Scanner sc = new Scanner(System.in);
		System.out.println("社員ID");
		String emp_ID = sc.next();
		System.out.println("社員名");
		String emp_name = sc.next();
		System.out.println("点数");
		int score = sc.nextInt();
		Employee emp = new Employee(emp_ID,emp_name);
		
		//入力の妥当性、IDの重複がないかを確認
		TrainingValidator tra = new TrainingValidator();
		boolean a = tra.validate(emp,score);
		if(a ) {
			//社員IDと名前と点数を持ったインスタンスを作成
			//合否の判定
			Map<String, List<String>> empList = new HashMap<>();
			TrainingService ts = new TrainingService();
			empList = ts.registerTrainingResult(emp, score);
			//合否の判定
			String judge = ts.judgeResult(score);
			//トレーニングリザルトをインスタンス化して結果を代入する
			//結果をファイルに保存
			TrainingRepository tr = new TrainingRepository();
			
		}else {
			System.out.println("入力が正しくありません。プログラムを終了します。");		
		}
		
	}
}

package traning;

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
		
		TrainingService ts = new TrainingService();
		//入力を判定
		TrainingValidator tv = new TrainingValidator();
		//==trueを入れたほうが可読性高い？
		if((tv.validate(emp, score))){
			//TrainingServiceをつかってTrainingResultをインスタンス化。ID、名前、点数、合否が入っている。
			TrainingResult tr = ts.registerTrainingResult(emp, score);
			//保存するためにTrainingRepositoryをインスタンス化する
			TrainingRepository tres = new TrainingRepository();
			String save = tres.save(tr);
			
			System.out.println(save);
			//正常に登録されていたら今回の結果をプリント
			if(save.equals("書き込みが完了しました")){
				System.out.println("社員ID:" + tr.getEmp().getEmp_ID());
				System.out.println("社員名:" + tr.getEmp().getEmp_name());
				System.out.println("点数:" + tr.getScore());
				System.out.println("判定:" + tr.getJudge());
			}
		}else{
			System.out.println("入力が正しくありません");
		}
		System.err.println("システムを終了します。");


		
	}
}

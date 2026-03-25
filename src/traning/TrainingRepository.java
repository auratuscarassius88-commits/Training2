package traning;

import java.io.FileWriter;
import java.io.PrintWriter;

public class TrainingRepository {

	private String path = "Trining.text";
	public String save(TrainingResult result) {
		//研修結果を保存する。
		String save = "";
		
		try (PrintWriter pw = new PrintWriter(new FileWriter(path))){
			//Employeeのインスタンス化
			Employee emp = result.getEmp();
			//ID、名前、点数、合否の順で書き込む
			pw.println(emp.getEmp_ID() + " " + emp.getEmp_name() + " " + result.getScore() + " " + result.getJudge());
			save = "書き込みが完了しました";
		} catch (Exception e) {
			save = "書き込みが失敗しました。";

		}
		return save;
	}
	
	public void findAll() {
		//研修結果一覧を取得する。
		
	}

}

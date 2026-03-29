package traning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
public class TrainingRepository {

	private String path = "Training.text";
	public String save(TrainingResult result) {
		//研修結果を保存する。
		String save = "";
		
		try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))){
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
	
	public List<TrainingResult> findAll() {
		//研修結果一覧を取得する。
		//格納するリストを作成
		List<TrainingResult> resultList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			//中身がなくなったら終了できるように判別するString
			String txt;
			while((txt = br.readLine()) != null){
				//空白で分割して配列に格納
				String[] value = txt.split(" ");

				//リストに格納
				//employeeをインスタンス化してIDと名前を渡す
				Employee emp = new Employee(value[0], value[1]);
				//empとスコアと合否をコンストラクタに渡してTrainingResluをインスタンス化
				TrainingResult result = new TrainingResult(emp, Integer.parseInt(value[2]), value[3]);
				
				//インスタンスをリストに追加
				resultList.add(result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();

		}
	//リストを返す	
	return resultList;
	}

}

package traning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainingService {
	public Map<String, List<String>> registerTrainingResult(Employee employee,int score) {
		//研修結果を登録する。
		Map<String, List<String>> empList = new HashMap<>();
		List<String> value = new ArrayList<>();
		value.add(employee.getEmp_name());
		value.add(Integer.toString(score));
		empList.put(employee.getEmp_ID(),value);
		
		
		return empList;
	}
	
	public String judgeResult(int score) {
		//点数に基づいて合否を判定する。
		
		String discrimination = "不合格";
		if(score > 75) {
			discrimination = "合格";
		}
		return discrimination;
	}
	
	public String getResultList() {
		//研修結果一覧を取得する。
		String result = "";
		try (BufferedReader reader = new BufferedReader((new FileReader("Training.txt")))){
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}

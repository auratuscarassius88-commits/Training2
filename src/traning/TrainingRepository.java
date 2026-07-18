package traning;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class TrainingRepository {

    private String path = "Training.text";

    public boolean save(TrainingResult result) {
        //研修結果を保存する。
        boolean save;

        try (BufferedWriter bw = Files.newBufferedWriter(
                Path.of(path),
                StandardCharsets.UTF_8,//文字コードを強制
                StandardOpenOption.CREATE,//ファイルがなければ作る
                StandardOpenOption.APPEND)) {//末尾に追記
            //Employeeのインスタンス化
            Employee emp = result.getEmp();
            //ID、名前、点数、合否の順で書き込む
            bw.write(emp.getEmpId() + "," + emp.getEmpName() + "," + result.getScore() + "," + result.getJudge());
            bw.newLine();//改行用
            save = true;
        } catch (Exception e) {
            save = false;

        }
        return save;
    }

    public List<TrainingResult> findAll() {
        //研修結果一覧を取得する。
        //格納するリストを作成
        List<TrainingResult> resultList = new ArrayList<>();
        Path filePath = Path.of(path);
        //何もない場合からのリストを返す
        if (!Files.exists(filePath)) {
            return resultList;
        }
        try (BufferedReader br = Files.newBufferedReader(
                Path.of(path),
                StandardCharsets.UTF_8)) {
            //中身がなくなったら終了できるように判別するString
            String txt;
            while ((txt = br.readLine()) != null) {
                //空白を削除して空行なら処理をスキップ
                if (txt.trim().isEmpty()) {
                    continue;
                }
                
                //空白で分割して配列に格納
                String[] value = txt.split(",");

                //要素数が4でない場合はスキップ
                if(value.length != 4){
                    continue;
                }
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

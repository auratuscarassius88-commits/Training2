package training;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class TrainingRepository {

    private String path = "Training.text";

    public void save(TrainingResult result) {
        //研修結果を保存する。

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
        } catch (IOException e) {
            throw new IllegalStateException("研修結果の保存に失敗しました。", e);
        }
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
            //ｎ行目がおかしかった時エラー表示するためのカウンター
            int lineNumber = 0;
            while ((txt = br.readLine()) != null) {
                lineNumber++;//インクリメント
                //空白を削除して空行なら処理をスキップ
                if (txt.trim().isEmpty()) {
                    continue;
                }

                //カンマで分割して配列に格納
                String[] value = txt.split(",");

                //要素数が4でない場合はエラー処理としてストップ
                if (value.length != 4) {
                    throw new IllegalStateException(lineNumber + "行目の保存データの形が不正です。" + txt);
                }

                //scoreをintに変換するとき数字以外のものが入っていた場合エラーをキャッチする
                int score = 0;
                try {
                    score = Integer.parseInt(value[2].trim());

                } catch (NumberFormatException e) {
                    throw new IllegalStateException(lineNumber + "行目のデータが数字ではありません。" + txt, e);
                }
                //リストに格納
                //employeeをインスタンス化してIDと名前を渡す
                Employee emp = new Employee(value[0].trim(), value[1].trim());
                //empとスコアと合否をコンストラクタに渡してTrainingResluをインスタンス化
                TrainingResult result = new TrainingResult(emp, score, value[3].trim());//空白を消す意味はあるのだろうか？

                //インスタンスをリストに追加
                resultList.add(result);

            }

        } catch (IOException e) {
            throw new IllegalStateException("研修結果の読み込みに失敗しました", e);
        }
        //リストを返す	
        return resultList;
    }

}
/*正直この辺のエラーキャッチはこのコンソールシステムの書き込みで書き込まれているはずなのでここまで過剰にエラーキャッチしようとする意味はあるのか？みたいな感じはある
その場合はよそからテキストファイル持ってこれないようにしなければいけないのでは？ */

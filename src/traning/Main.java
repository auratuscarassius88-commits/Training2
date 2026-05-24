package traning;

import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            //入力用のスキャナーをインスタンス化
            Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);//文字化け対策
            
            System.out.println("社員ID、名前、点数の順に入力してください");
            System.out.println("社員ID");
            String emp_ID = sc.nextLine();
            System.out.println("社員名");
            String emp_name = sc.nextLine();
            System.out.println("点数");
            //文字列で来たらキャッチする必要性がある
            int score = Integer.parseInt(sc.nextLine());
            //empをインスタンス化
            Employee emp = new Employee(emp_ID, emp_name);

            TrainingService ts = new TrainingService();
            //入力を判定はTrainingServerへ
            // TrainingValidator tv = new TrainingValidator();
            //==trueを入れたほうが可読性高い？
            //TrainingServiceをつかってTrainingResultをインスタンス化。ID、名前、点数、合否が入っている。
            TrainingResult tr = ts.registerTrainingResult(emp, score);
            //保存するためにTrainingRepositoryをインスタンス化する
            TrainingRepository tres = new TrainingRepository();
            String save = tres.save(tr);

            System.out.println(save);
            //正常に登録されていたら今回の結果をプリント
            if (save.equals("書き込みが完了しました")) {
                System.out.println("社員ID:" + tr.getEmp().getEmp_ID());
                System.out.println("社員名:" + tr.getEmp().getEmp_name());
                System.out.println("点数:" + tr.getScore());
                System.out.println("判定:" + tr.getJudge());
            }

        } catch (InputMismatchException e) {
            System.out.println("点数には数値を入力してください");

        } catch (IllegalArgumentException e) {
            System.out.println("入力エラー: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("予期しないエラーが発生しました");

        } finally {
            System.out.println("システムを終了します。");
        }
    }
}

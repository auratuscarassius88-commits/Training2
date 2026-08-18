package traning;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean ran = true;
        
        //入力用のスキャナーをインスタンス化
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);//文字化け対策
        TrainingService service = new TrainingService();
        TrainingRepository repository = new TrainingRepository();
        //終了を選択しない限りループする仕様にする
        while(ran){
             try {

            
            

                System.out.println("入力方法を選択してください");
                System.out.println("1: 手入力");
                System.out.println("2: ファイル読み込み");
                System.out.println("3: 登録されている結果の表示");
                System.out.println("4: 終了");

                //分岐用の変数
                String mode = sc.nextLine();

                switch(mode){
                    case "1": inputOne(sc, service, repository);
                         break;
                    case "2": inputFile(sc, service, repository);
                         break;
                    case "3":printResultList(service);   
                        break;
                    case "4":ran = false;
                        break;
                    default:System.out.println("正しい番号を入力してください");
                        break;
                }
            
            
            } catch (NumberFormatException e) {
                System.out.println("点数には数値を入力してください");

            } catch (IllegalArgumentException e) {
                System.out.println("入力エラー: " + e.getMessage());
            //ファイルの読み込み失敗時エラーをキャッチできるようにする
            } catch (IOException e) {
                System.out.println("ファイルの読み込みに失敗しました。");
                System.out.println("ファイル名または配置場所を確認してください。");
            
            } catch (Exception e) {
                System.out.println("予期しないエラーが発生しました");
                //修復不可能なエラーなのでranを終了
                ran = false;
            }
        }
        System.out.println("プログラムを終了します。");
        
    }

    //単体読み込み用のメソッド ほかで使わないためprivateに

    private static void inputOne(Scanner sc, TrainingService service, TrainingRepository repository) {

        System.out.println("社員ID、名前、点数の順に入力してください");
        System.out.println("社員ID");
        String empId = sc.nextLine();
        System.out.println("社員名");
        String empName = sc.nextLine();
        System.out.println("点数");
        //文字列で来たらキャッチする必要性がある
        int score = Integer.parseInt(sc.nextLine());

        //empをインスタンス化
        Employee emp = new Employee(empId, empName);
        //保存処理に移行
        saveandprint(emp, score, service, repository);
    }

    //ファイル読み込み用
    private static void inputFile(Scanner sc, TrainingService service, TrainingRepository repository) throws IOException {

        System.out.println("読み込むファイル名を入力してください");
        //同じ階層にあることが前提？
        String fileName = sc.nextLine();

        try (BufferedReader br = Files.newBufferedReader(Path.of(fileName), StandardCharsets.UTF_8)) {
            //中身を入れる変数
            String line;
            //nullになるまで繰り返す
            while ((line = br.readLine()) != null) {
                //trimで空白を無くし空行であればスキップ
                if (line.trim().isEmpty()) {
                    continue;
                }

                try {
                    //カンマ区切りで配列に
                    String[] data = line.split(",");
                    //配列が3つ以外ならエラー処理する
                    if (data.length != 3) {
                        throw new IllegalArgumentException("ファイル形式に問題があります:" + line);
                    }
                    String empId = data[0].trim();
                    String empName = data[1].trim();
                    int score = Integer.parseInt(data[2].trim());
                    Employee emp = new Employee(empId, empName);

                    //All or Nothingにしたかったが、工数がかかるのでで1行づつ保存、結果表示
                    saveandprint(emp, score, service, repository);

                } catch (NumberFormatException e) {
                    System.out.println("この行は登録できませんでした: " + line);
                    System.out.println("理由: 点数には数値を入力してください");

                } catch (IllegalArgumentException e) {
                    System.out.println("この行は登録できませんでした: " + line);
                    System.out.println("理由: " + e.getMessage());
                }
            }
        }

    }

    private static void saveandprint(Employee emp, int score, TrainingService service, TrainingRepository repository) {

        TrainingResult result = service.registerTrainingResult(emp, score);

        repository.save(result);

        printResult(result);
        
    }

    private static void printResultList(TrainingService service){
        List<TrainingResult> resultList = service.getResultList();
        System.out.println("登録されてる結果を表示します。");
        if(resultList.isEmpty()){
            System.out.println("登録されている結果がありません");
        }else{
            for (TrainingResult result : resultList) {
                printResult(result);
            }
        }
            
    }
    private static void printResult(TrainingResult result) {
        System.out.println("社員ID:" + result.getEmp().getEmpId());
        System.out.println("社員名:" + result.getEmp().getEmpName());
        System.out.println("点数:" + result.getScore());
        System.out.println("判定:" + result.getJudge());
        System.out.println("--------------------");
    }

    
    
}

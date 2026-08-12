package traning;


public class TrainingResultCsvExport{

    public static void exportCsv(Scanner sc, TrainingService service)throws IOException{
        System.out.println("CSV出力する対象を選択してください");
        System.out.println("1: 全件");
        System.out.println("2: 不合格者");
        System.out.println("3: 社員IDで検索した結果");

        String mode = sc.nextLine();

        List<TrainingResult> exportList;

        switch(mode){
            cese "1":exportList = service.getResultList();
                    break;

            case "2":exportList = getFiledResultList(service);
                    break;
            
            case "3":exportList = getSearchResultList(service, sc);
                    break;
            
            default:System.out.println("選択肢の数字を選んでください。");
        }
        //書き込むコードを書く
    }

    public static List<TrainingResult> getFiledResultList(TrainingService service){
        //登録されている結果を取り出す
        List<TrainingResult> resultList = service.getResultList();
        List<TrainingResult> filedList = new ArrayList<>();

        //Mainのメソッド使った方が良くないか？
        for(TrainingResult result : resultList){
            if(!"合格".equals(resultList.getJudge())){
                filedList.add(result);
            }
            return filedList;
        }
    }

    public static List<TrainingResult> getSearchResultList(TrainingService service, Scanner sc){

        List<TrainingResult> result = service.getResultList();
        List<TrainingResult> searchList = new ArrayList<>();
        //検索ワードを入力
        System.out.println("検索したい社員IDを入力してください");
        String searchId = sc.nextLine();

        boolean found = false;
        for(TrainingResult result :resultList){
            if(searchId.equals(result.getEmp().getEmpId())){
                printResult(result);
                found = true;
                //IDが一意な為、見つけることができたら処理から脱出
                break; 
            }
        }
        if(found == false){
            System.out.println(searchId + "は存在しません");
        }
    }

}
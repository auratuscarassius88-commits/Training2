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
    }
}
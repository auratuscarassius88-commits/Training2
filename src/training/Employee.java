package training;

public class Employee {

    private String empId;
    private String empName;

    public Employee(String empId, String empName) {
        super();
        //三項演算子でnullかを判定してそうでない場合空白を削除
        this.empId = empId == null ? null : empId.strip();
        this.empName = empName == null ? null : empName.strip();
    }

    public String getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

}

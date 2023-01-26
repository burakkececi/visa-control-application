public class FinancialStatus {

    private String applicantId;
    private int income;
    private int savings;

    public FinancialStatus(String applicantId, int income, int savings) {
        this.applicantId = applicantId;
        this.income = income;
        this.savings = savings;
    }

    public FinancialStatus(FinancialStatus oldFinancialStatus){
        if(oldFinancialStatus == null){
            System.out.println("Fatal Error!");
            System.exit(0);
        }
        this.applicantId = oldFinancialStatus.applicantId;
        this.income = oldFinancialStatus.income;
        this.savings = oldFinancialStatus.savings;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getSavings() {
        return savings;
    }

    public void setSavings(int savings) {
        this.savings = savings;
    }
}

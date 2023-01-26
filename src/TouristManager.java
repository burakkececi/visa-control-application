import java.time.Period;

public class TouristManager extends ApplicantManager {

    Tourist tourist;
    Application application;

    TouristManager(Tourist tourist) {
        super(tourist);
        this.tourist = tourist;
        this.application = tourist.getApplication();
    }

    public boolean isFinancialStatusValid() {
        FinancialStatus financialStatus = application.getFinancialStatus();
        boolean flagIn = false;
        for (Document doc : application.getDocument()) {
            flagIn = doc.getDocumentType().equals("IL");
        }
        if (flagIn) {
            checkIL(application, financialStatus,
                    1000, 1500, 3000,
                    1500, 2000, 6000,
                    2000
            );
        } else {
            checkIL(application, financialStatus,
                    1000, 2000, 6000,
                    2000, 3000, 3000,
                    4000
            );
        }
        return flag;
    }

    private void checkIL(Application application, FinancialStatus financialStatus,
                         int incomeInt1, int incomeInt2, int saving1,
                         int incomeInt3, int incomeInt4, int saving2, int incomeLast
    ) {
        if (financialStatus.getIncome() < incomeInt1) {
            application.setApplicationStatus(false);
            application.setRejectedReason("Applicant income should be at least $2000");
            flag = false;
        }
        if (!((financialStatus.getIncome() >= incomeInt1
                && financialStatus.getIncome() < incomeInt2
                && financialStatus.getSavings() == saving1) ||
                (financialStatus.getIncome() >= incomeInt3
                        && financialStatus.getIncome() < incomeInt4
                        && financialStatus.getSavings() == saving2) ||
                (financialStatus.getIncome() >= incomeLast))
        ) {
            application.setApplicationStatus(false);
            application.setRejectedReason("Applicant financial status not satisfy conditions.");
            flag = false;
        }
    }

    public boolean isDocumentsValid() {
        /*
         *
         * null
         *
         */
        return true;
    }

    @Override
    public void calculateVisaDuration() {
        FinancialStatus financialStatus = tourist.getApplication().getFinancialStatus();


        int savings = financialStatus.getSavings(), income = financialStatus.getIncome();
        int period = calculatePassportExpirationDate(tourist.getApplication().getPassport());
        double DC = (((income - 2000) * 6) + savings) / 12000.0;
        for (Document doc : tourist.getApplication().getDocument()) {
            DC = doc.getDocumentType().equals("IL") ?
                    (((income - 2000) * 6) + savings) / 6000.0 : 0.0;
        }
        if (DC >= 1 && DC < 2 && period < 12) tourist.setVisaDuration("6 months");
        else if (DC >= 2 && DC < 4 && period < 12 * 5) tourist.setVisaDuration("1 year");
        else if (DC >= 4) tourist.setVisaDuration("5 year");
        else tourist.setVisaDuration(null);
    }
}

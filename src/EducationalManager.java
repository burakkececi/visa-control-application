import java.time.Period;
import java.util.ArrayList;

public class EducationalManager extends ApplicantManager {

    Educational educational;

    EducationalManager(Educational educational) {
        super(educational);
        this.educational = educational;
    }

    public boolean isFinancialStatusValid() {
        Application application = educational.getApplication();
        FinancialStatus financialStatus = application.getFinancialStatus();
        boolean flagIn = false;
        for (Document doc : application.getDocument()) {
            flagIn = doc.getDocumentType().equals("LA");
        }
        if (flagIn) {
            checkIL(application, financialStatus,
                    500, 1000, 3000,
                    1000, 1500, 1500,
                    1500
            );
        } else {
            checkIL(application, financialStatus,
                    1000, 2000, 6000,
                    2000, 3000, 3000,
                    3000
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
            application.setRejectedReason("Applicant income should be at least $1000");
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
        Application application = educational.getApplication();
        ArrayList<Document> documents = application.getDocument();
        boolean flagIn = false;
        for (Document doc : documents) {
            if (doc.getDocumentType().equals("LA")) {
                flagIn = true;
                break;
            }
        }
        if (!flagIn) {
            application.setApplicationStatus(false);
            application.setRejectedReason("Applicant does not have letter of acceptance");
            flag = false;
        }
        return flag;
    }

    @Override
    public void calculateVisaDuration() {

        int visaDuration = calculatePassportExpirationDate(educational.getApplication().getPassport());
        int durationInMonths = 0;
        for (Document doc : educational.getApplication().getDocument()) {
            if (doc.getDocumentType().equals("LA"))
                durationInMonths = doc.getDurationInMonths();
        }
        if (visaDuration < 12) {
            application.setApplicationStatus(false);
            application.setRejectedReason("Applicant passport expiration date not satisfy for visa");
        } else if (visaDuration < durationInMonths) {
            if (visaDuration / 12 == 1) educational.setVisaDuration("1 year");
            else if (visaDuration / 12 == 2) educational.setVisaDuration("2 year");
            else if (visaDuration / 12 >= 4) educational.setVisaDuration("4 year");
            else{
                application.setApplicationStatus(false);
                application.setRejectedReason("Applicant passport expiration date not satisfy for visa");
            }
        } else if (visaDuration > durationInMonths) {
            if (visaDuration / 12 == 1 && durationInMonths < 12) educational.setVisaDuration("1 year");
            else if (visaDuration / 12 == 2 && durationInMonths < 24) educational.setVisaDuration("2 year");
            else if (visaDuration / 12 >= 4 && durationInMonths < 48) educational.setVisaDuration("4 year");
            else{
                application.setApplicationStatus(false);
                application.setRejectedReason("Applicant passport expiration date not satisfy for visa");
            }
        }else {
            application.setApplicationStatus(false);
            application.setRejectedReason("Applicant passport expiration date not satisfy for visa");
        }

    }
}

import java.util.ArrayList;

public class WorkerManager extends ApplicantManager {

    Worker worker;

    WorkerManager(Worker worker) {
        super(worker);
        this.worker = worker;
    }

    public boolean isFinancialStatusValid() {
        Application application = worker.getApplication();
        FinancialStatus financialStatus = application.getFinancialStatus();

        if (financialStatus.getSavings() < 2000) {
            flag = false;
            application.setApplicationStatus(false);
            application.setRejectedReason("Applicant savings should be at least $2000");
        }
        return flag;
    }

    public boolean isDocumentsValid() {
        Application application = worker.getApplication();
        ArrayList<Document> document = application.getDocument();
        boolean flagIn = false;
        for (Document doc : application.getDocument()) {
            flagIn = doc.getDocumentType().equals("LA");
        }
        if (!flagIn) {
            flag = false;
            application.setApplicationStatus(false);
            application.setRejectedReason("Applicant does not have letter of acceptance");
        }
        return flag;
    }

    @Override
    public void calculateVisaDuration() {
        int visaDuration = calculatePassportExpirationDate(worker.getApplication().getPassport());
        int durationInMonths = 0;
        for (Document doc : worker.getApplication().getDocument()) {
            if (doc.getDocumentType().equals("LA"))
                durationInMonths = doc.getDurationInMonths();
        }
        if (visaDuration < 12) {
            application.setApplicationStatus(false);
            application.setRejectedReason("Applicant passport expiration date not satisfy for visa");
        } else if (visaDuration < durationInMonths) {
            if (visaDuration / 12 == 1) worker.setVisaDuration("1 year");
            else if (visaDuration / 12 == 2) worker.setVisaDuration("2 year");
            else if (visaDuration / 12 >= 5) worker.setVisaDuration("4 year");
            else{
                application.setApplicationStatus(false);
                application.setRejectedReason("Applicant passport expiration date not satisfy for visa");
            }
        } else if (visaDuration > durationInMonths) {
            if (visaDuration / 12 == 1 && durationInMonths < 12) worker.setVisaDuration("1 year");
            else if (visaDuration / 12 == 2 && durationInMonths < 24) worker.setVisaDuration("2 year");
            else if (visaDuration / 12 >= 5 && durationInMonths < 60) worker.setVisaDuration("5 year");
            else{
                application.setApplicationStatus(false);
                application.setRejectedReason("Applicant passport expiration date not satisfy for visa");
            }
        }else{
            application.setApplicationStatus(false);
            application.setRejectedReason("Applicant passport expiration date not satisfy for visa");
        }
    }
}

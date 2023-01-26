public class ImmigrantManager extends ApplicantManager {

    Immigrant immigrant;

    ImmigrantManager(Immigrant immigrant) {
        super(immigrant);
        this.immigrant = immigrant;
    }

    public boolean isFinancialStatusValid() {
        Application application = immigrant.getApplication();
        FinancialStatus financialStatus = application.getFinancialStatus();
        boolean flagIn = false;
        for (Document doc : application.getDocument()) {
            flagIn = doc.getDocumentType().equals("LA");
        }
        if (flagIn) {
            checkIL(application, financialStatus, 2000, 25000);
        } else {
            checkIL(application, financialStatus, 4000, 50000);
        }
        return flag;
    }

    private void checkIL(Application application, FinancialStatus financialStatus, int saving1, int saving2) {
        boolean flagIn= false;
        for (Document doc : application.getDocument()) {
            flagIn = doc.getDocumentType().equals("GC");
        }
        if(flagIn && financialStatus.getSavings() < saving1) {
            application.setApplicationStatus(false);
            application.setRejectedReason("Applicant savings should be at least $" + saving1);
            flag = false;
        } else if (financialStatus.getSavings() < saving2) {
            application.setApplicationStatus(false);
            application.setRejectedReason("Applicant savings should be at least $" + saving2);
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
        applicant.setVisaDuration("Permanent");
    }

}

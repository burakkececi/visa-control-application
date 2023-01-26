import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class VisaControlApp {
    public static void main(String[] args) {

        FileIO fileIO = new FileIO();
        ArrayList<Applicant> appl = fileIO.getApplicants("data/HW2_ApplicantsInfo.csv");
        Collections.sort(appl, new Comparator<Applicant>() {
            @Override
            public int compare(Applicant o1, Applicant o2) {
                int obj1 = Integer.parseInt(o1.getApplicantId());
                int obj2 = Integer.parseInt(o2.getApplicantId());
                return Integer.compare(obj1, obj2);
            }
        });
        ApplicantManager validateApplication = null;
        for (Applicant applicant : appl) {
            switch (applicant.getClass().toString().split(" ")[1]) {
                case "Tourist" -> validateApplication = new TouristManager((Tourist) applicant);
                case "Worker" -> validateApplication = new WorkerManager((Worker) applicant);
                case "Educational" -> validateApplication = new EducationalManager((Educational) applicant);
                case "Immigrant" -> validateApplication = new ImmigrantManager((Immigrant) applicant);
            }

            if ((validateApplication.checkDocumentsExist() && (
                    validateApplication.isPassportValid() &&
                            validateApplication.isPhotoValid() &&
                            validateApplication.isFinancialStatusValid() &&
                            validateApplication.isDocumentsValid()))) {
                validateApplication.calculateVisaDuration();

                if (applicant.getApplication().getApplicationStatus()) {
                    System.out.println("Applicant ID: " + applicant.getApplicantId() +
                            ", Name: " + applicant.getApplicantName() +
                            ", Visa Type: " + applicant.getClass().toString().split(" ")[1] +
                            ", Status: Accepted" +
                            ", Visa Duration: " + applicant.getVisaDuration());
                }
            } else {
                System.out.println("Applicant ID: " + applicant.getApplicantId() +
                        ", Name: " + applicant.getApplicantName() +
                        ", Visa Type: " + applicant.getClass().toString().split(" ")[1] +
                        ", Status: Rejected" +
                        ", Reason: " + applicant.getApplication().getRejectedReason().toString());
            }
        }
    }

}

public class Immigrant extends Applicant{
    public Immigrant(String applicantId, String applicantName) {
        super(applicantId, applicantName);
    }

    public Immigrant(String applicantId, String applicantName, Application application) {
        super(applicantId, applicantName, application);
    }
}

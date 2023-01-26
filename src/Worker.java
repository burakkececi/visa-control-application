public class Worker extends Applicant{
    public Worker(String applicantId, String applicantName) {
        super(applicantId, applicantName);
    }

    public Worker(String applicantId, String applicantName, Application application) {
        super(applicantId, applicantName, application);
    }
}

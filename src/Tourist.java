public class Tourist extends Applicant{
    public Tourist(String applicantId, String applicantName) {
        super(applicantId, applicantName);
    }

    public Tourist(String applicantId, String applicantName, Application application) {
        super(applicantId, applicantName, application);
    }
}

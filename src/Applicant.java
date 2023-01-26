public abstract class Applicant {
    private String applicantId;
    private String applicantName;
    private Application application;
    private String visaDuration;

    public Applicant(String applicantId, String applicantName) {
        this.applicantId = applicantId;
        this.applicantName = applicantName;
    }
    public Applicant(String applicantId, String applicantName, Application application) {
        this.applicantId = applicantId;
        this.applicantName = applicantName;
        this.application = application;

    }

    public String getApplicantId() {
        return applicantId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public Application getApplication() {
        return application;
    }

    public String getVisaDuration() {
        return visaDuration;
    }

    public void setVisaDuration(String visaDuration) {
        this.visaDuration = visaDuration;
    }
}

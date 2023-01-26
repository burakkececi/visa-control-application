public class Document {

    private String applicantId;
    private String documentType;
    private int durationInMonths;

    public Document(String applicantId, String documentType) {
        this(applicantId, documentType, 0);
    }
    public Document(String applicantId, String documentType, int durationInMonths) {
        this.applicantId = applicantId;
        this.documentType = documentType;
        this.durationInMonths = durationInMonths;
    }

    public Document(Document oldDocument){
        if(oldDocument == null){
            System.out.println("Fatal Error!");
            System.exit(0);
        }
        this.applicantId = oldDocument.applicantId;
        this.documentType = oldDocument.documentType;
        this.durationInMonths = oldDocument.durationInMonths;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public String getDocumentType() {
        return documentType;
    }
    public int getDurationInMonths() {
        return durationInMonths;
    }

}

import java.util.ArrayList;

public class Application {

    private String applicationId;
    private Passport passport;
    private Photo photo;
    private FinancialStatus financialStatus;
    private ArrayList<Document> documents;
    private boolean applicationStatus;
    private ArrayList<String> rejectedReason; // may be null

    public Application(String applicationId){
        this.applicationId = applicationId;
        this.applicationStatus = true;
        this.rejectedReason = new ArrayList<>();
        this.documents = new ArrayList<>();
    }

    public Application(String applicationId, Passport passport, Photo photo, FinancialStatus financialStatus) {
        this.applicationId = applicationId;
        this.passport = new Passport(passport);
        this.photo = new Photo(photo);
        this.financialStatus = new FinancialStatus(financialStatus);
        this.documents = new ArrayList<>();
        this.applicationStatus = true;
        this.rejectedReason = new ArrayList<>();
    }
    public Application(Application oldApplication){
        if(oldApplication == null){
            System.out.println("Fatal Error!");
            System.exit(0);
        }
        this.applicationId = oldApplication.applicationId;
        this.passport = new Passport(oldApplication.passport);
        this.photo = new Photo(oldApplication.photo);
        this.financialStatus = new FinancialStatus(oldApplication.financialStatus);
        this.documents = new ArrayList<>(oldApplication.documents);
        this.applicationStatus = oldApplication.applicationStatus;
        this.rejectedReason = oldApplication.rejectedReason;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public FinancialStatus getFinancialStatus() {
        return financialStatus;
    }

    public void setFinancialStatus(FinancialStatus financialStatus) {
        this.financialStatus = financialStatus;
    }

    public ArrayList<Document> getDocument() {
        return documents;
    }

    public void setDocument(Document document) {
        this.documents.add(document);
    }
    public boolean getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(boolean applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public ArrayList<String> getRejectedReason() {
        return rejectedReason;
    }

    public void setRejectedReason(String rejectedReason) {
        this.rejectedReason.add(rejectedReason);
    }
}

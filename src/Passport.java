import java.time.LocalDate;
public class Passport {
    private String applicantId;
    private String passportNumber;
    private LocalDate expirationDate;

    public Passport(String applicantId, String passportNumber, LocalDate expirationDate) {
        this.applicantId = applicantId;
        this.passportNumber = passportNumber;
        this.expirationDate = expirationDate;
    }

    public Passport(Passport oldPassport){
        if(oldPassport == null){
            System.out.println("Fatal Error!");
            System.exit(0);
        }
        this.applicantId = oldPassport.applicantId;
        this.passportNumber = oldPassport.passportNumber;
        this.expirationDate = oldPassport.expirationDate;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}

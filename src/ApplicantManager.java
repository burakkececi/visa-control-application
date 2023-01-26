import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ApplicantManager {

    Applicant applicant;
    Application application;
    boolean flag;

    ApplicantManager(Applicant applicant) {
        this.applicant = applicant;
        this.application = applicant.getApplication();
        flag = true;
    }

    public boolean checkDocumentsExist() {


        if (application.getPassport() == null) {
            application.setRejectedReason("Applicant does not have a passport.");
            application.setApplicationStatus(false);
            flag = false;
        }
        if (application.getPhoto() == null) {
            application.setRejectedReason("Applicant does not have a photo.");
            application.setApplicationStatus(false);
            flag = false;
        }
        if (application.getFinancialStatus() == null) {
            application.setRejectedReason("Applicant does not have a financial status.");
            application.setApplicationStatus(false);
            flag = false;
        }
        /*if ((applicant.getClass().toString().split(" ")[1].equals("Worker") ||
                applicant.getClass().toString().split(" ")[1].equals("Educational")) &&
                        application.getDocument().size() == 0) {
            application.setRejectedReason("Applicant does not have a document.");
            application.setApplicationStatus(false);
            flag = false;
        }*/
        return flag;
    }

    public boolean isPassportValid() {
        Passport passport = application.getPassport();

        String regex = "^P......[0-9][0-9][0-9]$";
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(passport.getPassportNumber());
        int period = calculatePassportExpirationDate(passport);
        if (!m.matches()) {
            application.setRejectedReason("Passport is not valid.");
            application.setApplicationStatus(false);
            flag = false;
        }
        if (period < 6) {
            application.setRejectedReason("Expiration date of the passport is not valid.");
            application.setApplicationStatus(false);
            flag = false;
        }
        return flag;
    }

    protected int calculatePassportExpirationDate(Passport passport) {
         Period period = Period.between(LocalDate.now(), passport.getExpirationDate());
        return (period.getMonths() + (period.getYears() * 12));
    }

    public boolean isPhotoValid() {

        Photo photo = application.getPhoto();

        String validPosition1 = "Neutral Face", validPosition2 = "Natural Smile";
        int validResolution1 = 600, validResolution2 = 1200;
        String[] resolution = photo.getResolution().split("x");
        String position = photo.getPosition();

        if (!(Integer.parseInt(resolution[0]) >= validResolution1 &&
                Integer.parseInt(resolution[1]) <= validResolution2 &&
                Integer.parseInt(resolution[0]) == Integer.parseInt(resolution[1]))) {
            application.setRejectedReason("Resulation of the photo is not valid.");
            application.setApplicationStatus(false);
            flag = false;
        }
        if (!(position.equals(validPosition1) || position.equals(validPosition2))) {
            application.setRejectedReason("Position in the photo is not valid.");
            application.setApplicationStatus(false);
            flag = false;
        }
        return flag;
    }


    public abstract boolean isFinancialStatusValid();

    public abstract boolean isDocumentsValid();

    public abstract void calculateVisaDuration();
}

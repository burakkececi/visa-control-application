import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

    private enum Columns {A, S, P, F, D}

    private ArrayList<Applicant> applicants;

    public ArrayList<Applicant> getApplicants(String datapath) {
        readApplicant(datapath);
        readApplications(datapath);
        return applicants;
    }

    private void readApplicant(String datapath) {
        try {
            Scanner inFile = new Scanner(new File(datapath));
            String[] data;
            applicants = new ArrayList<>();
            while (inFile.hasNextLine()) {
                data = inFile.nextLine().split(",");
                if (data[0].equals("A")) {
                    switch (data[1].substring(0,2)){
                        case "11" -> applicants.add(new Tourist(data[1], data[2], new Application(data[1])));
                        case "23" -> applicants.add(new Worker(data[1], data[2], new Application(data[1])));
                        case "25" -> applicants.add(new Educational(data[1], data[2], new Application(data[1])));
                        case "30" -> applicants.add(new Immigrant(data[1], data[2], new Application(data[1])));
                    }

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
            System.exit(0);
        }
    }

    private void readApplications(String datapath) {

        try {
            Scanner inFile = new Scanner(new File(datapath));
            String[] data;

            while (inFile.hasNextLine()) {
                Application application = null;
                data = inFile.nextLine().split(",");
                for (Applicant applicant : applicants) {
                    if (data[1].equals(applicant.getApplicantId()))
                        application = applicant.getApplication();
                }
                Columns column = Columns.valueOf(data[0]);
                if (application != null) {
                    switch (column) {
                        case S -> {
                            String [] tmp = data[3].split("-");
                            application.setPassport(new Passport(data[1], data[2], LocalDate.of(Integer.parseInt(tmp[0]),
                                                                                                Integer.parseInt(tmp[1]),
                                                                                                Integer.parseInt(tmp[2]))));
                        }
                        case P -> application.setPhoto(new Photo(data[1], data[2], data[3]));
                        case F -> application.setFinancialStatus(new FinancialStatus(data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3])));
                        case D -> application.setDocument(new Document(data[1], data[2], data.length == 4 ? Integer.parseInt(data[3]) : 0));
                        default -> {
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
            System.exit(0);
        }catch (NumberFormatException e){
            System.out.println("Not valid records!");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
       /* ArrayList<Applicant> appl = FileIO.getApplicants("data/HW2_ApplicantsInfo.csv");
        ApplicantManager validateApplication = new ApplicantManager();
        for (Applicant applicant : appl) {
            validateApplication.checkApplicationDocuments(applicant);
            if(applicant.getApplication().getApplicationStatus()){
                System.out.println(applicant.getApplication().getPassport().getPassportNumber());
                System.out.println("----------");}
            else{
                System.out.println(applicant.getApplication().getRejectedReason().toString());
                System.out.println("-------");
        }
        }*/
    }
}

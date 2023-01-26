public class Photo {

    private String applicantId;
    private String resolution;
    private String position;

    public Photo(String applicantId, String resolution, String position) {
        this.applicantId = applicantId;
        this.resolution = resolution;
        this.position = position;
    }

    public Photo(Photo oldPhoto){
        if(oldPhoto == null){
            System.out.println("Fatal Error!");
            System.exit(0);
        }
        this.applicantId = oldPhoto.applicantId;
        this.resolution = oldPhoto.resolution;
        this.position = oldPhoto.position;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

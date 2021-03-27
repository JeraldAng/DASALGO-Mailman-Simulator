package model;

public class Mail {
    private String location;
    private String school;

    public Mail(String location, String school) {
        this.location = location;
        this.school = school;

    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }

}

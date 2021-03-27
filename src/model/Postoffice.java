package model;

public class Postoffice
{
    private String location;
    //has the properties of the postoffice location and changes postoffice location accordingly

    public Postoffice(String location)
    {
        String spost[] = location.split(" ");
        spost[0] = Character.toUpperCase(spost[0].charAt(0)) + spost[0].substring(1).toLowerCase();
        spost[1] = Character.toUpperCase(spost[1].charAt(0)) + spost[1].substring(1).toLowerCase();
        spost[2] = Character.toUpperCase(spost[2].charAt(0)) + spost[2].substring(1).toLowerCase();
        spost[3] = Character.toUpperCase(spost[3].charAt(0)) + spost[3].substring(1).toLowerCase();

        changepostoffice(spost[0] + " " + spost[1] + " " + spost[2] + " " + spost[3]);
    }

    public void changepostoffice(String location)
    {
        this.location = location;
    }

    public Mail givemail(String location, String school) {
        return new Mail(location+ " Post Office", school);
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location) {this.location = location;}
}

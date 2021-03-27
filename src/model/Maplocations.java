package model;

public class Maplocations
{
    private String postoffice;
    private String location;
    private String destination;
    private float distance;

    public Maplocations (String postoffice, String location, String destination, float distance)
    {
        this.postoffice = postoffice;
        this.location = location;
        this.destination = destination;
        this.distance = distance;
    }

    public String getPostoffice()
    {
        return postoffice;
    }

    public void setPostoffice(String postoffice)
    {
        this.postoffice = postoffice;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getDestination()
    {
        return destination;
    }

    public void setDestination(String destination)
    {
        this.destination = destination;
    }

    public float getDistance()
    {
        return distance;
    }

    public void setDistance(float distance)
    {
        this.distance = distance;
    }
}

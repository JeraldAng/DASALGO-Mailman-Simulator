package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mailman
{
    private Postoffice post;
    Scanner scn = new Scanner(System.in);
    private ArrayList<String> locs = new ArrayList<String>(4);
    private ArrayList<Maplocations> mapl = new ArrayList<Maplocations>();
    private int lastindex;
    private ArrayList<Mail> mail = new ArrayList<Mail>(500);
    private int repeat = 0;
    private String startingloc, destinloc;
    private float totdist = 0;
    private float dist;
    private String MapFile = "";
    private ArrayList<String> sentmails = new ArrayList<String>(4);

    public Mailman()
    {
        ArrayList<Maplocations> map2 = new ArrayList<Maplocations>(100);
        readMap();
    }

    public void readMap ()
    {
        String csvfile = MapFile;
        System.out.println(MapFile);
        String line = "";
        String csvSplit = ",";
        if (!MapFile.equals(""))
            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvfile), "ISO-8859-1"));
                int count = 0;

                while ((line = br.readLine()) != null)
                {
                    int e = 0;
                    List<String> mapdetails = model.CSVUtils.parseLine(line);

                    if (count != 0)
                    {
                        mapl.add(new Maplocations(mapdetails.get(0), mapdetails.get(1), mapdetails.get(2), Float.valueOf(mapdetails.get(3))));
                    }
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void addmail(String address)
    {
        totdist = 0;
        int found = 0;
        int ind = 0, schl = 0;
        String school = address;
        for (int i = 0; i < mapl.size(); i++) {

            if (school.equals(mapl.get(i).getLocation())) {
                found = 1;
                ind = i;
                schl = 1;

            }
            else if (school.equals(mapl.get(i).getDestination())) {

                found = 1;
                ind = i;
                schl = 2;

            }
        }
        if (found == 1) {
            if (schl == 1)
                mail.add(post.givemail(mapl.get(ind).getPostoffice(), mapl.get(ind).getLocation()));
            else if (schl == 2)
                mail.add(post.givemail(mapl.get(ind).getPostoffice(), mapl.get(ind).getDestination()));

        }
    }

    public void prim() {
        int count = 0;

        for (int i = 0; i < mapl.size(); i++) {
            for (int j = 0; j < mail.size(); j++) {
                if (mail.get(j).getLocation().equals(post.getLocation()) && mapl.get(i).getLocation().equals(startingloc) && mapl.get(i).getDestination().equals(mail.get(j).getSchool())) {
                    if (count == 0) {
                        count++;
                        dist = mapl.get(i).getDistance();
                        destinloc = mail.get(j).getSchool();
                    }
                    else {
                        if (dist > mapl.get(i).getDistance()) {
                            dist = mapl.get(i).getDistance();
                            destinloc = mail.get(j).getSchool();
                        }
                    }
                }
            }
        }
        System.out.println("Priority from: " + startingloc + " to " + destinloc + " with a distance of " + dist);
    }

    public int searchschool(String school)
    {
        for (int i = 0; i < mapl.size(); i++)
        {
            if (mapl.get(i).getDestination().equals(school))
            {
                return i;
            }
        }
        return -1;
    }

    public void sendmail() {
        sentmails.clear();
        boolean exit = false;
        while (exit == false) {
            int found = 0;
            int sub = 0;

            for (int i = 0; i < mail.size(); i++) {
                if (post.getLocation().equals(mail.get(i).getLocation())) {
                    found = 1;
                }
            }
            if (found == 1) {
                prim();
                for (int i = 0; i < mail.size(); i++) {
                    if (post.getLocation().equalsIgnoreCase(mail.get(i - sub).getLocation()) || destinloc.contains(mail.get(i - sub).getSchool())) {
                        sentmails.add(mail.get(i-sub).getSchool());
                        mail.remove(i - sub);
                        sub++;
                    }
                }
                    totdist = totdist + dist;
                    System.out.println(sub + " mail(s) have been sent to " + destinloc + " at a distance of " + dist + " from " + startingloc);
                    System.out.printf("Total distance covered %.1f\n", totdist);
                    startingloc = destinloc;
            }
            else {
                if (mail.size() != 0) {
                    post.setLocation(mail.get(0).getLocation());
                    startingloc = mail.get(0).getLocation();
                    exit = true;
                }
                else if (mail.size() == 0)
                    exit = true;
            }
        }
    }

    public int hugeplace() {
        int hug = 0, count = 1;
        int div = 1;
        //change these to the first and last index of the place
        /*for (int i = 0; i < mail.size(); i++) {

            for (int j = i + 1; j < mail.size(); j++) {
                int num1 = distanceconv(mail.get(i).getDistance() * 100);
                int num2 = distanceconv(mail.get(j).getDistance() * 100);

                if (num1 < num2) {
                    hug = num2;
                }
                else {
                    hug = num1;
                }
            }
        }*/

        while (div <= hug) {
            count++;
            div = div * 10;
        }
        count--;
        return count;
    }

    public int distanceconv(float dist)
    {
        int count = 0;

        while (dist >= 1)
        {
            count++;
            dist--;
        }
        return count;
    }

    public int OnlyDifferentPosts ()
    {
        for (int diff = 0; diff < mail.size(); diff++)
            if (mail.get(diff).getLocation().equals(post.getLocation()))
                return 0;

        return 1;
    }

    public Postoffice getPostOffice ()
    {
        return post;
    }

    public void setPostOffice (Postoffice newpost)
    {
        this.post = newpost;
    }

    public ArrayList<Maplocations> getMap ()
    {
        return mapl;
    }

    public ArrayList<Mail> getMails () { return mail;}

    public ArrayList<String> getLocs () {return locs;}

    public void setStartingLoc (String startingloc) {this.startingloc = startingloc;}

    public ArrayList<String> getSentMails () {return sentmails;}

    public String getStartingLoc () {return startingloc;}

    public void setMapFile (String MapFile) {this.MapFile = MapFile;}

    public String getMapFile () {return MapFile;}

    }

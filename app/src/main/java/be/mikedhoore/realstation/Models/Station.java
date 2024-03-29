package be.mikedhoore.realstation.Models;

import java.io.Serializable;

public class Station implements Serializable {

    private int id;
    private double locationX;
    private double locationY;
    private String nmbsId;
    private String name;

    public Station(double locationX, double locationY, String nmbsId, String name) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.nmbsId = nmbsId;
        this.name = name;
    }

    public Station(int id, double locationX, double locationY, String nmbsId, String name) {
        this.id = id;
        this.locationX = locationX;
        this.locationY = locationY;
        this.nmbsId = nmbsId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public double getLocationX() {
        return locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public String getNmbsId() {
        return nmbsId;
    }

    public String getName() {
        return name;
    }
}

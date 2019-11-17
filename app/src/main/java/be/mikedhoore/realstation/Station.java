package be.mikedhoore.realstation;

public class Station {

    int id;
    double locationX;
    double locationY;
    String nmbsId;
    String name;

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
}

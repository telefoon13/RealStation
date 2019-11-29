package be.mikedhoore.realstation.Models;

import java.io.Serializable;

public class BoardItem implements Serializable {

    private int delay;
    private Station endStation;
    private int time;
    private String vehicle;
    private int track;
    private int canceld;

    public BoardItem(int delay, Station endStation, int time, String vehicle, int track, int canceld) {
        this.delay = delay;
        this.endStation = endStation;
        this.time = time;
        this.vehicle = vehicle;
        this.track = track;
        this.canceld = canceld;
    }

    public int getDelay() {
        return delay;
    }

    public Station getEndStation() {
        return endStation;
    }

    public int getTime() {
        return time;
    }

    public String getVehicle() {
        return vehicle;
    }

    public int getTrack() {
        return track;
    }

    public int getCanceld() {
        return canceld;
    }
}

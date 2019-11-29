package be.mikedhoore.realstation.Models;

import java.io.Serializable;

public class BoardItem implements Serializable {

    private String delay;
    private String endStation;
    private String time;
    private String vehicle;
    private String track;
    private String canceled;

    public BoardItem(String delay, String endStation, String time, String vehicle, String track, String canceled) {
        this.delay = delay;
        this.endStation = endStation;
        this.time = time;
        this.vehicle = vehicle;
        this.track = track;
        this.canceled = canceled;
    }

    public String getDelay() {
        return delay;
    }

    public String getEndStation() {
        return endStation;
    }

    public String getTime() {
        return time;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getTrack() {
        return track;
    }

    public String getCanceled() {
        return canceled;
    }
}

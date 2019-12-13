package be.mikedhoore.realstation.Models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StationTest {

    Station testStation;

    @Before
    public void setUp() throws Exception {
        testStation = new Station(3.5,4.5,"NMBSID","Temse");
    }

    @Test
    public void getId() {
        assertEquals("Id is not set so it's 0",0,testStation.getId());
    }

    @Test
    public void getLocationX() {
        double delta = .1;
        assertEquals("Location X is set properly",3.5,testStation.getLocationX(),delta);
    }

    @Test
    public void getLocationY() {
        double delta = .1;
        assertEquals("Location X is set properly",3.5,testStation.getLocationX(),delta);
    }

    @Test
    public void getNmbsId() {
        assertEquals("Id is null","NMBSID",testStation.getNmbsId());
    }

    @Test
    public void getName() {
        assertEquals("Id is null","Temse",testStation.getName());
    }
}
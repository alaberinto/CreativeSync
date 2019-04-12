/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.DBException;
import dataaccess.LocationBroker;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Location;

/**
 * Constructor for LocationService which gives the LocationBroker object
 * 
 * @author Mason Hill
 * @author Alvin Laberinto
 * @author Cooper Vasiliou
 * @author Arsal Butt
 * @author Brittany Low
 * @author Matthew Carmichael
 * @author Omurbek Kadyrov
 */
public class LocationService {
    
    private final LocationBroker lb;
    
    /**
     * Non-default constructor that instantiates a LocationBroker.
     */
    public LocationService() {
        lb = new LocationBroker();
    }
    
    /**
     * Access method to retrieve all locations.
     * 
     * @return an ArrayList object of Location objects.
     */
    public ArrayList<Location> getAllLocations() {
        try {
            return lb.getAllLocations();
        } catch (DBException ex) {
            Logger.getLogger(LocationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}

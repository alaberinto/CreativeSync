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
 *
 * @author Mason
 */
public class LocationService {
    
    private final LocationBroker lb;
    
    /**
     * Constructor that instantiates a LocationBroker.
     */
    public LocationService() {
        lb = new LocationBroker();
    }
    
    /**
     * Gets all locations.
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.DBException;
import dataaccess.PositionBroker;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Account;
import models.Position;

/**
 *
 * @author Mason
 */
public class PositionService {

    private final PositionBroker pb;

    public PositionService() {
        pb = new PositionBroker();
    }

    public ArrayList<Position> getAllPositions() {
        try {
            return pb.getAllPositions();
        } catch (DBException ex) {
            Logger.getLogger(PositionService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public ArrayList<Position> getCreatablePositions(Account user) {
        ArrayList<Position> pos;
        try {
            pos = pb.getAllPositions();
        } catch (DBException ex) {
            Logger.getLogger(PositionService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        if(user.getPosition().getPositionId() == 1) {
            pos.remove(0);
        }
        else {
            pos.remove(0);
            pos.remove(0);
        }
        
        return pos;
    }
}

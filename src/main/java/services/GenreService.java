/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.DBException;
import dataaccess.GenreBroker;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Genre;

/**
 *
 * @author Mason
 */
public class GenreService {
    private final GenreBroker gb;

    public GenreService() {
        gb = new GenreBroker();
    }
    
    public ArrayList<Genre> getAllGenres() {
        try {
            return new ArrayList(gb.getAllGenres());
        } catch (DBException ex) {
            Logger.getLogger(GenreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

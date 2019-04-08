package services;

import dataaccess.DBException;
import dataaccess.GenreBroker;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Genre;

/**
 * GenreService is a service class to process requests to access or mutate Genre information.
 * 
 * @author Mason Hill
 * @version 1.0
 */
public class GenreService {

    /**
     * Final GenreBroker to handle all Genre access to information in the database.
     */
    private final GenreBroker gb;

    /**
     * Default constructor to create a new instance of GenreService and create the GenreBroker.
     */
    public GenreService() {
        gb = new GenreBroker();
    }

    /**
     * Access method to retrieve an ArrayList of all Genres.
     * @return The ArrayList of Genres in the database.
     */
    public ArrayList<Genre> getAllGenres() {
        return new ArrayList(gb.getAllGenres());
    }
    
    public Genre getGenreById(String id) {
        try {
            return gb.getGenreById(Integer.parseInt(id));
        } catch (DBException ex) {
            Logger.getLogger(GenreService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
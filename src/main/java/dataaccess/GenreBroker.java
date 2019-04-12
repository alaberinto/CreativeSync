package dataaccess;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import models.Genre;

/**
 * GenreBroker is a data-access class to retrieve Genre information from the
 * database.
 *
 * @author Mason Hill
 * @version 1.0
 */
public class GenreBroker {

    /**
     * Access method to retrieve a Collection of all Genre objects currently in
     * the database.
     *
     * @return A collection of Genres.
     */
    public ArrayList<Genre> getAllGenres() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Genre> genres = em.createNamedQuery("Genre.findAll", Genre.class).getResultList();
            return new ArrayList(genres);
        } catch (Exception ex) {
            Logger.getLogger(Genre.class.getName()).log(Level.SEVERE, "Cannot read genres", ex);
        } finally {
            em.close();
        }
        return null;
    }

    /**
     * Access method to retrieve a genre by its matching ID.
     * 
     * @param id the id of the genre.
     * @return a Genre object holding this type of information.
     * @throws DBException if the genre could not be retrieved from the database.
     */
    public Genre getGenreById(Integer id) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Genre genre = em.find(Genre.class, id);
            return genre;

        } finally {
            em.close();
        }

    }
}

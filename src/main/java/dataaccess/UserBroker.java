package dataaccess;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Account;

/**
 * UserBroker is a data-access class to manage User information.
 *
 * @author Mason Hill
 * @author Alvin Laberinto
 * @author Cooper Vasiliou
 * @author Arsal Butt
 * @author Brittany Low
 * @author Matthew Carmichael
 * @author Omurbek Kadyrov
 * @version 1.0
 */
public class UserBroker {

    /**
     * Access method to retrieve an Account by their email.
     *
     * @param email The email of the Account to find.
     * @return The Account with the matching email, null if an error occurs or
     * user is not found.
     * @throws DBException When a database error occurs.
     */
    public Account getUserByEmail(String email) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Query query = em.createNamedQuery("Account.findByEmail", Account.class);
            query.setParameter("email", email);

            List<Account> users = query.getResultList();
            return users.get(0);
        } catch (Exception ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, "Cannot read user", ex);
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * Access method to retrieve an Account by their ID.
     *
     * @param userId The ID of the Account to find.
     * @return The Account with the matching ID.
     * @throws DBException When there is an Error getting this Account.
     */
    public Account getUserById(int userId) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Account user = em.find(Account.class, userId);
            return user;

        } finally {
            em.close();
        }
    }

    /**
     * Access method to retrieve a user by their first name.
     * 
     * @param firstname The first name of the Account to find.
     * @return The account with the matching ID.
     * @throws DBException When there is an error getting this Account.
     */
    public Account getUserByFirstname(String firstname) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Query query = em.createNamedQuery("Account.findByFirstname", Account.class);
            query.setParameter("firstname", firstname);

            List<Account> users = query.getResultList();
            return users.get(0);
        } catch (Exception ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, "Cannot read user", ex);
            throw new DBException("Error getting user.");
        } finally {
            em.close();
        }
    }

    /**
     * Mutator method to persist any changes to a Account in the database.
     *
     * @param ac The account to update.
     * @throws DBException When there is a database error.
     */
    public void update(Account ac) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(ac);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
        } finally {
            em.close();
        }
    }

    /**
     * Access method to retrieve all Accounts from the database.
     *
     * @return List of all Users if successful, null if a database error occurs,
     */
    public ArrayList<Account> getAllUsers() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Account> users = em.createNamedQuery("Account.findAll", Account.class).getResultList();
            return new ArrayList(users);
        } catch (Exception ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            return null;
        }
    }

    /**
     * Mutator method to remove a specified Account from the database.
     *
     * @param ac The Account to remove.
     * @throws DBException When a database error occurs.
     */
    public void deleteUser(Account ac) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.remove(em.merge(ac));
            trans.commit();
        } catch (Exception ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, "Cannot read users", ex);

        } finally {
            em.close();
        }
    }

    /**
     * Mutator method to insert a specific Account into the database.
     *
     * @param ac The account to insert.
     * @throws DBException When a database error occurs.
     */
    public void insertUser(Account ac) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(ac);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
        } finally {
            em.close();
        }
    }
}

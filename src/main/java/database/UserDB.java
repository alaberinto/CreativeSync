package database;

//package dataaccess;
//
//import dataaccess.DBException;
//import dataaccess.DBUtil;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
//import models.User;
//
///**
// *
// * @author 731866
// */
//public class UserDB {
//    public int insert(User user) throws DBException {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        EntityTransaction trans = em.getTransaction();
//        try {
//            trans.begin();
//            em.persist(user);
//            trans.commit();
//        } catch (Exception e) {
//            if (trans.isActive()) {
//                trans.rollback();
//            }
//
//        } finally {
//            em.close();
//        }
//        return 1;
//    }
//
//    public int update(User user) throws DBException {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        EntityTransaction trans = em.getTransaction();
//        trans.begin();
//        try {
//            em.merge(user);
//            trans.commit();
//        } catch (Exception e) {
//            System.out.println(e);
//            trans.rollback();
//        } finally {
//            em.close();
//        }
//        return 1;
//    }
//
//    public List<User> getAll() throws DBException {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        try {
//            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
//            return users;
//        } finally {
//            em.close();
//        }
//    }
//
//    public User getUser(String email) throws DBException {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        try {
//            User user = em.find(User.class, email
//            );
//            return user;
//        } finally {
//            em.close();
//        }
//    }
//
//    public int delete(User user) throws DBException {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        EntityTransaction trans = em.getTransaction();
//        trans.begin();
//        try {
//            em.remove(em.merge(user));
//            trans.commit();
//            
//        } catch (Exception e) {
//            System.out.println(e);
//            trans.rollback();
//        } finally {
//            em.close();
//        }
//        return 1;
//    }
//}

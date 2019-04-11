package listeners;

import dataaccess.DBUtil;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import models.Account;

/**
 * 
 * @author Mason
 */
public class SessionInspectorListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {       
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Account user = (Account)se.getSession().getAttribute("user");
        EntityManager em = DBUtil.getEmFactory().createEntityManager();        
        em.getTransaction().begin();
        int updateVal = em.createNamedQuery("Account.activeUser", Account.class)
                    .setParameter("isactive", 1)
                    .setParameter("userId",user.getUserId())
                    .executeUpdate();        
        em.getTransaction().commit();
    }    
}

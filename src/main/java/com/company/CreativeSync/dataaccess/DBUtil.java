/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.CreativeSync.dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author 731866
 */
public class DBUtil {
     private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("NetflixDB");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
    
}

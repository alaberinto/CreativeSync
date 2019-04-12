/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.ArtworkBroker;
import dataaccess.DBException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Artwork;

/**
  * AccountService is a service class to process requests to access or mutate
 * Artwork information.
 * 
 * @author Matthew
 */
public class ArtworkService {

    private ArtworkBroker ab;

    /**
     * Non-default constructor that instantiates the ArtworkBroker.
     */
    public ArtworkService() {
        ab = new ArtworkBroker();
    }

    /**
     * Access method to retrieve a list of all artworks.
     * 
     * @return an ArrayList object of all Artwork objects.
     * @throws DBException if there was a problem retrieving Artworks from the database.
     */
    public ArrayList<Artwork> getAllArtwork() throws DBException {
        return new ArrayList(ab.getAllArtwork());
    }

    /**
     * Access method to retrieve a list of all artworks associated with a title.
     * 
     * @param id the id of the Title whose Artworks we want.
     * @return a List object of all Artworks linked to the specified Title.
     * @throws DBException if there was a problem retrieving Artworks from the database.
     */
    public List<Artwork> getAllArtworkByTitleId(int id) throws DBException {

        List<Artwork> arts = ab.getAllArtwork();
        List<Artwork> artsSpecific = new ArrayList();

        int title_id;

        for (int i = 0; i < arts.size(); i++) {

            title_id = arts.get(i).getTitleHasAccount().getTitle().getTitleId();

            if (title_id == id) {
                artsSpecific.add(arts.get(i));
            }
        }

        return artsSpecific;
    }

    /**
     * Method to find the maximum round of a given title.
     * 
     * @param id_t the id of a title.
     * @return the integer value of the maximum round.
     * @throws DBException if there was a problem retrieving the round number from the database.
     */
    public int findMaxRound(int id_t) throws DBException {

        List<Artwork> arts = (List<Artwork>) getAllArtworkByTitleId(id_t);

        int roundId;
        int maxRound = 1;

        for (int i = 0; i < arts.size(); i++) {

            roundId = arts.get(i).getRound();

            if (roundId > maxRound) {
                maxRound = roundId;
            }
        }

        return maxRound;
    }

    public List<Artwork> getAllArtworkByRound(int id_t, int id_r) throws DBException {

        List<Artwork> arts = getAllArtworkByTitleId(id_t);
        List<Artwork> artsSpecific = new ArrayList();

        int round_id;

        for (int i = 0; i < arts.size(); i++) {

            round_id = arts.get(i).getRound();

            if (round_id == id_r) {
                artsSpecific.add(arts.get(i));
            }
        }

        return artsSpecific;
    }

    //This method does not get all rounds with all information, only the number of rounds in the correct order for a specific title. The other method above will get artwork for any round properly, only for a round.
    public List<Artwork> getAllRounds(int id_t) throws DBException {

        List<Artwork> arts = getAllArtworkByTitleId(id_t);

        if (arts.isEmpty()) {
            return arts;
        }

        List<Artwork> artsSpecific;

        int roundId;
        int maxRound = 0;

        //find max round
        for (int i = 0; i < arts.size(); i++) {

            roundId = arts.get(i).getRound();

            if (roundId > maxRound) {
                maxRound = roundId;
            }

        }

        artsSpecific = new ArrayList(maxRound);

        //add highest round artwork to new sorted list
        for (int i = 0; i < arts.size(); i++) {

            roundId = arts.get(i).getRound();

            if (roundId == maxRound) { //ignore second if the same, stop loop when it finds what it needs (the max round)
                artsSpecific.add(arts.get(i));
                break;
            }
        }

        //because if there is only one round, there is no need to look for other rounds so just return it here
        if (maxRound == 1) {
            return artsSpecific;
        }

        //add lower rounds artwork to new sorted list, when there is more than one artwork in the round
        for (int i = maxRound - 1; i > 0; i--) {

            roundId = arts.get(i).getRound();

            if (roundId == i) {
                artsSpecific.add(arts.get(i));
            }
        }

        return artsSpecific;
    }

    //check twice, once for deny and one for approve not finished
    public int getArtworkStatus(int id, int status) throws DBException {

        List<Artwork> arts = getAllArtworkByTitleId(id);

        int artStatus = 0;
        int count = 0;

        for (int i = 0; i < arts.size(); i++) {

            artStatus = arts.get(i).getTitleHasAccount().getStatus().getStatusId();

            if (artStatus == status) {
                count++;
            }
        }

        if (count == arts.size()) {
            return status;
        }

        return 0;

    }

    //update artwork status
    public String updateArtworkStatus(int id, int status) {
        try {
            Artwork art = ab.getArtworkById(id);

            if (status == 1) {
                //            art.setStatus(1);    
            } else if (status == 2) {
                //            art.setStatus(2);    
            }

            ab.updateArtwork(art);

            return null;

        } catch (DBException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return "Update failed!";
        }
    }

}

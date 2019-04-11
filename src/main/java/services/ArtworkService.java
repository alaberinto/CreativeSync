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
import models.Artwork;

/**
  * AccountService is a service class to process requests to access or mutate
 * Artwork information.
 * @author Matthew
 */
public class ArtworkService {

    private ArtworkBroker ab;

    /**
     * Constructor that instantiates the ArtworkBroker.
     */
    public ArtworkService() {
        ab = new ArtworkBroker();
    }

    /**
     * Gets a list of all artworks.
     * @return an ArrayList object of all Artwork objects.
     * @throws DBException if there was a problem retrieving Artworks from the database.
     */
    public ArrayList<Artwork> getAllArtwork() throws DBException {
        return new ArrayList(ab.getAllArtwork());
    }

    /**
     * Gets a list of all artworks associated with a title.
     * @param id the id of the Title whose Artworks we want.
     * @return a List object of all Artworks linked to the specified Title.
     * @throws DBException if there was a problem retrieving Artworks from the database.
     */
    public List<Artwork> getAllArtworkByTitleId(int id) throws DBException {

        List<Artwork> arts = ab.getAllArtwork();
        List<Artwork> arts_specific = new ArrayList();

        int title_id;

        for (int i = 0; i < arts.size(); i++) {

            title_id = arts.get(i).getTitleHasAccount().getTitle().getTitleId();

            if (title_id == id) {
                arts_specific.add(arts.get(i));
            }
        }

        return arts_specific;
    }

    /**
     * Find the maximum round of a given title.
     * @param id_t the id of a title.
     * @return the integer value of the maximum round.
     * @throws DBException if there was a problem retrieving the round number from the database.
     */
    public int findMaxRound(int id_t) throws DBException {

        List<Artwork> arts = (List<Artwork>) getAllArtworkByTitleId(id_t);

        int round_id;
        int max_round = 1;

        for (int i = 0; i < arts.size(); i++) {

            round_id = arts.get(i).getRound();

            if (round_id > max_round) {
                max_round = round_id;
            }
        }

        return max_round;
    }

    public List<Artwork> getAllArtworkByRound(int id_t, int id_r) throws DBException {

        List<Artwork> arts = getAllArtworkByTitleId(id_t);
        List<Artwork> arts_specific = new ArrayList();

        int round_id;

        for (int i = 0; i < arts.size(); i++) {

            round_id = arts.get(i).getRound();

            if (round_id == id_r) {
                arts_specific.add(arts.get(i));
            }
        }

        return arts_specific;
    }

    //This method does not get all rounds with all information, only the number of rounds in the correct order for a specific title. The other method above will get artwork for any round properly, only for a round.
    public List<Artwork> getAllRounds(int id_t) throws DBException {

        List<Artwork> arts = getAllArtworkByTitleId(id_t);

        if (arts.isEmpty()) {
            return arts;
        }

        List<Artwork> arts_specific;

        int round_id;
        int round_id_specific;
        int max_round = 0;

        //find max round
        for (int i = 0; i < arts.size(); i++) {

            round_id = arts.get(i).getRound();

            if (round_id > max_round) {
                max_round = round_id;
            }

        }

        arts_specific = new ArrayList(max_round);

        //add highest round artwork to new sorted list
        for (int i = 0; i < arts.size(); i++) {

            round_id = arts.get(i).getRound();

            if (round_id == max_round) { //ignore second if the same, stop loop when it finds what it needs (the max round)
                arts_specific.add(arts.get(i));
                break;
            }
        }

        //because if there is only one round, there is no need to look for other rounds so just return it here
        if (max_round == 1) {
            return arts_specific;
        }

        //store max round, to be used for comparison with arts list below
        round_id_specific = arts_specific.get(0).getRound();

        //add lower rounds artwork to new sorted list, when there is more than one artwork in the round
        for (int i = max_round-1; i > 0; i--) {

            round_id = arts.get(i).getRound();

            if (round_id == i) {
                arts_specific.add(arts.get(i));
            }
        }

        return arts_specific;
    }

}

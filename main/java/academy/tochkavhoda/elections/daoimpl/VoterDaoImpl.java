package academy.tochkavhoda.elections.daoimpl;

import academy.tochkavhoda.elections.dao.VoterDao;
import academy.tochkavhoda.elections.database.DataBase;
import academy.tochkavhoda.elections.model.Suggestion;
import academy.tochkavhoda.elections.model.Voter;
import academy.tochkavhoda.elections.exceptions.ServerException;

import java.util.List;

public class VoterDaoImpl implements VoterDao {

    @Override
    public void addVoter(Voter voter) throws ServerException {
        DataBase.getInstance().addVoter(voter);
    }

    @Override
    public Voter getVoter(String login) {
        return DataBase.getInstance().getVoter(login);
    }

    @Override
    public void addToken(String token, Voter voter) {
        DataBase.getInstance().addToken(token, voter);
    }

    @Override
    public Voter getVoterByToken(String token) {
        return DataBase.getInstance().getVoterByToken(token);
    }


    @Override
    public void delToken(String token) {
        DataBase.getInstance().delToken(token);
    }

    @Override
    public Voter getVoterById(int id) {
       return DataBase.getInstance().getVoterById(id);
    }


    @Override
    public Voter getVoterBySurname(String surname, String name) throws ServerException {
        return DataBase.getInstance().getVoterBySurname(surname, name);
    }

    @Override
    public Voter getVoterByLogin(String login) throws ServerException {
        return DataBase.getInstance().getVoterByLogin(login);
    }

    @Override
    public void addSuggestion(Suggestion suggestion, Voter voter) {
        DataBase.getInstance().addSuggestion(suggestion, voter);
    }


    @Override
    public void moveSuggestionToCommunity(Voter voter) {
        DataBase.getInstance().moveSuggestionToCommunity(voter);
    }

    @Override
    public void delRating(int id) {
        DataBase.getInstance().delRating(id);
    }

    @Override
    public Suggestion getSuggestion(int id) {
        return DataBase.getInstance().getSuggestionById(id);
    }


    @Override
    public void addRating(Suggestion suggestion, int rating) {
        DataBase.getInstance().addRating(suggestion, rating);
    }

    @Override
    public List<Suggestion> getAllSuggestions() {
        return DataBase.getInstance().getAllSuggestions();
    }


    @Override
    public void clearDB() {
        DataBase.getInstance().clearData();
    }

}

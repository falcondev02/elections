package academy.tochkavhoda.elections.dao;

import academy.tochkavhoda.elections.model.*;
import academy.tochkavhoda.elections.exceptions.ServerException;

import java.util.List;

public interface VoterDao {

    void addVoter(Voter voter) throws ServerException;
    Voter getVoter(String login);
    void addToken(String token, Voter voter);
    Voter getVoterByToken(String token);
    void delToken(String token);
    Voter getVoterById(int id);
    Voter getVoterBySurname(String surname, String name) throws ServerException;
    Voter getVoterByLogin(String login)  throws ServerException;
    void addSuggestion(Suggestion suggestion, Voter voter);

    void addRating(Suggestion suggestion, int rating);
    Suggestion getSuggestion(int id);

    void delRating(int id);
    void moveSuggestionToCommunity(Voter voter);
    List<Suggestion> getAllSuggestions();
    void clearDB();
}

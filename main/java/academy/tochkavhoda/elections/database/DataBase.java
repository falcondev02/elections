package academy.tochkavhoda.elections.database;

import academy.tochkavhoda.elections.exceptions.ServerErrorCode;
import academy.tochkavhoda.elections.model.*;
import academy.tochkavhoda.elections.exceptions.ServerException;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.util.*;

public class DataBase {
    private final Map<String, Voter> voterByLogin = new HashMap<>();
    private final BidiMap<String, Voter> voterByToken = new DualHashBidiMap<>();
    private final Map<Integer, Voter> voterById = new HashMap<>();
    private final BidiMap<Integer, Candidate> candidateById = new DualHashBidiMap<>();
    private final Map<Fullname, Voter> voterByFullname = new HashMap<>();
    private final DualHashBidiMap<Integer, Suggestion> suggestionById = new DualHashBidiMap<>();
    private final MultiValuedMap<Integer, Integer> ratingBySuggestionById = new ArrayListValuedHashMap<>();
    private final Map<Integer, Program> programCandidateById = new HashMap<>();
    private final Suggester community = new Community();


    private int nextUserId = 1;
    private int nextSuggestionId = 1;
    private int nextProgramId = 1;


    private static DataBase instance;

    public static synchronized DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    private DataBase() {
    }

    public void addVoter(Voter voter) throws ServerException {
        Fullname fi = new Fullname(voter.getUser().getSurname(), voter.getUser().getFirstName());
        if (voterByLogin.putIfAbsent(voter.getUser().getLogin(), voter) != null) {
            throw new ServerException(ServerErrorCode.LOGIN_ALREADY_USED);
        }
        voterById.put(nextUserId, voter);
        voter.getUser().setId(nextUserId);
        voterByFullname.put(fi, voter);

        nextUserId++;
    }

    public Voter getVoter(String login) {
        return voterByLogin.get(login);
    }

    public void addToken(String token, Voter voter) {
        String oldToken = voterByToken.getKey(voter);
        if (oldToken != null) return;
        voterByToken.put(token, voter);
    }

    public Voter getVoterByToken(String token) {
        return voterByToken.get(token);

    }

    public void delToken(String token) {
        voterByToken.remove(token);
    }

    public Voter getVoterByLogin(String login) throws ServerException {
        if (voterByLogin.get(login) != null) {
            return voterByLogin.get(login);
        } else {
            throw new ServerException(ServerErrorCode.VOTER_ERROR);
        }
    }

    public Voter getVoterById(int id) {
        return voterById.get(id);
    }

    public void addCandidateById(Integer id, Candidate candidate) {
        candidateById.putIfAbsent(id, candidate);
    }

    public Candidate getCandidateById(Integer id) {
        return candidateById.get(id);
    }

    public Voter getVoterBySurname(String surname, String name) throws ServerException {
        Fullname fi = new Fullname(surname, name);
        if (voterByFullname.get(fi) != null) {
            return voterByFullname.get(fi);
        } else {
            throw new ServerException(ServerErrorCode.VOTER_ERROR);
        }
    }

    public void addSuggestion(Suggestion suggestion, Voter voter) {
        suggestionById.put(nextSuggestionId, suggestion);
        ratingBySuggestionById.get(nextSuggestionId).add(suggestion.getRating());
        suggestion.setId(nextSuggestionId);
        voter.addSuggestion(suggestion);
        nextSuggestionId++;
    }

    public void moveSuggestionToCommunity(Voter voter) {
        Set<Suggestion> suggestionForCommunity = voter.getSuggestions();
        for (Suggestion sugg : suggestionForCommunity) {
            sugg.setAuthor(community);
            community.getSuggestions().add(sugg);
        }
        voter.getSuggestions().clear();

    }

    public Suggestion getSuggestionById(int id) {
        return suggestionById.get(id);
    }


    //Список всех предложений и их средний рейтинг
    public List<Suggestion> getAllSuggestions() {
        Set<Suggestion> allSuggestions = new HashSet<>();
        for (Integer num : ratingBySuggestionById.keys()) {
            List<Integer> rates = (List<Integer>) ratingBySuggestionById.get(num);
            Suggestion sug = suggestionById.get(num);
            sug.setRating(getAverageRating(rates));
            allSuggestions.add(suggestionById.get(num));
        }
        List<Suggestion> allSuggestionsList = new ArrayList<>(allSuggestions);
        allSuggestionsList.sort(Comparator.comparingInt(Suggestion::getRating));
        return allSuggestionsList;

    }

    public void addRating(Suggestion suggestion, int rating) {
        ratingBySuggestionById.get(suggestion.getId()).add(rating);
    }

    public void delRating(int id) {
        ratingBySuggestionById.remove(id);
    }

    // создаем лист предложения и добавляем их в него через listIdProgram, в котором находятся id Suggestions
    public int addProgramAndGetId(Candidate candidate, Set<Integer> listIdSuggestions) {
        Set<Suggestion> listSuggestions = new HashSet<>();
        for (Integer num : listIdSuggestions) {
            listSuggestions.add(suggestionById.get(num));
        }
        Program program = new Program(candidate, listSuggestions);
        programCandidateById.put(nextProgramId, program);
        program.setId(nextProgramId);
        nextProgramId++;
        return nextProgramId - 1;
    }

    public void stopCandidate(Candidate candidate, int id) {
        candidateById.inverseBidiMap().remove(candidate);
        programCandidateById.remove(id);
    }

    public void delProgram(int id) {
        programCandidateById.remove(id);
    }

    public Map<Integer, Program> getAllCandidates() {
        return programCandidateById;
    }

    public int getAverageRating(List<Integer> list) {
        int count = 0;
        for (Integer integer : list) {
            count += integer;
        }
        return count * 100 / list.size();
    }

    public void clearData() {
        voterByLogin.clear();
        voterByToken.clear();
        voterById.clear();
        candidateById.clear();
        nextUserId = 1;
        voterByFullname.clear();
        nextSuggestionId = 1;
        suggestionById.clear();
        ratingBySuggestionById.clear();
        nextProgramId = 1;
        programCandidateById.clear();
        community.getSuggestions().clear();
    }


}




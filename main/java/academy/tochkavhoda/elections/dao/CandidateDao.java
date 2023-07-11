package academy.tochkavhoda.elections.dao;

import academy.tochkavhoda.elections.model.Candidate;
import academy.tochkavhoda.elections.model.Program;

import java.util.Map;
import java.util.Set;

public interface CandidateDao {
    void addCandidateById(Integer id, Candidate candidate);
    void stopCandidate(Candidate candidate, int id);
    Candidate getCandidateById(Integer id);
    int addProgramAndGetId(Candidate candidate, Set<Integer> listIdSuggestions);
    void delProgram(int id);
    Map<Integer, Program> getAllCandidates();
}

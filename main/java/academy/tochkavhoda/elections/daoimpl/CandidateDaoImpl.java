package academy.tochkavhoda.elections.daoimpl;

import academy.tochkavhoda.elections.dao.CandidateDao;
import academy.tochkavhoda.elections.database.DataBase;
import academy.tochkavhoda.elections.model.Candidate;
import academy.tochkavhoda.elections.model.Program;

import java.util.Map;
import java.util.Set;

public class CandidateDaoImpl implements CandidateDao {

    public void addCandidateById(Integer id, Candidate candidate) {
        DataBase.getInstance().addCandidateById(id, candidate);
    }

    public void stopCandidate(Candidate candidate, int id) {
        DataBase.getInstance().stopCandidate(candidate, id);
    }

    public Candidate getCandidateById(Integer id) {
        return DataBase.getInstance().getCandidateById(id);
    }



    public int addProgramAndGetId(Candidate candidate, Set<Integer> listIdSuggestions) {
       return DataBase.getInstance().addProgramAndGetId(candidate, listIdSuggestions);
    }

    public void delProgram(int id) {
        DataBase.getInstance().delProgram(id);
    }

    public Map<Integer, Program> getAllCandidates() {
        return DataBase.getInstance().getAllCandidates();
    }
}

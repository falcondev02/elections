package academy.tochkavhoda.elections.server;

import academy.tochkavhoda.elections.dto.response.EmptyResponse;
import academy.tochkavhoda.elections.dto.response.ErrorResponse;
import academy.tochkavhoda.elections.exceptions.ServerErrorCode;
import academy.tochkavhoda.elections.service.VoterService;
import academy.tochkavhoda.elections.service.CandidateService;
import com.google.gson.Gson;

public class Server {
    private final VoterService voterService = new VoterService();
    private final CandidateService candidateService = new CandidateService();
    private boolean electionStarted = false;
    private static final int ERROR_CODE = 400;
    private final Gson gson = new Gson();
    private static final int SUCCESS_CODE = 200;

    public void startElection(){
        electionStarted = true;
    }

    public ServerResponse checkElectionStarted() {
        if(electionStarted) {
            ErrorResponse errorResponse = new ErrorResponse(ServerErrorCode.ELECTIONS_STARTED.getErrorString(), ERROR_CODE);
            return new ServerResponse(ERROR_CODE, gson.toJson(errorResponse));
        }
        EmptyResponse emptyResponse = new EmptyResponse();
        return new ServerResponse(SUCCESS_CODE, gson.toJson(emptyResponse));
    }

    public ServerResponse registerVoter(String requestJsonString) {
        if(electionStarted) {return checkElectionStarted();}
        return voterService.registerVoter(requestJsonString);
    }

    public ServerResponse restoreRegistration(String requestJsonString) {
        if(electionStarted) {return checkElectionStarted();}
        return voterService.restoreRegistration(requestJsonString);
    }

    public ServerResponse login(String requestJsonString) {
        if(electionStarted) {return checkElectionStarted();}
        return voterService.login(requestJsonString);
    }

    public ServerResponse logout(String token) {
        if(electionStarted) {return checkElectionStarted();}
        return voterService.logout(token);
    }

    public ServerResponse leaveFromServer(String token) {
        if(electionStarted) {return checkElectionStarted();}
        return voterService.leaveFromServer(token);
    }

    public ServerResponse addCandidate(String token, String requestJsonString) {
        if(electionStarted) {return checkElectionStarted();}
        return candidateService.addCandidate(token, requestJsonString);
    }

    public ServerResponse addCurrentCandidate(String token) {
        if(electionStarted) {return checkElectionStarted();}
        return candidateService.addCurrentCandidate(token);
    }

    public void clearDB() {
        voterService.dataClear();
    }

    public ServerResponse getCurrentVoter(String token) {
        if(electionStarted) {return checkElectionStarted();}
        return candidateService.getCurrentVoter(token);
    }

    public ServerResponse getCandidateFullname(String token, String fullname) {
        if(electionStarted) {return checkElectionStarted();}
        return candidateService.getCandidateByFullName(token, fullname);
    }

    public ServerResponse setAgree(String token, String agreement) {
        if(electionStarted) {return checkElectionStarted();}
        return candidateService.setAgree(token, agreement);
    }

    public ServerResponse addSuggestion(String token, String suggestion) {
        if(electionStarted) {return checkElectionStarted();}
        return voterService.addSuggestion(token, suggestion);
    }

    public ServerResponse addRating(String token, String ratingAndSuggestionId) {
        if(electionStarted) {return checkElectionStarted();}
        return voterService.addRating(token, ratingAndSuggestionId);
    }

    //список предложений, сделанных тем или иным избирателем или несколькими избирателями
    public ServerResponse getSetSuggestions(String token, String voterId) {
        if(electionStarted) {return checkElectionStarted();}
        return voterService.getSetSuggestions(token, voterId);
    }

    //список всех предложений с их средней оценкой, отсортированный по оценке
    public ServerResponse getAllSuggestions(String token) {
        if(electionStarted) {return checkElectionStarted();}
        return voterService.getAllSuggestions(token);
    }

    public ServerResponse addCandidateProgram(String token, String suggestionsByCandidate) {
        if(electionStarted) {return checkElectionStarted();}
        return candidateService.addCandidateProgram(token, suggestionsByCandidate);
    }

    public ServerResponse delCandidateProgram(String token, String requestJsonString) {
        if(electionStarted) {return checkElectionStarted();}
        return candidateService.delCandidateProgram(token, requestJsonString);
    }

    public ServerResponse getAllCandidates(String token) {
        if(electionStarted) {return checkElectionStarted();}
        return candidateService.getAllCandidates(token);
    }

    public ServerResponse stopCandidate(String token, String id) {
        if(electionStarted) {return checkElectionStarted();}
        return candidateService.stopCandidate(token, id);
    }
}

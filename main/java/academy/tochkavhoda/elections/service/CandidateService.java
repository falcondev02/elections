package academy.tochkavhoda.elections.service;


import academy.tochkavhoda.elections.dao.VoterDao;

import academy.tochkavhoda.elections.dao.CandidateDao;
import academy.tochkavhoda.elections.daoimpl.CandidateDaoImpl;
import academy.tochkavhoda.elections.daoimpl.VoterDaoImpl;
import academy.tochkavhoda.elections.dto.request.*;
import academy.tochkavhoda.elections.dto.response.*;
import academy.tochkavhoda.elections.exceptions.ServerErrorCode;
import academy.tochkavhoda.elections.exceptions.ServerException;
import academy.tochkavhoda.elections.model.*;
import academy.tochkavhoda.elections.server.ServerResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Map;


public class CandidateService {
    private final Gson json = new Gson();
    private final VoterDao voterDao = new VoterDaoImpl();
    private final CandidateDao candidateDao = new CandidateDaoImpl();

    // Добавление кандидата в БД
    public ServerResponse addCandidate(String token, String requestJsonString) {
        try {
            CandidateDtoRequest candidateId = getClassFromJson(requestJsonString, CandidateDtoRequest.class);
            getVoterByToken(token);
            Voter voterCandidate = voterDao.getVoterById(candidateId.getId());
            Candidate candidate = new Candidate(voterCandidate.getUser(), false);
            candidateDao.addCandidateById(voterCandidate.getUser().getId(), candidate);
            EmptyResponse emptyResponse = new EmptyResponse();
            return new ServerResponse(json.toJson(emptyResponse));
        } catch (ServerException e) {
            return new ServerResponse(e);
        }
    }

    // Добавление текущего кандидата в БД
    public ServerResponse addCurrentCandidate(String token) {
        try {
            Voter owner = getVoterByToken(token);
            Candidate candidate = new Candidate(owner.getUser(), true);
            candidateDao.addCandidateById(owner.getUser().getId(), candidate);
            EmptyResponse emptyResponse = new EmptyResponse();
            return new ServerResponse(json.toJson(emptyResponse));
        } catch (ServerException e) {
            return new ServerResponse(e);
        }
    }

    //Снятие кандидатуры кандидата
    public ServerResponse stopCandidate(String token, String idProgram) {
        try {
            DelProgramDtoRequest delProgramDtoRequest = getClassFromJson(idProgram, DelProgramDtoRequest.class);
            Voter owner = getVoterByToken(token);
            Candidate candidate = new Candidate(owner.getUser(), false);
            candidateDao.stopCandidate(candidate, delProgramDtoRequest.getId());
            EmptyResponse emptyResponse = new EmptyResponse();
            return new ServerResponse(json.toJson(emptyResponse));
        } catch (ServerException e) {
            return new ServerResponse(e);
        }
    }

    // Получение текущего Voter
    public ServerResponse getCurrentVoter(String token) {
        try {
            Voter voter = getVoterByToken(token);
            CurrentVoterResponse currentVoterResponse = new CurrentVoterResponse(voter.getUser().getId());
            return new ServerResponse(json.toJson(currentVoterResponse));
        } catch (ServerException e) {
            return new ServerResponse(e);
        }
    }

    // Возврат кандидата из БД по ФИ
    public ServerResponse getCandidateByFullName(String token, String fullname) {
        try {
            CandidateFullnameRequest candidateSurnameRequest = getClassFromJson(fullname, CandidateFullnameRequest.class);
            getVoterByToken(token);
            Voter futureCandidate = voterDao.getVoterBySurname(candidateSurnameRequest.getSurname(), candidateSurnameRequest.getName());
            if (futureCandidate == null) throw new ServerException(ServerErrorCode.EMPTY_LOGIN);
            CandidateFullnameResponse candidateSurnameResponse = new CandidateFullnameResponse(futureCandidate.getUser().getId());
            return new ServerResponse(json.toJson(candidateSurnameResponse));
        } catch (ServerException e) {
            return new ServerResponse(e);
        }
    }

    public ServerResponse setAgree(String token, String Agreement) {
        try {
            SetAgreeCandidateRequest setAgreeCandidateRequest = getClassFromJson(Agreement, SetAgreeCandidateRequest.class);
            Voter voter = getVoterByToken(token);
            Candidate candidate = candidateDao.getCandidateById(voter.getUser().getId());
            if (candidate == null) {
                throw new ServerException(ServerErrorCode.CANDIDATE_DOESNT_NOMINATE);
            }
            candidate.setAgreement(setAgreeCandidateRequest.isAgreement());
            SetAgreeCandidateResponse setAgreeCandidateResponse = new SetAgreeCandidateResponse(candidate.isAgreement());
            return new ServerResponse(json.toJson(setAgreeCandidateResponse));
        } catch (ServerException e) {
            return new ServerResponse(e);
        }
    }

    //Добавляем к кандидатам ID каких-либо предложений
    public ServerResponse addCandidateProgram(String token, String suggestionsByCandidate) {
        try {
            SuggestionsIdDtoRequest listIdSuggestions = getClassFromJson(suggestionsByCandidate, SuggestionsIdDtoRequest.class);
            Voter voter = getVoterByToken(token);
            Candidate candidate = candidateDao.getCandidateById(voter.getUser().getId());
            if (!candidate.isAgreement()) {
                throw new ServerException(ServerErrorCode.CANDIDATE_IS_DISAGREE);
            }
            //Добавляем программу в бд и возвращаем айди этой программы, который будет нужен для метода delProgram
            ProgramIdDtoResponse programDtoResponse = new ProgramIdDtoResponse(candidateDao.
                    addProgramAndGetId(candidate, listIdSuggestions.getId()));
            return new ServerResponse(json.toJson(programDtoResponse));
        } catch (ServerException e) {
            return new ServerResponse(e);
        }
    }

    // Удаление программы кандидата
    public ServerResponse delCandidateProgram(String token, String requestJsonString) {
        try {
            ProgramDeleteDtoRequest programDeleteDtoRequest = getClassFromJson(requestJsonString, ProgramDeleteDtoRequest.class);
            Voter voter = getVoterByToken(token);
            if (programDeleteDtoRequest.getOwnerSuggestion().equals(voter.getUser().getLogin())) {
                throw new ServerException(ServerErrorCode.CANDIDATE_CANT_DEL_PROGRAM);
            }
            candidateDao.delProgram(programDeleteDtoRequest.getId());
            EmptyResponse emptyResponse = new EmptyResponse();
            return new ServerResponse(json.toJson(emptyResponse));
        } catch (ServerException e) {
            return new ServerResponse(e);
        }
    }

    //список всех кандидатов , с указанием программы каждого из них
    public ServerResponse getAllCandidates(String token) {
        try {
            getVoterByToken(token);
            Map<Integer, Program> allCandidates = candidateDao.getAllCandidates();
            AllCandidatesDtoResponse allCandidatesDtoResponse = new AllCandidatesDtoResponse();
            SetSuggestionsDtoResponse setSuggestionsDtoResponse = new SetSuggestionsDtoResponse();
            for (Program program : allCandidates.values()) {
                for (Suggestion sugg : program.getSuggestions()) {
                    SuggestionDtoResponse suggestionDtoResponse = new SuggestionDtoResponse(sugg.getId(), sugg.getText(),
                            program.getCandidate().getUser().getId());
                    setSuggestionsDtoResponse.addNewResponse(suggestionDtoResponse);
                }
                ProgramDtoResponse programDtoResponse = new ProgramDtoResponse(setSuggestionsDtoResponse,
                        program.getId(), program.getCandidate().getUser().getId());
                allCandidatesDtoResponse.addNewResponse(programDtoResponse);
            }
            return new ServerResponse(json.toJson(allCandidatesDtoResponse));

        } catch (ServerException e) {
            return new ServerResponse(e);
        }
    }

    private Voter getVoterByToken(String token) throws ServerException {
        Voter voter = voterDao.getVoterByToken(token);
        if (voter == null) {
            throw new ServerException(ServerErrorCode.EMPTY_TOKEN);
        }
        return voter;
    }

    public <T> T getClassFromJson(String jsonString, Class<T> Request) throws ServerException {
        try {
            T request = json.fromJson(jsonString, Request);
            return request;
        } catch (JsonSyntaxException e) {
            throw new ServerException(ServerErrorCode.WRONG_JSON);
        }
    }

}

package academy.tochkavhoda.elections.server;

import academy.tochkavhoda.elections.dto.request.*;
import academy.tochkavhoda.elections.dto.response.*;
import com.google.gson.Gson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestServer {
    private final Gson gson = new Gson();
    private final Server server = new Server();

    @BeforeEach
    public void dataClear() {
        server.clearDB();
    }

    @Test
    public void testRegister() {
        RegisterVoterDtoRequest registerVoterDtoRequest = new RegisterVoterDtoRequest("Сергей", "Викторович", "Соколов",
                "FDSfsdf213", "AbsD1234gh", "авыпыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest1 = new RegisterVoterDtoRequest("", "Viktorovich", "Sokolov",
                "fdsa", "fdsafsd", "авапыыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest2 = new RegisterVoterDtoRequest("Ильяавфы", "Сервыцвыф", "ЫВАываыав",
                "Ka4ok74", "FabsD1234gh", "авыпыва", 10, 20);
        String result = gson.toJson(registerVoterDtoRequest);
        ServerResponse serverResponse = server.registerVoter(result);
        String result1 = gson.toJson(registerVoterDtoRequest1);
        ServerResponse serverResponse1 = server.registerVoter(result1);
        String result2 = gson.toJson(registerVoterDtoRequest2);
        ServerResponse serverResponse2 = server.registerVoter(result2);
        assertEquals(200, serverResponse.getResponseCode());
        assertEquals(400, serverResponse1.getResponseCode());
        assertEquals(200, serverResponse2.getResponseCode());

    }

    @Test
    public void testLogin() {
        RegisterVoterDtoRequest registerVoterDtoRequest = new RegisterVoterDtoRequest("Сергей", "Викторович", "Соколов",
                "FDSfsdf213", "AbsD1234gh", "Ленина", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest1 = new RegisterVoterDtoRequest("", "Viktorovich", "Sokolov",
                "Сергей", "AbsD1234gh", "авапыыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest2 = new RegisterVoterDtoRequest("Илья", "Андреевич", "Лебединский",
                "Ilya74", "DFki0987qq", "Кирова", 10, 20);
        String result = gson.toJson(registerVoterDtoRequest);
        ServerResponse serverResponse = server.registerVoter(result);
        String result1 = gson.toJson(registerVoterDtoRequest1);
        ServerResponse serverResponse1 = server.registerVoter(result1);
        String result2 = gson.toJson(registerVoterDtoRequest2);
        ServerResponse serverResponse2 = server.registerVoter(result2);
        assertEquals(200, serverResponse.getResponseCode());
        assertEquals(400, serverResponse1.getResponseCode());
        assertEquals(200, serverResponse2.getResponseCode());
        LoginDtoRequest loginDtoRequest = new LoginDtoRequest(registerVoterDtoRequest.getLogin(), registerVoterDtoRequest.getPassword());
        LoginDtoRequest loginDtoRequest1 = new LoginDtoRequest(registerVoterDtoRequest1.getLogin(), registerVoterDtoRequest1.getPassword());
        LoginDtoRequest loginDtoRequest2 = new LoginDtoRequest(registerVoterDtoRequest2.getLogin(), registerVoterDtoRequest2.getPassword());
        String resultLogin = gson.toJson(loginDtoRequest);
        ServerResponse serverResponseLogin = server.login(resultLogin);
        String resultLogin1 = gson.toJson(loginDtoRequest1);
        ServerResponse serverResponseLogin1 = server.login(resultLogin1);
        String resultLogin2 = gson.toJson(loginDtoRequest2);
        ServerResponse serverResponseLogin2 = server.login(resultLogin2);
        assertEquals(200, serverResponseLogin.getResponseCode());
        assertEquals(400, serverResponseLogin1.getResponseCode());
        assertEquals(200, serverResponseLogin2.getResponseCode());
    }

    @Test
    public void testLogout() {
        RegisterVoterDtoRequest registerVoterDtoRequest = new RegisterVoterDtoRequest("Сергей", "Викторович", "Соколов",
                "FDSfsdf213", "AbsD1234gh", "авыпыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest1 = new RegisterVoterDtoRequest("Сергей", "Авыффыцй", "Соколов",
                "к", "Bfdsaefd12", "авыпыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest2 = new RegisterVoterDtoRequest("Илья", "Андреевич", "Лебединский",
                "Ilya74", "DFki0987qq", "Кирова", 10, 20);
        String result = gson.toJson(registerVoterDtoRequest);
        ServerResponse serverResponse = server.registerVoter(result);
        String result1 = gson.toJson(registerVoterDtoRequest1);
        ServerResponse serverResponse1 = server.registerVoter(result1);
        String result2 = gson.toJson(registerVoterDtoRequest2);
        ServerResponse serverResponse2 = server.registerVoter(result2);
        assertEquals(200, serverResponse.getResponseCode());
        assertEquals(400, serverResponse1.getResponseCode());
        assertEquals(200, serverResponse2.getResponseCode());
        LoginDtoRequest loginDtoRequest = new LoginDtoRequest(registerVoterDtoRequest.getLogin(), registerVoterDtoRequest.getPassword());
        LoginDtoRequest loginDtoRequest1 = new LoginDtoRequest(registerVoterDtoRequest1.getLogin(), registerVoterDtoRequest1.getPassword());
        LoginDtoRequest loginDtoRequest2 = new LoginDtoRequest(registerVoterDtoRequest2.getLogin(), registerVoterDtoRequest2.getPassword());
        String resultLogin = gson.toJson(loginDtoRequest);
        ServerResponse serverResponseLogin = server.login(resultLogin);
        String resultLogin1 = gson.toJson(loginDtoRequest1);
        ServerResponse serverResponseLogin1 = server.login(resultLogin1);
        String resultLogin2 = gson.toJson(loginDtoRequest2);
        ServerResponse serverResponseLogin2 = server.login(resultLogin2);
        assertEquals(200, serverResponseLogin.getResponseCode());
        assertEquals(400, serverResponseLogin1.getResponseCode());
        assertEquals(200, serverResponseLogin2.getResponseCode());
        LoginDtoResponse token = gson.fromJson(serverResponseLogin.getResponseData(), LoginDtoResponse.class);
        LoginDtoResponse token1 = gson.fromJson(serverResponseLogin1.getResponseData(), LoginDtoResponse.class);
        LoginDtoResponse token2 = gson.fromJson(serverResponseLogin2.getResponseData(), LoginDtoResponse.class);
        ServerResponse serverResponseLogout = server.logout(token.getToken());
        ServerResponse serverResponseLogout1 = server.logout(token1.getToken());
        ServerResponse serverResponseLogout2 = server.logout(token2.getToken());
        assertEquals(200, serverResponseLogout.getResponseCode());
        assertEquals(400, serverResponseLogout1.getResponseCode());
        assertEquals(200, serverResponseLogout2.getResponseCode());
    }

    @Test
    public void testAddCandidate() {
        //Registration user
        RegisterVoterDtoRequest registerVoterDtoRequest = new RegisterVoterDtoRequest("Сергей", "Викторович", "Соколов",
                "FDSfsdf213", "AbsD1234gh", "авыпыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest1 = new RegisterVoterDtoRequest("", "Viktorovich", "Sokolov",
                "fdsa", "fdsafsd", "авапыыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest2 = new RegisterVoterDtoRequest("Илья", "Сервыц", "ЫВАываыав",
                "FDSfssdf213", "AbsD1234gh", "авыпыва", 10, 20);
        String result = gson.toJson(registerVoterDtoRequest);
        ServerResponse serverResponse = server.registerVoter(result);
        String result1 = gson.toJson(registerVoterDtoRequest1);
        ServerResponse serverResponse1 = server.registerVoter(result1);
        String result2 = gson.toJson(registerVoterDtoRequest2);
        ServerResponse serverResponse2 = server.registerVoter(result2);

        assertEquals(200, serverResponse.getResponseCode());
        assertEquals(400, serverResponse1.getResponseCode());
        assertEquals(200, serverResponse2.getResponseCode());

        //1
        // Пользователь отдает свой голос за себя и agreement автоматически = true;
        LoginDtoRequest loginDtoRequest = new LoginDtoRequest(registerVoterDtoRequest.getLogin(), registerVoterDtoRequest.getPassword());
        String resultLogin = gson.toJson(loginDtoRequest);
        ServerResponse serverResponseLogin = server.login(resultLogin);
        LoginDtoResponse token = gson.fromJson(serverResponseLogin.getResponseData(), LoginDtoResponse.class);

        ServerResponse serverResponseId = server.getCurrentVoter(token.getToken());
        CurrentVoterResponse currentVoterResponse = gson.fromJson(serverResponseId.getResponseData(), CurrentVoterResponse.class);
        CandidateDtoRequest candidateDtoRequest = new CandidateDtoRequest(currentVoterResponse.getId());
        String candidate = gson.toJson(candidateDtoRequest);
        ServerResponse serverResponseCandidate = server.addCandidate(token.getToken(), candidate);
        assertEquals(200, serverResponseCandidate.getResponseCode());

        // Будущий кандидат "registerVoterDtoRequest" #1 разлогинивается
        ServerResponse serverResponseLogout = server.logout(token.getToken());
        assertEquals(200, serverResponseLogout.getResponseCode());

        //3 Пользователь #3 "registerVoterDtoRequest2" отдает свой голос за "registerVoterDtoRequest" #1
        LoginDtoRequest loginDtoRequest2 = new LoginDtoRequest(registerVoterDtoRequest2.getLogin(), registerVoterDtoRequest2.getPassword()); //login #3
        String resultLogin2 = gson.toJson(loginDtoRequest2);
        ServerResponse serverResponseLogin2 = server.login(resultLogin2);
        LoginDtoResponse token2 = gson.fromJson(serverResponseLogin2.getResponseData(), LoginDtoResponse.class);
        // Через ФИ находим ID кандидата
        CandidateFullnameRequest candidateSurnameRequest = new CandidateFullnameRequest(registerVoterDtoRequest.getSurname(), registerVoterDtoRequest.getFirstName());
        String candidateIdByFullname = gson.toJson(candidateSurnameRequest);
        ServerResponse serverResponseCandidateId = server.getCandidateFullname(token2.getToken(), candidateIdByFullname);
        CandidateFullnameResponse candidateFullnameId = gson.fromJson(serverResponseCandidateId.getResponseData(), CandidateFullnameResponse.class);
        String candidateFirstTime = gson.toJson(candidateFullnameId);
        ServerResponse serverResponseCandidate2FirstTime = server.addCandidate(token2.getToken(), candidateFirstTime);
        assertEquals(200, serverResponseCandidate2FirstTime.getResponseCode());
        // Передаём id кандидата, и автоматически false, т.к. он еще не залогинен

        // Кандидат логинится второй раз после logout и решает согласиться на свое продвижение
        LoginDtoRequest loginDtoRequestSecondTime = new LoginDtoRequest(registerVoterDtoRequest.getLogin(), registerVoterDtoRequest.getPassword());
        String resultLoginSecondTime = gson.toJson(loginDtoRequestSecondTime);
        ServerResponse serverResponseLoginSecondTime = server.login(resultLoginSecondTime);
        // Кандидат логинится и мы получаем новый токен с тем же Voter
        LoginDtoResponse tokenSecondTime = gson.fromJson(serverResponseLoginSecondTime.getResponseData(), LoginDtoResponse.class);
        // Передаём в addCandidate согласие/несогласие кандидата,
        SetAgreeCandidateRequest setAgreeCandidateRequest = new SetAgreeCandidateRequest(true);


        String candidate2Agreement = gson.toJson(setAgreeCandidateRequest);
        ServerResponse serverResponseCandidate2 = server.setAgree(tokenSecondTime.getToken(), candidate2Agreement);
        assertEquals(200, serverResponseCandidate2.getResponseCode());
    }

    @Test
    public void testAddSuggestion() {
        RegisterVoterDtoRequest registerVoterDtoRequest = new RegisterVoterDtoRequest("Сергей", "Викторович", "Соколов",
                "FDSfsdf213", "AbsD1234gh", "авыпыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest1 = new RegisterVoterDtoRequest("", "Viktorovich", "Sokolov",
                "fdsa", "fdsafsd", "авапыыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest2 = new RegisterVoterDtoRequest("Илья", "Сервыц", "ЫВАываыав",
                "FDSfssdf213", "AbsD1234gh", "авыпыва", 10, 20);
        String result = gson.toJson(registerVoterDtoRequest);
        ServerResponse serverResponse = server.registerVoter(result);
        String result1 = gson.toJson(registerVoterDtoRequest1);
        ServerResponse serverResponse1 = server.registerVoter(result1);
        String result2 = gson.toJson(registerVoterDtoRequest2);
        ServerResponse serverResponse2 = server.registerVoter(result2);

        assertEquals(200, serverResponse.getResponseCode());
        assertEquals(400, serverResponse1.getResponseCode());
        assertEquals(200, serverResponse2.getResponseCode());

        LoginDtoRequest loginDtoRequest = new LoginDtoRequest(registerVoterDtoRequest.getLogin(), registerVoterDtoRequest.getPassword());
        String resultLogin = gson.toJson(loginDtoRequest);
        ServerResponse serverResponseLogin = server.login(resultLogin);
        LoginDtoResponse token = gson.fromJson(serverResponseLogin.getResponseData(), LoginDtoResponse.class);

        LoginDtoRequest loginDtoRequest2 = new LoginDtoRequest(registerVoterDtoRequest2.getLogin(), registerVoterDtoRequest2.getPassword());
        String resultLogin2 = gson.toJson(loginDtoRequest2);
        ServerResponse serverResponseLogin2 = server.login(resultLogin2);
        LoginDtoResponse token2 = gson.fromJson(serverResponseLogin2.getResponseData(), LoginDtoResponse.class);

        //User 1 ставит 5 своему предложению
        SuggestionDtoRequest suggestionByUser = new SuggestionDtoRequest("Text with suggestion", 5);
        String suggestion = gson.toJson(suggestionByUser);
        ServerResponse serverResponseSuggestion = server.addSuggestion(token.getToken(), suggestion);
        assertEquals(200, serverResponseSuggestion.getResponseCode());
        // id предложения первого юзера
        SuggestionDtoResponse suggestionId = gson.fromJson(serverResponseSuggestion.getResponseData(), SuggestionDtoResponse.class);

        //User 2 ставит 4 предложению юзера 1
        RatingDtoRequest ratingByUser2 = new RatingDtoRequest(suggestionId.getId(), 4);
        String raiting = gson.toJson(ratingByUser2);
        ServerResponse serverResponseRaiting = server.addRating(token2.getToken(), raiting);
        assertEquals(200, serverResponseRaiting.getResponseCode());

    }

    @Test
    public void testGetSetSuggestions() {
        RegisterVoterDtoRequest registerVoterDtoRequest = new RegisterVoterDtoRequest("Сергей", "Викторович", "Соколов",
                "FDSfsdf213", "AbsD1234gh", "авыпыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest1 = new RegisterVoterDtoRequest("", "Viktorovich", "Sokolov",
                "fdsa", "fdsafsd", "авапыыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest2 = new RegisterVoterDtoRequest("Илья", "Сервыц", "ЫВАываыав",
                "FDSfssdf213", "AbsD1234gh", "авыпыва", 10, 20);
        String result = gson.toJson(registerVoterDtoRequest);
        ServerResponse serverResponse = server.registerVoter(result);
        String result1 = gson.toJson(registerVoterDtoRequest1);
        ServerResponse serverResponse1 = server.registerVoter(result1);
        String result2 = gson.toJson(registerVoterDtoRequest2);
        ServerResponse serverResponse2 = server.registerVoter(result2);

        assertEquals(200, serverResponse.getResponseCode());
        assertEquals(400, serverResponse1.getResponseCode());
        assertEquals(200, serverResponse2.getResponseCode());

        LoginDtoRequest loginDtoRequest = new LoginDtoRequest(registerVoterDtoRequest.getLogin(), registerVoterDtoRequest.getPassword());
        String resultLogin = gson.toJson(loginDtoRequest);
        ServerResponse serverResponseLogin = server.login(resultLogin);
        LoginDtoResponse token = gson.fromJson(serverResponseLogin.getResponseData(), LoginDtoResponse.class);

        LoginDtoRequest loginDtoRequest2 = new LoginDtoRequest(registerVoterDtoRequest2.getLogin(), registerVoterDtoRequest2.getPassword());
        String resultLogin2 = gson.toJson(loginDtoRequest2);
        ServerResponse serverResponseLogin2 = server.login(resultLogin2);
        LoginDtoResponse token2 = gson.fromJson(serverResponseLogin2.getResponseData(), LoginDtoResponse.class);

        //User 1 ставит 5 своему предложению
        SuggestionDtoRequest suggestionByUser = new SuggestionDtoRequest("Text with suggestion", 5);
        String suggestion = gson.toJson(suggestionByUser);
        ServerResponse serverResponseSuggestion = server.addSuggestion(token.getToken(), suggestion);
        assertEquals(200, serverResponseSuggestion.getResponseCode());

        SuggestionDtoRequest suggestionByUser2 = new SuggestionDtoRequest("asdfadsfasdf", 5);
        String suggestion2 = gson.toJson(suggestionByUser2);
        ServerResponse serverResponseSuggestion2 = server.addSuggestion(token.getToken(), suggestion2);
        assertEquals(200, serverResponseSuggestion2.getResponseCode());
        VoterDtoResponse voterId = gson.fromJson(serverResponseSuggestion.getResponseData(), VoterDtoResponse.class);
        VoterDtoRequest voterDtoRequest = new VoterDtoRequest(voterId.getId());
        String id = gson.toJson(voterDtoRequest);

        ServerResponse serverResponseText = server.getSetSuggestions(token2.getToken(), id);
        assertEquals(200, serverResponseText.getResponseCode());

        SuggestionDtoResponse suggestionId1 = gson.fromJson(serverResponseSuggestion.getResponseData(), SuggestionDtoResponse.class);

        //User 2 ставит 4 предложению юзера 1
        RatingDtoRequest raitingByUser2 = new RatingDtoRequest(suggestionId1.getId(), 4);
        String raiting = gson.toJson(raitingByUser2);
        ServerResponse serverResponseRaiting = server.addRating(token2.getToken(), raiting);
        assertEquals(200, serverResponseRaiting.getResponseCode());

    }


    @Test
    public void testGetAllSuggestions() {
        RegisterVoterDtoRequest registerVoterDtoRequest = new RegisterVoterDtoRequest("Сергей", "Викторович", "Соколов",
                "FDSfsdf213", "AbsD1234gh", "авыпыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest1 = new RegisterVoterDtoRequest("", "Viktorovich", "Sokolov",
                "fdsa", "fdsafsd", "авапыыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest2 = new RegisterVoterDtoRequest("Илья", "Сервыц", "ЫВАываыав",
                "FDSfssdf213", "AbsD1234gh", "авыпыва", 10, 20);
        String result = gson.toJson(registerVoterDtoRequest);
        ServerResponse serverResponse = server.registerVoter(result);
        String result1 = gson.toJson(registerVoterDtoRequest1);
        ServerResponse serverResponse1 = server.registerVoter(result1);
        String result2 = gson.toJson(registerVoterDtoRequest2);
        ServerResponse serverResponse2 = server.registerVoter(result2);

        assertEquals(200, serverResponse.getResponseCode());
        assertEquals(400, serverResponse1.getResponseCode());
        assertEquals(200, serverResponse2.getResponseCode());

        LoginDtoRequest loginDtoRequest = new LoginDtoRequest(registerVoterDtoRequest.getLogin(), registerVoterDtoRequest.getPassword());
        String resultLogin = gson.toJson(loginDtoRequest);
        ServerResponse serverResponseLogin = server.login(resultLogin);
        LoginDtoResponse token = gson.fromJson(serverResponseLogin.getResponseData(), LoginDtoResponse.class);

        LoginDtoRequest loginDtoRequest2 = new LoginDtoRequest(registerVoterDtoRequest2.getLogin(), registerVoterDtoRequest2.getPassword());
        String resultLogin2 = gson.toJson(loginDtoRequest2);
        ServerResponse serverResponseLogin2 = server.login(resultLogin2);
        LoginDtoResponse token2 = gson.fromJson(serverResponseLogin2.getResponseData(), LoginDtoResponse.class);

        //User 1 ставит 5 своему предложению
        SuggestionDtoRequest suggestionByUser = new SuggestionDtoRequest("Text with suggestion", 5);
        String suggestion = gson.toJson(suggestionByUser);
        ServerResponse serverResponseSuggestion = server.addSuggestion(token.getToken(), suggestion);
        assertEquals(200, serverResponseSuggestion.getResponseCode());

        SuggestionDtoResponse suggestionId1 = gson.fromJson(serverResponseSuggestion.getResponseData(), SuggestionDtoResponse.class);

        //User 2 ставит 4 предложению юзера 1
        RatingDtoRequest raitingByUser2 = new RatingDtoRequest(suggestionId1.getId(), 4);
        String raiting = gson.toJson(raitingByUser2);
        ServerResponse serverResponseRaiting = server.addRating(token2.getToken(), raiting);
        assertEquals(200, serverResponseRaiting.getResponseCode());

        SuggestionDtoRequest suggestionByUser2 = new SuggestionDtoRequest("asdfadsfasdf", 5);
        String suggestion2 = gson.toJson(suggestionByUser2);
        ServerResponse serverResponseSuggestion2 = server.addSuggestion(token.getToken(), suggestion2);
        assertEquals(200, serverResponseSuggestion2.getResponseCode());

        ServerResponse serverResponseGetAllSuggestions = server.getAllSuggestions(token.getToken());
        assertEquals(200, serverResponseGetAllSuggestions.getResponseCode());

    }


    @Test
    public void addProgram() {
        RegisterVoterDtoRequest registerVoterDtoRequest = new RegisterVoterDtoRequest("Сергей", "Викторович", "Соколов",
                "FDSfsdf213", "AbsD1234gh", "авыпыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest1 = new RegisterVoterDtoRequest("", "Viktorovich", "Sokolov",
                "fdsa", "fdsafsd", "авапыыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest2 = new RegisterVoterDtoRequest("Илья", "Сервыц", "ЫВАываыав",
                "FDSfssdf213", "AbsD1234gh", "авыпыва", 10, 20);
        String result = gson.toJson(registerVoterDtoRequest);
        ServerResponse serverResponse = server.registerVoter(result);
        String result1 = gson.toJson(registerVoterDtoRequest1);
        ServerResponse serverResponse1 = server.registerVoter(result1);
        String result2 = gson.toJson(registerVoterDtoRequest2);
        ServerResponse serverResponse2 = server.registerVoter(result2);

        assertEquals(200, serverResponse.getResponseCode());
        assertEquals(400, serverResponse1.getResponseCode());
        assertEquals(200, serverResponse2.getResponseCode());

        LoginDtoRequest loginDtoRequest = new LoginDtoRequest(registerVoterDtoRequest.getLogin(), registerVoterDtoRequest.getPassword());
        String resultLogin = gson.toJson(loginDtoRequest);
        ServerResponse serverResponseLogin = server.login(resultLogin);
        LoginDtoResponse token = gson.fromJson(serverResponseLogin.getResponseData(), LoginDtoResponse.class);

        LoginDtoRequest loginDtoRequest2 = new LoginDtoRequest(registerVoterDtoRequest2.getLogin(), registerVoterDtoRequest2.getPassword());
        String resultLogin2 = gson.toJson(loginDtoRequest2);
        ServerResponse serverResponseLogin2 = server.login(resultLogin2);
        LoginDtoResponse token2 = gson.fromJson(serverResponseLogin2.getResponseData(), LoginDtoResponse.class);

        //User 1 ставит 5 своему предложению
        SuggestionDtoRequest suggestionByUser = new SuggestionDtoRequest("Text with suggestion", 5);
        String suggestion = gson.toJson(suggestionByUser);
        ServerResponse serverResponseSuggestion = server.addSuggestion(token.getToken(), suggestion);
        assertEquals(200, serverResponseSuggestion.getResponseCode());

        SuggestionDtoResponse suggestionId1 = gson.fromJson(serverResponseSuggestion.getResponseData(), SuggestionDtoResponse.class);

        //User 2 ставит 4 предложению юзера 1
        RatingDtoRequest raitingByUser2 = new RatingDtoRequest(suggestionId1.getId(), 1);
        String raiting = gson.toJson(raitingByUser2);
        ServerResponse serverResponseRaiting = server.addRating(token.getToken(), raiting);
        assertEquals(200, serverResponseRaiting.getResponseCode());

        SuggestionDtoRequest suggestionByUser2 = new SuggestionDtoRequest("asdfadsfasdf", 5);
        String suggestion2 = gson.toJson(suggestionByUser2);
        ServerResponse serverResponseSuggestion2 = server.addSuggestion(token2.getToken(), suggestion2);
        assertEquals(200, serverResponseSuggestion2.getResponseCode());

        ServerResponse serverResponseGetAllSuggestions = server.getAllSuggestions(token.getToken());
        assertEquals(200, serverResponseGetAllSuggestions.getResponseCode());

        VoterDtoRequest voterId= new VoterDtoRequest(suggestionId1.getId());
        String id = gson.toJson(voterId);

        ServerResponse serverResponseText = server.getSetSuggestions(token2.getToken(),id);
        assertEquals(200, serverResponseText.getResponseCode());
        //user 1 true за себя
        ServerResponse serverResponseCandidate = server.addCurrentCandidate(token.getToken());
        assertEquals(200, serverResponseCandidate.getResponseCode());
        //ListSuggestionsDtoResponse listSuggestionsDtoResponse = gson.fromJson(serverResponseText.getResponseData(), ListSuggestionsDtoResponse.class);
        Set<Integer> SetId = new HashSet<>();
        SetId.add(suggestionId1.getId());
        SuggestionsIdDtoRequest programDtoRequest = new SuggestionsIdDtoRequest(SetId);
        String idListToJson = gson.toJson(programDtoRequest);
        ServerResponse serverResponseAddProgram = server.addCandidateProgram(token.getToken(), idListToJson);
        assertEquals(200, serverResponseAddProgram.getResponseCode());
    }

    @Test
    public void delProgram() {
        RegisterVoterDtoRequest registerVoterDtoRequest = new RegisterVoterDtoRequest("Сергей", "Викторович", "Соколов",
                "FDSfsdf213", "AbsD1234gh", "авыпыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest1 = new RegisterVoterDtoRequest("", "Viktorovich", "Sokolov",
                "fdsa", "fdsafsd", "авапыыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest2 = new RegisterVoterDtoRequest("Илья", "Сервыц", "ЫВАываыав",
                "FDSfssdf213", "AbsD1234gh", "авыпыва", 10, 20);
        String result = gson.toJson(registerVoterDtoRequest);
        ServerResponse serverResponse = server.registerVoter(result);
        String result1 = gson.toJson(registerVoterDtoRequest1);
        ServerResponse serverResponse1 = server.registerVoter(result1);
        String result2 = gson.toJson(registerVoterDtoRequest2);
        ServerResponse serverResponse2 = server.registerVoter(result2);

        assertEquals(200, serverResponse.getResponseCode());
        assertEquals(400, serverResponse1.getResponseCode());
        assertEquals(200, serverResponse2.getResponseCode());

        LoginDtoRequest loginDtoRequest = new LoginDtoRequest(registerVoterDtoRequest.getLogin(), registerVoterDtoRequest.getPassword());
        String resultLogin = gson.toJson(loginDtoRequest);
        ServerResponse serverResponseLogin = server.login(resultLogin);
        LoginDtoResponse token = gson.fromJson(serverResponseLogin.getResponseData(), LoginDtoResponse.class);

        LoginDtoRequest loginDtoRequest2 = new LoginDtoRequest(registerVoterDtoRequest2.getLogin(), registerVoterDtoRequest2.getPassword());
        String resultLogin2 = gson.toJson(loginDtoRequest2);
        ServerResponse serverResponseLogin2 = server.login(resultLogin2);
        LoginDtoResponse token2 = gson.fromJson(serverResponseLogin2.getResponseData(), LoginDtoResponse.class);

        //User 1 ставит 5 своему предложению
        SuggestionDtoRequest suggestionByUser = new SuggestionDtoRequest("Text with suggestion", 5);
        String suggestion = gson.toJson(suggestionByUser);
        ServerResponse serverResponseSuggestion = server.addSuggestion(token.getToken(), suggestion);
        assertEquals(200, serverResponseSuggestion.getResponseCode());

        SuggestionDtoResponse suggestionId1 = gson.fromJson(serverResponseSuggestion.getResponseData(), SuggestionDtoResponse.class);

        //User 2 ставит 4 предложению юзера 1
        RatingDtoRequest raitingByUser2 = new RatingDtoRequest(suggestionId1.getId(), 1);
        String raiting = gson.toJson(raitingByUser2);
        ServerResponse serverResponseRaiting = server.addRating(token.getToken(), raiting);
        assertEquals(200, serverResponseRaiting.getResponseCode());

        SuggestionDtoRequest suggestionByUser2 = new SuggestionDtoRequest("asdfadsfasdf", 5);
        String suggestion2 = gson.toJson(suggestionByUser2);
        ServerResponse serverResponseSuggestion2 = server.addSuggestion(token2.getToken(), suggestion2);
        assertEquals(200, serverResponseSuggestion2.getResponseCode());

        SuggestionDtoResponse suggestionId2 = gson.fromJson(serverResponseSuggestion2.getResponseData(), SuggestionDtoResponse.class);

        ServerResponse serverResponseGetAllSuggestions = server.getAllSuggestions(token.getToken());
        assertEquals(200, serverResponseGetAllSuggestions.getResponseCode());

        SetSuggestionsDtoRequest listSuggestionsDtoRequest = new SetSuggestionsDtoRequest(suggestionId1.getId());
        String id = gson.toJson(listSuggestionsDtoRequest);

        ServerResponse serverResponseText = server.getSetSuggestions(token2.getToken(),id);
        assertEquals(200, serverResponseText.getResponseCode());

        //user 1 true за себя
        ServerResponse serverResponseCandidate = server.addCurrentCandidate(token.getToken());
        assertEquals(200, serverResponseCandidate.getResponseCode());


        //ListSuggestionsDtoResponse listSuggestionsDtoResponse = gson.fromJson(serverResponseText.getResponseData(), ListSuggestionsDtoResponse.class);
        Set<Integer> SetId = new HashSet<>();
        SetId.add(suggestionId1.getId());
        SetId.add(suggestionId2.getId());
        SuggestionsIdDtoRequest programDtoRequest = new SuggestionsIdDtoRequest(SetId);
        String idListToJson = gson.toJson(programDtoRequest);
        ServerResponse serverResponseAddProgram = server.addCandidateProgram(token.getToken(), idListToJson);
        assertEquals(200, serverResponseAddProgram.getResponseCode());

        //del
        // первый кандидат удаляет чужую программу
        ProgramIdDtoResponse programDtoResponse = gson.fromJson(serverResponseAddProgram.getResponseData(), ProgramIdDtoResponse.class);
        ProgramDeleteDtoRequest programDeleteDtoRequest = new ProgramDeleteDtoRequest(programDtoResponse.getId(),
                                                    suggestionByUser2.getText(), registerVoterDtoRequest2.getLogin());
        String programDel = gson.toJson(programDeleteDtoRequest);
        ServerResponse serverResponseDel = server.delCandidateProgram(token.getToken(), programDel);
        assertEquals(200, serverResponseDel.getResponseCode());


        // первый кандидат удаляет свою программу
        ProgramDeleteDtoRequest programDeleteDtoRequest2 = new ProgramDeleteDtoRequest(programDtoResponse.getId(), suggestionByUser.getText(),
                                                                        registerVoterDtoRequest.getLogin());
        String programDel2 = gson.toJson(programDeleteDtoRequest2);
        ServerResponse serverResponseDel2 = server.delCandidateProgram(token.getToken(), programDel2);
        assertEquals(400, serverResponseDel2.getResponseCode());
        server.startElection();
    }

    @Test
    public void GetAllCandidates() {

        RegisterVoterDtoRequest registerVoterDtoRequest = new RegisterVoterDtoRequest("Сергей", "Викторович", "Соколов",
                "FDSfsdf213", "AbsD1234gh", "авыпыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest1 = new RegisterVoterDtoRequest("", "Viktorovich", "Sokolov",
                "fdsa", "fdsafsd", "авапыыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest2 = new RegisterVoterDtoRequest("Илья", "Сервыц", "ЫВАываыав",
                "FDSfssdf213", "AbsD1234gh", "авыпыва", 10, 20);
        String result = gson.toJson(registerVoterDtoRequest);
        ServerResponse serverResponse = server.registerVoter(result);
        String result1 = gson.toJson(registerVoterDtoRequest1);
        ServerResponse serverResponse1 = server.registerVoter(result1);
        String result2 = gson.toJson(registerVoterDtoRequest2);
        ServerResponse serverResponse2 = server.registerVoter(result2);

        assertEquals(200, serverResponse.getResponseCode());
        assertEquals(400, serverResponse1.getResponseCode());
        assertEquals(200, serverResponse2.getResponseCode());

        LoginDtoRequest loginDtoRequest = new LoginDtoRequest(registerVoterDtoRequest.getLogin(), registerVoterDtoRequest.getPassword());
        String resultLogin = gson.toJson(loginDtoRequest);
        ServerResponse serverResponseLogin = server.login(resultLogin);
        LoginDtoResponse token = gson.fromJson(serverResponseLogin.getResponseData(), LoginDtoResponse.class);

        LoginDtoRequest loginDtoRequest2 = new LoginDtoRequest(registerVoterDtoRequest2.getLogin(), registerVoterDtoRequest2.getPassword());
        String resultLogin2 = gson.toJson(loginDtoRequest2);
        ServerResponse serverResponseLogin2 = server.login(resultLogin2);
        LoginDtoResponse token2 = gson.fromJson(serverResponseLogin2.getResponseData(), LoginDtoResponse.class);

        //User 1 ставит 5 своему предложению
        SuggestionDtoRequest suggestionByUser = new SuggestionDtoRequest("Text with suggestion", 5);
        String suggestion = gson.toJson(suggestionByUser);
        ServerResponse serverResponseSuggestion = server.addSuggestion(token.getToken(), suggestion);
        assertEquals(200, serverResponseSuggestion.getResponseCode());

        SuggestionDtoResponse suggestionId1 = gson.fromJson(serverResponseSuggestion.getResponseData(), SuggestionDtoResponse.class);

        //User 2 ставит 4 предложению юзера 1
        RatingDtoRequest raitingByUser2 = new RatingDtoRequest(suggestionId1.getId(), 1);
        String raiting = gson.toJson(raitingByUser2);
        ServerResponse serverResponseRaiting = server.addRating(token.getToken(), raiting);
        assertEquals(200, serverResponseRaiting.getResponseCode());

        SuggestionDtoRequest suggestionByUser2 = new SuggestionDtoRequest("asdfadsfasdf", 5);
        String suggestion2 = gson.toJson(suggestionByUser2);
        ServerResponse serverResponseSuggestion2 = server.addSuggestion(token2.getToken(), suggestion2);
        assertEquals(200, serverResponseSuggestion2.getResponseCode());

        ServerResponse serverResponseGetAllSuggestions = server.getAllSuggestions(token.getToken());
        assertEquals(200, serverResponseGetAllSuggestions.getResponseCode());

        SetSuggestionsDtoRequest listSuggestionsDtoRequest = new SetSuggestionsDtoRequest(suggestionId1.getId());
        String id = gson.toJson(listSuggestionsDtoRequest);

        ServerResponse serverResponseText = server.getSetSuggestions(token2.getToken(),id);
        assertEquals(200, serverResponseText.getResponseCode());

        //user 1 true за себя
        ServerResponse serverResponseCandidate = server.addCurrentCandidate(token.getToken());
        assertEquals(200, serverResponseCandidate.getResponseCode());

        SuggestionDtoResponse suggestionId2 = gson.fromJson(serverResponseSuggestion2.getResponseData(), SuggestionDtoResponse.class);
        ///ListSuggestionsDtoResponse listSuggestionsDtoResponse = gson.fromJson(serverResponseText.getResponseData(), ListSuggestionsDtoResponse.class);
        Set<Integer> SetId = new HashSet<>();
        SetId.add(suggestionId1.getId());
        SetId.add(suggestionId2.getId());
        SuggestionsIdDtoRequest programDtoRequest = new SuggestionsIdDtoRequest(SetId);
        String idListToJson = gson.toJson(programDtoRequest);
        ServerResponse serverResponseAddProgram = server.addCandidateProgram(token.getToken(), idListToJson);
        assertEquals(200, serverResponseAddProgram.getResponseCode());


        //get
        ServerResponse serverResponseGetProgram = server.getAllCandidates(token.getToken());
        assertEquals(200, serverResponseGetProgram.getResponseCode());
        server.startElection();

    }


    @Test
    public void StopCandidate() {
        RegisterVoterDtoRequest registerVoterDtoRequest = new RegisterVoterDtoRequest("Сергей", "Викторович", "Соколов",
                "FDSfsdf213", "AbsD1234gh", "авыпыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest1 = new RegisterVoterDtoRequest("", "Viktorovich", "Sokolov",
                "fdsa", "fdsafsd", "авапыыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest2 = new RegisterVoterDtoRequest("Илья", "Сервыц", "ЫВАываыав",
                "FDSfssdf213", "AbsD1234gh", "авыпыва", 10, 20);
        String result = gson.toJson(registerVoterDtoRequest);
        ServerResponse serverResponse = server.registerVoter(result);
        String result1 = gson.toJson(registerVoterDtoRequest1);
        ServerResponse serverResponse1 = server.registerVoter(result1);
        String result2 = gson.toJson(registerVoterDtoRequest2);
        ServerResponse serverResponse2 = server.registerVoter(result2);

        assertEquals(200, serverResponse.getResponseCode());
        assertEquals(400, serverResponse1.getResponseCode());
        assertEquals(200, serverResponse2.getResponseCode());

        LoginDtoRequest loginDtoRequest = new LoginDtoRequest(registerVoterDtoRequest.getLogin(), registerVoterDtoRequest.getPassword());
        String resultLogin = gson.toJson(loginDtoRequest);
        ServerResponse serverResponseLogin = server.login(resultLogin);
        LoginDtoResponse token = gson.fromJson(serverResponseLogin.getResponseData(), LoginDtoResponse.class);

        LoginDtoRequest loginDtoRequest2 = new LoginDtoRequest(registerVoterDtoRequest2.getLogin(), registerVoterDtoRequest2.getPassword());
        String resultLogin2 = gson.toJson(loginDtoRequest2);
        ServerResponse serverResponseLogin2 = server.login(resultLogin2);
        LoginDtoResponse token2 = gson.fromJson(serverResponseLogin2.getResponseData(), LoginDtoResponse.class);

        //User 1 ставит 5 своему предложению
        SuggestionDtoRequest suggestionByUser = new SuggestionDtoRequest("Text with suggestion", 5);
        String suggestion = gson.toJson(suggestionByUser);
        ServerResponse serverResponseSuggestion = server.addSuggestion(token.getToken(), suggestion);
        assertEquals(200, serverResponseSuggestion.getResponseCode());

        SuggestionDtoResponse suggestionId1 = gson.fromJson(serverResponseSuggestion.getResponseData(), SuggestionDtoResponse.class);

        //User 2 ставит 4 предложению юзера 1
        RatingDtoRequest raitingByUser2 = new RatingDtoRequest(suggestionId1.getId(), 1);
        String raiting = gson.toJson(raitingByUser2);
        ServerResponse serverResponseRaiting = server.addRating(token.getToken(), raiting);
        assertEquals(200, serverResponseRaiting.getResponseCode());

        SuggestionDtoRequest suggestionByUser2 = new SuggestionDtoRequest("asdfadsfasdf", 5);
        String suggestion2 = gson.toJson(suggestionByUser2);
        ServerResponse serverResponseSuggestion2 = server.addSuggestion(token2.getToken(), suggestion2);
        assertEquals(200, serverResponseSuggestion2.getResponseCode());

        ServerResponse serverResponseGetAllSuggestions = server.getAllSuggestions(token.getToken());
        assertEquals(200, serverResponseGetAllSuggestions.getResponseCode());

        SetSuggestionsDtoRequest listSuggestionsDtoRequest = new SetSuggestionsDtoRequest(suggestionId1.getId());
        String id = gson.toJson(listSuggestionsDtoRequest);

        ServerResponse serverResponseText = server.getSetSuggestions(token2.getToken(),id);
        assertEquals(200, serverResponseText.getResponseCode());

        //user 1 true за себя
        ServerResponse serverResponseCandidate = server.addCurrentCandidate(token.getToken());
        assertEquals(200, serverResponseCandidate.getResponseCode());

        SuggestionDtoResponse suggestionId2 = gson.fromJson(serverResponseSuggestion2.getResponseData(), SuggestionDtoResponse.class);
        ///ListSuggestionsDtoResponse listSuggestionsDtoResponse = gson.fromJson(serverResponseText.getResponseData(), ListSuggestionsDtoResponse.class);
        Set<Integer> setId = new HashSet<>();
        setId.add(suggestionId1.getId());
        setId.add(suggestionId2.getId());
        SuggestionsIdDtoRequest programDtoRequest = new SuggestionsIdDtoRequest(setId);
        String idListToJson = gson.toJson(programDtoRequest);
        ServerResponse serverResponseAddProgram = server.addCandidateProgram(token.getToken(), idListToJson);
        assertEquals(200, serverResponseAddProgram.getResponseCode());


        //Stop
        ProgramIdDtoResponse programDtoResponse = gson.fromJson(serverResponseAddProgram.getResponseData(), ProgramIdDtoResponse.class);
        DelProgramDtoRequest delProgramDtoRequest = new DelProgramDtoRequest(programDtoResponse.getId());
        String idStop = gson.toJson(delProgramDtoRequest);
        ServerResponse serverStopCandidate = server.stopCandidate(token.getToken(), idStop);
        assertEquals(200, serverStopCandidate.getResponseCode());
        server.startElection();
    }

    @Test
    public void testLeaveFromServer() {
        RegisterVoterDtoRequest registerVoterDtoRequest = new RegisterVoterDtoRequest("Сергей", "Викторович", "Соколов",
                "FDSfsdf213", "AbsD1234gh", "авыпыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest1 = new RegisterVoterDtoRequest("", "Viktorovich", "Sokolov",
                "fdsa", "fdsafsd", "авапыыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest2 = new RegisterVoterDtoRequest("Илья", "Сервыц", "ЫВАываыав",
                "FDSfssdf213", "AbsD1234gh", "авыпыва", 10, 20);
        String result = gson.toJson(registerVoterDtoRequest);
        ServerResponse serverResponse = server.registerVoter(result);
        String result1 = gson.toJson(registerVoterDtoRequest1);
        ServerResponse serverResponse1 = server.registerVoter(result1);
        String result2 = gson.toJson(registerVoterDtoRequest2);
        ServerResponse serverResponse2 = server.registerVoter(result2);

        assertEquals(200, serverResponse.getResponseCode());
        assertEquals(400, serverResponse1.getResponseCode());
        assertEquals(200, serverResponse2.getResponseCode());

        LoginDtoRequest loginDtoRequest = new LoginDtoRequest(registerVoterDtoRequest.getLogin(), registerVoterDtoRequest.getPassword());
        String resultLogin = gson.toJson(loginDtoRequest);
        ServerResponse serverResponseLogin = server.login(resultLogin);
        LoginDtoResponse token = gson.fromJson(serverResponseLogin.getResponseData(), LoginDtoResponse.class);

        LoginDtoRequest loginDtoRequest2 = new LoginDtoRequest(registerVoterDtoRequest2.getLogin(), registerVoterDtoRequest2.getPassword());
        String resultLogin2 = gson.toJson(loginDtoRequest2);
        ServerResponse serverResponseLogin2 = server.login(resultLogin2);
        LoginDtoResponse token2 = gson.fromJson(serverResponseLogin2.getResponseData(), LoginDtoResponse.class);

        //User 1 ставит 5 своему предложению
        SuggestionDtoRequest suggestionByUser = new SuggestionDtoRequest("Text with suggestion", 5);
        String suggestion = gson.toJson(suggestionByUser);
        ServerResponse serverResponseSuggestion = server.addSuggestion(token.getToken(), suggestion);
        assertEquals(200, serverResponseSuggestion.getResponseCode());

        SuggestionDtoRequest suggestionByUser2 = new SuggestionDtoRequest("asdfadsfasdf", 5);
        String suggestion2 = gson.toJson(suggestionByUser2);
        ServerResponse serverResponseSuggestion2 = server.addSuggestion(token2.getToken(), suggestion2);
        assertEquals(200, serverResponseSuggestion2.getResponseCode());
        SuggestionDtoResponse suggestionId = gson.fromJson(serverResponseSuggestion.getResponseData(), SuggestionDtoResponse.class);
        SetSuggestionsDtoRequest listSuggestionsDtoRequest = new SetSuggestionsDtoRequest(suggestionId.getId());
        String id = gson.toJson(listSuggestionsDtoRequest);

        ServerResponse serverResponseText = server.getSetSuggestions(token2.getToken(),id);
        assertEquals(200, serverResponseText.getResponseCode());

        SuggestionDtoResponse suggestionId1 = gson.fromJson(serverResponseSuggestion.getResponseData(), SuggestionDtoResponse.class);

        //User 2 ставит 4 предложению юзера 1
        RatingDtoRequest raitingByUser2 = new RatingDtoRequest(suggestionId1.getId(), 4);
        String raiting = gson.toJson(raitingByUser2);
        ServerResponse serverResponseRaiting = server.addRating(token2.getToken(), raiting);
        assertEquals(200, serverResponseRaiting.getResponseCode());

        ServerResponse leaveFromServer = server.leaveFromServer(token.getToken());
        assertEquals(200, leaveFromServer.getResponseCode());
        server.startElection();
    }

    @Test
    public void testReRegistrationToServer() {
        RegisterVoterDtoRequest registerVoterDtoRequest = new RegisterVoterDtoRequest("Сергей", "Викторович", "Соколов",
                "FDSfsdf213", "AbsD1234gh", "авыпыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest1 = new RegisterVoterDtoRequest("", "Viktorovich", "Sokolov",
                "fdsa", "fdsafsd", "авапыыва", 10, 20);
        RegisterVoterDtoRequest registerVoterDtoRequest2 = new RegisterVoterDtoRequest("Илья", "Сервыц", "ЫВАываыав",
                "FDSfssdf213", "AbsD1234gh", "авыпыва", 10, 20);
        String result = gson.toJson(registerVoterDtoRequest);
        ServerResponse serverResponse = server.registerVoter(result);
        String result1 = gson.toJson(registerVoterDtoRequest1);
        ServerResponse serverResponse1 = server.registerVoter(result1);
        String result2 = gson.toJson(registerVoterDtoRequest2);
        ServerResponse serverResponse2 = server.registerVoter(result2);

        assertEquals(200, serverResponse.getResponseCode());
        assertEquals(400, serverResponse1.getResponseCode());
        assertEquals(200, serverResponse2.getResponseCode());

        LoginDtoRequest loginDtoRequest = new LoginDtoRequest(registerVoterDtoRequest.getLogin(), registerVoterDtoRequest.getPassword());
        String resultLogin = gson.toJson(loginDtoRequest);
        ServerResponse serverResponseLogin = server.login(resultLogin);
        LoginDtoResponse token = gson.fromJson(serverResponseLogin.getResponseData(), LoginDtoResponse.class);

        LoginDtoRequest loginDtoRequest2 = new LoginDtoRequest(registerVoterDtoRequest2.getLogin(), registerVoterDtoRequest2.getPassword());
        String resultLogin2 = gson.toJson(loginDtoRequest2);
        ServerResponse serverResponseLogin2 = server.login(resultLogin2);
        LoginDtoResponse token2 = gson.fromJson(serverResponseLogin2.getResponseData(), LoginDtoResponse.class);

        //User 1 ставит 5 своему предложению
        SuggestionDtoRequest suggestionByUser = new SuggestionDtoRequest("Text with suggestion", 5);
        String suggestion = gson.toJson(suggestionByUser);
        ServerResponse serverResponseSuggestion = server.addSuggestion(token.getToken(), suggestion);
        assertEquals(200, serverResponseSuggestion.getResponseCode());

        SuggestionDtoRequest suggestionByUser2 = new SuggestionDtoRequest("asdfadsfasdf", 5);
        String suggestion2 = gson.toJson(suggestionByUser2);
        ServerResponse serverResponseSuggestion2 = server.addSuggestion(token2.getToken(), suggestion2);
        assertEquals(200, serverResponseSuggestion2.getResponseCode());
        SuggestionDtoResponse suggestionId = gson.fromJson(serverResponseSuggestion.getResponseData(), SuggestionDtoResponse.class);
        SetSuggestionsDtoRequest listSuggestionsDtoRequest = new SetSuggestionsDtoRequest(suggestionId.getId());
        String id = gson.toJson(listSuggestionsDtoRequest);

        ServerResponse serverResponseText = server.getSetSuggestions(token2.getToken(),id);
        assertEquals(200, serverResponseText.getResponseCode());

        SuggestionDtoResponse suggestionId1 = gson.fromJson(serverResponseSuggestion.getResponseData(), SuggestionDtoResponse.class);

        //User 2 ставит 4 предложению юзера 1
        RatingDtoRequest raitingByUser2 = new RatingDtoRequest(suggestionId1.getId(), 4);
        String raiting = gson.toJson(raitingByUser2);
        ServerResponse serverResponseRaiting = server.addRating(token2.getToken(), raiting);
        assertEquals(200, serverResponseRaiting.getResponseCode());

        ServerResponse leaveFromServer = server.leaveFromServer(token.getToken());
        assertEquals(200, leaveFromServer.getResponseCode());
        // логин и пароль
        //передаем логин и пароль и согласие на перерегистрацию
        LoginDtoRequest restoreRegistration =
                new LoginDtoRequest(registerVoterDtoRequest.getLogin(), registerVoterDtoRequest.getPassword());
        String reRegistration = gson.toJson(restoreRegistration);
        ServerResponse reRegistrationServer = server.restoreRegistration(reRegistration);
        assertEquals(200, reRegistrationServer.getResponseCode());
    }
}

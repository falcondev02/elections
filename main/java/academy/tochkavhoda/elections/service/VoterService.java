package academy.tochkavhoda.elections.service;

import academy.tochkavhoda.elections.dao.VoterDao;
import academy.tochkavhoda.elections.daoimpl.VoterDaoImpl;
import academy.tochkavhoda.elections.dto.request.*;
import academy.tochkavhoda.elections.dto.response.*;
import academy.tochkavhoda.elections.mappers.UserMapper;
import academy.tochkavhoda.elections.exceptions.ServerErrorCode;
import academy.tochkavhoda.elections.exceptions.ServerException;
import academy.tochkavhoda.elections.model.*;
import academy.tochkavhoda.elections.server.ServerResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VoterService {
    private final VoterDao voterDao = new VoterDaoImpl();

    private final Gson json = new Gson();

    //Регистрация пользователя
    public ServerResponse registerVoter(String requestJsonString) {
        try {
            RegisterVoterDtoRequest registerVoterDtoRequest = getClassFromJson(requestJsonString, RegisterVoterDtoRequest.class);
            validate(registerVoterDtoRequest);
            User user = UserMapper.INSTANCE.REGISTER_VOTER_DTO_REQUEST(registerVoterDtoRequest);
            Voter voter = new Voter(user);
            voterDao.addVoter(voter);
            EmptyResponse emptyResponse = new EmptyResponse();
            return new ServerResponse(json.toJson(emptyResponse));

        } catch (ServerException e) {
            return new ServerResponse(e);
        }
    }

    //перерегистрация пользователя
    public ServerResponse restoreRegistration(String requestJsonString) {
        try {
            //Через логин и пароль находим вотера и даем ему токен
            LoginDtoRequest restoreRegistration = getClassFromJson(requestJsonString, LoginDtoRequest.class);
            validateLogin(restoreRegistration);
            //Проверка пользователя на указание перерегистрации
            Voter voter = voterDao.getVoterByLogin(restoreRegistration.getLogin());
            String token = UUID.randomUUID().toString();
            voterDao.addToken(token, voter);
            LoginDtoResponse loginDtoResponse = new LoginDtoResponse(token);
            return new ServerResponse(json.toJson(loginDtoResponse));
        } catch (ServerException e) {
            return new ServerResponse(e);
        }

    }

    //логин пользователя
    public ServerResponse login(String requestJsonString) {
        try {
            String token = UUID.randomUUID().toString();
            LoginDtoRequest loginVoter = getClassFromJson(requestJsonString, LoginDtoRequest.class);
            validateLogin(loginVoter);
            Voter voter = voterDao.getVoter(loginVoter.getLogin());
            if (voter == null) {
                throw new ServerException(ServerErrorCode.LOGIN_ERROR);
            }

            voterDao.addToken(token, voter);
            //возврат токена
            LoginDtoResponse loginDtoResponse = new LoginDtoResponse(token);
            return new ServerResponse(json.toJson(loginDtoResponse));
        } catch (ServerException e) {
            return new ServerResponse(e);
        }
    }

    public ServerResponse logout(String token) {
        try {
            if (token == null) {
                throw new ServerException(ServerErrorCode.INVALID_TOKEN);
            }
            voterDao.delToken(token);
            EmptyResponse emptyResponse = new EmptyResponse();
            return new ServerResponse(json.toJson(emptyResponse));
        } catch (ServerException e) {
            return new ServerResponse(e);
        }
    }

    //выход пользователя с сервера
    public ServerResponse leaveFromServer(String token) {
        try {
            Voter voter = getVoterByToken(token);
            voterDao.moveSuggestionToCommunity(voter);  //Предложения, которые сделал житель, передаются обществу города
            voterDao.delRating(voter.getUser().getId()); //удаление рейтинга этих предложений
            voterDao.delToken(token);
            EmptyResponse emptyResponse = new EmptyResponse();
            return new ServerResponse(json.toJson(emptyResponse));
        } catch (ServerException e) {
            return new ServerResponse(e);
        }
    }

    // Добавление предложений пользователю
    public ServerResponse addSuggestion(String token, String suggestion) {
        try {
            SuggestionDtoRequest suggestionsDtoRequest = getClassFromJson(suggestion, SuggestionDtoRequest.class);
            Voter voter = getVoterByToken(token);
            Suggestion suggestionOwner = new Suggestion(voter, suggestionsDtoRequest.getText(), suggestionsDtoRequest.getRating());
            voterDao.addSuggestion(suggestionOwner, voter);
            // Ответ: айди предложения, текст предложения и айди владельца этого предложения
            SuggestionDtoResponse suggestionDtoResponse = new SuggestionDtoResponse(suggestionOwner.getId(),
                    suggestionOwner.getText(), voter.getUser().getId());
            return new ServerResponse(json.toJson(suggestionDtoResponse));
        } catch (ServerException e) {
            return new ServerResponse(e);
        }
    }

    public ServerResponse addRating(String token, String ratingAndSuggestionId) {
        try {
            RatingDtoRequest ratingDtoRequest = getClassFromJson(ratingAndSuggestionId, RatingDtoRequest.class);
            getVoterByToken(token);
            Suggestion suggestion = voterDao.getSuggestion(ratingDtoRequest.getId());
            voterDao.addRating(suggestion, ratingDtoRequest.getRating());
            EmptyResponse emptyResponse = new EmptyResponse();
            return new ServerResponse(json.toJson(emptyResponse));
        } catch (ServerException e) {
            return new ServerResponse(e);
        }
    }

    //список предложений, сделанных тем или иным избирателем или несколькими избирателями
    public ServerResponse getSetSuggestions(String token, String voterId) {
        try {
            VoterDtoRequest voterDtoRequest = getClassFromJson(voterId, VoterDtoRequest.class);
            getVoterByToken(token);
            Voter voter = voterDao.getVoterById(voterDtoRequest.getId());
            Set<Suggestion> suggestionsSet = voter.getSuggestions();
            SetSuggestionsDtoResponse setSuggestionsDtoResponse = new SetSuggestionsDtoResponse();
            for (Suggestion sugg : suggestionsSet) {
                Suggester suggester = sugg.getAuthor();
                if (suggester instanceof Voter) {
                    SuggestionDtoResponse suggestionDtoResponse = new SuggestionDtoResponse(sugg.getId(), sugg.getText(),
                            voter.getUser().getId());
                    setSuggestionsDtoResponse.addNewResponse(suggestionDtoResponse);
                }
                if (suggester instanceof Community) {
                    SuggestionDtoResponse suggestionDtoResponse = new SuggestionDtoResponse(sugg.getId(), sugg.getText(),
                            0);
                    setSuggestionsDtoResponse.addNewResponse(suggestionDtoResponse);
                }
            }
            return new ServerResponse(json.toJson(setSuggestionsDtoResponse));

        } catch (ServerException e) {
            return new ServerResponse(e);
        }
    }

    //список всех предложений с их средней оценкой, отсортированный по оценке
    public ServerResponse getAllSuggestions(String token) {
        try {
            getVoterByToken(token);
            List<Suggestion> allSuggestions = voterDao.getAllSuggestions();
            AllSuggestionsDtoResponse allSuggestionsDtoResponse = new AllSuggestionsDtoResponse();
            for (Suggestion sugg : allSuggestions) {
                Suggester suggester = sugg.getAuthor();
                // проверка автора на вотера, т.к. у community отсутствует рейтинг
                if (suggester instanceof Voter) {
                    Voter voter = (Voter) suggester;
                    SuggestionAndRatingDtoResponse suggestionAndRatingDtoResponse = new SuggestionAndRatingDtoResponse(sugg.getId(),
                            sugg.getText(), voter.getUser().getId(), sugg.getRating());
                    allSuggestionsDtoResponse.addNewResponse(suggestionAndRatingDtoResponse);
                }
            }
            return new ServerResponse(json.toJson(allSuggestionsDtoResponse));
        } catch (ServerException e) {
            return new ServerResponse(e);
        }
    }

    // Получение Voter по токену
    private Voter getVoterByToken(String token) throws ServerException {
        Voter voter = voterDao.getVoterByToken(token);
        if (voter == null) {
            throw new ServerException(ServerErrorCode.EMPTY_LOGIN);
        }
        return voter;
    }

    // Проверка на логин, пароль
    private static void validateLogin(LoginDtoRequest word) throws ServerException {
        Matcher matcherLogin = Pattern.compile("^[A-Za-z]([.A-Za-z\\d-]+)([A-Za-z\\d])$").matcher(word.getLogin());
        if (!matcherLogin.matches()) throw new ServerException(ServerErrorCode.LOGIN_ERROR);
        Matcher matcherPassword = Pattern.compile("^[A-Za-z]([.A-Za-z\\d-]{8,})([A-Za-z\\d])$").matcher(word.getPassword());
        if (!matcherPassword.matches()) throw new ServerException(ServerErrorCode.PASSWORD_ERROR);
    }

    // Полная проверка пользователя
    private static void validate(RegisterVoterDtoRequest word) throws ServerException {

        Matcher matcherFirstname = Pattern.compile("^[а-яА-я](?!.*\\.\\.)[а-яА-я.\\d\\s+]{2,}$").matcher(word.getFirstName());
        if (!matcherFirstname.matches()) throw new ServerException(ServerErrorCode.FIRSTNAME_ERROR);

        Matcher matcherSurname = Pattern.compile("^[а-яА-я](?!.*\\.\\.)[а-яА-я.\\d\\s+]{2,}$").matcher(word.getSurname());
        if (!matcherSurname.matches()) throw new ServerException(ServerErrorCode.SURNAME_ERROR);

        Matcher matcherLogin = Pattern.compile("^[A-Za-z]([.A-Za-z\\d-]+)([A-Za-z\\d])$").matcher(word.getLogin());
        if (!matcherLogin.matches()) throw new ServerException(ServerErrorCode.LOGIN_ERROR);

        Matcher matcherStreet = Pattern.compile("^[а-яА-я](?!.*\\.\\.)[а-яА-я.\\d]{2,}$").matcher(word.getStreet());
        if (!matcherStreet.matches()) throw new ServerException(ServerErrorCode.STREET_ERROR);

        Matcher matcherPatronymic = Pattern.compile("^[а-яА-я](?!.*\\.\\.)[а-яА-я.\\d]{2,}$").matcher(word.getPatronymic());
        if (!matcherPatronymic.matches())
            throw new ServerException(ServerErrorCode.PATRONYMIC_ERROR);

        Matcher matcherPassword = Pattern.compile("^[A-Za-z]([.A-Za-z\\d-]{8,})([A-Za-z\\d])$").matcher(word.getPassword());
        if (!matcherPassword.matches()) throw new ServerException(ServerErrorCode.PASSWORD_ERROR);
        if (word.getHouse() <= 0) throw new ServerException(ServerErrorCode.HOUSE_ERROR);
        if (word.getApartment() <= 0) throw new ServerException(ServerErrorCode.APARTMENT_ERROR);
    }

    public <T> T getClassFromJson(String jsonString, Class<T> requestClass) throws ServerException {
        try {
            return json.fromJson(jsonString, requestClass);
        } catch (JsonSyntaxException e) {
            throw new ServerException(ServerErrorCode.WRONG_JSON);
        }
    }


    public void dataClear() {
        voterDao.clearDB();
    }
}

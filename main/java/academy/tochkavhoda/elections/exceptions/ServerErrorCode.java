package academy.tochkavhoda.elections.exceptions;

public enum ServerErrorCode {
    FIRSTNAME_ERROR("Имя должно быть на русском, состоящее только из русских букв, без пробелов"),
    SURNAME_ERROR("Фамилия должна быть на русском, состоящая только из русских букв, без пробелов"),
    VOTER_ERROR("Такого пользователя нет"),
    PATRONYMIC_ERROR("Отчество должно быть на русском, состоящее только из русских букв, без пробелов"),
    LOGIN_ERROR("Логин не может быть пустой строкой"),
    PASSWORD_ERROR("Пароль должен быть от 8ми символом"),
    STREET_ERROR("Улица должна быть на русском, состоящая только из русских букв, без пробелов"),
    HOUSE_ERROR("Дом должен быть больше 0"),
    APARTMENT_ERROR("Квартира должна быть больше 0"),
    INVALID_TOKEN("Токен считается недействительным"),
    EMPTY_LOGIN("Такого избирателя не существует"),
    LOGIN_ALREADY_USED("Уже есть логин такой"),
    EMPTY_TOKEN("Такого токена нет"),
    WRONG_JSON("JSON с ошибкой"),
    CANDIDATE_DOESNT_NOMINATE("Кандидата никто не выдвигал"),
    CANDIDATE_CANT_DEL_PROGRAM("Кандидаты не могут удалять предложения, авторами которых они являются."),
    CANDIDATE_IS_DISAGREE("Избиратель не согласен быть кандидатом"),
    ELECTIONS_STARTED("Выборы уже начались");



    private final String errorString;


    ServerErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}

package academy.tochkavhoda.elections.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class RegisterVoterDtoRequest {
    private String firstName;
    private String patronymic;
    private String surname;
    private String login;
    private String password;
    private String street;
    private int house;
    private int apartment;
    public RegisterVoterDtoRequest(String firstName, String patronymic,
                                   String surname, String login, String password,
                                   String street, int house, int apartment) {
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }
}

package academy.tochkavhoda.elections.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class LoginDtoRequest {
    private String login;
    private String password;

    public LoginDtoRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }
}

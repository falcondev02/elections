package academy.tochkavhoda.elections.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class LoginDtoResponse {
    String token;

    public LoginDtoResponse(String token) {
        this.token = token;
    }
}

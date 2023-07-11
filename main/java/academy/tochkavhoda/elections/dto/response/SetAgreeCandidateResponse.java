package academy.tochkavhoda.elections.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class SetAgreeCandidateResponse {
    boolean agreement;

    public SetAgreeCandidateResponse(boolean agreement) {
        this.agreement = agreement;
    }
}

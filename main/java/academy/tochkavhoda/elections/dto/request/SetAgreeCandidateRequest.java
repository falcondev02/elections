package academy.tochkavhoda.elections.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class SetAgreeCandidateRequest {
    boolean agreement;

    public SetAgreeCandidateRequest(boolean agreement) {
        this.agreement = agreement;
    }
}

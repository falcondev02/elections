package academy.tochkavhoda.elections.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CandidateFullnameResponse {
    int id;

    public CandidateFullnameResponse(int id) {
        this.id = id;
    }
}

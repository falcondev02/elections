package academy.tochkavhoda.elections.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CandidateDtoRequest {
    private int id;
    public CandidateDtoRequest(int id) {
        this.id = id;
    }

}

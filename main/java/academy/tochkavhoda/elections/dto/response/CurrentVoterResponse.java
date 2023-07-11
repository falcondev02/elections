package academy.tochkavhoda.elections.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CurrentVoterResponse {
    int id;

    public CurrentVoterResponse(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

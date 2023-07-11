package academy.tochkavhoda.elections.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ProgramIdDtoResponse {
    int id;

    public ProgramIdDtoResponse(int id) {
        this.id = id;
    }
}

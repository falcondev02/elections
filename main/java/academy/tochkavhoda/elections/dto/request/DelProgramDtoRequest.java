package academy.tochkavhoda.elections.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Setter
@Getter
public class DelProgramDtoRequest {
    int id;

    public DelProgramDtoRequest(int id) {
        this.id = id;
    }
}

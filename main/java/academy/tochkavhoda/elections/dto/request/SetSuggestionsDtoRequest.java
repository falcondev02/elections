package academy.tochkavhoda.elections.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class SetSuggestionsDtoRequest {
    int id;

    public SetSuggestionsDtoRequest(int id) {
        this.id = id;
    }
}

package academy.tochkavhoda.elections.dto.request;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
public class SuggestionsIdDtoRequest {
    Set<Integer> id = new HashSet<>();
    public SuggestionsIdDtoRequest(Set<Integer> id) {
        this.id = id;
    }
}

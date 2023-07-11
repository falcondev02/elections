package academy.tochkavhoda.elections.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Setter
@Getter
public class ProgramDeleteDtoRequest {
    private int id;
    private String text;
    private String ownerSuggestion;

    public ProgramDeleteDtoRequest(int id, String text, String ownerSuggestion) {
        this.id = id;
        this.text = text;
        this.ownerSuggestion = ownerSuggestion;
    }
}

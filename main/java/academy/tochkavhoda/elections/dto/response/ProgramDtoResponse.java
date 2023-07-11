package academy.tochkavhoda.elections.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProgramDtoResponse {
    private SetSuggestionsDtoResponse setSuggestionsDtoResponse;
    private int id;
    private int candidateId;
}

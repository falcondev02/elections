package academy.tochkavhoda.elections.dto.response;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetSuggestionsDtoResponse {
    Set<SuggestionDtoResponse> suggestions = new HashSet<>();

    public void addNewResponse(SuggestionDtoResponse suggestionDtoResponse) {
        suggestions.add(suggestionDtoResponse);
    }
}

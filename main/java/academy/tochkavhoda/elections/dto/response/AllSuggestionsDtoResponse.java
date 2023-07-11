package academy.tochkavhoda.elections.dto.response;

import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllSuggestionsDtoResponse {
    List<SuggestionAndRatingDtoResponse> allSuggestions = new ArrayList<>();
    public void addNewResponse(SuggestionAndRatingDtoResponse suggestionAndRatingDtoResponse) {
        allSuggestions.add(suggestionAndRatingDtoResponse);
    }
}

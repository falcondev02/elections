package academy.tochkavhoda.elections.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionAndRatingDtoResponse {

    private int id;
    private String text;
    private int idAuthor;
    private int rating;

}

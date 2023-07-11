package academy.tochkavhoda.elections.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionDtoResponse {
    private int id;
    private String text;
    private int idAuthor;
}

package academy.tochkavhoda.elections.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class SuggestionDtoRequest {
    String text;
    int rating;

    public SuggestionDtoRequest(String text, int rating) {

        this.text = text;
        this.rating = rating;
    }
}

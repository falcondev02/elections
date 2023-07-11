package academy.tochkavhoda.elections.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class RatingDtoRequest {
    int id;
    int rating;

    public RatingDtoRequest(int id, int rating) {
        this.id = id;
        this.rating = rating;
    }
}

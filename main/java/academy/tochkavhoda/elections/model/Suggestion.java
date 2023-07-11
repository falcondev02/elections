package academy.tochkavhoda.elections.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Suggestion {
    private Suggester author;
    private String text;
    private int rating;
    private int id;
    public Suggestion(Suggester author, String text, int rating) {
        this.author = author;
        this.text = text;
        this.rating = rating;
    }
}

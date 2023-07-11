package academy.tochkavhoda.elections.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Voter extends Suggester {
    private User user;
    public Voter(User user) {
        this.user = user;
    }
}

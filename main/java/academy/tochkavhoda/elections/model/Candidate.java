package academy.tochkavhoda.elections.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Candidate{
    private User user;
    private boolean agreement;

    public Candidate(User user, boolean agreement) {
        this.user = user;
        this.agreement = agreement;
    }
}

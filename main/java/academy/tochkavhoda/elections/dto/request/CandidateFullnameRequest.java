package academy.tochkavhoda.elections.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode

public class CandidateFullnameRequest {
    String name, surname;

    public CandidateFullnameRequest(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }
}

package academy.tochkavhoda.elections.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
public class Program {
    private Candidate candidate;
    private Set<Suggestion> suggestions;
    private int id;
    public Program(Candidate candidate, Set<Suggestion> suggestions) {
        this.candidate = candidate;
        this.suggestions = suggestions;
    }
}

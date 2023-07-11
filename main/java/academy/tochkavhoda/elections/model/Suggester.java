package academy.tochkavhoda.elections.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Suggester {
    private Set<Suggestion> suggestions = new HashSet<>();
    public void addSuggestion(Suggestion suggestion) {
        suggestions.add(suggestion);
    }
}

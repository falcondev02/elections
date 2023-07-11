package academy.tochkavhoda.elections.model;

import lombok.*;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String firstName;
    private String patronymic;
    private String surname;
    private String login;
    private String password;
    private String street;
    private int house;
    private int apartment;
    private Set<Suggestion> suggestions;
}

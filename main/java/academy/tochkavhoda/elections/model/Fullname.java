package academy.tochkavhoda.elections.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode

public class Fullname {
    private String surname;
    private String firstname;


    public Fullname(String surName, String firstName) {
        this.surname = surName;
        this.firstname = firstName;
    }
}

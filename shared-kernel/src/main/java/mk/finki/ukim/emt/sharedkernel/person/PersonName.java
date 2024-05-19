package mk.finki.ukim.emt.sharedkernel.person;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.domain.base.ValueObject;

@Embeddable
@Getter
public class PersonName implements ValueObject {
    private String name;
    private String surname;

    public PersonName(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public PersonName() {
        name = "";
        surname = "";
    }

    @Override
    public String toString() {
        return "PersonName{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}

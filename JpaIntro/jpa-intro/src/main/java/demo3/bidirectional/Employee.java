package demo3.bidirectional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<>();

    public Employee() {}

    Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    void addPhone(Phone.PhoneType phoneType, String phoneNumber) {
        this.phones.add(new Phone(this, phoneType, phoneNumber));
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    public List<Phone> getPhones() {
        return phones;
    }
}

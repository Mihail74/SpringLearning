package demo2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private Integer id;

    private String city;

    private String state;

    @OneToOne(mappedBy = "address")
    private Employee employee;

    public Address() {}

    Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Employee getEmployee() {
        return employee;
    }
}

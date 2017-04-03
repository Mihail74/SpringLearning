package demo3.bidirectional;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Phone {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;

    private String phoneNumber;

    @ManyToOne
    @JoinColumn
    private Employee employee;

    public Phone() {}

    Phone(Employee employee, PhoneType phoneType, String phoneNumber) {
        this.employee = employee;
        this.phoneType = phoneType;
        this.phoneNumber = phoneNumber;
    }

    PhoneType getPhoneType() {
        return phoneType;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    public Employee getEmployee() {
        return employee;
    }

    public enum PhoneType {
        HOME, WORK, FAX, MAIN
    }
}

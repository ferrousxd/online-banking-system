package kz.edu.astanait.bankingsystem.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    private String phoneNumber;

    private String password;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE
    )
    private Role role;

    public User() {

    }

    public User(String phoneNumber, String password, Role role) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String phoneNumber, String password, Role role) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

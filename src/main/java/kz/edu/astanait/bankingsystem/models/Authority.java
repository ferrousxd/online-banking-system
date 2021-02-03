package kz.edu.astanait.bankingsystem.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @SequenceGenerator(
            name = "authority_sequence",
            sequenceName = "authority_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "authority_sequence"
    )
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles = new HashSet<>();

    public Authority() {

    }

    public Authority(String name) {
        this.name = name;
    }

    public Authority(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

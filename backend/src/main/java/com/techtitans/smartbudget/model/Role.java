package com.techtitans.smartbudget.model;

import com.techtitans.smartbudget.model.enums.RoleName;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName name;
    @OneToMany(mappedBy = "role")
    private Collection<Users> users;
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            })
    @JoinTable(name = "role_privilege",
            inverseJoinColumns = @JoinColumn(name = "privilege_id", nullable = false, insertable = false, updatable = false),
            joinColumns = @JoinColumn(name = "role_id", nullable = false, insertable = false, updatable = false),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Collection<Privilege> privileges = new HashSet<>();

    public Role(RoleName name, Collection<Privilege> privileges) {
        this.name = name;
        this.privileges = privileges;
    }

    public Role(RoleName name) {
        this.name = name;
        this.privileges = List.of();
    }

    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }

    public Collection<Users> getUsers() {
        return users;
    }

    public void setUsers(Collection<Users> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}

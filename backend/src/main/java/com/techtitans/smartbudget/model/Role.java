package com.techtitans.smartbudget.model;

import com.techtitans.smartbudget.model.enums.RoleName;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName name;
    @OneToMany(mappedBy = "role")
    private Collection<Users> users;
    @ManyToMany(
            fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinTable(name = "role_privilege", inverseJoinColumns = @JoinColumn(name = "privilege_id", nullable = false, updatable = false),
            joinColumns = @JoinColumn(name = "role_id", nullable = false, updatable = false), foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Set<Privilege> privileges = new HashSet<>();

    public Role(Long id, RoleName name, Set<Privilege> privileges) {
        this.id = id;
        this.name = name;
        this.privileges = privileges;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
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

package com.techtitans.smartbudget.model;

import com.techtitans.smartbudget.model.enums.PrivilegeName;


import jakarta.persistence.*;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "privilege")
public class Privilege {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PrivilegeName name;
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public Privilege(Integer id, PrivilegeName name, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public Privilege() {
    }

    public Privilege(PrivilegeName name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PrivilegeName getName() {
        return name;
    }

    public void setName(PrivilegeName name) {
        this.name = name;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
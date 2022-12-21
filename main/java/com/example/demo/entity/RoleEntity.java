package com.example.demo.entity;

import javax.persistence.*;

@Entity
public class RoleEntity {

    @Id
    @SequenceGenerator(name = "roleSequence", sequenceName = "ROLE_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSequence")
    private Long id;

    private String name;

    public RoleEntity() {
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
}

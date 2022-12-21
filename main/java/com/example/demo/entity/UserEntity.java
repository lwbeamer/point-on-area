package com.example.demo.entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class UserEntity {
    @Id
    @SequenceGenerator(name = "userSequence", sequenceName = "USER_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
    private Long id;
    private String username;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<PointEntity> points;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;

    public UserEntity() {
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public List<PointEntity> getPoints() {
        return points;
    }

    public void setPoints(List<PointEntity> points) {
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

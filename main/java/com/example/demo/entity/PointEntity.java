package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class PointEntity {

    @Id
    @SequenceGenerator(name = "pointSequence", sequenceName = "POINT_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointSequence")
    private Long id;

    private double xValue;

    private double yValue;

    private double rValue;

    private String currentTime;
    private String executionTime;
    private boolean hitResult;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public PointEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getxValue() {
        return xValue;
    }

    public void setxValue(double xValue) {
        this.xValue = xValue;
    }

    public double getyValue() {
        return yValue;
    }

    public void setyValue(double yValue) {
        this.yValue = yValue;
    }

    public double getrValue() {
        return rValue;
    }

    public void setrValue(double rValue) {
        this.rValue = rValue;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public boolean isHitResult() {
        return hitResult;
    }

    public void setHitResult(boolean hitResult) {
        this.hitResult = hitResult;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

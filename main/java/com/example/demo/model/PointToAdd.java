package com.example.demo.model;

import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

public class PointToAdd {


    @NotNull(message = "X не может быть пустым")
    @DecimalMin(value = "-2.9999999", message = "некорректное значение x")
    @DecimalMax(value = "2.99999999", message = "некорректное значение x")
    private Double xValue;
    @NotNull(message = "Y не может быть пустым")
    @DecimalMin(value = "-4.9999999", message = "некорректное значение y")
    @DecimalMax(value = "4.99999999", message = "некорректное значение y")
    private Double yValue;
    @NotNull(message = "R не может быть пустым")
    @DecimalMin(value = "0.00000001", message = "некорректное значение r")
    @DecimalMax(value = "2.99999999", message = "некорректное значение r")
    private Double rValue;

    public PointToAdd() {
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
}

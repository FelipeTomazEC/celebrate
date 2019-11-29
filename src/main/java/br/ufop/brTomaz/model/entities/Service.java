package br.ufop.brTomaz.model.entities;

import java.io.Serializable;

public class Service implements Serializable {
    private String name;
    private Double value;
    private Integer id;
    private Enterprise enterprise;

    public Service(String name, Double value, Enterprise enterprise) {
        this.name = name;
        this.value = value;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

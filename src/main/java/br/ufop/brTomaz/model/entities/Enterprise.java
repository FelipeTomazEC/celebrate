package br.ufop.brTomaz.model.entities;

import java.util.Objects;

public class Enterprise {
    private Integer id;
    private String cnpj;
    private String phone;
    private String place;
    private String name;

    public Enterprise(String cnpj, String phone, String place, String name) {
        this.cnpj = cnpj;
        this.phone = phone;
        this.place = place;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enterprise that = (Enterprise) o;
        return Objects.equals(cnpj, that.cnpj) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(place, that.place) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj, phone, place, name);
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "cnpj=" + cnpj +
                ", phone='" + phone + '\'' +
                ", place='" + place + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

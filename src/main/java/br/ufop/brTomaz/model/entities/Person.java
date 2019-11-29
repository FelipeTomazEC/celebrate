package br.ufop.brTomaz.model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {
    private String name;
    private String cpf;
    private String email;
    private String sex;
    private Integer id;

    public Person(){}

    public Person(String name, String cpf, String email, String sex) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getSex() {
        return sex;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(cpf, person.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", cpf=" + cpf +
                ", email='" + email + '\'' +
                '}';
    }
}

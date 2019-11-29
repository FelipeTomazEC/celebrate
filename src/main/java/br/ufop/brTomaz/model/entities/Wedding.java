package br.ufop.brTomaz.model.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Wedding {
    private Integer id;
    private Marriage marriage;
    private Civil civil;
    private List<Service> services;
    private List<Person> guests;
    private List<Person> witnesses;
    private Spouse spouse1;
    private Spouse spouse2;

    public Wedding(Marriage marriage, Civil civil, Spouse spouse1, Spouse spouse2) {
        this.marriage = marriage;
        this.civil = civil;
        this.spouse1 = spouse1;
        this.spouse2 = spouse2;
        this.services = new ArrayList<>();
        this.guests = new ArrayList<>();
        this.witnesses = new ArrayList<>();

    }

    public Integer getId() {
        return id;
    }

    public Marriage getMarriage() {
        return marriage;
    }

    public Civil getCivil() {
        return civil;
    }

    public void addService(Service service) {
        this.services.add(service);
    }
    public List<Service> getServices() { return this.services; }

    public void addGuests(Person ... guests) {
        this.guests.addAll(Arrays.asList(guests));
    }

    public void addWitnesses(Person ... witnesses) {
        this.witnesses.addAll(Arrays.asList(witnesses));
    }

    public List<Person> getGuests() {
        return guests;
    }

    public List<Person> getWitnesses() {
        return witnesses;
    }

    public Spouse getSpouse1() {
        return spouse1;
    }

    public Spouse getSpouse2() {
        return spouse2;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wedding wedding = (Wedding) o;
        return Objects.equals(id, wedding.id) &&
                Objects.equals(marriage, wedding.marriage) &&
                Objects.equals(civil, wedding.civil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marriage, civil);
    }
}

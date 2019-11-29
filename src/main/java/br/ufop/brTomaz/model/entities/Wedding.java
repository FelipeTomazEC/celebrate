package br.ufop.brTomaz.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Wedding {
    private Integer id;
    private Marriage marriage;
    private Civil civil;
    private List<Service> services;

    public Wedding(Marriage marriage, Civil civil) {
        this.marriage = marriage;
        this.civil = civil;
        services = new ArrayList<>();
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

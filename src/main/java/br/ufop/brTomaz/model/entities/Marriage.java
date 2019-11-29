package br.ufop.brTomaz.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Marriage implements Serializable {
    private Integer id;
    private String place;
    private Date date;

    public Marriage(String place, Date date) {
        this.place = place;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marriage marriage = (Marriage) o;
        return Objects.equals(id, marriage.id) &&
                Objects.equals(place, marriage.place) &&
                Objects.equals(date, marriage.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, place, date);
    }
}

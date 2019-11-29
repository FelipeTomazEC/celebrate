package br.ufop.brTomaz.model.entities;

import java.util.Date;
import java.util.Objects;

public class Civil {
    private Integer id;
    private String place;
    private Date date;

    public void setId(Integer id) {
        this.id = id;
    }

    public Civil(String place, Date date) {
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
        Civil civil = (Civil) o;
        return Objects.equals(id, civil.id) &&
                Objects.equals(place, civil.place) &&
                Objects.equals(date, civil.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, place, date);
    }
}

package br.ufop.brTomaz.model.entities;

import java.util.Objects;

public class Couple {
    private Spouse spouse1;
    private Spouse spouse2;
    private Wedding wedding;

    public Couple(Spouse spouse1, Spouse spouse2, Wedding wedding) {
        this.spouse1 = spouse1;
        this.spouse2 = spouse2;
        this.wedding = wedding;
    }

    public Spouse getSpouse1() {
        return spouse1;
    }

    public Spouse getSpouse2() {
        return spouse2;
    }

    public Wedding getWedding() {
        return wedding;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Couple couple = (Couple) o;
        return Objects.equals(spouse1, couple.spouse1) &&
                Objects.equals(spouse2, couple.spouse2) &&
                Objects.equals(wedding, couple.wedding);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spouse1, spouse2, wedding);
    }
}

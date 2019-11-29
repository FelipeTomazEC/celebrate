package br.ufop.brTomaz.util;

import br.ufop.brTomaz.model.entities.*;

public class Singleton {
    private static Singleton instance = null;

    private Spouse spouse1;
    private Spouse spouse2;
    private Person witness1;
    private Person witness2;
    private Civil civil;

    public Spouse getSpouse1() {
        return spouse1;
    }

    public Spouse getSpouse2() {
        return spouse2;
    }

    private Marriage marriage;

    private Singleton(){
    }

    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }

        return instance;
    }

    public void setSpouse1(Spouse spouse1) {
        this.spouse1 = spouse1;
    }
   
    public void setSpouse2(Spouse spouse2) {
        this.spouse2 = spouse2;
    }

    public void setWitness1(Person witness1) {
        this.witness1 = witness1;
    }

    public void setWitness2(Person witness2) {
        this.witness2 = witness2;
    }

    public void setCivil(Civil civil) {
        this.civil = civil;
    }

    public void setMarriage(Marriage marriage) {
        this.marriage = marriage;
    }

    public Person getWitness1() {
        return witness1;
    }

    public Person getWitness2() {
        return witness2;
    }

    public Wedding getWedding() throws Exception {
        if(spouse1 == null || spouse2 == null){
            throw new Exception("A wedding must have a couple associated with it.");
        }
        
        if(witness1 == null || witness2 == null){
            throw new Exception("A wedding must have at least two witness.");
        }

        if(civil == null){
            throw new Exception("A wedding must have a civil marriage.");
        }

        Wedding wedding = new Wedding(this.marriage, this.civil, this.spouse1, this.spouse2);
        wedding.addWitnesses(witness1, witness2);

        return wedding;
    }
}
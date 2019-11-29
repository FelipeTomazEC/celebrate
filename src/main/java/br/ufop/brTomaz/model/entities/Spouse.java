package br.ufop.brTomaz.model.entities;

public class Spouse extends Person {
    private String password;
    private String phone;

    public Spouse(){
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Spouse(String name, String cpf, String email, String password, String phone, String sex) {
        super(name, cpf, email, sex);
        this.password = password;
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

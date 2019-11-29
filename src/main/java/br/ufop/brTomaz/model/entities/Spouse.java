package br.ufop.brTomaz.model.entities;

public class Spouse extends Person {
    private String password;
    private String phone;

    public Spouse(String name, String cpf, String email, String password, String phone) {
        super(name, cpf, email);
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

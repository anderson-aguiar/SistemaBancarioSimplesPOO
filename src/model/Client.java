package model;

public class Client {
    private String name;
    private String cpf;

    public Client() {

    }

    public Client(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
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

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Client other)) return false;
        return cpf.equals(other.cpf);
    }

    @Override
    public int hashCode() {
        return cpf.hashCode();
    }
}

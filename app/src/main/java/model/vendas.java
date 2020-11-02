package model;

public class vendas {
    private int id_vda;
    private int id_cli;
    private int id_forma;
    private String data_vda;
    private double valor_vda;

    public int getId_vda() {
        return id_vda;
    }

    public void setId_vda(int id_vda) {
        this.id_vda = id_vda;
    }

    public int getId_cli() {
        return id_cli;
    }

    public void setId_cli(int id_cli) {
        this.id_cli = id_cli;
    }

    public int getId_forma() {
        return id_forma;
    }

    public void setId_forma(int id_forma) {
        this.id_forma = id_forma;
    }

    public String getData_vda() {
        return data_vda;
    }

    public void setData_vda(String data_vda) {
        this.data_vda = data_vda;
    }

    public double getValor_vda() {
        return valor_vda;
    }

    public void setValor_vda(double valor_vda) {
        this.valor_vda = valor_vda;
    }
}

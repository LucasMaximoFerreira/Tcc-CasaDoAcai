package model;

public class it_venda {
    private int id_it_venda;
    private int id_vda;
    private int id_prod;
    private int qtd_it;
    private double total_ped;
    private String adicional;
    private int status_it_vda;


    public int getId_it() {
        return id_it_venda;
    }


    public void setId_it(int id_it) {
        this.id_it_venda = id_it;
    }

    public int getId_vda() {
        return id_vda;
    }

    public void setId_vda(int id_vda) {
        this.id_vda = id_vda;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public int getQtd_it() {
        return qtd_it;
    }

    public void setQtd_it(int qtd_it) {
        this.qtd_it = qtd_it;
    }

    public double getTotal_ped() {
        return total_ped;
    }

    public void setTotal_ped(double total_ped) {
        this.total_ped = total_ped;
    }

    public String getAdicional() {
        return adicional;
    }

    public void setAdicional(String adicional) {
        this.adicional = adicional;
    }

    public int getStatus_it_vda() {
        return status_it_vda;
    }

    public void setStatus_it_vda(int status_it_vda) {
        this.status_it_vda = status_it_vda;
    }
}

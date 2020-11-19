package dao;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.cadastro_cliente;
import model.it_venda;
import model.produto;
import model.vendas;
import utils.criptografia;
import utils.utilsCadastro_cliente;
import utils.utilsCompra;
import utils.utilsProduto;

public class conectarBD extends AsyncTask<Integer, Object, Boolean> {


    Connection conexao;

    Context tela;

    ProgressDialog dialogo;

    int op;

    ////////////////////////////////////////// - Lista ultimos pedidos

    private List<produto> listaUltPedidos = new ArrayList<produto>();

    public List<produto> getListaUltPedidos() {
        return listaUltPedidos;
    }

    public void setListaUltPedidos(List<produto> listaUltPedidos) {
        this.listaUltPedidos = listaUltPedidos;
    }
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Lista histórico

    private List<produto> listaHistorico = new ArrayList<produto>();

    public List<produto> getListaHistorico() {
        return listaHistorico;
    }

    public void setListaHistorico(List<produto> listaHistorico) {
        this.listaHistorico = listaHistorico;
    }
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Classe it_venda

    private it_venda it_vendaClasse = new it_venda();

    public it_venda getIt_vendaClasse() {
        return it_vendaClasse;
    }

    public void setIt_vendaClasse(it_venda it_vendaClasse) {
        this.it_vendaClasse = it_vendaClasse;
    }
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Classe vendas
    private vendas vendaClasse = new vendas();

    public vendas getVendaClasse() {
        return vendaClasse;
    }

    public void setVendaClasse(vendas vendaClasse) {
        this.vendaClasse = vendaClasse;
    }
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Classe produto


    private produto prodClasse = new produto();

    public produto getProdClasse() {
        return prodClasse;
    }

    public void setProdClasse(produto prodClasse) {
        this.prodClasse = prodClasse;
    }
    //////////////////////////////////////////

    //--------------------------------------//


    ////////////////////////////////////////// - Boolean Login

    private Boolean login;

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Classe Cripto

    criptografia cripto;
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Lista Acai

    private List<produto> listaAcai = new ArrayList<produto>();

    public List<produto> getListaAcai() {
        return listaAcai;
    }

    public void setListaAcai(List<produto> listaAcai) {
        this.listaAcai = listaAcai;
    }
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Lista Cremosinho

    private List<produto> listaCremosinho = new ArrayList<produto>();

    public List<produto> getListaCremosinho() {
        return listaCremosinho;
    }

    public void setListaCremosinho(List<produto> listaCremosinho) {
        this.listaCremosinho = listaCremosinho;
    }

    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Lista Geladinho

    private List<produto> listaGeladinho = new ArrayList<produto>();

    public List<produto> getListaGeladinho() {
        return listaGeladinho;
    }

    public void setListaGeladinho(List<produto> listaGeladinho) {
        this.listaGeladinho = listaGeladinho;
    }
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Lista Picole

    private List<produto> listaPicole = new ArrayList<produto>();

    public List<produto> getListaPicole() {
        return listaPicole;
    }

    public void setListaPicole(List<produto> listaPicole) {
        this.listaPicole = listaPicole;
    }
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Lista Sacole

    private List<produto> listaSacole = new ArrayList<produto>();

    public List<produto> getListaSacole() {
        return listaSacole;
    }

    public void setListaSacole(List<produto> listaSacole) {
        this.listaSacole = listaSacole;
    }
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Lista Sorvete

    private List<produto> listaSorvete = new ArrayList<produto>();

    public List<produto> getListaSorvete() {
        return listaSorvete;
    }

    public void setListaSorvete(List<produto> listaSorvete) {
        this.listaSorvete = listaSorvete;
    }
    //////////////////////////////////////////

    //--------------------------------------//

    ////////////////////////////////////////// - Clasee Cliente

    private cadastro_cliente classeCli = new cadastro_cliente();

    public cadastro_cliente getClasseCli() {
        return classeCli;
    }

    public void setClasseCli(cadastro_cliente classeCli) {
        this.classeCli = classeCli;
    }
    //////////////////////////////////////////

    public Boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
                conexao = DriverManager.getConnection("jdbc:mysql://192.168.15.22:3306/casadoacai", "root", "lucas4max");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public void disconnect() {
        try {
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public conectarBD(Context tela) {
        super();
        this.tela = tela;
        cripto = new criptografia("ETEP");
    }

    @Override
    protected Boolean doInBackground(Integer... integers) {
        Boolean resp = null;

        op = integers[0];

        connect();

        switch (op) {
            case 0:
                resp = inserir();
                break;
            case 1:
                resp = logar();
                break;
            case 2:
                resp = pesquisarPerfil();
                break;
            case 3:
                resp = alterar();
                break;
            case 4:
                resp = listarAcai();
                break;
            case 5:
                resp = listarCremosinho();
                break;
            case 6:
                resp = listarGeladinho();
                break;
            case 7:
                resp = listarPicole();
                break;
            case 8:
                resp = listarSacole();
                break;
            case 9:
                resp = listarSorvete();
                break;
            case 10:
                resp = pesqProduto();
                break;
            case 11:
                resp = inserirVenda();
                break;
            case 12:
                resp = inserirItemVenda();
                break;
            case 13:
                resp = carrinhoFinalizarCompra();
                break;
            case 14:
                resp = carrinhoCalcularCompra();
                break;
            case 15:
                resp = puxarIdCliente();
                break;
            case 16:
                resp = puxarNomeCliente();
                break;
            case 17:
                resp = listarHistorico();
                break;
            case 18:
                resp = listaUltimosPedidos();
                break;
        }

        return resp;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialogo = new ProgressDialog(tela);
        dialogo.setMessage("Aguarde conectando...");
        dialogo.show();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        switch (op) {
            case 0:
                if (aBoolean == true) {
                    Toast.makeText(tela, "cadastro ok", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(tela, "erro", Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:
                if (aBoolean == false) {
                    Toast.makeText(tela, "usuario nao cadastrado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(tela, "usuario cadastrado", Toast.LENGTH_SHORT).show();

                }
                break;
            case 3:
                if (aBoolean == true) {
                    Toast.makeText(tela, "Informações alteradas com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(tela, "erro na alteração - verifique as informações", Toast.LENGTH_SHORT).show();
                }
                break;
            case 4:
                if (aBoolean == false) {
                    Toast.makeText(tela, "Nao existe Acai", Toast.LENGTH_SHORT).show();
                }
                break;
            case 5:
                if (aBoolean == false) {
                    Toast.makeText(tela, "Nao existe Cremosinho", Toast.LENGTH_SHORT).show();
                }
                break;
            case 6:
                if (aBoolean == false) {
                    Toast.makeText(tela, "Nao existe Geladinho", Toast.LENGTH_SHORT).show();
                }
                break;
            case 7:
                if (aBoolean == false) {
                    Toast.makeText(tela, "Nao existe Picole", Toast.LENGTH_SHORT).show();
                }
                break;
            case 8:
                if (aBoolean == false) {
                    Toast.makeText(tela, "Nao existe Sacole", Toast.LENGTH_SHORT).show();
                }
                break;
            case 9:
                if (aBoolean == false) {
                    Toast.makeText(tela, "Nao existe Sorvete", Toast.LENGTH_SHORT).show();
                }
                break;
            case 10:
                if (aBoolean == false) {
                    Toast.makeText(tela, "Confirmar Pedido", Toast.LENGTH_SHORT).show();
                }
                break;
            case 11:
                if (aBoolean == true) {
                    Toast.makeText(tela, "Carrinho de Compras", Toast.LENGTH_SHORT).show();
                }
                break;
            case 12:
                if (aBoolean == true) {
                    Toast.makeText(tela, "Pedido adicionado ao carrinho", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(tela, "Pedido não adicionado ao carrinho", Toast.LENGTH_SHORT).show();

                }
                break;
            case 13:
                if (aBoolean == true) {
                    Toast.makeText(tela, "Compra efetuada com sucesso", Toast.LENGTH_SHORT).show();
                }
                break;
            case 17:
                if (aBoolean == false) {
                    Toast.makeText(tela, "Nenhuma compra Realizada!", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        dialogo.dismiss();

        disconnect();
    }

    private Boolean inserir() {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            Date dataUtil = formato.parse(classeCli.getDtnasc_cli());
            java.sql.Date dataMYSQL = new java.sql.Date(dataUtil.getTime());

            String sql = "insert into cadastro_cliente values (0,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, cripto.encrypt(classeCli.getNome_cli().getBytes()).replace("\n", ""));
            comando.setString(2, cripto.encrypt(classeCli.getSenha_cli().getBytes()).replace("\n", ""));
            comando.setString(3, cripto.encrypt(classeCli.getCpf_cli().getBytes()).replace("\n", ""));
            comando.setString(4, cripto.encrypt(classeCli.getTel_cli().getBytes()).replace("\n", ""));
            comando.setString(5, cripto.encrypt(classeCli.getCep_cli().getBytes()).replace("\n", ""));
            comando.setString(6, cripto.encrypt(classeCli.getNum_cli().getBytes()).replace("\n", ""));
            comando.setString(7, cripto.encrypt(classeCli.getComp_cli().getBytes()).replace("\n", ""));
            comando.setString(8, cripto.encrypt(classeCli.getEmail_cli().getBytes()).replace("\n", ""));
            comando.setString(9, cripto.encrypt(String.valueOf(dataUtil).getBytes()).replace("\n", ""));
            comando.setString(10, cripto.encrypt(classeCli.getGen_cli().getBytes()).replace("\n", ""));

            comando.executeUpdate();

            return true;

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    private Boolean logar() {
        try {

            String sql = "select * from cadastro_cliente where cpf_cli=? and senha_cli=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, cripto.encrypt(classeCli.getCpf_cli().getBytes()).replace("\n", ""));
            comando.setString(2, cripto.encrypt(classeCli.getSenha_cli().getBytes()).replace("\n", ""));
            ResultSet tabelamemoria = comando.executeQuery();

            if (tabelamemoria.next()) {
                login = true;
                return true;
            } else {
                login = false;
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    private Boolean pesquisarPerfil() {

        try {

            String sql = "select nome_cli, senha_cli, email_cli, cep_cli, num_cli, comp_cli, tel_cli, gen_cli from cadastro_cliente where cpf_cli=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, cripto.encrypt(classeCli.getCpf_cli().getBytes()).replace("\n", ""));
            ResultSet tabelamemoria = comando.executeQuery();

            if (tabelamemoria.next()) {
                classeCli.setNome_cli(cripto.decrypt(tabelamemoria.getString("nome_cli")));
                classeCli.setSenha_cli(cripto.decrypt(tabelamemoria.getString("senha_cli")));
                classeCli.setEmail_cli(cripto.decrypt(tabelamemoria.getString("email_cli")));
                classeCli.setCep_cli(cripto.decrypt(tabelamemoria.getString("cep_cli")));
                classeCli.setNum_cli(cripto.decrypt(tabelamemoria.getString("num_cli")));
                classeCli.setComp_cli(cripto.decrypt(tabelamemoria.getString("comp_cli")));
                classeCli.setTel_cli(cripto.decrypt(tabelamemoria.getString("tel_cli")));
                classeCli.setGen_cli(cripto.decrypt(tabelamemoria.getString("gen_cli")));
                return true;

            } else {
                classeCli = null;

                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean alterar() {
        try {


            String sql = "update cadastro_cliente set nome_cli=?, senha_cli=?, email_cli=?, " +
                    "cep_cli=?, num_cli=?, comp_cli=?, tel_cli=?, gen_cli=? where cpf_cli=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, cripto.encrypt(classeCli.getNome_cli().getBytes()).replace("\n", ""));
            comando.setString(2, cripto.encrypt(classeCli.getSenha_cli().getBytes()).replace("\n", ""));
            comando.setString(3, cripto.encrypt(classeCli.getEmail_cli().getBytes()).replace("\n", ""));
            comando.setString(4, cripto.encrypt(classeCli.getCep_cli().getBytes()).replace("\n", ""));
            comando.setString(5, cripto.encrypt(classeCli.getNum_cli().getBytes()).replace("\n", ""));
            comando.setString(6, cripto.encrypt(classeCli.getComp_cli().getBytes()).replace("\n", ""));
            comando.setString(7, cripto.encrypt(classeCli.getTel_cli().getBytes()).replace("\n", ""));
            comando.setString(8, cripto.encrypt(classeCli.getGen_cli().getBytes()).replace("\n", ""));
            comando.setString(9, cripto.encrypt(classeCli.getCpf_cli().getBytes()).replace("\n", ""));

            comando.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Boolean listarAcai() {

        try {
            String sql = "select * from produto where id_tipoProd=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, utilsProduto.getIdTipoProd());
            ResultSet tabelaMemoria = comando.executeQuery();

            while (tabelaMemoria.next()) {

                produto prodTEMP = new produto();

                prodTEMP.setId_prod(tabelaMemoria.getInt("id_prod"));
                prodTEMP.setNome_prod(cripto.decrypt(tabelaMemoria.getString("nome_prod")));
                prodTEMP.setId_tipoProd(tabelaMemoria.getInt("id_tipoProd"));
                prodTEMP.setTam_prod(cripto.decrypt(tabelaMemoria.getString("tam_prod")));
                prodTEMP.setPreco_prod(Double.parseDouble(cripto.decrypt(tabelaMemoria.getString("preco_prod"))));


                listaAcai.add(prodTEMP);

            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean listarCremosinho() {
        try {
            String sql = "select * from produto where id_tipoProd=6";
            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet tabelaMemoria = comando.executeQuery();

            while (tabelaMemoria.next()) {

                produto prodTEMP = new produto();

                prodTEMP.setId_prod(tabelaMemoria.getInt("id_prod"));
                prodTEMP.setNome_prod(cripto.decrypt(tabelaMemoria.getString("nome_prod")));
                prodTEMP.setId_tipoProd(tabelaMemoria.getInt("id_tipoProd"));
                prodTEMP.setTam_prod(cripto.decrypt(tabelaMemoria.getString("tam_prod")));
                prodTEMP.setPreco_prod(Double.parseDouble(cripto.decrypt(tabelaMemoria.getString("preco_prod"))));

                listaCremosinho.add(prodTEMP);


            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean listarSorvete() {
        try {
            String sql = "select * from produto where id_tipoProd=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, utilsProduto.getIdTipoProd());
            ResultSet tabelaMemoria = comando.executeQuery();

            while (tabelaMemoria.next()) {

                produto prodTEMP = new produto();

                prodTEMP.setId_prod(tabelaMemoria.getInt("id_prod"));
                prodTEMP.setNome_prod(cripto.decrypt(tabelaMemoria.getString("nome_prod")));
                prodTEMP.setId_tipoProd(tabelaMemoria.getInt("id_tipoProd"));
                prodTEMP.setTam_prod(cripto.decrypt(tabelaMemoria.getString("tam_prod")));
                prodTEMP.setPreco_prod(Double.parseDouble(cripto.decrypt(tabelaMemoria.getString("preco_prod"))));

                listaSorvete.add(prodTEMP);

            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean listarGeladinho() {
        try {
            String sql = "select * from produto where id_tipoProd=3";
            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet tabelaMemoria = comando.executeQuery();

            while (tabelaMemoria.next()) {

                produto prodTEMP = new produto();

                prodTEMP.setId_prod(tabelaMemoria.getInt("id_prod"));
                prodTEMP.setNome_prod(cripto.decrypt(tabelaMemoria.getString("nome_prod")));
                prodTEMP.setId_tipoProd(tabelaMemoria.getInt("id_tipoProd"));
                prodTEMP.setTam_prod(cripto.decrypt(tabelaMemoria.getString("tam_prod")));
                prodTEMP.setPreco_prod(Double.parseDouble(cripto.decrypt(tabelaMemoria.getString("preco_prod"))));

                listaGeladinho.add(prodTEMP);

            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean listarPicole() {
        try {
            String sql = "select * from produto where id_tipoProd=5";
            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet tabelaMemoria = comando.executeQuery();

            while (tabelaMemoria.next()) {

                produto prodTEMP = new produto();

                prodTEMP.setId_prod(tabelaMemoria.getInt("id_prod"));
                prodTEMP.setNome_prod(cripto.decrypt(tabelaMemoria.getString("nome_prod")));
                prodTEMP.setId_tipoProd(tabelaMemoria.getInt("id_tipoProd"));
                prodTEMP.setTam_prod(cripto.decrypt(tabelaMemoria.getString("tam_prod")));
                prodTEMP.setPreco_prod(Double.parseDouble(cripto.decrypt(tabelaMemoria.getString("preco_prod"))));

                listaPicole.add(prodTEMP);


            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean listarSacole() {
        try {
            String sql = "select * from produto where id_tipoProd=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, utilsProduto.getIdTipoProd());
            ResultSet tabelaMemoria = comando.executeQuery();

            while (tabelaMemoria.next()) {

                produto prodTEMP = new produto();

                prodTEMP.setId_prod(tabelaMemoria.getInt("id_prod"));
                prodTEMP.setNome_prod(cripto.decrypt(tabelaMemoria.getString("nome_prod")));
                prodTEMP.setId_tipoProd(tabelaMemoria.getInt("id_tipoProd"));
                prodTEMP.setTam_prod(cripto.decrypt(tabelaMemoria.getString("tam_prod")));
                prodTEMP.setPreco_prod(Double.parseDouble(cripto.decrypt(tabelaMemoria.getString("preco_prod"))));

                listaSacole.add(prodTEMP);

            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean pesqProduto() {
        try {
            String sql = "select * from produto where id_prod=? and id_tipoProd=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, prodClasse.getId_prod());
            comando.setInt(2, prodClasse.getId_tipoProd());
            ResultSet tabelaMemoria = comando.executeQuery();

            if (tabelaMemoria.next()) {
                prodClasse.setId_prod(tabelaMemoria.getInt("id_prod"));
                prodClasse.setId_tipoProd(tabelaMemoria.getInt("id_tipoProd"));
                prodClasse.setNome_prod(cripto.decrypt(tabelaMemoria.getString("nome_prod")));
                prodClasse.setTam_prod(cripto.decrypt(tabelaMemoria.getString("tam_prod")));
                prodClasse.setPreco_prod(Double.parseDouble(cripto.decrypt(tabelaMemoria.getString("preco_prod"))));

                return true;
            } else {
                prodClasse = null;
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean inserirVenda() {

        try {
            Date data = new Date(System.currentTimeMillis());

            java.sql.Date dataMySQL = new java.sql.Date(data.getTime());

            String sql = "insert into vendas values(0,?,?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, vendaClasse.getId_cli());
            comando.setInt(2, vendaClasse.getId_forma());
            comando.setString(3, cripto.encrypt(String.valueOf(dataMySQL).getBytes()).replace("\n", ""));
            comando.setString(4, cripto.encrypt(String.valueOf(vendaClasse.getValor_vda()).getBytes()).replace("\n", ""));
            comando.executeUpdate();

            String sql2 = "select max(id_vda) as ult_venda from vendas";
            PreparedStatement comando2 = conexao.prepareStatement(sql2);
            ResultSet tabelaMemoria = comando2.executeQuery();

            if (tabelaMemoria.next()) {
                utilsCompra.setUltimaVenda(tabelaMemoria.getInt("ult_venda"));

                return true;
            } else {
                return false;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean inserirItemVenda() {

        try {
            String sql = "insert into it_venda values(0,?,?,?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setInt(1, it_vendaClasse.getId_vda());
            comando.setInt(2, it_vendaClasse.getId_prod());
            comando.setString(3, cripto.encrypt(String.valueOf(it_vendaClasse.getQtd_it()).getBytes()).replace("\n", ""));
            comando.setString(4, cripto.encrypt(String.valueOf(it_vendaClasse.getTotal_ped()).getBytes()).replace("\n", ""));
            comando.setString(5, cripto.encrypt(it_vendaClasse.getAdicional().getBytes()).replace("\n", ""));


            comando.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }

    }

    public Boolean carrinhoFinalizarCompra() {
        try {
            Date data = new Date(System.currentTimeMillis());

            java.sql.Date dataMySQL = new java.sql.Date(data.getTime());

            String sql = "update vendas set id_forma=?, data_vda=?, valor_vda=? where id_vda=?";
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setInt(1, vendaClasse.getId_forma());
            comando.setString(2, cripto.encrypt(String.valueOf(dataMySQL).getBytes()).replace("\n", ""));
            comando.setString(3, cripto.encrypt(String.valueOf(vendaClasse.getValor_vda()).getBytes()).replace("\n", ""));
            comando.setInt(4, utilsCompra.getUltimaVenda());
            comando.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean carrinhoCalcularCompra() {
        try {
            String sql = "select sum(total_ped) as total from it_venda where id_vda=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, utilsCompra.getUltimaVenda());
            ResultSet tabelaMemoria = comando.executeQuery();

            if (tabelaMemoria.next()) {
                utilsCompra.setTotalCompra(tabelaMemoria.getDouble("total"));
                vendaClasse.setValor_vda(tabelaMemoria.getDouble("total"));
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean puxarIdCliente() {

        try {

            String sql = "select id_cli from cadastro_cliente where cpf_cli=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, cripto.encrypt(classeCli.getCpf_cli().getBytes()).replace("\n", ""));
            ResultSet tabelamemoria = comando.executeQuery();

            if (tabelamemoria.next()) {
                classeCli.setId_cli(tabelamemoria.getInt("id_cli"));
                return true;

            } else {
                classeCli = null;

                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean puxarNomeCliente() {

        try {

            String sql = "select nome_cli from cadastro_cliente where cpf_cli=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, cripto.encrypt(classeCli.getCpf_cli().getBytes()).replace("\n", ""));
            ResultSet tabelamemoria = comando.executeQuery();

            if (tabelamemoria.next()) {
                classeCli.setNome_cli(cripto.decrypt(tabelamemoria.getString("nome_cli")));
                return true;

            } else {
                classeCli = null;

                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Boolean listarHistorico(){

        try{
            String sql = "select p.nome_prod, p.preco_prod\n" +
                    "from produto p inner join it_venda it_v \n" +
                    "on it_v.id_prod = p.id_prod\n" +
                    "inner join vendas v\n" +
                    "on v.id_vda = it_v.id_vda\n" +
                    "inner join cadastro_cliente cli\n" +
                    "on cli.id_cli = v.id_cli\n" +
                    "where cli.id_cli = ?\n";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, utilsCadastro_cliente.getUid_cli());
            ResultSet tabelaMemoria = comando.executeQuery();

            while(tabelaMemoria.next()){

                produto prodTEMP = new produto();

                prodTEMP.setNome_prod(cripto.decrypt(tabelaMemoria.getString("nome_prod")));
                prodTEMP.setPreco_prod(Double.parseDouble(cripto.decrypt(tabelaMemoria.getString("preco_prod"))));

                listaHistorico.add(prodTEMP);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
    }
    public Boolean listaUltimosPedidos (){
        try{
            String sql = "select p.nome_prod, p.preco_prod\n" +
                    "from produto p inner join it_venda it_v \n" +
                    "on it_v.id_prod = p.id_prod\n" +
                    "inner join vendas v\n" +
                    "on v.id_vda = it_v.id_vda\n" +
                    "inner join cadastro_cliente cli\n" +
                    "on cli.id_cli = v.id_cli\n" +
                    "where cli.id_cli = ? and v.id_vda = ?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, utilsCadastro_cliente.getUid_cli());
            comando.setInt(2, utilsCompra.getUltimaVenda());
            ResultSet tabelaMemoria = comando.executeQuery();

            while(tabelaMemoria.next()){

                produto prodTEMP = new produto();

                prodTEMP.setNome_prod(cripto.decrypt(tabelaMemoria.getString("nome_prod")));
                prodTEMP.setPreco_prod(Double.parseDouble(cripto.decrypt(tabelaMemoria.getString("preco_prod"))));

                getListaUltPedidos().add(prodTEMP);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
    }



}




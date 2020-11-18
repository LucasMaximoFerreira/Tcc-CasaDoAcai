package com.example.casadoacaitcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.casadoacaitcc.Navegacao.EntrarEmContato;
import com.example.casadoacaitcc.Navegacao.Historico;
import com.example.casadoacaitcc.Navegacao.MenuProdutos;
import com.example.casadoacaitcc.Navegacao.Perfil;
import com.example.casadoacaitcc.Navegacao.SobreApp;

import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.it_venda;
import model.produto;
import model.vendas;
import utils.utilsCompra;
import utils.utilsProduto;
import utils.utilsCadastro_cliente;

public class ConfirmarPedido extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout drawerLayout;

    TextView txtAdicionais;
    EditText txtNomeProduto, txtQuantidadeDesejada;
    Button btnContinuarComprando, btnFrcharCompra, btnCancelarPedido;

    int qtd;
    Double precoFianl, precoProduto, total = 0.0, totalVenda = 0.0;


    produto prodTela = new produto();
    vendas vendaTela = new vendas();
    it_venda it_vendaTela = new it_venda();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);

        txtNomeProduto = findViewById(R.id.txtNomeProduto);
        txtAdicionais = findViewById(R.id.txtAdicionais);
        txtQuantidadeDesejada = findViewById(R.id.txtQtdDesejada);
        btnCancelarPedido = findViewById(R.id.btnCancelarPedido);
        btnContinuarComprando = findViewById(R.id.btnContinuarComprando);
        btnFrcharCompra = findViewById(R.id.btnFecharCompra);

        btnFrcharCompra.setOnClickListener(this);
        btnCancelarPedido.setOnClickListener(this);
        btnContinuarComprando.setOnClickListener(this);

        try {

            conectarBD pesq = new conectarBD(this);


            prodTela.setId_prod(utilsProduto.getIdProdSelecionado());
            prodTela.setId_tipoProd(utilsProduto.getIdTipoProd());

            pesq.setProdClasse(prodTela);

            pesq.execute(10).get();

            prodTela = pesq.getProdClasse();

            txtNomeProduto.setText(prodTela.getNome_prod());
            txtAdicionais.setText(utilsProduto.getNomeAdicionaisText());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnContinuarComprando:

                AdicionarItemCarrinho();

                Intent conitnuarComprando = new Intent(this, MenuProdutos.class);
                startActivity(conitnuarComprando);
                break;
            case R.id.btnFecharCompra:

                AdicionarItemCarrinho();

                utilsCompra.setNovaCompra("sim");

                Intent Carrinho = new Intent(this, Carrinho.class);
                startActivity(Carrinho);
                break;
            case R.id.btnCancelarPedido:

                Intent cancelarPedido = new Intent(this, MenuProdutos.class);
                startActivity(cancelarPedido);
                break;


        }
    }
    private void AdicionarItemCarrinho(){

        if(utilsCompra.getNovaCompra().equals("sim")){
            try{
                conectarBD cadastrarVenda = new conectarBD(this);
                vendaTela.setId_cli(utilsCadastro_cliente.getUid_cli());
                vendaTela.setId_forma(1);
                vendaTela.setValor_vda(0);

                cadastrarVenda.setVendaClasse(vendaTela);

                cadastrarVenda.execute(11).get();

                utilsCompra.setNovaCompra("não");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        if(prodTela.getId_prod() != 1){
            utilsProduto.setNomeAdicionais(" ");
        }
        qtd = Integer.parseInt(txtQuantidadeDesejada.getText().toString());
        precoProduto = prodTela.getPreco_prod();

        precoFianl = precoProduto * qtd;

        total = total + precoFianl;

        totalVenda = total + utilsCompra.getTotalCompra();
        utilsCompra.setTotalCompra(totalVenda);

        conectarBD cadastrarIt_venda = new conectarBD(this);

        it_vendaTela.setId_vda(utilsCompra.getUltimaVenda());
        it_vendaTela.setId_prod(utilsProduto.getIdProdSelecionado());
        it_vendaTela.setQtd_it(Integer.parseInt(txtQuantidadeDesejada.getText().toString()));
        it_vendaTela.setTotal_ped(precoFianl);
        it_vendaTela.setAdicional(utilsProduto.getNomeAdicionais());

        cadastrarIt_venda.setIt_vendaClasse(it_vendaTela);

        cadastrarIt_venda.execute(12);

    }
    ///////////////////////////////////////////////////////
    public void ClickMenu(View view){
        MenuProdutos.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        //Fechar drawer
        MenuProdutos.closeDrawer(drawerLayout);
    }

    public void ClickMenuProdutos(View view) {
        Intent MenuProd = new Intent(this, MenuProdutos.class);
        startActivity(MenuProd);

    }

    public void ClickPerfil(View view) {
        Intent MenuProd = new Intent(this, Perfil.class);
        startActivity(MenuProd);
    }



    public void ClickHistorico(View view) {
        Intent perfil = new Intent(this, Historico.class);
        startActivity(perfil);
    }

    public void ClickSobreApp(View view) {
        Intent perfil = new Intent(this, SobreApp.class);
        startActivity(perfil);
    }

    public void ClickRelatar(View view) {
        Intent perfil = new Intent(this, EntrarEmContato.class);
        startActivity(perfil);
    }
    public void ClickSair(View view) {
        Intent perfil = new Intent(this, Login.class);
        startActivity(perfil);
    }

    ///////////////////////////////////////////////////////
}
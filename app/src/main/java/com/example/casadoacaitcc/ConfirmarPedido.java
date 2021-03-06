package com.example.casadoacaitcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.casadoacaitcc.Navegacao.EntrarEmContato;
import com.example.casadoacaitcc.Navegacao.Historico;
import com.example.casadoacaitcc.Navegacao.HistoricoCompra;
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

    private TextView txtQuantidadeDesejada;
    Button btnMais, btnMenos;
    TextView txtAdicionais, lblNomeDoProduto, lblPrecoDoProduto, lblTamanhoDoProduto, lblNomeCompletoDoProduto,
            lbladicionais;
    Button btnContinuarComprando, btnFrcharCompra, btnCancelarPedido;
    ImageView imgDoProduto, imgDoProduto2, linhabranca1, linhabranca2;



    int qtd, contador, tempoTotal, novoTempo;
    Double precoFianl, precoProduto, total = 0.0, totalVenda = 0.0;


    produto prodTela = new produto();
    vendas vendaTela = new vendas();
    it_venda it_vendaTela = new it_venda();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);

        txtAdicionais = findViewById(R.id.txtAdicionais);
        txtQuantidadeDesejada = findViewById(R.id.txtQtdDesejada);
        btnCancelarPedido = findViewById(R.id.btnCancelarPedido);
        btnContinuarComprando = findViewById(R.id.btnContinuarComprando);
        btnFrcharCompra = findViewById(R.id.btnFecharCompra);
        lblNomeDoProduto = findViewById(R.id.lblNomeDoProduto);
        lblPrecoDoProduto = findViewById(R.id.lblPrecoDoProduto);
        lblTamanhoDoProduto = findViewById(R.id.lblTamanhaDoProduto);
        imgDoProduto = findViewById(R.id.imgDoProduto);
        imgDoProduto2 = findViewById(R.id.imgDoProduto2);
        btnMais = findViewById(R.id.btnMais);
        btnMenos = findViewById(R.id.btnMenos);
        linhabranca1 = findViewById(R.id.linhabranca1);
        linhabranca2 = findViewById(R.id.linhabranca2);
        lblNomeCompletoDoProduto = findViewById(R.id.lblNomeCompletoDoProduto);
        lbladicionais = findViewById(R.id.lbladicionais);

        drawerLayout = findViewById(R.id.drawer_layout);
        btnMais.setOnClickListener(this);
        btnMenos.setOnClickListener(this);
        btnFrcharCompra.setOnClickListener(this);
        btnCancelarPedido.setOnClickListener(this);
        btnContinuarComprando.setOnClickListener(this);
        contador = 1;
        txtQuantidadeDesejada.setText(String.valueOf(contador));
        tempoTotal = utilsProduto.getTempoDeEspera();

        //ALTERAR A FOTO DE ACORDO COM O TIPO DO PRODUTO
        if(utilsProduto.getIdTipoProd() == 1) {
            Glide.with(this).load(R.drawable.acaifundo).into(imgDoProduto);
            imgDoProduto2.setVisibility(View.INVISIBLE);
        }else if(utilsProduto.getIdTipoProd() == 2){
            Glide.with(this).load(R.drawable.fundosacole).into(imgDoProduto);
            imgDoProduto2.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.fundosacole).into(imgDoProduto2);
        }else if(utilsProduto.getIdTipoProd() == 3){
            Glide.with(this).load(R.drawable.fundogeladinho).into(imgDoProduto);
            imgDoProduto2.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.fundogeladinho).into(imgDoProduto2);
        }else if(utilsProduto.getIdTipoProd() == 4){
            Glide.with(this).load(R.drawable.fundosorvete).into(imgDoProduto);
            imgDoProduto2.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.fundosorvete).into(imgDoProduto2);
        }else if(utilsProduto.getIdTipoProd() == 5){
            Glide.with(this).load(R.drawable.fundopicole).into(imgDoProduto);
            imgDoProduto2.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.fundopicole).into(imgDoProduto2);
        }else if(utilsProduto.getIdTipoProd() == 6){
            Glide.with(this).load(R.drawable.fundocremosinho).into(imgDoProduto);
            imgDoProduto2.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.fundocremosinho).into(imgDoProduto2);
        }

        lblNomeDoProduto.setText(utilsProduto.getNomeTipoProd());

        try {

            conectarBD pesq = new conectarBD(this);


            prodTela.setId_prod(utilsProduto.getIdProdSelecionado());
            prodTela.setId_tipoProd(utilsProduto.getIdTipoProd());

            pesq.setProdClasse(prodTela);

            pesq.execute(10).get();

            prodTela = pesq.getProdClasse();
            lblTamanhoDoProduto.setText(prodTela.getTam_prod());

            //VIEWS PARA TIPOS DE PRODUTOS DIFERENTES
            if(utilsProduto.getIdTipoProd() == 1) {
                lbladicionais.setVisibility(View.VISIBLE);
                linhabranca1.setVisibility(View.VISIBLE);
                linhabranca2.setVisibility(View.VISIBLE);
                txtAdicionais.setVisibility(View.VISIBLE);
                txtAdicionais.setText(utilsProduto.getNomeAdicionaisText());
                lblNomeCompletoDoProduto.setVisibility(View.INVISIBLE);
            }else{
                lbladicionais.setVisibility(View.INVISIBLE);
                txtAdicionais.setVisibility(View.INVISIBLE);
                linhabranca1.setVisibility(View.INVISIBLE);
                linhabranca2.setVisibility(View.INVISIBLE);
                lblNomeCompletoDoProduto.setVisibility(View.VISIBLE);
                lblNomeCompletoDoProduto.setText(prodTela.getNome_prod());
            }
                lblPrecoDoProduto.setText(String.valueOf(prodTela.getPreco_prod()));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    //QUANTIDADE DESEJADA DO PRODUTO
    private void contadorMais(){
        contador++;
        txtQuantidadeDesejada.setText(String.valueOf(contador));
    }
    private void contadorMenos(){
        if(contador > 1){
            contador--;
            txtQuantidadeDesejada.setText(String.valueOf(contador));
        }else{
            contador = 1;
            txtQuantidadeDesejada.setText(String.valueOf(contador));
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
            case R.id.btnMais:
                contadorMais();
                break;
            case R.id.btnMenos:
                contadorMenos();
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

        if(prodTela.getId_tipoProd() != 1){
            utilsProduto.setNomeAdicionais(" ");
        }
        qtd = Integer.parseInt(txtQuantidadeDesejada.getText().toString());
        precoProduto = prodTela.getPreco_prod();

        if(utilsProduto.getIdTipoProd() == 1) {


            novoTempo = qtd * 5;
            tempoTotal = tempoTotal + novoTempo;
            utilsProduto.setTempoDeEspera(tempoTotal);
        }
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
    public void ClickMenu(View view) {
        //Abrir o Drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //Abrir o layout do Drawer
        drawerLayout.openDrawer(GravityCompat.START);
    }


    public static void closeDrawer(DrawerLayout drawerLayout) {
        //Fechar o layout do Drawer
        //Verificar condição
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            //Quando o Drawer estiver aberto
            //Fechar Drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    ///////////////////////////////////////////////////////
    public void ClickMenuProdutos(View view) {
        Intent MenuProd = new Intent(this, MenuProdutos.class);
        startActivity(MenuProd);
    }

    public void ClickPerfil(View view) {
        Intent perfil = new Intent(this, Perfil.class);
        startActivity(perfil);
    }



    public void ClickHistorico(View view) {
        Intent perfil = new Intent(this, HistoricoCompra.class);
        startActivity(perfil);
    }

    public void ClickSobreApp(View view) {
        Intent perfil = new Intent(this, SobreApp.class);
        startActivity(perfil);
    }

    public void ClickContate(View view) {
        Intent perfil = new Intent(this, EntrarEmContato.class);
        startActivity(perfil);
    }
    public void ClickSair(View view) {
        Intent perfil = new Intent(this, Login.class);
        startActivity(perfil);
    }
    ///////////////////////////////////////////////////////
}
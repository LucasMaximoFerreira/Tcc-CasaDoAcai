package com.example.casadoacaitcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.casadoacaitcc.Navegacao.EntrarEmContato;
import com.example.casadoacaitcc.Navegacao.HistoricoCompra;
import com.example.casadoacaitcc.Navegacao.MenuProdutos;
import com.example.casadoacaitcc.Navegacao.Perfil;
import com.example.casadoacaitcc.Navegacao.SobreApp;

import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.it_venda;
import model.produto;
import utils.utilsCompra;
import utils.utilsProduto;

public class DetalhesDoPedido extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout drawerLayout;

    TextView lblQtdDoPedidoRealizado, lblAdicionalDoPedidoRealizado, lblNomePedidoRealizado, lbladicionais2;
    ImageView imgDoProduto5, linhabranca3, linhabranca4;
    Button btnPedidoFeito;

    it_venda it_vendaTela = new it_venda();

    produto prodTela = new produto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_do_pedido);

        drawerLayout = findViewById(R.id.drawer_layout);

        lblAdicionalDoPedidoRealizado = findViewById(R.id.lblAdicionalDoPedidoRealizado);
        lblQtdDoPedidoRealizado = findViewById(R.id.lblQtdDoPedidoRealizado);
        lblNomePedidoRealizado = findViewById(R.id.lblNomePedidoRealizado);
        imgDoProduto5 = findViewById(R.id.imgDoProduto5);
        linhabranca3 = findViewById(R.id.linhabranca3);
        linhabranca4 = findViewById(R.id.linhabranca4);
        lbladicionais2 = findViewById(R.id.lbladicionais2);
        btnPedidoFeito = findViewById(R.id.btnPedidoFeito);

        btnPedidoFeito.setOnClickListener(this);


        try{
            conectarBD detalhes = new conectarBD(this);

            detalhes.setIt_vendaClasse(it_vendaTela);

            detalhes.setProdClasse(prodTela);
            detalhes.execute(23).get();

            prodTela = detalhes.getProdClasse();


            lblNomePedidoRealizado.setText(prodTela.getNome_prod());


            if(utilsCompra.getIdDetalhesPedido() == 2){
                imgDoProduto5.setVisibility(View.VISIBLE);
                Glide.with(this).load(R.drawable.fundosacole).into(imgDoProduto5);
            }else if(utilsCompra.getIdDetalhesPedido() == 3){
                imgDoProduto5.setVisibility(View.VISIBLE);
                Glide.with(this).load(R.drawable.fundogeladinho).into(imgDoProduto5);
            }else if(utilsCompra.getIdDetalhesPedido() == 4){
                imgDoProduto5.setVisibility(View.VISIBLE);
                Glide.with(this).load(R.drawable.fundosorvete).into(imgDoProduto5);
            }else if(utilsCompra.getIdDetalhesPedido() == 5){
                imgDoProduto5.setVisibility(View.VISIBLE);
                Glide.with(this).load(R.drawable.fundopicole).into(imgDoProduto5);
            }else if(utilsCompra.getIdDetalhesPedido() == 6){
                imgDoProduto5.setVisibility(View.VISIBLE);
                Glide.with(this).load(R.drawable.fundocremosinho).into(imgDoProduto5);
            }

            it_vendaTela = detalhes.getIt_vendaClasse();

            lblQtdDoPedidoRealizado.setText(String.valueOf(it_vendaTela.getQtd_it()));

            if(utilsCompra.getIdDetalhesPedido() == 1) {
                lbladicionais2.setVisibility(View.VISIBLE);
                linhabranca4.setVisibility(View.VISIBLE);
                linhabranca3.setVisibility(View.VISIBLE);
                imgDoProduto5.setVisibility(View.INVISIBLE);
                lblAdicionalDoPedidoRealizado.setVisibility(View.VISIBLE);
                lblAdicionalDoPedidoRealizado.setText(it_vendaTela.getAdicional());
            }else{
                lbladicionais2.setVisibility(View.INVISIBLE);
                linhabranca4.setVisibility(View.INVISIBLE);
                imgDoProduto5.setVisibility(View.VISIBLE);
                linhabranca3.setVisibility(View.INVISIBLE);
                lblAdicionalDoPedidoRealizado.setVisibility(View.INVISIBLE);

            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPedidoFeito:
                conectarBD excluirPed = new conectarBD(this);

                excluirPed.setIt_vendaClasse(it_vendaTela);

                excluirPed.execute(29);

                Intent compras = new Intent(this, PedidosDaVenda.class);
                startActivity(compras);
                break;
        }
    }


    ///////////////////////////////////////////////////////
}
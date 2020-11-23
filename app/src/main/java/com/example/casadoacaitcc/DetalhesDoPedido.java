package com.example.casadoacaitcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

public class DetalhesDoPedido extends AppCompatActivity {
    DrawerLayout drawerLayout;

    TextView lblQtdDoPedidoRealizado, lblAdicionalDoPedidoRealizado, lblNomePedidoRealizado;

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

        try{
            conectarBD detalhes = new conectarBD(this);

            detalhes.setIt_vendaClasse(it_vendaTela);

            detalhes.setProdClasse(prodTela);
            detalhes.execute(23).get();


            prodTela = detalhes.getProdClasse();

            lblNomePedidoRealizado.setText(prodTela.getNome_prod());



            it_vendaTela = detalhes.getIt_vendaClasse();

            lblQtdDoPedidoRealizado.setText(String.valueOf(it_vendaTela.getQtd_it()));
            lblAdicionalDoPedidoRealizado.setText(it_vendaTela.getAdicional());


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
    ///////////////////////////////////////////////////////
}
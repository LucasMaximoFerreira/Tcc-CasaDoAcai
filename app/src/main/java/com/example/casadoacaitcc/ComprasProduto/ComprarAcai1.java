package com.example.casadoacaitcc.ComprasProduto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.casadoacaitcc.ListaAdapter.ListaAdapterAcai;
import com.example.casadoacaitcc.Login;
import com.example.casadoacaitcc.Navegacao.EntrarEmContato;
import com.example.casadoacaitcc.Navegacao.Historico;
import com.example.casadoacaitcc.Navegacao.MenuProdutos;
import com.example.casadoacaitcc.Navegacao.Perfil;
import com.example.casadoacaitcc.Navegacao.SobreApp;
import com.example.casadoacaitcc.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.produto;
import utils.utilsProduto;

public class ComprarAcai1 extends AppCompatActivity implements AdapterView.OnItemClickListener {
    DrawerLayout drawerLayout;

    conectarBD listar;
    ListView lstAcai;

    List<produto> listaAcaiTela;
    ListaAdapterAcai adapterListaAcai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar_acai1);

        try{
            lstAcai = findViewById(R.id.lstAcai);

            listar = new conectarBD(this);
            listar.execute(4).get();

            listaAcaiTela = listar.getListaAcai();

            adapterListaAcai = new ListaAdapterAcai(listaAcaiTela,this);
            lstAcai.setAdapter(adapterListaAcai);

            lstAcai.setOnItemClickListener(this);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        produto prodSelecionado;

        prodSelecionado = (produto) adapterListaAcai.getItem(position);

        utilsProduto.setIdProdSelecionado(prodSelecionado.getId_prod());


        Intent carrinho = new Intent(this, ComprarAcai2.class);
        startActivity(carrinho);

        finish();

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
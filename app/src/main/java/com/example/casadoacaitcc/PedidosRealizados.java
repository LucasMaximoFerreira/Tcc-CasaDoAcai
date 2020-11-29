package com.example.casadoacaitcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.casadoacaitcc.ListaAdapter.ListaAdapterComprasAdm;
import com.example.casadoacaitcc.ListaAdapter.ListaHistoricoDeCompra;
import com.example.casadoacaitcc.Navegacao.EntrarEmContato;
import com.example.casadoacaitcc.Navegacao.Historico;
import com.example.casadoacaitcc.Navegacao.HistoricoCompra;
import com.example.casadoacaitcc.Navegacao.MenuProdutos;
import com.example.casadoacaitcc.Navegacao.Perfil;
import com.example.casadoacaitcc.Navegacao.SobreApp;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.vendas;
import utils.utilsCompra;

public class PedidosRealizados extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    DrawerLayout drawerLayout;

    Button btnVoltarAoMenu;
    conectarBD listar;
    ListView lstComprasAdm;

    List<vendas> listaComprasAdmTela;
    ListaAdapterComprasAdm adapterComprasAdm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_realizados);

        drawerLayout = findViewById(R.id.drawer_layout);
        btnVoltarAoMenu = findViewById(R.id.btnVoltarAoMenu);
        btnVoltarAoMenu.setOnClickListener(this);
        try{
            lstComprasAdm = findViewById(R.id.lstCompraAdm);

            listar = new conectarBD(this);
            listar.execute(21).get();

            listaComprasAdmTela = listar.getListaComprasAdm();

            adapterComprasAdm = new ListaAdapterComprasAdm(listaComprasAdmTela,this);
            lstComprasAdm.setAdapter(adapterComprasAdm);

            lstComprasAdm.setOnItemClickListener(this);

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
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        vendas vendaSelecionada;

        vendaSelecionada = (vendas) adapterComprasAdm.getItem(position);

        utilsCompra.setIdVendaSelecionada(vendaSelecionada.getId_vda());

        Intent hist = new Intent(this, PedidosDaVenda.class);
        startActivity(hist);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnVoltarAoMenu:
                Intent voltarMenu = new Intent(this, MenuProdutos.class);
                startActivity(voltarMenu);
                break;
        }
    }
}
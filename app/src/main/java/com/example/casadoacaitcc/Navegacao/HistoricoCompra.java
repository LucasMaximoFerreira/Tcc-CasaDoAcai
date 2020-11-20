package com.example.casadoacaitcc.Navegacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.casadoacaitcc.ConfirmarPedido;
import com.example.casadoacaitcc.ListaAdapter.ListaAdapterHistorico;
import com.example.casadoacaitcc.ListaAdapter.ListaHistoricoDeCompra;
import com.example.casadoacaitcc.Login;
import com.example.casadoacaitcc.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.produto;
import model.vendas;
import utils.utilsCompra;
import utils.utilsProduto;

public class HistoricoCompra extends AppCompatActivity implements AdapterView.OnItemClickListener {
    DrawerLayout drawerLayout;

    conectarBD listar;
    ListView lstHistoricoCompra;

    List<vendas> listaHistoricoDeCompraTela;
    ListaHistoricoDeCompra adapterHistoricoDeCompra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_compra);

        drawerLayout = findViewById(R.id.drawer_layout);

        try{
            lstHistoricoCompra = findViewById(R.id.lstHistoricoDeCompras);

            listar = new conectarBD(this);
            listar.execute(19).get();

            listaHistoricoDeCompraTela = listar.getListaHistoricoDeCompras();

            adapterHistoricoDeCompra = new ListaHistoricoDeCompra(listaHistoricoDeCompraTela,this);
            lstHistoricoCompra.setAdapter(adapterHistoricoDeCompra);

            lstHistoricoCompra.setOnItemClickListener(this);

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
        vendas compraSelecionada;

        compraSelecionada = (vendas) adapterHistoricoDeCompra.getItem(position);

        utilsCompra.setIdCompraSelecionada(compraSelecionada.getId_vda());

        Intent hist = new Intent(this, Historico.class);
        startActivity(hist);

        finish();
    }

    ///////////////////////////////////////////////////////
}
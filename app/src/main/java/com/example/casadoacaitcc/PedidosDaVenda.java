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
import com.example.casadoacaitcc.ListaAdapter.ListaAdapterPedidosDaVenda;
import com.example.casadoacaitcc.Navegacao.EntrarEmContato;
import com.example.casadoacaitcc.Navegacao.HistoricoCompra;
import com.example.casadoacaitcc.Navegacao.MenuProdutos;
import com.example.casadoacaitcc.Navegacao.Perfil;
import com.example.casadoacaitcc.Navegacao.SobreApp;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.it_venda;
import model.produto;
import model.vendas;
import utils.utilsCompra;

public class PedidosDaVenda extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    DrawerLayout drawerLayout;

    Button btnExcluirVenda;
    conectarBD listar;
    ListView lstIdDoPedidoRealizadoDaCompra;

    List<it_venda> listIdDoPedidoRealizadoDaCompraTela;
    ListaAdapterPedidosDaVenda adapterIdDoPedidoRealizadoDaCompra;

    vendas vendTela = new vendas();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_da_venda);

        drawerLayout = findViewById(R.id.drawer_layout);
        btnExcluirVenda = findViewById(R.id.btnExcluirVenda);

        btnExcluirVenda.setOnClickListener(this);
        vendTela = new vendas();

        try{
            lstIdDoPedidoRealizadoDaCompra = findViewById(R.id.lstPedidosRealizadosDaCompra);

            listar = new conectarBD(this);
            listar.execute(22).get();


            listIdDoPedidoRealizadoDaCompraTela = listar.getListaIdDoPedidoRealizado();

            adapterIdDoPedidoRealizadoDaCompra = new ListaAdapterPedidosDaVenda(listIdDoPedidoRealizadoDaCompraTela,this);
            lstIdDoPedidoRealizadoDaCompra.setAdapter(adapterIdDoPedidoRealizadoDaCompra);


            lstIdDoPedidoRealizadoDaCompra.setOnItemClickListener(this);

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
        it_venda pedidoSelecionado;

        pedidoSelecionado = (it_venda) adapterIdDoPedidoRealizadoDaCompra.getItem(position);

        utilsCompra.setIdPedidoRealizado(pedidoSelecionado.getId_it());



        Intent hist = new Intent(this, DetalhesDoPedido.class);
        startActivity(hist);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnExcluirVenda:
                conectarBD excluirVenda = new conectarBD(this);

                excluirVenda.setVendaClasse(vendTela);

                excluirVenda.execute(28);

                Intent compras = new Intent(this, PedidosRealizados.class);
                startActivity(compras);
                break;
        }
    }
}
package com.example.casadoacaitcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.casadoacaitcc.ListaAdapter.ListaAdapterHistorico;
import com.example.casadoacaitcc.ListaAdapter.ListaAdapterPicole;
import com.example.casadoacaitcc.ListaAdapter.ListaAdapterUltimosProdutos;
import com.example.casadoacaitcc.Navegacao.EntrarEmContato;
import com.example.casadoacaitcc.Navegacao.Historico;
import com.example.casadoacaitcc.Navegacao.HistoricoCompra;
import com.example.casadoacaitcc.Navegacao.MenuProdutos;
import com.example.casadoacaitcc.Navegacao.Perfil;
import com.example.casadoacaitcc.Navegacao.SobreApp;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.produto;
import model.vendas;
import utils.utilsCadastro_cliente;
import utils.utilsCompra;

public class Carrinho extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    DrawerLayout drawerLayout;

    TextView txtTotal;
    RadioButton rbDinheiro, rbCartao;
    RadioGroup rgForma;
    Button btnFinalizarCompra;

    vendas vendaTela = new vendas();

    conectarBD listar;
    ListView lstUltimosPedidos;

    List<produto> listaUltimosPedidosTela;
    ListaAdapterUltimosProdutos adapterListaUltimosPedidos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);



        txtTotal = findViewById(R.id.txtTotal);
        rbDinheiro = findViewById(R.id.rbDinheiro);
        rbCartao = findViewById(R.id.rbCartao);
        rgForma = findViewById(R.id.rgForma);
        btnFinalizarCompra = findViewById(R.id.btnFinalizarCompra);
        drawerLayout = findViewById(R.id.drawer_layout);
        btnFinalizarCompra.setOnClickListener(this);

        vendaTela.setValor_vda(utilsCompra.getTotalCompra());

        txtTotal.setText(new DecimalFormat("#0.00").format(vendaTela.getValor_vda()));

        try{
            lstUltimosPedidos = findViewById(R.id.lstUltimosPedidos);

            listar = new conectarBD(this);
            listar.execute(18).get();

            listaUltimosPedidosTela = listar.getListaUltPedidos();

            adapterListaUltimosPedidos = new ListaAdapterUltimosProdutos(listaUltimosPedidosTela,this);
            lstUltimosPedidos.setAdapter(adapterListaUltimosPedidos);

            lstUltimosPedidos.setOnItemClickListener(this);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnFinalizarCompra:
                utilsCompra.setNovaCompra("sim");

                int opForma = rgForma.getCheckedRadioButtonId();
                switch (opForma) {
                    case R.id.rbDinheiro:
                        vendaTela.setId_forma(1);
                        break;
                    case R.id.rbCartao:
                        vendaTela.setId_forma(2);
                        break;


                }




                conectarBD finalizar = new conectarBD(this);

                finalizar.setVendaClasse(vendaTela);

                finalizar.execute(13);

                Intent telaInicial = new Intent(this, MenuProdutos.class);
                startActivity(telaInicial);
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
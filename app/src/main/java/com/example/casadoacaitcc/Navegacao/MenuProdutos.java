package com.example.casadoacaitcc.Navegacao;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.casadoacaitcc.ComprasProduto.ComprarAcai1;
import com.example.casadoacaitcc.ComprasProduto.ComprarCremosinho;
import com.example.casadoacaitcc.ComprasProduto.ComprarGeladinho;
import com.example.casadoacaitcc.ComprasProduto.ComprarPicole;
import com.example.casadoacaitcc.ComprasProduto.ComprarSacole;
import com.example.casadoacaitcc.ComprasProduto.ComprarSorvete;
import com.example.casadoacaitcc.InserirProduto;
import com.example.casadoacaitcc.Login;
import com.example.casadoacaitcc.PedidosRealizados;
import com.example.casadoacaitcc.PesqAltExc;
import com.example.casadoacaitcc.R;


import de.hdodenhof.circleimageview.CircleImageView;
import utils.telaLogada;
import utils.utilsProduto;

public class MenuProdutos extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnAcai, btnSacole, btnGeladinho, btnSorvete, btnPicole, btnCremosinho;
    DrawerLayout drawerLayout;
    Button btnPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_produtos);
        btnAcai = findViewById(R.id.btnAcai);
        btnSacole = findViewById(R.id.btnSacole);
        btnGeladinho = findViewById(R.id.btnGeladinho);
        btnSorvete = findViewById(R.id.btnSorvete);
        btnPicole = findViewById(R.id.btnPicole);
        btnCremosinho = findViewById(R.id.btnCremosinho);
        btnPedidos = findViewById(R.id.btnPedidos);

        drawerLayout = findViewById(R.id.drawer_layout);

        if(telaLogada.getNumeroDaTela() == 1){
            btnPedidos.setVisibility(View.VISIBLE);
        }else{
            btnPedidos.setVisibility(View.INVISIBLE);
        }

        btnPedidos.setOnClickListener(this);
        btnAcai.setOnClickListener(this);
        btnSacole.setOnClickListener(this);
        btnGeladinho.setOnClickListener(this);
        btnSorvete.setOnClickListener(this);
        btnPicole.setOnClickListener(this);
        btnCremosinho.setOnClickListener(this);


    }
    public void ClickMenu(View view) {
        //Abrir o Drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //Abrir o layout do Drawer
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void CLickLogo(View view) {
        //Fechar drawer
        closeDrawer(drawerLayout);
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
        switch (v.getId()) {
            case R.id.btnPedidos:
                    Intent pedidos = new Intent(this, PedidosRealizados.class);
                    startActivity(pedidos);

                break;
            case R.id.btnAcai:
                //DEFININDO O ID DO PRODUTO
                utilsProduto.setIdTipoProd(1);
                //SETTANDO O NOME DO PRODUTO PARA VISUALIZACAO NO LABEL
                utilsProduto.setNomeTipoProd("AÇAÍ");

                //VERIFICACAO CASO SEJA ADM OU CLINTE
                if(telaLogada.getNumeroDaTela() == 1){
                    Intent inserir1 = new Intent(this, PesqAltExc.class);
                    startActivity(inserir1);
                }else {
                    Intent Acai = new Intent(this, ComprarAcai1.class);
                    startActivity(Acai);
                }
                break;
            case R.id.btnSacole:
                utilsProduto.setIdTipoProd(2);
                utilsProduto.setNomeTipoProd("SACOLÉ");
                if(telaLogada.getNumeroDaTela() == 1){
                    Intent inserir2 = new Intent(this, PesqAltExc.class);
                    startActivity(inserir2);
                }else {
                    Intent sacole = new Intent(this, ComprarSacole.class);
                    startActivity(sacole);
                }
                break;
            case R.id.btnGeladinho:
                utilsProduto.setIdTipoProd(3);
                utilsProduto.setNomeTipoProd("GELADINHO");
                if(telaLogada.getNumeroDaTela() == 1){
                    Intent inserir3 = new Intent(this, PesqAltExc.class);
                    startActivity(inserir3);
                }else {
                    Intent geladinho = new Intent(this, ComprarGeladinho.class);
                    startActivity(geladinho);
                }
                break;
            case R.id.btnSorvete:
                utilsProduto.setIdTipoProd(4);
                utilsProduto.setNomeTipoProd("SORVETE");
                if(telaLogada.getNumeroDaTela() == 1){
                    Intent inserir4 = new Intent(this, PesqAltExc.class);
                    startActivity(inserir4);
                }else {
                    Intent sorvete = new Intent(this, ComprarSorvete.class);
                    startActivity(sorvete);
                }
                break;
            case R.id.btnPicole:
                utilsProduto.setIdTipoProd(5);
                utilsProduto.setNomeTipoProd("PICOLÉ");
                if(telaLogada.getNumeroDaTela() == 1){
                    Intent inserir5 = new Intent(this, PesqAltExc.class);
                    startActivity(inserir5);
                }else {
                    Intent picole = new Intent(this, ComprarPicole.class);
                    startActivity(picole);
                }
                break;
            case R.id.btnCremosinho:
                utilsProduto.setIdTipoProd(6);
                utilsProduto.setNomeTipoProd("CREMOSINHO");
                if(telaLogada.getNumeroDaTela() == 1){
                    Intent inserir6 = new Intent(this, PesqAltExc.class);
                    startActivity(inserir6);
                }else {
                    Intent cremosinho = new Intent(this, ComprarCremosinho.class);
                    startActivity(cremosinho);
                    break;
                }
        }
    }

    ///////////////////////////////////////////////////////
}













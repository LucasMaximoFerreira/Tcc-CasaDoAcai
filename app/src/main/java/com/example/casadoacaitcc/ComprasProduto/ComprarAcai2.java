package com.example.casadoacaitcc.ComprasProduto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.casadoacaitcc.Carrinho;
import com.example.casadoacaitcc.ConfirmarPedido;
import com.example.casadoacaitcc.Login;
import com.example.casadoacaitcc.Navegacao.EntrarEmContato;
import com.example.casadoacaitcc.Navegacao.Historico;
import com.example.casadoacaitcc.Navegacao.HistoricoCompra;
import com.example.casadoacaitcc.Navegacao.MenuProdutos;
import com.example.casadoacaitcc.Navegacao.Perfil;
import com.example.casadoacaitcc.Navegacao.SobreApp;
import com.example.casadoacaitcc.R;

import model.it_venda;
import model.produto;
import utils.utilsProduto;

public class ComprarAcai2 extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout drawerLayout;

    Button btnComprarAcai2;

    CheckBox chkAmendoim, chkGranulado, chkGranola, chkLeiteEmPo, chkLeiteCondensado, chkChocobol,
            chkPacoca, chkMorango, chkChocolate, chkCaramelo, chkMenta, chkTutiFruti, chkMaracuja;

    String amendoimText, granuladoText, granolaText, leiteEmPoText, leiteCondensadoText, chocobolText, pacocaText, morangoText, chocolateText,
            carameloText, mentaText, tutiFrutiText, maracujaText, adicionaisText;

    produto prodTela = new produto();
    it_venda itVendaTela = new it_venda();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar_acai2);
        drawerLayout = findViewById(R.id.drawer_layout);
        btnComprarAcai2 = findViewById(R.id.btnComprarAcai2);

        chkAmendoim = findViewById(R.id.chkAmendoim);
        chkGranulado = findViewById(R.id.chkGranulado);
        chkGranola = findViewById(R.id.chkGranola);
        chkLeiteEmPo = findViewById(R.id.chkLeiteEmPo);
        chkLeiteCondensado = findViewById(R.id.chkLeiteCondensado);
        chkChocobol = findViewById(R.id.chkChocobol);
        chkPacoca = findViewById(R.id.chkPacoca);
        chkMorango = findViewById(R.id.chkMorango);
        chkChocolate = findViewById(R.id.chkChocolate);
        chkCaramelo = findViewById(R.id.chkCaramelo);
        chkMenta = findViewById(R.id.chkMenta);
        chkTutiFruti = findViewById(R.id.chkTutiFruti);
        chkMaracuja = findViewById(R.id.chkMaracuja);

        amendoimText = granuladoText = granolaText = leiteEmPoText = leiteCondensadoText = chocobolText = pacocaText = morangoText =
                chocolateText = carameloText = mentaText = tutiFrutiText = maracujaText = " - ";

        btnComprarAcai2.setOnClickListener(this);

        chkAmendoim.setOnClickListener(this);
        chkCaramelo.setOnClickListener(this);
        chkChocobol.setOnClickListener(this);
        chkChocolate.setOnClickListener(this);
        chkGranola.setOnClickListener(this);
        chkGranulado.setOnClickListener(this);
        chkLeiteCondensado.setOnClickListener(this);
        chkTutiFruti.setOnClickListener(this);
        chkPacoca.setOnClickListener(this);
        chkMorango.setOnClickListener(this);
        chkLeiteEmPo.setOnClickListener(this);
        chkMaracuja.setOnClickListener(this);
        chkMenta.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.chkAmendoim:
                if(chkAmendoim.isChecked()){
                    amendoimText = "~ Amendoim ~";
                }else{
                    amendoimText = " - ";
                }
                break;
            case R.id.chkGranulado:
                if(chkGranulado.isChecked()){
                    granuladoText = "~ Granulado ~";
                }else{
                    granuladoText = " - ";

                }
                break;
            case R.id.chkGranola:
                if(chkGranola.isChecked()){
                    granolaText = "~ Granola ~";

                }else{
                    granolaText = " - ";
                }
                break;
            case R.id.chkLeiteEmPo:
                if(chkLeiteEmPo.isChecked()){
                    leiteEmPoText = "~ Leite em Pó ~";

                }else{
                    leiteEmPoText = " - ";
                }
                break;
            case R.id.chkLeiteCondensado:
                if(chkLeiteCondensado.isChecked()){
                    leiteCondensadoText = "~ Leite Condensado ~";
                }else{
                    leiteCondensadoText = " - ";

                }
                break;
            case R.id.chkChocobol:
                if(chkChocobol.isChecked()){
                    chocobolText = "~ Chocobol ~";
                }else{
                    chocobolText = " - ";
                }
                break;
            case R.id.chkPacoca:
                if(chkPacoca.isChecked()){
                    pacocaText = "~ Paçoca ~";
                }else{
                    pacocaText = " - ";
                }
                break;
            case R.id.chkMorango:
                if(chkMorango.isChecked()){
                    morangoText = "~ Morango ~";
                }else{
                    morangoText = " - ";
                }
                break;
            case R.id.chkChocolate:
                if(chkChocolate.isChecked()){
                    chocolateText = "~ Chocolate ~";
                }else{
                    chocolateText = " - ";
                }
                break;
            case R.id.chkCaramelo:
                if(chkCaramelo.isChecked()){
                    carameloText = "~ Caramelo ~";
                }else{
                    carameloText = " - ";
                }
                break;
            case R.id.chkMenta:
                if(chkMenta.isChecked()){
                    mentaText = "~ Menta ~";
                }else{
                    mentaText = " - ";
                }
                break;
            case R.id.chkTutiFruti:
                if(chkTutiFruti.isChecked()){
                    tutiFrutiText = "~ Tuti-Fruti ~";
                }else{
                    tutiFrutiText = " - ";
                }
                break;
            case R.id.chkMaracuja:
                if(chkMaracuja.isChecked()){
                    maracujaText = "~ Maracujá ~";
                }else{

                    maracujaText = " - ";
                }
                break;
            case R.id.btnComprarAcai2:


                adicionaisText = amendoimText + "\n" + granuladoText + "\n" + granolaText + "\n" + leiteEmPoText + "\n" + leiteCondensadoText + "\n" +
                        chocobolText + "\n"+ pacocaText + "\n" + morangoText + "\n" + chocolateText + "\n" + carameloText + "\n" +
                        mentaText + "\n" + tutiFrutiText + "\n" + maracujaText;

                utilsProduto.setNomeAdicionaisText(adicionaisText);
                utilsProduto.setNomeAdicionais(adicionaisText);
                Intent telaProd = new Intent(this, ConfirmarPedido.class);
                startActivity(telaProd);

                break;
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


package com.example.casadoacaitcc.Navegacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.casadoacaitcc.Login;
import com.example.casadoacaitcc.R;

import utils.utilsCadastro_cliente;

public class main_nav_drawer extends AppCompatActivity {

    EditText lblNomeUsuario2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav_drawer);

        lblNomeUsuario2 = findViewById(R.id.lblNomeUsuario2);

        lblNomeUsuario2.setText(utilsCadastro_cliente.getNomePesq());

    }




    public void ClickMenuProdutos(View view) {
        Intent MenuProd = new Intent(this, MenuProdutos.class);
        startActivity(MenuProd);

    }

    public void ClickPerfil(View view) {
        Intent MenuProd = new Intent(this, Perfil.class);
        startActivity(MenuProd);
    }

    public void ClickFavoritos(View view) {
        Intent perfil = new Intent(this, Favoritos.class);
        startActivity(perfil);
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
        Intent perfil = new Intent(this, RelatarProblema.class);
        startActivity(perfil);
    }

    public void ClickSair(View view) {
        Intent perfil = new Intent(this, Login.class);
        startActivity(perfil);
    }
}
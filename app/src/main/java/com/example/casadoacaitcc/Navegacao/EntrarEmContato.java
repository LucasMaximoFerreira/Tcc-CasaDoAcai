package com.example.casadoacaitcc.Navegacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;

import com.example.casadoacaitcc.Login;
import com.example.casadoacaitcc.R;

public class EntrarEmContato extends AppCompatActivity {
    DrawerLayout drawerLayout;
    TextView lblContatenos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar_em_contato);

        lblContatenos = findViewById(R.id.lblContatenos);

        setTextCorDegrade();

    }
    private void setTextCorDegrade(){
        TextPaint paint = lblContatenos.getPaint();
        float width = paint.measureText("CONTATE-NOS");

        Shader shader = new LinearGradient(0,0,width,lblContatenos.getTextSize(),
                new int[]{
                        Color.parseColor("#9300E9"),
                        Color.parseColor("#BF0085"),


                }, null, Shader.TileMode.CLAMP);
        lblContatenos.getPaint().setShader(shader);
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
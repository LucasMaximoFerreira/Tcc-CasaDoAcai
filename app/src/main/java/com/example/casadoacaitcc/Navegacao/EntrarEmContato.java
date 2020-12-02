package com.example.casadoacaitcc.Navegacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
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
    TextView lblContatenos, estabelecimento, insta, whatsapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar_em_contato);

        lblContatenos = findViewById(R.id.lblContatenos);
        drawerLayout = findViewById(R.id.drawer_layout);
        estabelecimento = findViewById(R.id.estabelecimento);
        insta = findViewById(R.id.insta);
        whatsapp = findViewById(R.id.whatsapp);


        setTextCorDegrade();
        setTextCorDegradeEstabelecimento();
        setTextCorDegradeInsta();
        setTextCorDegradeWhats();

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
    private void setTextCorDegradeInsta(){
        TextPaint paint = insta.getPaint();
        float width = paint.measureText("Instagram");

        Shader shader = new LinearGradient(0,0,width,insta.getTextSize(),
                new int[]{
                        Color.parseColor("#9300E9"),
                        Color.parseColor("#BF0085"),


                }, null, Shader.TileMode.CLAMP);
        insta.getPaint().setShader(shader);
    }
    private void setTextCorDegradeWhats(){
        TextPaint paint = whatsapp.getPaint();
        float width = paint.measureText("whatsapp");
        Shader shader = new LinearGradient(0,0,width,whatsapp.getTextSize(),
                new int[]{
                        Color.parseColor("#9300E9"),
                        Color.parseColor("#BF0085"),


                }, null, Shader.TileMode.CLAMP);
        whatsapp.getPaint().setShader(shader);
    }
    private void setTextCorDegradeEstabelecimento(){
        TextPaint paint = estabelecimento.getPaint();
        float width = paint.measureText("estabelecimento");

        Shader shader = new LinearGradient(0,0,width,estabelecimento.getTextSize(),
                new int[]{
                        Color.parseColor("#9300E9"),
                        Color.parseColor("#BF0085"),


                }, null, Shader.TileMode.CLAMP);
        estabelecimento.getPaint().setShader(shader);
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
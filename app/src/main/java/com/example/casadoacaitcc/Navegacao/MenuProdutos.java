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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.casadoacaitcc.ComprasProduto.ComprarAcai1;
import com.example.casadoacaitcc.ComprasProduto.ComprarCremosinho;
import com.example.casadoacaitcc.ComprasProduto.ComprarGeladinho;
import com.example.casadoacaitcc.ComprasProduto.ComprarPicole;
import com.example.casadoacaitcc.ComprasProduto.ComprarSacole;
import com.example.casadoacaitcc.ComprasProduto.ComprarSorvete;
import com.example.casadoacaitcc.Login;
import com.example.casadoacaitcc.R;


import de.hdodenhof.circleimageview.CircleImageView;
import utils.utilsProduto;

public class MenuProdutos extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnAcai, btnSacole, btnGeladinho, btnSorvete, btnPicole, btnCremosinho;
    DrawerLayout drawerLayout;

    private CircleImageView imgPerfil;
    private static final int PICK_IMAGE_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    Uri imageUri;

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
        imgPerfil = findViewById(R.id.imgPerfil);

        drawerLayout = findViewById(R.id.drawer_layout);


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
        Intent perfil = new Intent(this, Historico.class);
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

    public void ClickImg(View view) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

                requestPermissions(permissions, PERMISSION_CODE);
            } else {
                pickImageFromGallery();
            }
        } else {
            pickImageFromGallery();
        }
    }

    //////// ADICIONAR FOTO PELA GALERIA
    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else{
                    Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_CODE) {
            imgPerfil.setImageURI(data.getData());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAcai:
                utilsProduto.setIdTipoProd(1);
                utilsProduto.setNomeTipoProd("AÇAÍ");

                Intent Acai = new Intent(this, ComprarAcai1.class);
                startActivity(Acai);
                break;
            case R.id.btnSacole:
                utilsProduto.setIdTipoProd(2);
                utilsProduto.setNomeTipoProd("SACOLÉ");
                Intent sacole = new Intent(this, ComprarSacole.class);
                startActivity(sacole);
                break;
            case R.id.btnGeladinho:
                utilsProduto.setIdTipoProd(3);
                utilsProduto.setNomeTipoProd("GELADINHO");

                Intent geladinho = new Intent(this, ComprarGeladinho.class);
                startActivity(geladinho);
                break;
            case R.id.btnSorvete:
                utilsProduto.setIdTipoProd(4);
                utilsProduto.setNomeTipoProd("SORVETE");

                Intent sorvete = new Intent(this, ComprarSorvete.class);
                startActivity(sorvete);
                break;
            case R.id.btnPicole:
                utilsProduto.setIdTipoProd(5);
                utilsProduto.setNomeTipoProd("PICOLÉ");

                Intent picole = new Intent(this, ComprarPicole.class);
                startActivity(picole);
                break;
            case R.id.btnCremosinho:
                utilsProduto.setIdTipoProd(6);
                utilsProduto.setNomeTipoProd("CREMOSINHO");

                Intent cremosinho = new Intent(this, ComprarCremosinho.class);
                startActivity(cremosinho);
                break;
        }
    }

    ///////////////////////////////////////////////////////
}













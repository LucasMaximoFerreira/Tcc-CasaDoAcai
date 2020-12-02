package com.example.casadoacaitcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.casadoacaitcc.Navegacao.EntrarEmContato;
import com.example.casadoacaitcc.Navegacao.HistoricoCompra;
import com.example.casadoacaitcc.Navegacao.MenuProdutos;
import com.example.casadoacaitcc.Navegacao.Perfil;
import com.example.casadoacaitcc.Navegacao.SobreApp;

import dao.conectarBD;
import model.cadastro_cliente;
import model.produto;
import utils.utilsCadastro_cliente;

public class InserirProduto extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout drawerLayout;

    EditText txtInserirTamanho, txtInserirPreco, txtInserirNomeProd;
    Button btnInserirProd;
    RadioGroup rgInserirTipoProd;
    String tamanho = "";
    RadioButton rbAcai, rbCremosinho, rbSacole, rbPicole, rbGeladinho, rbSorvete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_produto);
        drawerLayout = findViewById(R.id.drawer_layout);

        txtInserirTamanho = findViewById(R.id.txtInserirTamanho);
        txtInserirPreco = findViewById(R.id.txtInserirPreco);
        txtInserirNomeProd = findViewById(R.id.txtInserirNomeProd);
        btnInserirProd = findViewById(R.id.btnInserirProd);
        rgInserirTipoProd = findViewById(R.id.rgTipoProd);
        rbAcai = findViewById(R.id.rbAcai);
        rbCremosinho = findViewById(R.id.rbCremosinho);
        rbSacole = findViewById(R.id.rbSacole);
        rbSorvete = findViewById(R.id.rbSorvete);
        rbPicole = findViewById(R.id.rbPicole);
        rbGeladinho = findViewById(R.id.rbGeladinho);
        txtInserirTamanho.setText(tamanho);

        btnInserirProd.setOnClickListener(this);


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
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnInserirProd:
                conectarBD inserirProd = new conectarBD(this);

                produto prodTela = new produto();

                prodTela.setNome_prod(txtInserirNomeProd.getText().toString());
                prodTela.setPreco_prod(Double.parseDouble(txtInserirPreco.getText().toString()));

                int opProd = rgInserirTipoProd.getCheckedRadioButtonId();
                switch (opProd) {
                    case R.id.rbAcai:
                        prodTela.setId_tipoProd(1);
                        break;
                    case R.id.rbSacole:
                        prodTela.setId_tipoProd(2);
                        break;
                    case R.id.rbGeladinho:
                        prodTela.setId_tipoProd(3);
                        break;
                    case R.id.rbSorvete:
                        prodTela.setId_tipoProd(4);
                        break;
                    case R.id.rbPicole:
                        prodTela.setId_tipoProd(5);
                        break;
                    case R.id.rbCremosinho:
                        prodTela.setId_tipoProd(6);
                        break;

                }
                prodTela.setTam_prod(txtInserirTamanho.getText().toString());

                inserirProd.setProdClasse(prodTela);

                inserirProd.execute(26);

                Intent menu = new Intent(this, MenuProdutos.class);
                startActivity(menu);
                break;
        }

    }
}
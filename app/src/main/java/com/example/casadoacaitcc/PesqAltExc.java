package com.example.casadoacaitcc;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.casadoacaitcc.Navegacao.EntrarEmContato;
import com.example.casadoacaitcc.Navegacao.HistoricoCompra;
import com.example.casadoacaitcc.Navegacao.MenuProdutos;
import com.example.casadoacaitcc.Navegacao.Perfil;
import com.example.casadoacaitcc.Navegacao.SobreApp;

import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.cadastro_cliente;
import model.produto;
import utils.utilsProduto;

public class PesqAltExc extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout drawerLayout;

    produto prodTela;
    EditText txtPesqProd, txtAltTamanho, txtAltNomeProd, txtAltPreco;
    TextView lblInserirProd;
    RadioButton rbAltAcai, rbAltSacole, rbAltGeladinho, rbAltSorvete, rbAltPicole, rbAltCremosinho;
    RadioGroup rgAltTipoProd;
    Button btnAlterarProd, btnExcluirProd, btnPesqProduto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesq_alt_exc);
        drawerLayout = findViewById(R.id.drawer_layout);

        rgAltTipoProd = findViewById(R.id.rgAltTipoProd);
        txtPesqProd = findViewById(R.id.txtPesqProd);
        txtAltNomeProd = findViewById(R.id.txtAltNomeProd);
        txtAltTamanho = findViewById(R.id.txtAltTamanho);
        txtAltPreco = findViewById(R.id.txtAltPreco);
        lblInserirProd = findViewById(R.id.lblInserirProd);
        rbAltAcai = findViewById(R.id.rbAltAcai);
        rbAltSacole = findViewById(R.id.btnAltSacole);
        rbAltGeladinho = findViewById(R.id.btnAltGeladinho);
        rbAltSorvete = findViewById(R.id.btnAltSorvete);
        rbAltPicole = findViewById(R.id.btnAltPicole);
        rbAltCremosinho = findViewById(R.id.btnAltCremosinho);
        btnExcluirProd = findViewById(R.id.btnExcluirProd);
        btnAlterarProd = findViewById(R.id.btnAlterarProd);
        btnPesqProduto = findViewById(R.id.btnPesqProduto);

        prodTela = new produto();

        btnAlterarProd.setOnClickListener(this);
        btnExcluirProd.setOnClickListener(this);
        lblInserirProd.setOnClickListener(this);
        btnPesqProduto.setOnClickListener(this);

        setTextCorDegrade();
    }
    private void setTextCorDegrade(){
        TextPaint paint = lblInserirProd.getPaint();
        float width = paint.measureText("Deseja inserir um produto?");

        Shader shader = new LinearGradient(0,0,width,lblInserirProd.getTextSize(),
                new int[]{
                        Color.parseColor("#9300E9"),
                        Color.parseColor("#BF0085"),


                }, null, Shader.TileMode.CLAMP);
        lblInserirProd.getPaint().setShader(shader);
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

            case R.id.btnPesqProduto:
                try {

                    conectarBD pesqProd = new conectarBD(this);
                    prodTela.setNome_prod(txtPesqProd.getText().toString());

                    pesqProd.setProdClasse(prodTela);

                    pesqProd.execute(24).get();

                    prodTela = pesqProd.getProdClasse();

                    if(prodTela != null ){
                        txtAltNomeProd.setText(prodTela.getNome_prod());
                        txtAltTamanho.setText(prodTela.getTam_prod());
                        txtAltPreco.setText(String.valueOf(prodTela.getPreco_prod()));

                        if (prodTela.getId_tipoProd() == 1) {
                            rbAltAcai.setChecked(true);
                        } else if (prodTela.getId_tipoProd() == 2) {
                            rbAltSacole.setChecked(true);
                        } else if (prodTela.getId_tipoProd() == 3) {
                            rbAltGeladinho.setChecked(true);
                        } else if (prodTela.getId_tipoProd() == 4) {
                            rbAltSorvete.setChecked(true);
                        } else if (prodTela.getId_tipoProd() == 5) {
                            rbAltPicole.setChecked(true);
                        } else if (prodTela.getId_tipoProd() == 6) {
                            rbAltCremosinho.setChecked(true);
                        }

                    }else{
                        txtAltNomeProd.setText("");
                        txtAltTamanho.setText("");
                        txtAltPreco.setText("");
                        rbAltAcai.setChecked(false);
                        rbAltSacole.setChecked(false);
                        rbAltGeladinho.setChecked(false);
                        rbAltSorvete.setChecked(false);
                        rbAltPicole.setChecked(false);
                        rbAltCremosinho.setChecked(false);


                        prodTela = new produto();
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;

            case R.id.btnAlterarProd:
                conectarBD alterarProd = new conectarBD(this);

                prodTela.setNome_prod(txtAltNomeProd.getText().toString());
                prodTela.setTam_prod(txtAltTamanho.getText().toString());
                prodTela.setPreco_prod(Double.parseDouble(txtAltPreco.getText().toString()));

                int tipoProdEscolhido = rgAltTipoProd.getCheckedRadioButtonId();
                switch (tipoProdEscolhido){
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

                alterarProd.setProdClasse(prodTela);

                alterarProd.execute(25);

                txtPesqProd.setText("");
                txtAltNomeProd.setText("");
                txtAltTamanho.setText("");
                txtAltPreco.setText("");
                rbAltAcai.setChecked(false);
                rbAltSacole.setChecked(false);
                rbAltGeladinho.setChecked(false);
                rbAltSorvete.setChecked(false);
                rbAltPicole.setChecked(false);
                rbAltCremosinho.setChecked(false);

                break;

            case R.id.btnExcluirProd:
                conectarBD excluirProd = new conectarBD(this);

                excluirProd.setProdClasse(prodTela);

                excluirProd.execute(27);

                txtPesqProd.setText("");
                txtAltNomeProd.setText("");
                txtAltTamanho.setText("");
                txtAltPreco.setText("");
                rbAltAcai.setChecked(false);
                rbAltSacole.setChecked(false);
                rbAltGeladinho.setChecked(false);
                rbAltSorvete.setChecked(false);
                rbAltPicole.setChecked(false);
                rbAltCremosinho.setChecked(false);

                break;
            case R.id.lblInserirProd:
                Intent inserir = new Intent(this, InserirProduto.class);
                startActivity(inserir);
                break;

        }
    }

}
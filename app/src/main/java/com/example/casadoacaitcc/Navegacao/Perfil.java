package com.example.casadoacaitcc.Navegacao;

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

import com.example.casadoacaitcc.Login;
import com.example.casadoacaitcc.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.cadastro_cliente;
import utils.utilsCadastro_cliente;

public class Perfil extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawerLayout;

    EditText txtNovoNome, txtNovaSenha, txtNovoEmail, txtNovoCep, txtNovoNum, txtNovoComp, txtNovotTel;
    Button btnAlterar;
    RadioGroup rgNovoGen;
    RadioButton rbNovoMasc, rbNovoFemin, rbNovoPND;

    cadastro_cliente cliTela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        drawerLayout = findViewById(R.id.drawer_layout);

        txtNovoNome = findViewById(R.id.txtNovoNome);
        txtNovaSenha = findViewById(R.id.txtNovaSenha);
        txtNovoEmail = findViewById(R.id.txtNovoEmail);
        txtNovoCep = findViewById(R.id.txtNovoCep);
        txtNovoNum = findViewById(R.id.txtNovoNum);
        txtNovoComp = findViewById(R.id.txtNovoComp);
        txtNovotTel = findViewById(R.id.txtNovotTel);
        btnAlterar = findViewById(R.id.btnAlterar);
        rgNovoGen = findViewById(R.id.rgNovoGen);
        rbNovoFemin = findViewById(R.id.rbNovoFeminino);
        rbNovoMasc = findViewById(R.id.rbNovoMasculino);
        rbNovoPND = findViewById(R.id.rbNovoPND);

        cliTela = new cadastro_cliente();

        btnAlterar.setOnClickListener(this);

        try {
            conectarBD pesq = new conectarBD(this);
            cliTela.setCpf_cli(utilsCadastro_cliente.getCpfPesq());

            pesq.setClasseCli(cliTela);
            pesq.execute(2).get();

            cliTela = pesq.getClasseCli();

            if (cliTela != null) {
                txtNovoNome.setText(cliTela.getNome_cli());
                txtNovaSenha.setText(cliTela.getSenha_cli());
                txtNovoCep.setText(cliTela.getCep_cli());
                txtNovoNum.setText(cliTela.getNum_cli());
                txtNovoEmail.setText(cliTela.getEmail_cli());
                txtNovoComp.setText(cliTela.getComp_cli());
                txtNovotTel.setText(cliTela.getTel_cli());

                if (cliTela.getGen_cli().equals("M")) {
                    rbNovoMasc.setChecked(true);
                } else if (cliTela.getGen_cli().equals("F")) {
                    rbNovoFemin.setChecked(true);
                } else {
                    rbNovoPND.setChecked(true);
                }
            } else {
                txtNovoNome.setText("");
                txtNovaSenha.setText("");
                txtNovoCep.setText("");
                txtNovoNum.setText("");
                txtNovoEmail.setText("");
                txtNovoComp.setText("");
                txtNovotTel.setText("");
                rgNovoGen.clearCheck();

                cliTela = new cadastro_cliente();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //MASCARA DO CEP
        SimpleMaskFormatter novoCepMask = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher ntwNovoCep = new MaskTextWatcher(txtNovoCep, novoCepMask);
        txtNovoCep.addTextChangedListener(ntwNovoCep);

        //MASCARA DO TELEFONE
        SimpleMaskFormatter novoTelMask = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher ntwnovoTel = new MaskTextWatcher(txtNovotTel, novoTelMask);
        txtNovotTel.addTextChangedListener(ntwnovoTel);
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
        switch (v.getId()) {
            case R.id.btnAlterar:

                conectarBD alterar = new conectarBD(this);

                cliTela.setNome_cli(txtNovoNome.getText().toString());
                cliTela.setSenha_cli(txtNovaSenha.getText().toString());
                cliTela.setCep_cli(txtNovoCep.getText().toString());
                cliTela.setEmail_cli(txtNovoEmail.getText().toString());
                cliTela.setNum_cli(txtNovoNum.getText().toString());
                cliTela.setComp_cli(txtNovoComp.getText().toString());
                cliTela.setTel_cli(txtNovotTel.getText().toString());

                int genEscolhido = rgNovoGen.getCheckedRadioButtonId();
                switch (genEscolhido){
                    case R.id.rbNovoMasculino:
                        cliTela.setGen_cli("M");
                        break;
                    case R.id.rbNovoFeminino:
                        cliTela.setGen_cli("F");
                        break;
                    case R.id.rbNovoPND:
                        cliTela.setGen_cli("PND");
                        break;
                }

                alterar.setClasseCli(cliTela);

                alterar.execute(3);

                break;
        }
    }
}
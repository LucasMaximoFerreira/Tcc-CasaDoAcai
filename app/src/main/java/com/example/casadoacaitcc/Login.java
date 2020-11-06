package com.example.casadoacaitcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.casadoacaitcc.CadastroCliente.Cadastro1;
import com.example.casadoacaitcc.Navegacao.MenuProdutos;

import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.cadastro_cliente;
import utils.utilsCadastro_cliente;
import utils.utilsCompra;

public class Login extends AppCompatActivity implements View.OnClickListener {
    TextView lblCadastrar;
    EditText txtLogin, txtSenha;
    Button btnLogar;

    cadastro_cliente telaCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtLogin = findViewById(R.id.txtLogin);
        txtSenha = findViewById(R.id.txtSenha);
        lblCadastrar = findViewById(R.id.lblCadastrar);
        btnLogar = findViewById(R.id.btnLogar);

        telaCliente = new cadastro_cliente();

        btnLogar.setOnClickListener(this);
        lblCadastrar.setOnClickListener(this);

        setTextCorDegrade();
    }

    private void setTextCorDegrade(){
        TextPaint paint = lblCadastrar.getPaint();
        float width = paint.measureText("cadastre-se aqui");

        Shader shader = new LinearGradient(0,0,width,lblCadastrar.getTextSize(),
                new int[]{
                        Color.parseColor("#9300E9"),
                        Color.parseColor("#BF0085"),


                }, null, Shader.TileMode.CLAMP);
        lblCadastrar.getPaint().setShader(shader);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogar:

                try {
                    //fazer login cliente
                    conectarBD logar = new conectarBD(this);
                    telaCliente.setCpf_cli(txtLogin.getText().toString());
                    telaCliente.setSenha_cli(txtSenha.getText().toString());
                    utilsCadastro_cliente.setCpfPesq(txtLogin.getText().toString());
                    logar.setClasseCli(telaCliente);

                    //puxar id do cliente para a tabela vendas
                    conectarBD puxarId = new conectarBD(this);

                    puxarId.setClasseCli(telaCliente);
                    telaCliente.setCpf_cli(txtLogin.getText().toString());

                    puxarId.execute(15).get();

                    utilsCadastro_cliente.setUid_cli(telaCliente.getId_cli());

                    telaCliente = puxarId.getClasseCli();

                    //puxar nome cliente para o nav_drawer
                    conectarBD puxarNome = new conectarBD(this);

                    puxarNome.setClasseCli(telaCliente);

                    telaCliente.setCpf_cli(txtLogin.getText().toString());

                    puxarNome.execute(16).get();

                    utilsCadastro_cliente.setNomePesq(telaCliente.getNome_cli());

                    telaCliente = puxarNome.getClasseCli();


                    logar.execute(1).get();


                    if(logar.getLogin() == true){
                        utilsCompra.setNovaCompra("sim");
                        Intent telaLogin = new Intent(this, MenuProdutos.class);
                        startActivity(telaLogin);
                    }else{
                        telaCliente = new cadastro_cliente();
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.lblCadastrar:
                Intent telaCad = new Intent(this, Cadastro1.class);
                startActivity(telaCad);
                break;

        }

    }
}
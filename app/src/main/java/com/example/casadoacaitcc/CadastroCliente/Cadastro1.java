package com.example.casadoacaitcc.CadastroCliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.casadoacaitcc.Login;
import com.example.casadoacaitcc.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.regex.Pattern;

import utils.utilsCadastro_cliente;

public class Cadastro1 extends AppCompatActivity implements View.OnClickListener {
    private static final Pattern SENHA_VAL =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                    "(?=.*[@#$%^&+=])" +
                    "(?=\\S+$)" +
                    ".{8,}" +
                    "$");

    EditText txtNome, txtSenha, txtEmail, txtData;
    Button btnCad1, btnVerificar;
    TextView lblFacaLogin, conferir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro1);

        txtNome = findViewById(R.id.txtNome);
        txtSenha = findViewById(R.id.txtSenha);
        txtEmail = findViewById(R.id.txtEmail);
        txtData = findViewById(R.id.txtData);
        btnCad1 = findViewById(R.id.btnCad1);
        lblFacaLogin = findViewById(R.id.lblFacaLogin);
        btnCad1.setOnClickListener(this);
        lblFacaLogin.setOnClickListener(this);
        conferir = findViewById(R.id.conferir);
        conferir.setVisibility(View.INVISIBLE);




        setTextCorDegrade();

        //MASCARA DA DATA
        SimpleMaskFormatter dataMask = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher ntwData = new MaskTextWatcher(txtData, dataMask);
        txtData.addTextChangedListener(ntwData);

    }

    //FUNCAO PARA COLORIR O LABEL EM DEGRADE
    private void setTextCorDegrade() {
        TextPaint paint = lblFacaLogin.getPaint();
        float width = paint.measureText("faça o login aqui");

        Shader shader = new LinearGradient(0, 0, width, lblFacaLogin.getTextSize(),
                new int[]{
                        Color.parseColor("#9300E9"),
                        Color.parseColor("#BF0085"),


                }, null, Shader.TileMode.CLAMP);
        lblFacaLogin.getPaint().setShader(shader);
    }

    private Boolean validarSenha() {
        String senha = txtSenha.getText().toString();
        if (senha.isEmpty()) {
            txtSenha.setError("campo não pode ser vazio");
            return false;
        } else if (!SENHA_VAL.matcher(senha).matches()) {
            txtSenha.setError("Deve Possuir: " +
                    "1 Numero, " +
                    "1 letra maiuscula, " +
                    "1 letra minuscula, " +
                    "1 caractere especial, " +
                    "ao menos 8 caracteres e " +
                    "não deve conter espaços");
            return false;
        } else {
            txtSenha.setError(null);
            return true;
        }
    }

    private Boolean validarEmail() {
        String email = txtEmail.getText().toString();
        if (email.isEmpty()) {
            txtEmail.setError("Campo Obrigatório");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmail.setError("digite um email válido");
            return false;
        } else {
            txtEmail.setError(null);
            return true;
        }
    }
    private Boolean validarData() {
        String data = txtData.getText().toString();
        if (data.isEmpty()) {
            txtData.setError("Campo Obrigatório");
            return false;
        } else {
            txtData.setError(null);
            return true;
        }
    }

    private Boolean validarNome() {
        String nome = txtNome.getText().toString();
        if (nome.isEmpty()) {
        txtNome.setError("Campo Obrigatório");
        return false;
        } else {
            txtNome.setError(null);
            return true;
        }
    }




    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnCad1:

                validarSenha();
                validarEmail();
                validarData();
                validarNome();


                if(validarData() & validarEmail() & validarSenha() == true){

                    utilsCadastro_cliente.setUnome_cli(txtNome.getText().toString());
                    utilsCadastro_cliente.setUsenha_cli(txtSenha.getText().toString());
                    utilsCadastro_cliente.setUemail_cli(txtEmail.getText().toString());
                    utilsCadastro_cliente.setUdtnasc_cli(txtData.getText().toString());

                    conferir.setVisibility(View.INVISIBLE);

                    Intent telaCad2 = new Intent(this, Cadastro2.class);
                    startActivity(telaCad2);

                }else{
                    conferir.setVisibility(View.VISIBLE);
                }


                break;
            case R.id.lblFacaLogin:
                Intent login = new Intent(this, Login.class);
                startActivity(login);
                break;

        }
    }
}
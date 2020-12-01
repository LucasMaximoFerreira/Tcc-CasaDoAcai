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
                    ".{6,}" +
                    "$");

    EditText txtNome, txtSenha, txtEmail, txtData;
    Button btnCad1, btnVerificar;
    TextView lblFacaLogin;

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
        btnVerificar = findViewById(R.id.btnVerificar);

        btnVerificar.setOnClickListener(this);
        txtNome.addTextChangedListener(cadastro1TextWatcher);
        txtSenha.addTextChangedListener(cadastro1TextWatcher);
        txtEmail.addTextChangedListener(cadastro1TextWatcher);
        txtData.addTextChangedListener(cadastro1TextWatcher);


        setTextCorDegrade();

        //MASCARA DA DATA
        SimpleMaskFormatter dataMask = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher ntwData = new MaskTextWatcher(txtData, dataMask);
        txtData.addTextChangedListener(ntwData);

    }

    //FUNCAO PARA NAO CONTINUAR SE CADASTRANDO SEM COMPLETAR AS DEMAIS INFORMAÇÕES
    private TextWatcher cadastro1TextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nomeInput = txtNome.getText().toString().trim();
            String dataInput = txtData.getText().toString().trim();

            btnCad1.setEnabled(!nomeInput.isEmpty() && !dataInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

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
            btnCad1.setEnabled(false);
            return false;
        } else if (!SENHA_VAL.matcher(senha).matches()) {
            txtSenha.setError("Senha Fraca");
            btnCad1.setEnabled(false);
            return false;
        } else {
            txtSenha.setError(null);
            btnCad1.setEnabled(true);
            return true;
        }
    }

    private Boolean validarEmail() {
        String email = txtEmail.getText().toString();
        if (email.isEmpty()) {
            txtEmail.setError("campo não pode ser vazio");
            btnCad1.setEnabled(false);
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmail.setError("digite um email válido");
            btnCad1.setEnabled(false);
            return false;
        } else {
            txtEmail.setError(null);
            btnCad1.setEnabled(true);
            return true;
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnCad1:


                utilsCadastro_cliente.setUnome_cli(txtNome.getText().toString());
                utilsCadastro_cliente.setUsenha_cli(txtSenha.getText().toString());
                utilsCadastro_cliente.setUemail_cli(txtEmail.getText().toString());

                utilsCadastro_cliente.setUdtnasc_cli(txtData.getText().toString());

                validarSenha();

                Intent telaCad2 = new Intent(this, Cadastro2.class);
                startActivity(telaCad2);

                break;
            case R.id.lblFacaLogin:
                Intent login = new Intent(this, Login.class);
                startActivity(login);
                break;
            case R.id.btnVerificar:
                validarEmail();
                break;
        }
    }
}
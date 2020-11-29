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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.casadoacaitcc.Login;
import com.example.casadoacaitcc.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import utils.utilsCadastro_cliente;

public class Cadastro2 extends AppCompatActivity implements View.OnClickListener {

    EditText txtCPF, txtTelefone;
    Button btnCad2;
    RadioGroup rgGen;
    TextView lblFacaLogin2;
    RadioButton rbMasc, rbFem, rbPnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro2);

        txtCPF = findViewById(R.id.txtCPF);
        txtTelefone = findViewById(R.id.txtTelefone);
        btnCad2 = findViewById(R.id.btnCad2);
        rgGen = findViewById(R.id.rgGen);
        rbFem = findViewById(R.id.rbFeminino);
        rbMasc = findViewById(R.id.rbMasculino);
        rbPnd = findViewById(R.id.rbPND);
        lblFacaLogin2 = findViewById(R.id.lblFacaLogin2);

        txtCPF.addTextChangedListener(cadastro2TextWatcher);
        txtTelefone.addTextChangedListener(cadastro2TextWatcher);

        btnCad2.setOnClickListener(this);
        lblFacaLogin2.setOnClickListener(this);
        setTextCorDegrade();

        //MASCARA DO TELEFONE
        SimpleMaskFormatter telMask = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher ntwTel = new MaskTextWatcher(txtTelefone, telMask);
        txtTelefone.addTextChangedListener(ntwTel);

        //MASCARA DO CPF
        SimpleMaskFormatter cpfMask = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher ntwCpf = new MaskTextWatcher(txtCPF, cpfMask);
        txtCPF.addTextChangedListener(ntwCpf);

    }
    //FUNCAO PARA NAO CONTINUAR SE CADASTRANDO SEM COMPLETAR AS DEMAIS INFORMAÇÕES

    private TextWatcher cadastro2TextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String cpfInput = txtCPF.getText().toString().trim();
            String telInput = txtTelefone.getText().toString().trim();


            btnCad2.setEnabled(!cpfInput.isEmpty() && !telInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    //FUNCAO PARA COLORIR O LABEL EM DEGRADE

    private void setTextCorDegrade(){
        TextPaint paint = lblFacaLogin2.getPaint();
        float width = paint.measureText("faça o login aqui");

        Shader shader = new LinearGradient(0,0,width,lblFacaLogin2.getTextSize(),
                new int[]{
                        Color.parseColor("#9300E9"),
                        Color.parseColor("#BF0085"),


                }, null, Shader.TileMode.CLAMP);
        lblFacaLogin2.getPaint().setShader(shader);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCad2:

                utilsCadastro_cliente.setUcpf_cli(txtCPF.getText().toString());
                utilsCadastro_cliente.setUtel_cli(txtTelefone.getText().toString());

                int opGen = rgGen.getCheckedRadioButtonId();
                switch (opGen) {
                    case R.id.rbMasculino:
                        utilsCadastro_cliente.setUgen_cli("M");
                        break;
                    case R.id.rbFeminino:
                        utilsCadastro_cliente.setUgen_cli("F");
                        break;
                    case R.id.rbPND:
                        utilsCadastro_cliente.setUgen_cli("PND");
                        break;

                }

                Intent telaCad3 = new Intent(this, Cadastro3.class);
                startActivity(telaCad3);
                break;
            case R.id.lblFacaLogin2:
                Intent login2 = new Intent(this, Login.class);
                startActivity(login2);
                break;
        }
    }
}
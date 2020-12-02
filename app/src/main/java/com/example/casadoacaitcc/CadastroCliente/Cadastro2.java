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
    TextView lblFacaLogin2, lblobg, conferir3;
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
        conferir3 = findViewById(R.id.conferir3);
        lblobg = findViewById(R.id.lblobg);

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

        lblobg.setVisibility(View.INVISIBLE);
        conferir3.setVisibility(View.INVISIBLE);
    }

    private Boolean validarTelefone() {
        String tel = txtTelefone.getText().toString();
        if (tel.isEmpty()) {
            txtTelefone.setError("Campo Obrigatório");
            return false;
        } else {
            txtTelefone.setError(null);
            return true;
        }
    }
    private Boolean validarGenero() {
        if (rbFem.isChecked() == false & rbPnd.isChecked() == false & rbMasc.isChecked() == false){
            lblobg.setVisibility(View.VISIBLE);
            return false;
        } else {
            return true;
        }
    }


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

                validarGenero();
                validarTelefone();

                if(validarGenero() & validarTelefone() == true) {


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
                    conferir3.setVisibility(View.INVISIBLE);
                    lblobg.setVisibility(View.INVISIBLE);

                    Intent telaCad3 = new Intent(this, Cadastro3.class);
                    startActivity(telaCad3);
                }else{
                    conferir3.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.lblFacaLogin2:
                Intent login2 = new Intent(this, Login.class);
                startActivity(login2);
                break;
        }
    }
}
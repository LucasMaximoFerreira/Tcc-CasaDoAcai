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
import android.widget.TextView;

import com.example.casadoacaitcc.Login;
import com.example.casadoacaitcc.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import dao.conectarBD;
import model.cadastro_cliente;
import utils.utilsCadastro_cliente;

public class Cadastro3 extends AppCompatActivity implements View.OnClickListener {

    TextView lblNaoObrigatorio, lblFacaLogin3, conferir2;
    EditText txtCEP, txtNumero, txtComplemento;
    Button btnCad3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro3);

        txtCEP = findViewById(R.id.txtCEP);
        txtNumero = findViewById(R.id.txtNumero);
        txtComplemento = findViewById(R.id.txtComplemento);
        btnCad3 = findViewById(R.id.btnCad3);
        lblNaoObrigatorio = findViewById(R.id.lblNaoObrigatorio);
        lblFacaLogin3 = findViewById(R.id.lblFacaLogin3);
        btnCad3.setOnClickListener(this);
        lblFacaLogin3.setOnClickListener(this);
        conferir2 = findViewById(R.id.conferir2);

        setTextCorDegrade();
        setTextCorDegrade2();

        //MASCARA DO CEP
        SimpleMaskFormatter cepMask = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher ntwCep = new MaskTextWatcher(txtCEP, cepMask);
        txtCEP.addTextChangedListener(ntwCep);
        conferir2.setVisibility(View.INVISIBLE);
    }

    //FUNCAO PARA COLORIR O LABEL EM DEGRADE

    private void setTextCorDegrade() {
        TextPaint paint = lblNaoObrigatorio.getPaint();
        float width = paint.measureText("* não obrigatório");

        Shader shader = new LinearGradient(0, 0, width, lblNaoObrigatorio.getTextSize(),
                new int[]{
                        Color.parseColor("#9300E9"),
                        Color.parseColor("#BF0085"),


                }, null, Shader.TileMode.CLAMP);
        lblNaoObrigatorio.getPaint().setShader(shader);


    }
    //FUNCAO PARA COLORIR O LABEL EM DEGRADE

    private void setTextCorDegrade2() {
        TextPaint paint = lblFacaLogin3.getPaint();
        float width = paint.measureText("faça o login aqui");

        Shader shader = new LinearGradient(0, 0, width, lblFacaLogin3.getTextSize(),
                new int[]{
                        Color.parseColor("#9300E9"),
                        Color.parseColor("#BF0085"),


                }, null, Shader.TileMode.CLAMP);
        lblFacaLogin3.getPaint().setShader(shader);

    }

    private Boolean validarCEP() {
        String cep = txtCEP.getText().toString();
        if (cep.isEmpty()) {
            txtCEP.setError("Campo Obrigatório");
            return false;
        } else {
            txtCEP.setError(null);
            return true;
        }
    }
    private Boolean validarNumero() {
        String num = txtNumero.getText().toString();
        if (num.isEmpty()) {
            txtNumero.setError("Campo Obrigatório");
            return false;
        } else {
            txtNumero.setError(null);
            return true;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCad3:

                validarNumero();
                validarCEP();

                if(validarCEP() & validarNumero() == true){

                    conectarBD cad = new conectarBD(this);

                    cadastro_cliente clienteTela = new cadastro_cliente();

                    clienteTela.setNome_cli(utilsCadastro_cliente.getUnome_cli());
                    clienteTela.setEmail_cli(utilsCadastro_cliente.getUemail_cli());
                    clienteTela.setSenha_cli(utilsCadastro_cliente.getUsenha_cli());
                    clienteTela.setDtnasc_cli(utilsCadastro_cliente.getUdtnasc_cli());
                    clienteTela.setCpf_cli(utilsCadastro_cliente.getUcpf_cli());
                    clienteTela.setTel_cli(utilsCadastro_cliente.getUtel_cli());
                    clienteTela.setGen_cli(utilsCadastro_cliente.getUgen_cli());
                    clienteTela.setCep_cli(txtCEP.getText().toString());
                    clienteTela.setNum_cli(txtNumero.getText().toString());
                    clienteTela.setComp_cli(txtComplemento.getText().toString());


                    cad.setClasseCli(clienteTela);

                    cad.execute(0);
                    conferir2.setVisibility(View.INVISIBLE);
                    Intent login = new Intent(this, Login.class);
                    startActivity(login);
                }else{
                    conferir2.setVisibility(View.VISIBLE);

                }

                break;
            case R.id.lblFacaLogin3:
                Intent login3 = new Intent(this, Login.class);
                startActivity(login3);
                break;

        }
    }
}
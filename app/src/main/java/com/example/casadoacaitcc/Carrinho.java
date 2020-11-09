package com.example.casadoacaitcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.casadoacaitcc.Navegacao.MenuProdutos;

import java.text.DecimalFormat;
import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.vendas;
import utils.utilsCadastro_cliente;
import utils.utilsCompra;

public class Carrinho extends AppCompatActivity implements View.OnClickListener {

    EditText txtTotal, txtDinheiro;
    RadioButton rbDinheiro, rbCartao;
    RadioGroup rgForma;
    CheckBox chkTroco;
    Button btnFinalizarCompra;

    double total, troco, dinheiro;

    vendas vendaTela = new vendas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        txtTotal = findViewById(R.id.txtTotal);
        txtDinheiro = findViewById(R.id.txtDinheiro);
        rbDinheiro = findViewById(R.id.rbDinheiro);
        rbCartao = findViewById(R.id.rbCartao);
        rgForma = findViewById(R.id.rgForma);
        chkTroco = findViewById(R.id.chkTroco);
        btnFinalizarCompra = findViewById(R.id.btnFinalizarCompra);

        btnFinalizarCompra.setOnClickListener(this);
        chkTroco.setOnClickListener(this);

        vendaTela.setValor_vda(utilsCompra.getTotalCompra());

        txtTotal.setText(new DecimalFormat("#0.00").format(vendaTela.getValor_vda()));



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnFinalizarCompra:
                utilsCompra.setNovaCompra("sim");

                int opForma = rgForma.getCheckedRadioButtonId();
                switch (opForma) {
                    case R.id.rbDinheiro:
                        vendaTela.setId_forma(1);
                        break;
                    case R.id.rbCartao:
                        vendaTela.setId_forma(2);
                        break;


                }

                if(chkTroco.isChecked()){
                    total = utilsCompra.getTotalCompra();
                    dinheiro = Double.parseDouble(txtDinheiro.getText().toString().replace(',', '.'));
                    troco = dinheiro - total;
                }


                utilsCompra.setTroco(troco);

                conectarBD finalizar = new conectarBD(this);

                finalizar.setVendaClasse(vendaTela);

                finalizar.execute(13);

                Intent telaInicial = new Intent(this, MenuProdutos.class);
                startActivity(telaInicial);
                break;

        }
    }
}
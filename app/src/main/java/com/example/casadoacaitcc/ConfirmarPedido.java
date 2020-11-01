package com.example.casadoacaitcc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.produto;
import utils.utilsProduto;

public class ConfirmarPedido extends AppCompatActivity implements View.OnClickListener {

    EditText txtNomeProduto, txtQuantidadeDesejada, txtDescricao;
    Button btnContinuarComprando, btnFrcharCompra, btnCancelarPedido;

    produto prodTela = new produto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);

        txtNomeProduto = findViewById(R.id.txtNomeProduto);
        txtDescricao = findViewById(R.id.txtDescricao);
        txtQuantidadeDesejada = findViewById(R.id.txtQtdDesejada);
        btnCancelarPedido = findViewById(R.id.btnCancelarPedido);
        btnContinuarComprando = findViewById(R.id.btnContinuarComprando);
        btnFrcharCompra = findViewById(R.id.btnFecharCompra);

        btnFrcharCompra.setOnClickListener(this);
        btnCancelarPedido.setOnClickListener(this);
        btnContinuarComprando.setOnClickListener(this);

        try {

            conectarBD pesq = new conectarBD(this);


            prodTela.setId_prod(utilsProduto.getIdProdSelecionado());
            prodTela.setId_tipoProd(utilsProduto.getIdTipoProd());

            pesq.setProdClasse(prodTela);

            pesq.execute(10).get();

            prodTela = pesq.getProdClasse();

            txtNomeProduto.setText(prodTela.getNome_prod());
            txtDescricao.setText(utilsProduto.getNomeAdicionais());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

    }
}
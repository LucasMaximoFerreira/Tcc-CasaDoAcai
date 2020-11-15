package com.example.casadoacaitcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.casadoacaitcc.ListaAdapter.ListaAdapterHistorico;
import com.example.casadoacaitcc.ListaAdapter.ListaAdapterPicole;
import com.example.casadoacaitcc.ListaAdapter.ListaAdapterUltimosProdutos;
import com.example.casadoacaitcc.Navegacao.MenuProdutos;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.ExecutionException;

import dao.conectarBD;
import model.produto;
import model.vendas;
import utils.utilsCadastro_cliente;
import utils.utilsCompra;

public class Carrinho extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    EditText txtTotal, txtDinheiro;
    RadioButton rbDinheiro, rbCartao;
    RadioGroup rgForma;
    CheckBox chkTroco;
    Button btnFinalizarCompra;

    double total, troco, dinheiro;

    vendas vendaTela = new vendas();

    conectarBD listar;
    ListView lstUltimosPedidos;

    List<produto> listaUltimosPedidosTela;
    ListaAdapterUltimosProdutos adapterListaUltimosPedidos;


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

        try{
            lstUltimosPedidos = findViewById(R.id.lstUltimosPedidos);

            listar = new conectarBD(this);
            listar.execute(18).get();

            listaUltimosPedidosTela = listar.getListaUltPedidos();

            adapterListaUltimosPedidos = new ListaAdapterUltimosProdutos(listaUltimosPedidosTela,this);
            lstUltimosPedidos.setAdapter(adapterListaUltimosPedidos);

            lstUltimosPedidos.setOnItemClickListener(this);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
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
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.casadoacaitcc.Navegacao.MenuProdutos;

import utils.utilsCompra;
import utils.utilsProduto;

public class InformacoesCompra extends AppCompatActivity implements View.OnClickListener {

    TextView tempo, lblminutos, lblpedidopronto, lbltempodeespera, lblretirarpedido, cod, lblcodCompra;
    Button btnvoltarcomeco;
    ImageButton btnMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_compra);

        lblminutos = findViewById(R.id.lblminutos);
        lblpedidopronto = findViewById(R.id.lblpedidopronto);
        tempo = findViewById(R.id.tempo);
        btnvoltarcomeco = findViewById(R.id.btnvoltarcomeco);
        lblretirarpedido = findViewById(R.id.lblretirarPedido);
        lbltempodeespera = findViewById(R.id.lbltempodeespera);
        btnMaps = findViewById(R.id.btnMaps);
        cod = findViewById(R.id.cod);
        lblcodCompra = findViewById(R.id.lblcodCompra);



        lblcodCompra.setText(utilsCompra.getUltimaVenda());
        btnMaps.setOnClickListener(this);
        btnvoltarcomeco.setOnClickListener(this);

        setTextCorDegrade();
        setTextCorDegrade2();
        setTextCorDegrade3();


        if(utilsProduto.getTempoDeEspera() == 0){
            tempo.setVisibility(View.INVISIBLE);
            lblminutos.setVisibility(View.INVISIBLE);
            lblpedidopronto.setVisibility(View.VISIBLE);
        }else{
            tempo.setVisibility(View.VISIBLE);
            lblminutos.setVisibility(View.VISIBLE);
            lblpedidopronto.setVisibility(View.INVISIBLE);
            tempo.setText(String.valueOf(utilsProduto.getTempoDeEspera()));
        }


    }

    private void setTextCorDegrade(){
        TextPaint paint = lblretirarpedido.getPaint();
        float width = paint.measureText("Tempo De Espera:");

        Shader shader = new LinearGradient(0,0,width,lblretirarpedido.getTextSize(),
                new int[]{
                        Color.parseColor("#9300E9"),
                        Color.parseColor("#BF0085"),


                }, null, Shader.TileMode.CLAMP);
        lblretirarpedido.getPaint().setShader(shader);
    }
    private void setTextCorDegrade2(){
        TextPaint paint = lbltempodeespera.getPaint();
        float width = paint.measureText("Retirar seu Pedido em:");

        Shader shader = new LinearGradient(0,0,width,lbltempodeespera.getTextSize(),
                new int[]{
                        Color.parseColor("#9300E9"),
                        Color.parseColor("#BF0085"),


                }, null, Shader.TileMode.CLAMP);
        lbltempodeespera.getPaint().setShader(shader);
    }
    private void setTextCorDegrade3(){
        TextPaint paint = lblcodCompra.getPaint();
        float width = paint.measureText("Coódigo da Compra:");

        Shader shader = new LinearGradient(0,0,width,lblcodCompra.getTextSize(),
                new int[]{
                        Color.parseColor("#9300E9"),
                        Color.parseColor("#BF0085"),


                }, null, Shader.TileMode.CLAMP);
        lblcodCompra.getPaint().setShader(shader);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnvoltarcomeco:
                utilsProduto.setTempoDeEspera(0);
                Intent menuProd = new Intent(this, MenuProdutos.class);
                startActivity(menuProd);
                break;
            case R.id.btnMaps:
                Intent maps = new Intent(this, Maps.class);
                startActivity(maps);
                break;
        }
    }
}
package com.example.casadoacaitcc.ListaAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.casadoacaitcc.R;

import java.util.List;

import model.produto;
import model.vendas;

public class ListaHistoricoDeCompra extends BaseAdapter {
    private LayoutInflater telaItem;
    private List<vendas> listaHistoricoDeCompra;

    Context tela;

    public ListaHistoricoDeCompra(List<vendas> listaHistDeCompra, Context tela){
        this.telaItem = LayoutInflater.from(tela);
        this.listaHistoricoDeCompra = listaHistDeCompra;
        this.tela = tela;
    }

    @Override
    public int getCount() {
        return listaHistoricoDeCompra.size();
    }

    @Override
    public Object getItem(int position) {
        return listaHistoricoDeCompra.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaHistoricoDeCompra.get(position).getId_vda();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListaHistoricoDeCompra.ItemSuporte itemTela;

        if(convertView == null){
            convertView = telaItem.inflate(R.layout.item_historicocompra, null);

            itemTela = new ListaHistoricoDeCompra.ItemSuporte();

            itemTela.lblHistDataCompra = convertView.findViewById(R.id.lblDataDaCompra);
            itemTela.lblPrecoHistCompra = convertView.findViewById(R.id.lblPrecoDaCompra);

            convertView.setTag(itemTela);
        }else{
            itemTela = (ListaHistoricoDeCompra.ItemSuporte) convertView.getTag();
        }

        vendas vend = listaHistoricoDeCompra.get(position);

        itemTela.lblHistDataCompra.setText(vend.getData_vda());
        itemTela.lblPrecoHistCompra.setText(String.valueOf(vend.getValor_vda()));


        return convertView;
    }
    private class ItemSuporte{
        TextView lblHistDataCompra, lblPrecoHistCompra;
    }
}

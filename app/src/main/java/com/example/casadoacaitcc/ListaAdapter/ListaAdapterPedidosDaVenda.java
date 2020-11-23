package com.example.casadoacaitcc.ListaAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.casadoacaitcc.R;

import java.util.List;

import model.it_venda;
import model.produto;

public class ListaAdapterPedidosDaVenda extends BaseAdapter {


    private LayoutInflater telaItem;
    private List<it_venda> listaIdDoPedidosRealizadosDaCompra;

    Context tela;

    public ListaAdapterPedidosDaVenda(List<it_venda> listaIdDoPedRealizadosCompra, Context tela){
        this.telaItem = LayoutInflater.from(tela);
        this.listaIdDoPedidosRealizadosDaCompra = listaIdDoPedRealizadosCompra;
        this.tela = tela;
    }



    @Override
    public int getCount() {
        return listaIdDoPedidosRealizadosDaCompra.size();
    }

    @Override
    public Object getItem(int position) {
        return listaIdDoPedidosRealizadosDaCompra.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaIdDoPedidosRealizadosDaCompra.get(position).getId_it();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListaAdapterPedidosDaVenda.ItemSuporte itemTela;

        if(convertView == null){
            convertView = telaItem.inflate(R.layout.item_pedidos_realizado_da_venda, null);

            itemTela = new ListaAdapterPedidosDaVenda.ItemSuporte();

            itemTela.lblNomeProd = convertView.findViewById(R.id.lblNomeDoProd);

            convertView.setTag(itemTela);
        }else{
            itemTela = (ListaAdapterPedidosDaVenda.ItemSuporte) convertView.getTag();
        }

        it_venda it_v = listaIdDoPedidosRealizadosDaCompra.get(position);

        itemTela.lblNomeProd.setText(String.valueOf(it_v.getId_it()));


        return convertView;
    }
    private class ItemSuporte{
        TextView lblNomeProd;
    }
}

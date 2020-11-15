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

public class ListaAdapterUltimosProdutos extends BaseAdapter {

    private LayoutInflater telaItem;
    private List<produto> listaUltimoProduto;

    Context tela;

    public ListaAdapterUltimosProdutos(List<produto> listaUltimoProd, Context tela){
        this.telaItem = LayoutInflater.from(tela);
        this.listaUltimoProduto = listaUltimoProd;
        this.tela = tela;
    }


    @Override
    public int getCount() {
        return listaUltimoProduto.size();
    }

    @Override
    public Object getItem(int position) {
        return listaUltimoProduto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaUltimoProduto.get(position).getId_prod();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListaAdapterUltimosProdutos.ItemSuporte itemTela;

        if(convertView == null){
            convertView = telaItem.inflate(R.layout.item_ultimos_pedidos, null);

            itemTela = new ListaAdapterUltimosProdutos.ItemSuporte();

            itemTela.lblUltimoPedido = convertView.findViewById(R.id.lblUltimoPedido);
            itemTela.lblPrecoUltimoPedido = convertView.findViewById(R.id.lblPrecoUltimoPedido);

            convertView.setTag(itemTela);
        }else{
            itemTela = (ListaAdapterUltimosProdutos.ItemSuporte) convertView.getTag();
        }

        produto prod = listaUltimoProduto.get(position);

        itemTela.lblUltimoPedido.setText(prod.getNome_prod());
        itemTela.lblPrecoUltimoPedido.setText(String.valueOf(prod.getPreco_prod()));


        return convertView;
    }
    private class ItemSuporte{
        TextView lblUltimoPedido, lblPrecoUltimoPedido;
    }
}

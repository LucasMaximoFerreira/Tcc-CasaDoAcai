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

public class ListaAdapterHistorico extends BaseAdapter {
    private LayoutInflater telaItem;
    private List<produto> listaHistorico;

    Context tela;

    public ListaAdapterHistorico(List<produto> listaHist, Context tela){
        this.telaItem = LayoutInflater.from(tela);
        this.listaHistorico = listaHist;
        this.tela = tela;
    }

    @Override
    public int getCount() {
        return listaHistorico.size();
    }

    @Override
    public Object getItem(int position) {
        return listaHistorico.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaHistorico.get(position).getId_prod();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    ListaAdapterHistorico.ItemSuporte itemTela;

        if(convertView == null){
            convertView = telaItem.inflate(R.layout.item_historico, null);

            itemTela = new ListaAdapterHistorico.ItemSuporte();

            itemTela.lblProdHist = convertView.findViewById(R.id.lblProdHist);
            itemTela.lblPrecoProdHist = convertView.findViewById(R.id.lblPrecoProdHist);

            convertView.setTag(itemTela);
        }else{
            itemTela = (ListaAdapterHistorico.ItemSuporte) convertView.getTag();
        }

        produto prod = listaHistorico.get(position);

        itemTela.lblProdHist.setText(prod.getNome_prod());
        itemTela.lblPrecoProdHist.setText(String.valueOf(prod.getPreco_prod()));


        return convertView;
    }
    private class ItemSuporte{
        TextView lblProdHist, lblPrecoProdHist;
    }
}

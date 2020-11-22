package com.example.casadoacaitcc.ListaAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.casadoacaitcc.R;

import java.util.List;

import model.vendas;

public class ListaAdapterComprasAdm extends BaseAdapter {

    private LayoutInflater telaItem;
    private List<vendas> listaCompraAdm;

    Context tela;

    public ListaAdapterComprasAdm(List<vendas> listaCompAdm, Context tela){
        this.telaItem = LayoutInflater.from(tela);
        this.listaCompraAdm = listaCompAdm;
        this.tela = tela;
    }

    @Override
    public int getCount() {
        return listaCompraAdm.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCompraAdm.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaCompraAdm.get(position).getId_vda();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListaAdapterComprasAdm.ItemSuporte itemTela;

        if(convertView == null){
            convertView = telaItem.inflate(R.layout.item_compra_adm, null);

            itemTela = new ListaAdapterComprasAdm.ItemSuporte();

            itemTela.lblIdCompra = convertView.findViewById(R.id.lblIdDaCompra);
            itemTela.lblTotalCompraAdm = convertView.findViewById(R.id.lblPrecoTotalDaCompra);
            itemTela.lblData = convertView.findViewById(R.id.lblDataCompra);

            convertView.setTag(itemTela);
        }else{
            itemTela = (ListaAdapterComprasAdm.ItemSuporte) convertView.getTag();
        }

        vendas vend = listaCompraAdm.get(position);

        itemTela.lblIdCompra.setText(String.valueOf(vend.getId_vda()));
        itemTela.lblTotalCompraAdm.setText(String.valueOf(vend.getValor_vda()));
        itemTela.lblData.setText(vend.getData_vda());

        return convertView;
    }
    private class ItemSuporte{
        TextView lblIdCompra, lblTotalCompraAdm, lblData;
    }
}

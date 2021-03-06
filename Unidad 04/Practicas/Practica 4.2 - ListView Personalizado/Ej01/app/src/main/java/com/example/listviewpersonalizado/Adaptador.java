package com.example.listviewpersonalizado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private Context context;
    private ArrayList<ItemListview> listItems;

    public Adaptador(Context context, ArrayList<ItemListview> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemListview item = (ItemListview) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_listview,null);
        ImageView imgFoto = convertView.findViewById(R.id.imgFoto);
        TextView tvTitulo = convertView.findViewById(R.id.tvTitulo);
        TextView tvContenido = convertView.findViewById(R.id.tvContenido);

        imgFoto.setImageResource(item.getImgFoto());
        tvTitulo.setText(item.getTitulo());
        tvContenido.setText(item.getContenido());

        return convertView;
    }
}

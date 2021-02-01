package com.example.gridviewpersonalizado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorDeCiudades extends BaseAdapter {

    private Context context;
    private ArrayList<Ciudad> listItems;

    public AdaptadorDeCiudades(Context context, ArrayList<Ciudad> lista) {
        this.context = context;
        this.listItems = lista;
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
        return listItems.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Ciudad item = (Ciudad) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.grid_item,null);

        ImageView imagen = (ImageView) convertView.findViewById(R.id.iv_ciudad_detalle);
        TextView nombre = (TextView) convertView.findViewById(R.id.tv_ciudad_detalle);

        nombre.setText(item.getNombre());
        imagen.setImageResource(item.getImagen());

        return convertView;
    }
}

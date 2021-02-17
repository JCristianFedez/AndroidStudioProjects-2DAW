package com.example.proyecto2evaluacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        TextView tvDni = convertView.findViewById(R.id.tvDni);
        TextView tvNombre = convertView.findViewById(R.id.tvNombre);
        TextView tvTipo = convertView.findViewById(R.id.tvTipo);
        TextView tvcantIncidencia = convertView.findViewById(R.id.tvcantIncidencia);

        imgFoto.setImageResource(item.getImgFoto());
        tvDni.setText(item.getDni());
        tvNombre.setText(item.getNombre());
        tvTipo.setText(item.getTipo());
        tvcantIncidencia.setText(item.getCantIncidencia());
        return convertView;
    }
}

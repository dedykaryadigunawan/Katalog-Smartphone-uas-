package com.dedy.katalogsmartphone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dedy.katalogsmartphone.GenericUtility;

import java.util.List;

public class DaftarSmartphone extends ArrayAdapter<Smartphone> {
    Context context;

    public DaftarSmartphone(@NonNull Context context, @NonNull List<Smartphone> objects) {
        super(context, R.layout.row_smartphone, objects);
        this.context = context;
    }

    class ViewHolder {
        TextView txTgl;
        TextView txBrand;
        TextView txSpesifikasi;
        TextView txHarga;
        TextView txModel;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Smartphone tr = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_smartphone,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txTgl = convertView.findViewById(R.id.row_tx_tgl_favorite);
            viewHolder.txBrand = convertView.findViewById(R.id.row_tx_brand);
            viewHolder.txModel = convertView.findViewById(R.id.row_tx_model);
            viewHolder.txSpesifikasi = convertView.findViewById(R.id.row_tx_spesifikasi);
            viewHolder.txHarga = convertView.findViewById(R.id.row_tx_harga);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txTgl.setText(GenericUtility.DATE_FORMAT.format(tr.getTanggal()));
        viewHolder.txBrand.setText(tr.getBrand());
        viewHolder.txModel.setText(tr.getModel());
        viewHolder.txSpesifikasi.setText(tr.getSpesifikasi());
        viewHolder.txHarga.setText(tr.getHarga());
        return convertView;
    }
}

package com.example.unitec.parsingxml_example;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Unitec on 30/10/2017.
 */

public class AdapterTinTuc extends BaseAdapter {
    Context context;
    int layout;
    List<BaiViet> dsBaiViet;

    public AdapterTinTuc(Context context, int layout, List<BaiViet> dsBaiViet) {
        this.context = context;
        this.layout = layout;
        this.dsBaiViet = dsBaiViet;
    }

    @Override
    public int getCount() {
        return dsBaiViet.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, viewGroup, false);

        TextView TieuDe = (TextView) view.findViewById(R.id.txtTieuDe);
        TextView NoiDung = (TextView) view.findViewById(R.id.txtNoiDung);
        TextView NgayDang = (TextView) view.findViewById(R.id.txtNgaydang);
        TieuDe.setText(dsBaiViet.get(i).getTitle());
        NgayDang.setText(dsBaiViet.get(i).getPubDay());
        NoiDung.setText(dsBaiViet.get(i).getDecription());

        return view;
    }
}


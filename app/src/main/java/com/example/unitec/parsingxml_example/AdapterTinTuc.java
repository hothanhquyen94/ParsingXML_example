package com.example.unitec.parsingxml_example;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Unitec on 30/10/2017.
 */

public class AdapterTinTuc extends BaseAdapter {
    Context context;
    int layout;
    List<BaiViet> dsBaiViet;
    public class ViewHolder{
        TextView txtTieuDe,TxtNgayDang,TxtMota;
        ImageView hinhanh;
    }

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
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder();

            viewHolder.txtTieuDe  = (TextView) view.findViewById(R.id.txtTieuDe);
            viewHolder.TxtMota  = (TextView) view.findViewById(R.id.txtNoiDung);
            viewHolder.TxtNgayDang  = (TextView) view.findViewById(R.id.txtNgaydang);
            viewHolder.hinhanh  = (ImageView)view.findViewById(R.id.ImageDaiDien);

            view.setTag(viewHolder);
        }
        ViewHolder viewHolder =  (ViewHolder) view.getTag();




        viewHolder.txtTieuDe.setText(dsBaiViet.get(i).getTitle());
        viewHolder.TxtNgayDang.setText(dsBaiViet.get(i).getPubDay());
        viewHolder.TxtMota.setText(dsBaiViet.get(i).getDecription());

        String link = dsBaiViet.get(i).getImage();
        DownloadImage downloadImage = new DownloadImage();
        downloadImage.execute(link);
        try {
            viewHolder.hinhanh .setImageBitmap(downloadImage.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return view;
    }

    public class DownloadImage extends AsyncTask<String,Void,Bitmap>{
        Bitmap hinhanhdownload;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                hinhanhdownload = BitmapFactory.decodeStream(inputStream);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return hinhanhdownload;
        }
    }
}


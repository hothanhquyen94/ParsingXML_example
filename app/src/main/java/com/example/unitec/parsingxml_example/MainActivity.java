package com.example.unitec.parsingxml_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    InputStream inputStream ;
    List<BaiViet> dsBaiViet;
    BaiViet baiViet;
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        dsBaiViet = new ArrayList<BaiViet>();
        LayDuLieuXML();

        AdapterTinTuc adapterTinTuc = new AdapterTinTuc(this,R.layout.custom_layout,dsBaiViet);
        listView.setAdapter(adapterTinTuc);

    }

    private void LayDuLieuXML(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://vietnamnet.vn/rss/cong-nghe.rss");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.connect();
                    inputStream = httpURLConnection.getInputStream();
              //      InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
              //     BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
              //     StringBuilder stringBuilder = new StringBuilder();
              //     String line = "";
              //     while ((line=bufferedReader.readLine()) != null){
              //         stringBuilder.append(line);
              //     }
              //     Log.d("Du lieu",stringBuilder.toString());


                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xmlPullParser = factory.newPullParser();
                    xmlPullParser.setInput(inputStream,null);

                    int events = xmlPullParser.getEventType();
                    while ( events!= XmlPullParser.END_DOCUMENT){
                        switch (events){
                            case XmlPullParser.START_TAG:
                                String theMo = xmlPullParser.getName();
                                if(theMo.equals("item")){
                                    baiViet = new BaiViet();
                                }
                                break;

                            case  XmlPullParser.TEXT:
                               content = xmlPullParser.getText();
                                break;

                            case XmlPullParser.END_TAG:
                                String theDong = xmlPullParser.getName();
                                if(theDong.equals("title") && baiViet != null){
                                    baiViet.setTitle(content);
                                }else if (theDong.equals("description")&& baiViet != null){
                                    baiViet.setDecription(content);
                                }else if (theDong.equals("link")&& baiViet != null){
                                    baiViet.setLink(content);
                                }else if (theDong.equals("pubDate")&& baiViet != null){
                                    baiViet.setPubDay(content);
                                } else if (theDong.equals("image")&& baiViet != null){
                                    baiViet.setImage(content);
                                }else if (theDong.equals("item")&& baiViet != null){
                                    dsBaiViet.add(baiViet);
                                }
                                break;
                        }
                        events =xmlPullParser.next();
                    }



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}

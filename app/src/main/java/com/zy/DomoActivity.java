package com.zy;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zy.view.MyBigView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * <p>
 * Created by yi on 2019/12/23.
 */
public class DomoActivity extends AppCompatActivity {

    String imgURL = "http://g.hiphotos.baidu.com/image/pic/item/c2cec3fdfc03924590b2a9b58d94a4c27d1e2500.jpg";
    MyBigView myBigView;
    ImageView imageView;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        myBigView = findViewById(R.id.bigView);
        imageView = findViewById(R.id.imageView);
        new DownTask(imageView, myBigView).execute(imgURL);

    }


    @SuppressLint("StaticFieldLeak")
    class DownTask extends AsyncTask<String, Void, Bitmap> {

        private ImageView imageView;
        private MyBigView myBigView;

        public DownTask(ImageView imageView, MyBigView myBigView) {
            this.imageView = imageView;
            this.myBigView = myBigView;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(strings[0]).openConnection();
                conn.setReadTimeout(5000);
                conn.setConnectTimeout(5000);
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    return BitmapFactory.decodeStream(conn.getInputStream());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
            //myBigView.setImage(bitmap.ge);

        }
    }
}

package com.zy

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.util.LruCache
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test.*
import java.net.HttpURLConnection
import java.net.URL


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
 *
 *
 * Created by yi on 2019/12/20.
 */
class TestActivity : AppCompatActivity() {

    val img =
        "http://g.hiphotos.baidu.com/image/pic/item/c2cec3fdfc03924590b2a9b58d94a4c27d1e2500.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)


        DownTask(imageView=imageView).execute(img)


    }


    class DownTask(var imageView: ImageView) : AsyncTask<String, Void, Bitmap>() {

        override fun doInBackground(vararg params: String?): Bitmap? {
            val conn = URL(params[0]).openConnection() as HttpURLConnection
            conn.readTimeout = 5000
            conn.connectTimeout = 5000
            conn.requestMethod = "GET"
            if (conn.responseCode == HttpURLConnection.HTTP_OK) {
                return BitmapFactory.decodeStream(conn.inputStream)
            }
            return null
        }

        override fun onPostExecute(bitmap: Bitmap?) {
            super.onPostExecute(bitmap)
            imageView.setImageBitmap(bitmap)
        }
    }

    private fun lruTest() {

        val lruCache = LruCache<Int, Int>(5)
        lruCache.put(1, 1)
        lruCache.put(2, 2)
        lruCache.put(3, 3)
        lruCache.put(4, 4)
        lruCache.put(5, 5)

        for ((key, value) in lruCache.snapshot()) {
            Log.e(TAG, "$key:$value")
        }
        Log.e(TAG, "超出设定存储后")
        lruCache.put(6, 6)
        for ((key, value) in lruCache.snapshot()) {
            Log.e(TAG, "$key:$value")
        }

    }

    companion object {

        val TAG = "TestActivity"
    }
}

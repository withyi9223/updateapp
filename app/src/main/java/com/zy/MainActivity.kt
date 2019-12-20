package com.zy

import android.animation.ObjectAnimator
import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    var handlerThread: HandlerThread? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val adapter =
            object : BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1) {
                override fun convert(helper: BaseViewHolder, item: String?) {
                    helper.setText(android.R.id.text1, item)
                }

            }
        recyclerView.adapter = adapter
        adapter.setNewData(
            listOf(
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1"
            )
        )

        tv_test.setOnClickListener {
            /*val animator = ObjectAnimator.ofFloat(tv_text, "alpha", 1f, 0f, 1f)
            animator.duration = 2000
            animator.start()*/

        }
        val animator1 = ObjectAnimator.ofFloat(tv_text, "translationY", 100f, 0f)
        animator1.duration = 500
        val animator2 = ObjectAnimator.ofFloat(tv_text, "translationY", 0f, -100f)
        animator2.duration = 500
        var h = 0
        var one=true
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                h += dy
                Log.e("ddd", h.toString())
                if (h > 100) {
                    if (one) {
                        one=false
                        animator1.start()
                    }
                }else{
                    if (!one) {
                        one=true
                        animator2.start()
                    }
                }


            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

    }


    private fun initHandler() {
        handlerThread = HandlerThread("handlerThread")
        handlerThread?.start()
        val workHandler = object : Handler(handlerThread?.looper) {
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                val isMain = Looper.myLooper() == Looper.getMainLooper()
                Log.e(TAG, "收到的信息：what=" + msg?.what + ",message=" + msg?.obj.toString())
                Log.e(TAG, "IsMainThread=" + isMain + ",currentThread:" + Thread.currentThread())
            }
        }


        Thread(
            Runnable {
                val msg = Message.obtain();
                msg.what = 1;
                msg.obj = "测试信息"
                workHandler.sendMessage(msg)
                val isMain = Looper.myLooper() == Looper.getMainLooper()
                Log.e(TAG, "IsMainThread=" + isMain + ",currentThread:" + Thread.currentThread())
            }
        ).start()
    }

    override fun onDestroy() {
        super.onDestroy()
        handlerThread?.quitSafely()
    }
}

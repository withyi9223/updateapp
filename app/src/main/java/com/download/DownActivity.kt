package com.download

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.MLog
import com.zy.R
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_down.*
import java.io.*


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
 * Created by yi on 2020/3/16.
 */
class DownActivity : AppCompatActivity() {

    var url =
        "https://0324bdd18b0435e477502e73d26700b7.dd.cdntips.com/imtt.dd.qq.com/16891/apk/CB87ABEA844BE6645D6A4E1CE8550C31.apk?mkey=5e6edec2777b5d44&f=1026&fsname=com.qq.reader_7.1.7.720_146.apk&csr=1bbd&cip=119.123.123.177&proto=https"

    var disposable: Disposable? = null
    var startByte: Long = 0
    var endByte1: Long = 0
    var endByte2: Long = 0
    var total: Long = 0

    //var filetag = 0
    //lateinit var file: File //多线程下载
    lateinit var file1: File
    lateinit var file2: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_down)
        //setfile(filetag)
        file1 = File(this.cacheDir.path + "/aaa.temp")
        file2 = File(this.cacheDir.path + "/aaa.apk")
        tv_down_load.setOnClickListener {
            if (file1.isFile) {
                file1.delete()
            }
            if (file2.isFile) {
                file2.delete()
            }
            startByte = 0
            endByte1 = 0
            endByte2 = 0
            loadApk(file1, "bytes=$startByte-", startByte)

        }

        tv_stop.setOnClickListener {
            disposable?.dispose()
        }

        tv_start.setOnClickListener {
            //filetag += 1
            //setfile(filetag)
            endByte2 += endByte1
            loadApk(file1, "bytes=$endByte2-", endByte2)

        }

    }

    fun setfile(tag: Int) {
        //file = File(this.cacheDir.path + "/$tag")
    }

    fun loadApk(file: File, range: String, range1: Long) {
        AppUpdateUtil.getInstance().getiNetManager()
            .download(url, file, object : INetDownLoadCallBack {
                override fun success(apkFile: File) {
                    MLog.e(apkFile.length().toString())
                    installApk(this@DownActivity, apkFile)
                }

                override fun progress(pregress: Int) {
                    progress_bar.progress = pregress
                }

                override fun failed(throwable: Throwable) {
                    MLog.e(throwable.message)
                }

                override fun cancel(d: Disposable) {
                    disposable = d
                }

                override fun curlen(curlen: Long) {
                    MLog.e("curlen:$curlen")
                    endByte1 = curlen

                }

                override fun total(total: Long) {
                    MLog.e("total$total")

                }

            }, range, range1, file2)
    }

    private fun installApk(context: Context, file: File) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            // 由于没有在Activity环境下启动Activity,设置下面的标签
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
                //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                val apkUri = FileProvider.getUriForFile(
                    context,
                    "com.zy.fileProvider",
                    file
                )
                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive")
            } else {
                intent.setDataAndType(
                    Uri.fromFile(file),
                    "application/vnd.android.package-archive"
                )
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun mergeFiles(
        fpaths: Array<String?>?,
        resultPath: String?
    ): Boolean {
        if (fpaths == null || fpaths.size < 1 || TextUtils.isEmpty(resultPath)) {
            return false
        }
        if (fpaths.size == 1) {
            return File(fpaths[0]).renameTo(File(resultPath))
        }
        val files = arrayOfNulls<File>(fpaths.size)
        for (i in fpaths.indices) {
            files[i] = File(fpaths[i])
            if (TextUtils.isEmpty(fpaths[i]) || !files[i]!!.exists() || !files[i]!!
                    .isFile
            ) {
                return false
            }
        }
        val resultFile = File(resultPath)
        try {
            val bufSize = 1024
            val outputStream =
                BufferedOutputStream(FileOutputStream(resultFile))
            val buffer = ByteArray(bufSize)
            for (i in fpaths.indices) {
                val inputStream = BufferedInputStream(FileInputStream(files[i]))
                var readcount: Int
                while (inputStream.read(buffer).also { readcount = it } > 0) {
                    outputStream.write(buffer, 0, readcount)
                }
                inputStream.close()
            }
            outputStream.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return false
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }
        for (i in fpaths.indices) {
            files[i]!!.delete()
        }
        return true
    }

}
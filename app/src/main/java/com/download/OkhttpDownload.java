package com.download;

import com.HttpService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
 * Created by zengyi on 2019/12/1.
 */
public class OkhttpDownload implements INetManager {

    private Retrofit retrofit;
    private HttpService service;


    public OkhttpDownload() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://120.77.37.252:80/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(new OkHttpClient.Builder()
                            //.addInterceptor(addQueryParameterInterceptor)
                            .addInterceptor(new LoggerInterceptor("zengyi", true)).build())
                    .build();
        }
        if (service == null) {
            service = retrofit.create(HttpService.class);
        }

    }

    @Override
    public void get(final INetCallback callback) {
		/*service.updateApp()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread(), true)
				.unsubscribeOn(Schedulers.io())
				.subscribe(new Observer<UpdateBean>() {

					@Override
					public void onError(Throwable e) {
						e.printStackTrace();
						callback.failed(e);
					}

					@Override
					public void onComplete() {

					}

					@Override
					public void onSubscribe(Disposable d) {

					}

					@Override
					public void onNext(UpdateBean bean) {
						callback.success(bean);
					}
				});*/


    }

    @Override
    public void download(String url, final File apkFile, final INetDownLoadCallBack callBack, String range, final Long range1, final File file) {
        if (!apkFile.exists()) {
            apkFile.getParentFile().mkdirs();
        }
        service.download(url, range).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.cancel(d);
                        disposable = d;
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        callBack.failed(e);
                    }

                    @Override
                    public void onComplete() {
                        callBack.success(file);
                    }

                    @Override
                    public void onNext(final ResponseBody responseBody) {
                        InputStream inputStream = null;
                        OutputStream outputStream = null;
                        try {
                            long total = responseBody.contentLength();
                            callBack.total(total);
                            if (range1 == 0) {
                                inputStream = responseBody.byteStream();
                                outputStream = new FileOutputStream(apkFile);
                                byte[] bytes = new byte[8 * 1024];
                                long curLen = 0;
                                int bufferLen = 0;
                                while ((bufferLen = inputStream.read(bytes)) != -1) {
                                    curLen += bufferLen;
                                    outputStream.write(bytes, 0, bufferLen);
                                    outputStream.flush();
                                    callBack.progress((int) (curLen * 1.0f / total * 100));
                                    callBack.curlen(curLen);
                                }
                            } else {
                                RandomAccessFile rTempFile = new RandomAccessFile(apkFile, "rw");
                                rTempFile.seek(range1);
                                inputStream = responseBody.byteStream();
                                byte[] bytes = new byte[8 * 1024];
                                long curLen = 0;
                                int bufferLen = 0;
                                while ((bufferLen = inputStream.read(bytes)) != -1) {
                                    curLen += bufferLen;
                                    rTempFile.write(bytes, 0, bufferLen);
                                    callBack.progress((int) (curLen * 1.0f / total * 100));
                                    callBack.curlen(curLen);
                                }
                                if (file.exists()) {
                                    file.delete();
                                } else {
                                    file.createNewFile();
                                }
                                apkFile.renameTo(file);
								
                            }

                        } catch (Throwable e) {
                            e.printStackTrace();
                            callBack.failed(e);
                        } finally {
                            try {
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                    }

                });

    }

    /**
     * 公共参数
     */
	/*private static Interceptor addQueryParameterInterceptor = chain -> {
		Request originalRequest = chain.request();
		Request request;
		//String method = originalRequest.method();
		//Headers headers = originalRequest.headers();
		HttpUrl modifiedUrl = originalRequest.url().newBuilder()
				// Provide your custom parameter here
				.addQueryParameter("userId", UserCache.getInstance().getUserId())
				.addQueryParameter("userName", UserCache.getInstance().getUserName())
				.addQueryParameter("token", UserCache.getInstance().getToken())
				.build();
		request = originalRequest.newBuilder().url(modifiedUrl).build();

		return chain.proceed(request);
	};*/
}

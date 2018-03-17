package com.example.martin.mymvp.net;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * 网络访问入口
 */
public class NetClient {
    //超时时间
    private static final int TIME_OUT = 60 * 1000;
//    public static final String RELEASE = "http://192.168.1.222:8084";  //建
//    public static final String RELEASE = "http://192.168.1.9:9083";  //林安楷
//    //public static final String RELEASE = "http://192.168.1.26:8084";  //汽车城
    //public static final String RELEASE = "http://124.90.42.54:11222";  //外网
     public static final String RELEASE = "http://124.90.42.54:7000";  //外网


    public static final String BASE_URL = RELEASE;
    private Retrofit mRetrofit;
    private static NetClient mNetClient;

    //创建Retrofit
    private NetClient() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getHttpClient())                  //okHttp
                .addConverterFactory(MyGsonConverterFactory.create())    //Gson数据转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   //回调适配器
                .build();
    }
    private static class SingletonHolder {
        private static final NetClient INSTANCE = new NetClient();
    }
//    public static NetClient getInstance() {
//        if (mNetClient == null) {
//            synchronized (NetClient.class) {
//                if (mNetClient == null) {
//                    mNetClient = new NetClient();
//                }
//            }
//        }
//        return mNetClient;
//    }

    public Retrofit net() {
        return mRetrofit;
    }

    public static NetClient getInstance() {
        return SingletonHolder.INSTANCE;
    }
//
//    public Retrofit net() {
//        return mRetrofit;
//    }

    private OkHttpClient getHttpClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog", "retrofitBack = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)   //连接超时时间
//                .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)     //读操作超时时间
//                .addInterceptor(new Interceptor() {              //拦截器
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request();
//                        long t1 = System.nanoTime();
//                        final Logger logger = Logger.getLogger("retrofit");
//                        StringBuilder sb = new StringBuilder();
//                        sb.append(request.url());
//                        sb.append("?");
//                        int size = 0;
//                        FormBody formBody = null;
//                        if (request.method().equals("POST")) {
//                            RequestBody body = request.body();
//                            if (body instanceof FormBody) {
//                                formBody = (FormBody) request.body();
//                                size = formBody.size();
//                            }
//                        }
//
//                        if (request.body() instanceof FormBody) {
//                            for (int i = 0; i < size; i++) {
//                                logger.info(formBody.encodedName(i) + "=" + formBody.encodedValue(i));
//                                sb.append(formBody.encodedName(i));
//                                sb.append("=");
//                                sb.append(formBody.encodedValue(i));
//                                if (i != size - 1) {
//                                    sb.append("&");
//                                }
//                            }
//                        }
//                        logger.info(sb.toString());
//
//
//                        Request original = request.newBuilder().header("x-access-token",(String) SPutils.get(App.getAppContext(),"token",""))
//                                .header("x-user-token",(String)SPutils.get(App.getAppContext(),"userToken","")).build();
//
//                        Response response = chain.proceed(original);
//                        long t2 = System.nanoTime();
//                        logger.info(String.format("Received response for %s in %.1fms%n%s",
//                                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
//
//                        return response;
//                    }
//                })
                .build();
        return client;
    }

}

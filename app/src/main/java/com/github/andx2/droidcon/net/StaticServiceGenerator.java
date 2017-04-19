package com.github.andx2.droidcon.net;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.github.andx2.droidcon.AppConfig;
import com.github.andx2.droidcon.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andrew on 05.04.2017.
 */

public class StaticServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(AppConfig.Net.STATIC_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass){

        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
            httpClient.addNetworkInterceptor(new StethoInterceptor());
        }

        httpClient.connectTimeout(AppConfig.Net.STATIC_MAX_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        httpClient.readTimeout(AppConfig.Net.STATIC_MAX_READ_TIMEOUT, TimeUnit.MILLISECONDS);

//        httpClient.addInterceptor(new HeaderInterceptors());
//        httpClient.cache(new Cache(getAppContext().getCacheDir(), Integer.MAX_VALUE));



        Retrofit retrofit = builder
                .client(httpClient.build())
                .build();
        return retrofit.create(serviceClass);

    }
}

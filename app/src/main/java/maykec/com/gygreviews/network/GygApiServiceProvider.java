package maykec.com.gygreviews.network;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marko on 5/15/2016.
 */
public class GygApiServiceProvider {

    public static GygService provideGygService(String baseUrl, Context context){

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .baseUrl(baseUrl)
                .client(provideHttpClient(context))
                .build();

        return retrofit.create(GygService.class);

    }

    private static OkHttpClient provideHttpClient(Context context) {
        //Interceptor for debug
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //5mb cache
        int size = 5 * 1024 * 1024;
        Cache cache = new Cache(context.getCacheDir(), size);

        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interceptor)
                .build();
        return client;

    }

    private static Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

}

package com.mdgz.dam.labdam2022.persistencia.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mdgz.dam.labdam2022.persistencia.retrofit.entity.ReservaRetrofit;
import com.mdgz.dam.labdam2022.persistencia.retrofit.interfaz.FavoritoInterface;
import com.mdgz.dam.labdam2022.persistencia.retrofit.interfaz.ReservaInterface;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRetrofit {
    private FavoritoInterface favoritoInterface;
    private ReservaInterface reservaInterface;

    private static AppRetrofit instance;
    public static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static AppRetrofit getInstance(){
        if(instance == null){
            instance = new AppRetrofit();
        }

        return instance;
    }

    private AppRetrofit(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class,dateDeserializer)
                .setDateFormat("yyyy-MM-dd'T'hh:mm")
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dam-recordatorio-favoritos-api.duckdns.org/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        favoritoInterface = retrofit.create(FavoritoInterface.class);
        reservaInterface = retrofit.create(ReservaInterface.class);
    }

    public FavoritoInterface getFavoritoInterface() {
        return favoritoInterface;
    }

    public ReservaInterface getReservaInterface() {
        return reservaInterface;
    }

    private  JsonDeserializer<Date> dateDeserializer = new JsonDeserializer<Date>() {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Date fecha = null;
            try {
                fecha = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").parse(json.getAsString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return fecha;
        }
    };
}

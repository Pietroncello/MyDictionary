package com.example.mydictionary;

import android.content.Context;
import android.widget.Toast;

import com.example.mydictionary.Models.APIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RequestManager {
    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    // Metodo per ottenere i significati di una parola
    public void prendiSignificato(OnFetchDataListener listener, String word) {
        ChiamaDizionario chiamaDizionario = retrofit.create(ChiamaDizionario.class);
        Call<List<APIResponse>> chiama = chiamaDizionario.chiamaSignificati(word);

        try{
            // Esecuzione della chiamata asincrona
            chiama.enqueue(new Callback<List<APIResponse>>() {
                @Override
                public void onResponse(Call<List<APIResponse>> call, Response<List<APIResponse>> response) {
                    if(!response.isSuccessful()) {
                        Toast.makeText(context, "word not found!!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.onFetchData(response.body().get(0), response.message());
                    System.out.println("test" + response.message());
                }

                @Override
                public void onFailure(Call<List<APIResponse>> call, Throwable t) {
                    // Gestione del fallimento della chiamata API
                    listener.onError("Request failed!!");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Oh no ce qualcosa che non va!!", Toast.LENGTH_SHORT).show();
        }
    }
    public interface ChiamaDizionario {
        @GET("entries/en/{word}")
        Call<List<APIResponse>> chiamaSignificati (
                @Path("word") String word
        );
    }
}

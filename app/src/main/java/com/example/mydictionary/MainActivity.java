package com.example.mydictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydictionary.Adapters.MeaningAdapter;
import com.example.mydictionary.Adapters.PhoneticsAdapter;
import com.example.mydictionary.Models.APIResponse;

public class MainActivity extends AppCompatActivity {

    SearchView search_view;
    TextView textView_word;
    RecyclerView recycler_phonetics, recycler_meanings;
    ProgressBar progressBar;
    PhoneticsAdapter phoneticsAdapter;
    MeaningAdapter meaningAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_view = findViewById(R.id.search_view);
        textView_word = findViewById(R.id.textView_word);
        recycler_phonetics = findViewById(R.id.recycler_phonetics);
        recycler_meanings = findViewById(R.id.recycler_meanings);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);
        RequestManager manager = new RequestManager(MainActivity.this);
        manager.prendiSignificato(listener, "welcome");


        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressBar.setVisibility(View.VISIBLE);
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.prendiSignificato(listener, query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressBar.setVisibility(View.INVISIBLE);
            if(apiResponse==null){
                Toast.makeText(MainActivity.this, "Error 404, no data found!!", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    };

    private void showData(APIResponse apiResponse) {
        textView_word.setText(apiResponse.getWord());
        recycler_phonetics.setHasFixedSize(true);
        recycler_phonetics.setLayoutManager(new GridLayoutManager(this, 1));
        phoneticsAdapter = new PhoneticsAdapter(this, apiResponse.getPhonetics());
        recycler_phonetics.setAdapter(phoneticsAdapter);

        recycler_meanings.setHasFixedSize(true);
        recycler_meanings.setLayoutManager(new GridLayoutManager(this, 1));
        meaningAdapter = new MeaningAdapter(this, apiResponse.getMeanings());
        recycler_meanings.setAdapter(meaningAdapter);

    }
}
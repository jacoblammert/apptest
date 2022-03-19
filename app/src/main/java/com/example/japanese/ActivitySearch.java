package com.example.japanese;

import static com.example.japanese.MainActivity.context;
import static com.example.japanese.MainActivity.data;
import static com.example.japanese.MainActivity.searchterm;

import android.content.Intent;
import android.graphics.Color;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class ActivitySearch extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    public static SearchView searchView;

    ArrayList<Spanned> dataset = new ArrayList<>();
    boolean isloading = false;
    ArrayList<Kanji> current_search_result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.search_recycler_view);

        resetRecyclerView();

        RecycleView();
        Search_main();

        Settings();
        setColorPalette();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setColorPalette();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void getMoreData() {
        isloading = true;

        int size = dataset.size();
        int number_new_items = 10;

        for (int i = size; i < size + number_new_items; ++i) {
            if (i < current_search_result.size()) {
                dataset.add(current_search_result.get(i).toSpanned_colored());
            }
        }
        isloading = false;
    }
    private void resetRecyclerView(){
        dataset = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(dataset, context);
        recyclerView.setAdapter(recyclerViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void Search_main() {

        searchView = findViewById(R.id.search_view);
        searchView.setDrawingCacheBackgroundColor(Color.BLACK);

        searchView.setIconified(false);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                resetRecyclerView();
                searchterm = query;
                current_search_result = data.find_kanji(searchterm);
                getMoreData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void RecycleView(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isloading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastVisibleItemPosition() > dataset.size() - 25) {
                        getMoreData();
                    }
                }
            }
        });
    }
    public void Settings(){

        Button settings = findViewById(R.id.button_search_settings);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(context,ActivitySettings.class);
                startActivity(settings);
            }
        });
    }

    public void setColorPalette(){
        resetRecyclerView();
        current_search_result = data.find_kanji(searchterm);
        getMoreData();

        Button settings = findViewById(R.id.button_search_settings);
        settings.setBackgroundColor(com.example.japanese.Color.button.getColorInt());

        recyclerView.setBackgroundColor(com.example.japanese.Color.activity_background.getColorInt());

        searchView.setBackgroundColor(com.example.japanese.Color.meaning.getColorInt());

        View background = findViewById(R.id.activity_search);
        background.setBackgroundColor(com.example.japanese.Color.activity_background.getColorInt());
    }
}

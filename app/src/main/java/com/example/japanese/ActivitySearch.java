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

        System.out.println("set up adapter");
        resetRecyclerView();


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isloading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastVisibleItemPosition() > dataset.size() - 25) {

                        //linearLayoutManager.removeViewAt();

                        isloading = true;
                        getMoreData();
                    }
                }
            }
        });
        Search_main();

    }

    private void updateViewAdapter(int position){






    }

    private void getMoreData() {

        int size = dataset.size();

        int number_new_items = 30;

        for (int i = size; i < size + number_new_items; ++i) {
            //dataset.add("Item " + i);
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
    private void resetAdapter() {

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
}

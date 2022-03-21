package com.example.japanese;

import static com.example.japanese.MainActivity.context;
import static com.example.japanese.MainActivity.data;
import static com.example.japanese.MainActivity.typeface;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Spanned;
import android.text.SpannedString;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivitySearch extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView.Adapter adapter;
    SearchView searchView;

    ArrayList<Spanned> dataset = new ArrayList<>();
    ArrayList<DataEntry> current_search_result_kanji = new ArrayList<>();
    ArrayList<DataEntry> current_search_result_vocabulary = new ArrayList<>();

    boolean isloading = false;
    boolean searchKanji_private = false;
    public static boolean searchKanji_public = true;

    public static String public_searchterm = "---";
    String private_searchterm = "---";
    public static boolean new_Search = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        private_searchterm = public_searchterm;
        searchKanji_private = searchKanji_public;

        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.search_recycler_view);


        resetRecyclerView();

        RecycleView();
        Search_main();

        Settings();
        SwitchSearch();
        setColorPalette();

        recyclerView.getAdapter().setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        recyclerView.getAdapter().setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.ALLOW);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int old_scroll_pos = recyclerViewAdapter.lastPosition;
        setColorPalette();
        recyclerView.scrollToPosition(old_scroll_pos-1);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void getMoreData() {
        isloading = true;

        int size = dataset.size();
        int number_new_items = 150;

        for (int i = size; i < size + number_new_items; ++i) {
            if (searchKanji_private) {
                if (i < current_search_result_kanji.size()) {
                    dataset.add(current_search_result_kanji.get(i).toSpanned_colored(private_searchterm));
                }
            } else {
                if (i < current_search_result_vocabulary.size()) {
                    dataset.add(current_search_result_vocabulary.get(i).toSpanned_colored(private_searchterm));
                }
            }
        }
        isloading = false;
    }
    private void resetRecyclerView() {
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
                public_searchterm = query;
                private_searchterm = public_searchterm;
                Search(public_searchterm);
                updateSearchFont();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void RecycleView() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isloading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastVisibleItemPosition() > dataset.size() - 100) {
                        getMoreData();
                    }
                }
                if (linearLayoutManager != null) {
                    recyclerViewAdapter.updatePosition(linearLayoutManager.findLastVisibleItemPosition());
                }
            }
        });
    }

    public void Settings() {

        Button settings = findViewById(R.id.button_search_settings);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(context, ActivitySettings.class);
                startActivity(settings);
            }
        });
    }

    public void SwitchSearch() {

        Button settings = findViewById(R.id.button_search_switch);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchKanji_public) {
                    searchKanji_public = !searchKanji_public;
                    Intent settings = new Intent(context, ActivitySearch.class);
                    startActivity(settings);
                }else{
                    searchKanji_public = !searchKanji_public;
                    finish();
                }
            }
        });
    }

    public void setColorPalette() {
        Search(private_searchterm);

        Button settings = findViewById(R.id.button_search_settings);
        settings.setBackgroundColor(MainActivity.color.getColorInt(0,8));

        Button search_switch = findViewById(R.id.button_search_switch);
        search_switch.setBackgroundColor(MainActivity.color.getColorInt(0,9));

        recyclerView.setBackgroundColor(MainActivity.color.getColorInt(0,7));

        searchView.setBackgroundColor(MainActivity.color.getColorInt(0,5));

        View background = findViewById(R.id.activity_search);
        background.setBackgroundColor(MainActivity.color.getColorInt(0,7));

        updateSearchFont();
    }

    public void Search(String searchterm) {
        resetRecyclerView();
        //if (searchKanji){
        current_search_result_kanji = data.findEntry(searchterm,0);
        current_search_result_vocabulary = data.findEntry(searchterm,1);
        getMoreData();
    }

    public void updateSearchFont(){
        TextView textViewSearch = findViewById(R.id.search_current_search);
        String text = private_searchterm;

        if (searchKanji_private) {
            text += " 漢字";
        }else {
            text += " 単語";
        }
        textViewSearch.setText(text);
        textViewSearch.setTextSize(TypedValue.COMPLEX_UNIT_SP,40);
        textViewSearch.setTextColor(MainActivity.color.getColorInt(0,9));
        textViewSearch.setTypeface(typeface);
    }
}

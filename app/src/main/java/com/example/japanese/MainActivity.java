package com.example.japanese;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        TextView textView = findViewById(R.id.textView);


        Data data = new Data();
        data.loadKanji(context,"kanji.csv");
        //data.loadVocabulary(context,"vocab.csv");

        Spinner dropdownmenu = (Spinner) findViewById(R.id.fontspinner);

        List<String> fonts = new ArrayList<>();
        fonts.add("02UtsukushiMincho.ttf");
        fonts.add("ipaexm.ttf");
        fonts.add("WaonJoyo-R.otf");
        fonts.add("YasashisaGothicBold-V2.otf");
        fonts.add("ipaexm.ttf");
        ArrayAdapter<String> font_adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,fonts);

        font_adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        dropdownmenu.setAdapter(font_adapter);

        dropdownmenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                textView.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/" + fonts.get(i)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setDrawingCacheBackgroundColor(Color.BLACK);

        searchView.setIconified(false);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String kanji_found = data.find_kanji(query);
                textView.setText(Html.fromHtml(kanji_found));

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                //String text = "<font color=#cc0029>First Color</font> <font color=#ffcc00>Second Color</font>";
                //searchView.setColo(Html.fromHtml(text));
                return false;
            }
        });



    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }







}
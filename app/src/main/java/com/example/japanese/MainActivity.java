package com.example.japanese;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static Context context;
    public static TextView text_main;
    public static TextView text_settings_example;
    public static Spinner dropdownmenu;
    public static SearchView searchView;
    public static Data data;
    public static Typeface typeface;
    public static List<String> fonts = Arrays.asList(
            //TODO load from resource file
            "ipaexm.ttf",
            "02UtsukushiMincho.ttf",
            "WaonJoyo-R.otf",
            "YasashisaGothicBold-V2.otf",
            "SeimeiKana-Free.otf",
            "ryasumagoho.ttf",
            "SeiminKanaClassicTrial-ExtraLight.otf",
            "g_brushtappitsu_freeB.ttf",
            "g_brushtappitsu_freeH.ttf",
            "g_brushtappitsu_freeR.ttf");

    public static int current_activity_id;
    public static Spanned current_search_result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        context = getApplicationContext();



        data = new Data();
        data.loadKanji(context, "kanji.csv");
        //data.loadVocabulary(context,"vocab.csv");

        activity_main();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }



    public void activity_main() {
        current_activity_id = R.layout.activity_main;
        setContentView(R.layout.activity_main);
        Text_main();
        Search_main();
        Settings();
    }

    public void activity_settings() {
        current_activity_id = R.layout.activity_settings;
        setContentView(R.layout.activity_settings);
        Spinner();
        Settings();
        Text_settings_example();
    }


    public void Spinner() {
        dropdownmenu = (Spinner) findViewById(R.id.fontspinner);


        ArrayAdapter<String> font_adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, fonts);

        font_adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        dropdownmenu.setAdapter(font_adapter);

        dropdownmenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + fonts.get(i));
                text_settings_example.setTypeface(typeface);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void Search_main() {

        searchView = findViewById(R.id.searchView);
        searchView.setDrawingCacheBackgroundColor(Color.BLACK);

        searchView.setIconified(false);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                current_search_result = data.find_kanji(query);
                text_main.setText(current_search_result);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void Text_main(){
        // Recreated when activity has been created or changed
        text_main = findViewById(R.id.textView);

        text_main.setText(current_search_result);

        try {
            text_main.setTypeface(typeface);
        }catch (Exception e){}
        text_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,view.)
            }
        });
    }

    public void Text_settings_example(){
        // Recreated when activity has been created or changed
        text_settings_example = findViewById(R.id.text_settings_example);

        text_settings_example.setText(data.find_kanji("Asia"));

        try {
            text_settings_example.setTypeface(typeface);
        }catch (Exception e){}
    }

    public void Settings(){
        //TODO switch from one activity to another

        Button settings = null;

        boolean activity_main = current_activity_id == R.layout.activity_main;

        if (activity_main){
            settings = findViewById(R.id.button_settings);
        }else {
            try {
                settings = findViewById(R.id.button_settings1);
            }catch (Exception e){}
        }

        if (settings != null) {
            settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (activity_main) {
                        activity_settings();
                    }else {
                        activity_main();
                    }
                }
            });
        }

    }


}
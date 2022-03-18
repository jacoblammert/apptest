package com.example.japanese;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
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

    public static TextView text_main;
    public static SearchView searchView;

    public static Context context;
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
        text_main.setTypeface(typeface);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    void activity_main(){

        setContentView(R.layout.activity_main);
        Search_main();
        Text_main();
        Settings();
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
                text_main.setMovementMethod(LinkMovementMethod.getInstance());
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
        text_main.setMovementMethod(LinkMovementMethod.getInstance());
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
/**/
    public void Settings() {

        Button settings = findViewById(R.id.button_settings);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(context,ActivitySettings.class);
                startActivity(settings);
            }
        });
    }/**/





}
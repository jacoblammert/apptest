package com.example.japanese;

import static com.example.japanese.ActivitySettings.bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
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
    //public static SearchView searchView;

    public static Context context;
    public static Data data;
    public static Color color;
    public static Typeface typeface;
    public static List<String> fonts = Arrays.asList(
            //TODO load from resource file
            "1",
            "2",
            "3",
            "3",
            "4",
            "5",
            "ipaexm.ttf",
            "02UtsukushiMincho.ttf",
            "WaonJoyo-R.otf",
            "YasashisaGothicBold-V2.otf",
            "ryasumagoho.ttf",
            "SeiminKanaClassicTrial-ExtraLight.otf",
            "g_brushtappitsu_freeB.ttf");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        context = getApplicationContext();

        data = new Data();
        data.loadKanji(context, "kanji.csv");
        data.loadVocabulary(context,"vocab.csv");
        color = new Color();
        color.loadColorpaletts("colorPalette.txt");

        activity_main();

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

    void activity_main(){

        setContentView(R.layout.activity_main);
        Search_main();
        Settings();
        setColorPalette();
    }

    private void Search_main() {
        Button search = findViewById(R.id.button_main_search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent(context,ActivitySearch.class);
                startActivity(search);
            }
        });
    }


    public void Settings() {

        Button settings = findViewById(R.id.button_main_settings);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(context,ActivitySettings.class);
                startActivity(settings);
            }
        });
    }


    public void setColorPalette(){
        Button settings = findViewById(R.id.button_main_settings);
        settings.setBackgroundColor(color.getColorInt(0,8));

        View background = findViewById(R.id.activity_main);
        background.setBackgroundColor(color.getColorInt(0,7));

    }
}
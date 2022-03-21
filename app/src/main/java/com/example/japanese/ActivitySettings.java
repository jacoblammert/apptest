package com.example.japanese;

import static com.example.japanese.MainActivity.*;
import static com.example.japanese.Color.*;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spanned;
import android.text.SpannedString;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sun.jna.StringArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.SAXParser;

public class ActivitySettings extends AppCompatActivity {

    public static TextView text_settings_example;
    public static Spinner spinner_font;
    public static Spinner spinner_palette;
    public static String search_term_example = "Asia";

    public static Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Text_settings_example();
        Settings();
        Spinner_font();
        Spinner_palette();
        setColorPalette();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (spinner_font != null) {
            spinner_font.setSelection(bundle.getInt("spinner_font", 0));
        }
        if (spinner_palette != null) {
            spinner_palette.setSelection(bundle.getInt("spinner_palette", 0));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        bundle.putInt("spinner_font", spinner_font.getSelectedItemPosition());
        bundle.putInt("spinner_palette", spinner_palette.getSelectedItemPosition());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void Spinner_font() {
        spinner_font = (Spinner) findViewById(R.id.spinner_font);


        ArrayAdapter<String> font_adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, fonts);

        font_adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        spinner_font.setAdapter(font_adapter);

        spinner_font.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        typeface = Typeface.DEFAULT;
                        text_settings_example.setTypeface(Typeface.DEFAULT);
                        break;
                    case 2:
                        typeface = Typeface.DEFAULT_BOLD;
                        text_settings_example.setTypeface(Typeface.DEFAULT_BOLD);
                        break;
                    case 3:
                        typeface = Typeface.MONOSPACE;
                        text_settings_example.setTypeface(Typeface.MONOSPACE);
                        break;
                    case 4:
                        typeface = Typeface.SANS_SERIF;
                        text_settings_example.setTypeface(Typeface.SANS_SERIF);
                        break;
                    case 5:
                        typeface = Typeface.SERIF;
                        text_settings_example.setTypeface(Typeface.SERIF);
                        break;
                    default:
                        try {
                            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + fonts.get(i));
                            text_settings_example.setTypeface(typeface);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void Spinner_palette() {
        spinner_palette = (Spinner) findViewById(R.id.spinner_palette);

        List<String> color_paletts = Arrays.asList("classic", "original", "basic", "standard");

        ArrayAdapter<String> palette_adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, color_paletts);

        palette_adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        spinner_palette.setAdapter(palette_adapter);

        spinner_palette.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadColorPalette(i);
                setColorPalette();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private Spanned getExample() {
        System.out.println("Searching Exampletext");

        ArrayList<DataEntry> found = data.findEntry(search_term_example,0); // TODO set enum for datatype
        System.out.println(found.size());
        try {
            return found.get(0).toSpanned_colored();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SpannedString("Error");
    }

    public void Text_settings_example() {
        // Recreated when activity has been created or changed
        text_settings_example = findViewById(R.id.text_settings_example);

        text_settings_example.setText(getExample());

        try {
            text_settings_example.setTypeface(typeface);
        } catch (Exception e) {
        }
    }

    public void Settings() {

        Button settings = findViewById(R.id.button_settings_settings);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setContentView(R.layout.activity_main);
                //Intent main = new Intent(context,MainActivity.class);
                //startActivity(main);

                finish();
            }
        });
    }

    public void loadColorPalette(int number) {
        active_color_palette = number;
        //color.loadColorpaletts("colorPalette.txt");
    }

    public void setColorPalette() {
        Button settings = findViewById(R.id.button_settings_settings);
        settings.setBackgroundColor(color.getColorInt(0,8));

        spinner_font.setBackgroundColor(color.getColorInt(0,8));
        spinner_palette.setBackgroundColor(color.getColorInt(0,8));

        View background = findViewById(R.id.activity_settings);
        background.setBackgroundColor(color.getColorInt(0,7));

        // Updates example search term color palette
        text_settings_example.setText(getExample());

    }
}

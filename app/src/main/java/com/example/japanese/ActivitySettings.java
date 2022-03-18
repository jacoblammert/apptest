package com.example.japanese;

import static com.example.japanese.MainActivity.*;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySettings extends AppCompatActivity {

    public static TextView text_settings_example;
    public static Spinner dropdownmenu;

    public static Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        Text_settings_example();
        Settings();
        Spinner();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (dropdownmenu != null) {
            dropdownmenu.setSelection(bundle.getInt("dropdownmenu", 0));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        bundle.putInt("dropdownmenu",dropdownmenu.getSelectedItemPosition());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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

    public void Text_settings_example() {
        // Recreated when activity has been created or changed
        text_settings_example = findViewById(R.id.text_settings_example);

        text_settings_example.setText(data.find_kanji("Asia"));

        try {
            text_settings_example.setTypeface(typeface);
        } catch (Exception e) {
        }
    }

    public void Settings() {

        Button settings = findViewById(R.id.button_settings1);

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
}

package com.example.japanese;

import static com.example.japanese.ActivitySearch.*;
import static com.example.japanese.MainActivity.context;

import android.content.Intent;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Vocabulary {

    private String reading;
    private String reading_romaji;
    private String writing;
    private String translation;
    private String reading_all;

    private static float size_reading = 1;
    private static float size_reading_romaji = 1;
    private static float size_writing = 2;
    private static float size_translation = 1.5f;
    private static float size_reading_all = 1;

    public Vocabulary(){}

    //TODO use interface
    public Vocabulary(String reading,String reading_romaji,String writing,String translation,String reading_all) {
        this.reading = reading;
        this.reading_romaji = reading_romaji;
        this.writing = writing;
        this.translation = translation.replace(";","\n");
        this.reading_all = reading_all;//.replace("…","\n");
    }

    public String toString() {
        return reading
                + "\n" + reading_romaji
                + "\n" + writing
                + "\n" + translation
                + "\n" + reading_all;
    }

    public Spanned toSpanned_colored() {

        SpannableStringBuilder text = new SpannableStringBuilder();
        text.append(getSpannedWriting());
        text.append("\n");
        text.append(getSpannedReading());
        text.append("\n");
        //text.append(getSpannedReadingAll());
        text.append(getSpannedReadingRomaji());
        text.append("\n");
        text.append(getSpannedTranslation());
        text.append("\n");
        return text;

    }

    private SpannableString getSpannableString(String color, float size, String value){
        SpannableString text = new SpannableString(value );

        int index_position_start = 0;
        int index_position_end = value.length();

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                System.out.println(value);
                public_searchterm = value;
                new_Search = true;
                searchKanji_public = true;
                Intent search = new Intent(context,ActivitySearch.class);
                search.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(search);
            }
            @Override
            public void updateDrawState(TextPaint ds){
                super.updateDrawState(ds);
                ds.setColor(android.graphics.Color.parseColor(color));
                ds.setTextSize(size * 25 * context.getResources().getDisplayMetrics().scaledDensity);
                ds.setUnderlineText(false);
            }
        };


        text.setSpan(clickableSpan,index_position_start,index_position_end,SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        return text;
    }

    private SpannableString getSpannedReading(){
        return getSpannableString(Color.kunyomi.getColor(),size_reading,reading);
    }

    private SpannableString getSpannedReadingRomaji(){
        return getSpannableString(Color.onyomi_romaji.getColor(),size_reading_romaji,reading_romaji);
    }
    private SpannableString getSpannedWriting(){

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (int i = 0; i < writing.length();++i){
            spannableStringBuilder.append(getSpannableString(Color.kanji.getColor(),size_writing, String.valueOf(writing.charAt(i))));
        }
        return SpannableString.valueOf(spannableStringBuilder);
    }
    private SpannableString getSpannedTranslation(){
        return getSpannableString(Color.meaning.getColor(),size_translation,translation);
    }
    private SpannableString getSpannedReadingAll(){
        return getSpannableString(Color.onyomi.getColor(),size_reading_all,reading_all);
    }

    public boolean contains(String searchterm) {

        searchterm = searchterm.toLowerCase(Locale.ROOT);

        if (reading.contains(searchterm)) {
            return true;
        }
        if (reading_romaji.contains(searchterm)) {
            return true;
        }
        if (writing.toLowerCase(Locale.ROOT).contains(searchterm)) {
            return true;
        }
        if (translation.contains(searchterm)) {
            return true;
        }
        if (reading_all.toLowerCase(Locale.ROOT).contains(searchterm)) {
            return true;
        }

        return false;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public void setReading_all(String reading_all) {
        this.reading_all = reading_all;
    }

    public void setReading_romaji(String reading_romaji) {
        this.reading_romaji = reading_romaji;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    public String getReading() {
        return reading;
    }

    public String getWriting() {
        return writing;
    }

    public String getTranslation() {
        return translation;
    }

    public String getReading_romaji() {
        return reading_romaji;
    }

    public String getReading_all() {
        return reading_all;
    }
}

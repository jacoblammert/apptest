package com.example.japanese;

import static com.example.japanese.MainActivity.context;

import android.content.Context;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Locale;

public class Kanji {

    private String kanji;
    private String onyomi;
    private String onyomi_romaji;
    private String kunyomi;
    private String kunyomi_romaji;
    private String meaning;
    private int level; // 1 - 4

    public static String color_kanji = Color.kanji.getColor();
    public static String color_onyomi = Color.onyomi.getColor();
    public static String color_onyomi_romaji = Color.onyomi_romaji.getColor();
    public static String color_kunyomi = Color.kunyomi.getColor();
    public static String color_kunyomi_romaji = Color.kunyomi_romaji.getColor();
    public static String color_meaning = Color.meaning.getColor();
    public static String color_level = Color.level.getColor();

    public static float size_kanji = 2;
    public static float size_onyomi = 1;
    public static float size_onyomi_romaji = 1;
    public static float size_kunyomi = 1;
    public static float size_kunyomi_romaji = 1;
    public static float size_meaning = 1.5f;
    public static float size_level = 1;

    public Kanji() {

    }

    public Kanji(String kanji, String onyomi, String onyomi_romaji, String kunyomi, String kunyomi_romaji, String meaning, int level) {
        this.kanji = kanji;
        this.onyomi = onyomi;
        this.onyomi_romaji = onyomi_romaji;
        this.kunyomi = kunyomi;
        this.kunyomi_romaji = kunyomi_romaji;
        this.meaning = meaning;
        this.level = level;
    }

    public String toString() {
        return kanji + "\n"
                + onyomi + "\n"
                + onyomi_romaji + "\n"
                + kunyomi + "\n"
                + kunyomi_romaji + "\n"
                + meaning + "\n"
                + level + "\n";
    }

    public Spanned toSpanned_colored() {

        SpannableStringBuilder text = new SpannableStringBuilder();
        text.append(getSpannedKanji());
        text.append(getSpannedOnyomi());
        text.append(getSpannedOnyomiRomaji());
        text.append(getSpannedKunyomi());
        text.append(getSpannedKunyomiRomaji());
        text.append(getSpannedMeaning());
        text.append(getSpannedLevel());
        return text;

    }

    private SpannableString getSpannableString(String color, float size, String value){
        SpannableString text = new SpannableString(value + " \n");

        int index_position_start = 0;
        int index_position_end = value.length();

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                System.out.println(value);
                Toast.makeText(context,value,Toast.LENGTH_SHORT);
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

    private SpannableString getSpannedKanji(){
        return getSpannableString(color_kanji,size_kanji,kanji);
    }

    private SpannableString getSpannedOnyomi(){
        return getSpannableString(color_onyomi,size_onyomi,onyomi);
    }
    private SpannableString getSpannedOnyomiRomaji(){
        return getSpannableString(color_onyomi_romaji,size_onyomi_romaji,onyomi_romaji);
    }
    private SpannableString getSpannedKunyomi(){
        return getSpannableString(color_kunyomi,size_kunyomi,kunyomi);
    }
    private SpannableString getSpannedKunyomiRomaji(){
        return getSpannableString(color_kunyomi_romaji,size_kunyomi_romaji,kunyomi_romaji);
    }
    private Spanned getSpannedMeaning(){
        return getSpannableString(color_meaning,size_meaning,meaning);
    }
    private SpannableString getSpannedLevel(){
        return getSpannableString(color_level,size_level,String.valueOf(level));
    }

    public boolean contains(String searchterm) {

        searchterm = searchterm.toLowerCase(Locale.ROOT);

        if (kanji.contains(searchterm)) {
            return true;
        }
        if (onyomi.contains(searchterm)) {
            return true;
        }
        if (onyomi_romaji.toLowerCase(Locale.ROOT).contains(searchterm)) {
            return true;
        }
        if (kunyomi.contains(searchterm)) {
            return true;
        }
        if (kunyomi_romaji.toLowerCase(Locale.ROOT).contains(searchterm)) {
            return true;
        }
        if (meaning.toLowerCase(Locale.ROOT).contains(searchterm)) {
            return true;
        }
        try {
            if (level == Integer.valueOf(searchterm)) {
                return true;
            }
        }catch (Exception e){
        }

        return false;
    }

    public static void updateColors(){
        color_kanji = Color.kanji.getColor();
        color_onyomi = Color.onyomi.getColor();
        color_onyomi_romaji = Color.onyomi_romaji.getColor();
        color_kunyomi = Color.kunyomi.getColor();
        color_kunyomi_romaji = Color.kunyomi_romaji.getColor();
        color_meaning = Color.meaning.getColor();
        color_level = Color.level.getColor();
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public void setKunyomi(String kunyomi) {
        this.kunyomi = kunyomi;
    }

    public void setKunyomi_romaji(String kunyomi_romaji) {
        this.kunyomi_romaji = kunyomi_romaji;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setOnyomi(String onyomi) {
        this.onyomi = onyomi;
    }

    public void setOnyomi_romaji(String onyomi_romaji) {
        this.onyomi_romaji = onyomi_romaji;
    }

    public int getLevel() {
        return level;
    }

    public String getColor_kanji() {
        return color_kanji;
    }

    public String getOnyomi_romaji() {
        return onyomi_romaji;
    }

    public String getOnyomi() {
        return onyomi;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getKunyomi_romaji() {
        return kunyomi_romaji;
    }

    public String getKunyomi() {
        return kunyomi;
    }

    public String getKanji() {
        return kanji;
    }

}

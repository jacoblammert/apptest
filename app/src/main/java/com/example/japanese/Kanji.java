package com.example.japanese;

import static com.example.japanese.ActivitySearch.*;
import static com.example.japanese.MainActivity.context;

import android.content.Context;
import android.content.Intent;
import android.graphics.MaskFilter;
import android.graphics.Typeface;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
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
        this.meaning = meaning.replace(";", "\n");
        ;
        this.level = level;
    }

    public String toString() {
        return kanji
                + "\n" + onyomi
                + "\n" + onyomi_romaji
                + "\n" + kunyomi
                + "\n" + kunyomi_romaji
                + "\n" + meaning
                + "\n" + level;
    }

    public Spanned toSpanned_colored() {

        SpannableStringBuilder text = new SpannableStringBuilder();
        text.append("\n");
        text.append(getSpannedKanji());
        text.append("\n");
        text.append(getSpannedOnyomi());
        text.append("\n");
        text.append(getSpannedOnyomiRomaji());
        text.append("\n");
        text.append(getSpannedKunyomi());
        text.append("\n");
        text.append(getSpannedKunyomiRomaji());
        text.append("\n");
        text.append(getSpannedMeaning());
        text.append("\n");
        text.append(getSpannedLevel());
        text.append("\n");
        return text;
    }

    public Spanned toSpanned_colored(String searchterm) {

        SpannableStringBuilder text = new SpannableStringBuilder();
        text.append("\n");
        text.append(getSpannedKanji(searchterm));
        text.append("\n");
        text.append(getSpannedOnyomi(searchterm));
        text.append("\n");
        text.append(getSpannedOnyomiRomaji(searchterm));
        text.append("\n");
        text.append(getSpannedKunyomi(searchterm));
        text.append("\n");
        text.append(getSpannedKunyomiRomaji(searchterm));
        text.append("\n");
        text.append(getSpannedMeaning(searchterm));
        text.append("\n");
        text.append(getSpannedLevel(searchterm));
        text.append("\n");
        return text;

    }

    private SpannableString getSpannableString(int color, float size, String value) {
        SpannableString text = new SpannableString(value);

        int index_position_start = 0;
        int index_position_end = value.length();

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                public_searchterm = value;
                new_Search = true;
                searchKanji_public = false;
                Intent search = new Intent(context, ActivitySearch.class);
                search.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(search);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(color);
                ds.setTextSize(size * 25 * context.getResources().getDisplayMetrics().scaledDensity);
                ds.setUnderlineText(false);
            }
        };


        text.setSpan(clickableSpan, index_position_start, index_position_end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        return text;
    }

    private SpannableString getSpannableString(int color, float size, String value, String searchterm) {

        if (!value.contains(searchterm)) {
            return getSpannableString(color, size, value);
        }


        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();

        if (value.startsWith(searchterm)){
            spannableStringBuilder.append(getSearchString(color,size,searchterm));
        }
        String[] split_value = value.split(searchterm);

        for (int i = 0; i < split_value.length; ++i) {
            System.out.println(split_value[i]);
        }

        for (int i = 0; i < split_value.length; ++i) {

            SpannableString substring = new SpannableString(split_value[i]);

            int index_position_start = 0;
            int index_position_end = split_value[i].length();

            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View view) {
                    public_searchterm = value;
                    new_Search = true;
                    searchKanji_public = false;
                    Intent search = new Intent(context, ActivitySearch.class);
                    search.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(search);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(color);
                    ds.setTextSize(size * 25 * context.getResources().getDisplayMetrics().scaledDensity);
                    ds.setUnderlineText(false);
                }
            };

            substring.setSpan(clickableSpan, index_position_start, index_position_end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

            spannableStringBuilder.append(substring);
            if (i < split_value.length - 1 || value.endsWith(searchterm)) {
                spannableStringBuilder.append(getSearchString(color,size,searchterm));
            }

        }
        return SpannableString.valueOf(spannableStringBuilder);
    }

    private SpannableString getSearchString(int color,float size, String searchterm){
        SpannableString result = getSpannableString(color, size, searchterm);
        result.setSpan(new StyleSpan(Typeface.BOLD),0,result.length(),0);
        return result;
    }
    private SpannableString getSpannedKanji(String searchterm) {
        return getSpannableString(Color.kanji.getColorInt(), size_kanji, kanji, searchterm);
    }

    private SpannableString getSpannedOnyomi(String searchterm) {
        return getSpannableString(Color.onyomi.getColorInt(), size_onyomi, onyomi, searchterm);
    }

    private SpannableString getSpannedOnyomiRomaji(String searchterm) {
        return getSpannableString(Color.onyomi_romaji.getColorInt(), size_onyomi_romaji, onyomi_romaji, searchterm);
    }

    private SpannableString getSpannedKunyomi(String searchterm) {
        return getSpannableString(Color.kunyomi.getColorInt(), size_kunyomi, kunyomi, searchterm);
    }

    private SpannableString getSpannedKunyomiRomaji(String searchterm) {
        return getSpannableString(Color.kunyomi_romaji.getColorInt(), size_kunyomi_romaji, kunyomi_romaji, searchterm);
    }

    private Spanned getSpannedMeaning(String searchterm) {
        return getSpannableString(Color.meaning.getColorInt(), size_meaning, meaning, searchterm);
    }

    private SpannableString getSpannedLevel(String searchterm) {
        return getSpannableString(Color.level.getColorInt(), size_level, String.valueOf(level), searchterm);
    }

    private SpannableString getSpannedKanji() {
        return getSpannableString(Color.kanji.getColorInt(), size_kanji, kanji);
    }

    private SpannableString getSpannedOnyomi() {
        return getSpannableString(Color.onyomi.getColorInt(), size_onyomi, onyomi);
    }

    private SpannableString getSpannedOnyomiRomaji() {
        return getSpannableString(Color.onyomi_romaji.getColorInt(), size_onyomi_romaji, onyomi_romaji);
    }

    private SpannableString getSpannedKunyomi() {
        return getSpannableString(Color.kunyomi.getColorInt(), size_kunyomi, kunyomi);
    }

    private SpannableString getSpannedKunyomiRomaji() {
        return getSpannableString(Color.kunyomi_romaji.getColorInt(), size_kunyomi_romaji, kunyomi_romaji);
    }

    private Spanned getSpannedMeaning() {
        return getSpannableString(Color.meaning.getColorInt(), size_meaning, meaning);
    }

    private SpannableString getSpannedLevel() {
        return getSpannableString(Color.level.getColorInt(), size_level, String.valueOf(level));
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
        } catch (Exception e) {
        }

        return false;
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

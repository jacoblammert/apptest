package com.example.japanese;

import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;

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
    public static String color_onyomi = Color.kanji.getColor();
    public static String color_onyomi_romaji = Color.onyomi.getColor();
    public static String color_kunyomi = Color.onyomi_romaji.getColor();
    public static String color_kunyomi_romaji = Color.kunyomi.getColor();
    public static String color_meaning = Color.kunyomi_romaji.getColor();
    public static String color_level = Color.level.getColor();

    public static String size_kanji = "big";
    public static String size_onyomi = "small";
    public static String size_onyomi_romaji = "small";
    public static String size_kunyomi = "small";
    public static String size_kunyomi_romaji = "small";
    public static String size_meaning = "normal";
    public static String size_level = "small";

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
        String text = "<font color=" + color_kanji          + "> <"+ size_kanji          + ">"+ kanji          + "</" + size_kanji          + "></font><br>"
                + "<font color=" + color_onyomi         + "> <"+ size_onyomi         + ">"+ onyomi         + "</" + size_onyomi         + "></font><br>"
                + "<font color=" + color_onyomi_romaji  + "> <"+ size_onyomi_romaji  + ">"+ onyomi_romaji  + "</" + size_onyomi_romaji  + "></font><br>"
                + "<font color=" + color_kunyomi        + "> <"+ size_kunyomi        + ">"+ kunyomi        + "</" + size_kunyomi        + "></font><br>"
                + "<font color=" + color_kunyomi_romaji + "> <"+ size_kunyomi_romaji + ">"+ kunyomi_romaji + "</" + size_kunyomi_romaji + "></font><br>"
                + "<font color=" + color_meaning        + "> <"+ size_meaning        + ">"+ meaning        + "</" + size_meaning        + "></font><br>"
                + "<font color=" + color_level          + "> <"+ size_level          + ">"+ level          + "</" + size_level          + "></font><br>";
        return Html.fromHtml(text);

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

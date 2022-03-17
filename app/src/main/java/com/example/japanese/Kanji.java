package com.example.japanese;

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

    public static String color_kanji = "#ff0034"; // values in hex
    public static String color_onyomi = "#734e55"; // values in hex
    public static String color_onyomi_romaji = "#3d292d"; // values in hex
    public static String color_kunyomi = "#6e2433"; // values in hex
    public static String color_kunyomi_romaji = "#471821"; // values in hex
    public static String color_meaning = "#368063"; // values in hex
    public static String color_level = "#61543c"; // values in hex

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

    public String toString_colored() {
        /*/return    "<h" + size_kanji          +" style = \"color:" + color_kanji           +  ";\">" + kanji          + "</h" + size_kanji            +">" + "<br>"
                + "<h" + size_onyomi         +" style = \"color:" + color_onyomi          +  ";\">" + onyomi         + "</h" + size_onyomi           +">" + "<br>"
                + "<h" + size_onyomi_romaji  +" style = \"color:" + color_onyomi_romaji   +  ";\">" + onyomi_romaji  + "</h" + size_onyomi_romaji    +">" + "<br>"
                + "<h" + size_kunyomi        +" style = \"color:" + color_kunyomi         +  ";\">" + kunyomi        + "</h" + size_kunyomi          +">" + "<br>"
                + "<h" + size_kunyomi_romaji +" style = \"color:" + color_kunyomi_romaji  +  ";\">" + kunyomi_romaji + "</h" + size_kunyomi_romaji   +">" + "<br>"
                + "<h" + size_meaning        +" style = \"color:" + color_meaning         +  ";\">" + meaning        + "</h" + size_meaning          +">" + "<br>"
                + "<h" + size_level          +" style = \"color:" + color_level           +  ";\">" + level          + "</h" + size_level            +">" + "<br><br>";
        /*/

        return    "<font color=" + color_kanji          + "> <"+ size_kanji          + ">"+ kanji          + "</" + size_kanji          + "></font><br>"
                + "<font color=" + color_onyomi         + "> <"+ size_onyomi         + ">"+ onyomi         + "</" + size_onyomi         + "></font><br>"
                + "<font color=" + color_onyomi_romaji  + "> <"+ size_onyomi_romaji  + ">"+ onyomi_romaji  + "</" + size_onyomi_romaji  + "></font><br>"
                + "<font color=" + color_kunyomi        + "> <"+ size_kunyomi        + ">"+ kunyomi        + "</" + size_kunyomi        + "></font><br>"
                + "<font color=" + color_kunyomi_romaji + "> <"+ size_kunyomi_romaji + ">"+ kunyomi_romaji + "</" + size_kunyomi_romaji + "></font><br>"
                + "<font color=" + color_meaning        + "> <"+ size_meaning        + ">"+ meaning        + "</" + size_meaning        + "></font><br>"
                + "<font color=" + color_level          + "> <"+ size_level          + ">"+ level          + "</" + size_level          + "></font><br><br>";
        /**/
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

    public void setColor_kanji(String color_kanji) {
        this.color_kanji = color_kanji;
    }

    public void setColor_kunyomi(String color_kunyomi) {
        this.color_kunyomi = color_kunyomi;
    }

    public void setColor_kunyomi_romaji(String color_kunyomi_romaji) {
        this.color_kunyomi_romaji = color_kunyomi_romaji;
    }

    public void setColor_level(String color_level) {
        this.color_level = color_level;
    }

    public void setColor_meaning(String color_meaning) {
        this.color_meaning = color_meaning;
    }

    public void setColor_onyomi(String color_onyomi) {
        this.color_onyomi = color_onyomi;
    }

    public void setColor_onyomi_romaji(String color_onyomi_romaji) {
        this.color_onyomi_romaji = color_onyomi_romaji;
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

    public String getColor_onyomi_romaji() {
        return color_onyomi_romaji;
    }

    public String getColor_onyomi() {
        return color_onyomi;
    }

    public String getColor_meaning() {
        return color_meaning;
    }

    public String getColor_level() {
        return color_level;
    }

    public String getColor_kunyomi_romaji() {
        return color_kunyomi_romaji;
    }

    public String getColor_kunyomi() {
        return color_kunyomi;
    }
}

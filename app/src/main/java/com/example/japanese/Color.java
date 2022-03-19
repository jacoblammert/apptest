package com.example.japanese;

import static com.example.japanese.MainActivity.context;

import android.view.View;

import java.io.InputStream;
import java.util.HashMap;

public enum Color {

    kanji("#ff0034"),
    onyomi("#734e55"),
    onyomi_romaji("#3d292d"),
    kunyomi("#6e2433"),
    kunyomi_romaji("#471821"),
    meaning("#368063"),
    level("#61543c"),
    activity_background("#161618"),
    button("#ff0034");


    private String color;

    Color(String color) {
        this.color = color;
    }

    public static void loadColorpalette(String location, int palette_number) {
        try {
            InputStream fileInputStream = context.getAssets().open(location);


            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();
            String[] all_paletts = new String(buffer).split("-");

            String[] color_palette = (all_paletts[palette_number].replace("[^a-zA-Z0-9# ]","")).split("\n");
            String[] color_palette_loaded = new String[Color.values().length]; // We need exactly as many values as we have colors

            System.out.println("Color palette: ");
            int count = 0;
            int count_failed = 0;
            while (count < color_palette.length) {
                try {
                    String[] entry = color_palette[count].split(" ");
                    System.out.println(entry[1]);
                    color_palette_loaded[count - count_failed] = entry[1];
                    //color_palette -> Number Color Comment
                    //color_palette -> 7 #4c4f48 background
                    //System.out.println("Color loaded from palette: " + entry[1]);
                }catch (Exception e){
                    e.printStackTrace();
                    count_failed++;
                }
                count++;
            }

            int counter = 0;
            for (Color color : Color.values()) {
                color.setColor(color_palette_loaded[counter].replaceAll("[^a-fA-F0-9#]", ""));
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static void printColors(){
        System.out.println("Color Palette: ");
        for (Color color: Color.values()){
            System.out.println(color.getColor());
        }
    }
}

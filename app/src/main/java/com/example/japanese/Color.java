package com.example.japanese;

import static com.example.japanese.MainActivity.context;

import android.view.View;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Color {

    private ArrayList<ArrayList<ArrayList<Integer>>> color_palette;
    private ArrayList<ArrayList<Float>> font_size;

    public static int active_color_palette = 0;

    Color() {
    }


    public void loadColorpaletts(String location) {
        try {
            InputStream fileInputStream = context.getAssets().open(location);


            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();

            String[] split_data = new String(buffer).split("/");
            loadFontSize(split_data[1]);
            loadColorPalette(split_data[0]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFontSize(String fontsizes) {
        String[] all_sizes = fontsizes.split("\n");

        if (null == font_size) {
            font_size = new ArrayList<>();
        }

        for (int i = 0; i < all_sizes.length; ++i) {
            if (all_sizes[i].contains(" ")) {
                ArrayList<Float> entry = new ArrayList<>();


                String[] row = all_sizes[i].split(" ");
                for (int j = 0; j < row.length; j++) {
                    entry.add(Float.valueOf(row[j]));
                }

                System.out.println("Entry: " + i + " " + entry.get(0) + ", " + entry.get(1));
                font_size.add(entry);
            }
        }
    }

    private void loadColorPalette(String colorpalettes) {

        String[] all_paletts = colorpalettes.split("-");

        int first_entry = 1;// Where does the first split start
        int number_of_entrys = 2; // where is the last split

        color_palette = new ArrayList<>();

        for (int palette_number = 0; palette_number < colorpalettes.length(); palette_number++) {
            String[] row = all_paletts[palette_number].replace("[^a-zA-Z0-9# ]", "").split("\n");

            ArrayList<ArrayList<Integer>> palette = new ArrayList<>();

            for (int row_number = 0; row_number < row.length; row_number++) {
                String[] values = row[row_number].split(" ");

                ArrayList<Integer> array_row = new ArrayList<>();
                try {
                    for (int entry = first_entry; entry < first_entry + number_of_entrys; entry++) {
                        String color_of_entry = values[entry].replaceAll("[^a-fA-F0-9#]", "");
                        if (!color_of_entry.isEmpty()) {
                            array_row.add(android.graphics.Color.parseColor(color_of_entry));
                            //array_row.add(color_of_entry);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!array_row.isEmpty()) {
                    palette.add(array_row);
                }
            }
            color_palette.add(palette);
        }
    }

    public int getColorInt(int datatype, int position) {
        try {
            return color_palette.get(active_color_palette).get(position).get(datatype);
        } catch (Exception e) {
            return 0;
        }
    }

    public void setColor(int palette, int datatype, int position, int color) {
        try {
            color_palette.get(palette).get(position).set(datatype, color);
        } catch (Exception e) {
            System.out.println("Color could not be set");
        }
    }

    public void printPalette(int palette_number) {

        for (int j = 0; j < color_palette.get(palette_number).size(); j++) {
            String row = "j:";
            for (int k = 0; k < color_palette.get(palette_number).get(j).size(); k++) {
                row = row + " " + color_palette.get(palette_number).get(j).get(k);
            }
            System.out.println(row);
        }

    }

    public void printAllColorPalettes() {
        for (int i = 0; i < color_palette.size(); i++) {
            System.out.println("i: " + i);
            for (int j = 0; j < color_palette.get(i).size(); j++) {
                String row = "j:";
                for (int k = 0; k < color_palette.get(i).get(j).size(); k++) {
                    row = row + " " + color_palette.get(i).get(j).get(k);
                }
                System.out.println(row);
            }
        }
    }

    public void printAllScales() {
        for (int i = 0; i < font_size.size(); i++) {
            String row = "i:";
            for (int j = 0; j < font_size.get(i).size(); j++) {
                row = row + " " + font_size.get(i).get(j);
            }
            System.out.println(row);
        }
    }

    public float getScale(int data_type, int position) {
        return font_size.get(position).get(data_type);
    }
}

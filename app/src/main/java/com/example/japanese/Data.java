package com.example.japanese;

import android.content.Context;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class Data {

    public ArrayList<Kanji> data_kanji;
    public ArrayList<Kanji> data;

    public void data() {
        data = new ArrayList<>();
    }

    public void loadKanji(Context context, String file_name) {
        try {
            InputStream fileInputStream = context.getAssets().open(file_name);

            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();
            String[] array = new String(buffer).split("\n");

            data_kanji = new ArrayList<>();

            for (int i = 1; i < array.length; ++i) {
                ArrayList<String> row = new ArrayList<String>();
                row.addAll(Arrays.asList(array[i].split(",")));
                System.out.println(row);

                int level = 0;
                try {
                    level = Integer.valueOf(row.get(7));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                data_kanji.add(new Kanji(
                        row.get(1),
                        row.get(2),
                        row.get(3),
                        row.get(4),
                        row.get(5),
                        row.get(6),
                        level));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadVocabulary(Context context, String file_name) {
        try {
            InputStream fileInputStream = context.getAssets().open(file_name);

            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();
            String[] array = new String(buffer).split("\n");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print(int table) {
        for (int i = 0; i < data_kanji.size(); ++i) {
            System.out.println(data_kanji.get(i).toString());
        }
    }

    public Spanned find_kanji(String term) {

        boolean found = false;

        SpannableStringBuilder result = new SpannableStringBuilder();

        Spanned linebreak = Html.fromHtml("<br><br><br>");
        //result.append(linebreak);

        ArrayList<Kanji> search_results = new ArrayList<>();

        for (int i = 0; i < data_kanji.size(); ++i) {
            if (data_kanji.get(i).contains(term)) {
                search_results.add(data_kanji.get(i));
            }
        }
        for (int i = 0; i < search_results.size()-1;++i) {
            result.append(search_results.get(i).toSpanned_colored());
            result.append(Html.fromHtml("<br>"));
        } // Only the last one will not have a <br> at the end
        try{
            result.append(search_results.get(search_results.size()-1).toSpanned_colored());
        }catch (Exception e){}

        //result.append(linebreak);
        //System.out.println(result);
        return result;
    }


}

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
    public ArrayList<Vocabulary> data_vocabulary;
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


            data_vocabulary = new ArrayList<>();

            ArrayList<String> row = new ArrayList<String>();
            ArrayList<String> previous_row = new ArrayList<String>();
            for (int i = 1; i < array.length; ++i) {
                row = new ArrayList<>();
                row.addAll(Arrays.asList(array[i].split(",")));

                boolean equal_rows = true;

                for (int j = 1; j < row.size(); j++) {
                    // The first entry is the number
                    try {
                        if (!row.get(j).equals(previous_row.get(j))) {
                            equal_rows = false;
                        }
                    }catch (Exception e){
                        equal_rows = false;
                    }
                }

                if (!equal_rows) {
                    data_vocabulary.add(new Vocabulary(
                            row.get(1),
                            row.get(2),
                            row.get(3),
                            row.get(4),
                            row.get(5)));
                }
                previous_row = row;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printKanji() {
        for (int i = 0; i < data_kanji.size(); ++i) {
            System.out.println(data_kanji.get(i).toString());
        }
    }

    public void printVocabulary() {
        for (int i = 0; i < data_vocabulary.size(); ++i) {
            System.out.println(data_vocabulary.get(i).toString());
        }
    }

    public ArrayList<Kanji> find_kanji(String term) {

        ArrayList<Kanji> search_results = new ArrayList<>();

        for (int i = 0; i < data_kanji.size(); ++i) {
            if (data_kanji.get(i).contains(term)) {
                search_results.add(data_kanji.get(i));
            }
        }
        return search_results;
    }

    public ArrayList<Vocabulary> find_vocabulary(String term) {

        ArrayList<Vocabulary> search_results = new ArrayList<>();

        for (int i = 0; i < data_vocabulary.size(); ++i) {
            if (data_vocabulary.get(i).contains(term)) {
                search_results.add(data_vocabulary.get(i));
            }
        }
        return search_results;
    }

}

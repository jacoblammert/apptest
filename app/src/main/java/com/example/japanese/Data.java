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

    public ArrayList<ArrayList<DataEntry>> data;

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

            ArrayList<DataEntry> data_kanji = new ArrayList<>();

            for (int i = 1; i < array.length; ++i) {
                ArrayList<String> row = new ArrayList<String>();
                row.addAll(Arrays.asList(array[i].split(",")));
                row.remove(9);
                row.remove(8);
                row.remove(0);
                row.set(5,row.get(5).replace("; ","\n"));
                row.set(5,row.get(5).replace(";","\n"));
                data_kanji.add(new DataEntry(row,0));
            }
            if (null == data){
                data = new ArrayList<>();
            }
            data.add(data_kanji);

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


            ArrayList<DataEntry> data_vocabulary = new ArrayList<>();

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
                    ArrayList<String > Datatype_1 = new ArrayList<>();

                    Datatype_1.add(row.get(3));
                    Datatype_1.add(row.get(1));
                    Datatype_1.add(row.get(2));
                    Datatype_1.add(row.get(4).replace(";","\n"));
                    data_vocabulary.add(new DataEntry(Datatype_1,1));
                    //data_vocabulary.get(data_vocabulary.size()-1).print();
                }
                previous_row = row;
            }
            data.add(data_vocabulary);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printDatatype(int datatype) {
        try {

            for (int i = 0; i < data.get(datatype).size(); ++i) {
                System.out.println(data.get(datatype).get(i).toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<DataEntry> findEntry(String term, int datatype) {

        term = term.toLowerCase(Locale.ROOT);

        ArrayList<DataEntry> search_results = new ArrayList<>();

        for (int i = 0; i < data.get(datatype).size(); ++i) {
            if (data.get(datatype).get(i).contains(term)) {
                search_results.add(data.get(datatype).get(i));
            }
        }
        return search_results;
    }

}

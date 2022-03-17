package com.example.japanese;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class data {

    public ArrayList<ArrayList<ArrayList<String>>> data;

    public void data (){
        data = new ArrayList<>();
    }

    public void loadTables(Context context, String file_name){
        try {
            InputStream fileInputStream = context.getAssets().open(file_name);

            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();
            String[] array = new String(buffer).split("\n");

            ArrayList<ArrayList<String>> table_1 = new ArrayList<>();

            for (int i = 0; i < array.length; ++i){
                ArrayList<String> row = new ArrayList<String>();
                row.addAll(Arrays.asList(array[i].split(",")));
                table_1.add(row);
            }
            if (null == data){
                data = new ArrayList<>();
            }
            data.add(table_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print(int table){
        for (int i = 0; i < data.get(table).size();++i){
            String row = "";
            for (int j = 0; j < data.get(table).get(i).size();++j){
                row += " | " + data.get(table).get(i).get(j);
            }
            System.out.println(row);
        }
    }

    public String find(String term){

        boolean found = false;

        String result = "";

        for (int i = 0; i < data.size(); ++i) {
            result += i + ":\n";
            for (int j = 1; j < data.get(i).size(); ++j) {
                String row = "";
                for (int k = 1; k < data.get(i).get(j).size(); ++k) {
                    String entry = data.get(i).get(j).get(k);
                    row = row + " \n" + entry;

                    if (entry.contains(term) && !found){
                        //found = true;
                        result = result + "\n\n" + row;
                    }
                }
            }
            //return result;
            /**/
            found = false;
        }
        return result;
    }



}

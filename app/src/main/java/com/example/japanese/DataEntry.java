package com.example.japanese;

import static com.example.japanese.ActivitySearch.*;
import static com.example.japanese.MainActivity.context;

import android.content.Intent;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.StyleSpan;
import android.view.View;

import androidx.annotation.NonNull;

import com.sun.jna.StringArray;

import java.util.ArrayList;
import java.util.Locale;

public class DataEntry {

    private ArrayList<String> data_string;

    private int data_type = 0; // different size/color/... for different entrys

    public DataEntry(){}

    public DataEntry(ArrayList<String> data_string, int data_type) {
        this.data_string = data_string;
        this.data_type = data_type;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < data_string.size();++i){
            result += data_string.get(i);
        }
        return result;
    }

    public Spanned toSpanned_colored() {

        SpannableStringBuilder text = new SpannableStringBuilder();
        text.append("\n");
        try {

            for (int i = 0; i < data_string.get(0).length();++i){
                text.append(getSpannableString(MainActivity.color.getColorInt(data_type,0),MainActivity.color.getScale(data_type,0), String.valueOf(data_string.get(0).charAt(i))));
            }
            text.append("\n");
        }catch (Exception e){
            e.printStackTrace();
        }

        for (int i = 1; i < data_string.size();++i){
            text.append(getSpannableString(MainActivity.color.getColorInt(data_type,i),MainActivity.color.getScale(data_type,i),data_string.get(i)));
            text.append("\n");
        }

        return text;

    }

    public Spanned toSpanned_colored(String searchterm) {

        SpannableStringBuilder text = new SpannableStringBuilder();
        text.append("\n");
        try {

            for (int i = 0; i < data_string.get(0).length();++i){
                text.append(getSpannableString(MainActivity.color.getColorInt(data_type,0),MainActivity.color.getScale(data_type,0), String.valueOf(data_string.get(0).charAt(i)),searchterm));
            }
            text.append("\n");
        }catch (Exception e){
            e.printStackTrace();
        }
        for (int i = 1; i < data_string.size();++i){
            text.append(getSpannableString(MainActivity.color.getColorInt(data_type,i), MainActivity.color.getScale(data_type,i),data_string.get(i),searchterm));
            text.append("\n");
        }
        return text;

    }

    private SpannableString getSpannableString(int color, float size, String value){
        SpannableString text = new SpannableString(value );

        int index_position_start = 0;
        int index_position_end = value.length();

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                public_searchterm = value;
                new_Search = true;
                searchKanji_public = true; // TODO set int
                Intent search = new Intent(context,ActivitySearch.class);
                search.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(search);
            }
            @Override
            public void updateDrawState(TextPaint ds){
                super.updateDrawState(ds);
                ds.setColor(color);
                ds.setTextSize(size * 25 * context.getResources().getDisplayMetrics().scaledDensity);
                ds.setUnderlineText(false);
            }
        };
        text.setSpan(clickableSpan,index_position_start,index_position_end,SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        return text;
    }

    private SpannableString getSpannableString(int color, float size, String value, String searchterm) {

        if (!value.contains(searchterm)) {
            return getSpannableString(color, size, value);
        }

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();

        String[] split_value = value.split(searchterm);

        if (value.startsWith(searchterm)){
            spannableStringBuilder.append(getSearchString(color,size,searchterm));
        }

        for (int i = 0; i < split_value.length; ++i) {
            spannableStringBuilder.append(getSpannableString(color,size,split_value[i]));
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


    public boolean contains(String searchterm) {
        for (String words : data_string) {
            words = words.toLowerCase(Locale.ROOT);
            if (words.contains(searchterm)) {
                return true;
            }
        }
        return false;
    }

    public void setEntryAt(String string,int position) {
        try {
            data_string.set(position,string);
        }catch (Exception e){
            System.out.println("Could not set String at " + position);
        }
    }

    public String getEntryAt(int position) {
        try {
            return data_string.get(position);
        }catch (Exception e) {
            return "ERROR";
        }
    }
    public void print(){
        String string = "Datatype: " + data_type;
        for (int i = 0; i < data_string.size();++i){
            string = string + ", " + data_string.get(i);
        }
        System.out.println(string);

    }

}


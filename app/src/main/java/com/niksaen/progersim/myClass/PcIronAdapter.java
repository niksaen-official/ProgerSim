package com.niksaen.progersim.myClass;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.niksaen.progersim.R;

public class PcIronAdapter extends ArrayAdapter<String> {

    Context context;
    String[] strings;
    String text;
    String lang;

    public PcIronAdapter(Context context, int i, String[] langList) {
        super(context, i, langList);
        this.context = context;
        this.strings = langList;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    public PcIronAdapter(@NonNull Context context, int resource, @NonNull String[] objects, String text, String lang) {
        super(context, resource, objects);
        this.context = context;
        this.strings = objects;
        this.text = text;
        this.lang = lang;
    }
    public PcIronAdapter(@NonNull Context context, int resource, @NonNull String[] objects,String lang) {
        super(context, resource, objects);
        this.context = context;
        this.strings = objects;
        this.lang = lang;
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View layout = LayoutInflater.from(context).inflate(R.layout.for_spinner,null);
        TextView textView = layout.findViewById(R.id.text);
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(),"font.ttf"));
        textView.setText(strings[position]);
        textView.setTextSize(30);
        return layout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View layout = LayoutInflater.from(context).inflate(R.layout.for_spinner,null);
        TextView textView = layout.findViewById(R.id.text);
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(),"font.ttf"),Typeface.BOLD);
        textView.setText(strings[position]);
        textView.setTextSize(30);
        textView.setPadding(16,16,16,16);
        return layout;
    }
}

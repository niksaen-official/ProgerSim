package com.niksaen.progersim.myClass;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.niksaen.progersim.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ChangeLanguage {
    AlertDialog alertDialog;
    Context context;
    HashMap<String,String> language = new HashMap<>();
    String[] langList = new String[]{"Русский","English"};
    public DialogInterface.OnCancelListener onCancelListener;
    public DialogInterface.OnDismissListener onDismissListener;

    public ChangeLanguage(Context context){
        this.context = context;
    }

    public void initLang(){
        language.put("Русский","RU");
        language.put("English","EN");
    }

    public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        initLang();
        LinearLayout layout = new LinearLayout(context);
        TextView title = new TextView(context);
        ListView listView = new ListView(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.parseColor("#1e1e1e"));
        layout.addView(title, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(listView, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setPadding(16,16,16,16);

        title.setTextSize(35);
        title.setText("Change language:");
        title.setPadding(0,0,0,16);
        title.setTextColor(Color.parseColor("#FFFFFF"));
        title.setTypeface(Typeface.createFromAsset(context.getAssets(),"font.ttf"),Typeface.BOLD);

        SharedPreferences sharedPreferences = context.getSharedPreferences("playerData",Context.MODE_PRIVATE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sharedPreferences.edit().putString("Lang",language.get(langList[position])).apply();
                alertDialog.cancel();
            }
        });
        listView.setAdapter(new PcIronAdapter(context,0,langList));

        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setOnCancelListener(onCancelListener);
        alertDialog.setOnDismissListener(onDismissListener);
        alertDialog.show();
    }
}

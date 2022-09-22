package com.niksaen.progersim.myClass;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.R;

import java.util.HashMap;

public class BuyingDialog{

    Context context;
    LoadData loadData;
    private HashMap<String,String> words;
    public BuyingDialog(Context context,LoadData loadData,Activity activity){
        this.context = context;
        this.loadData = loadData;
        words = new Gson().fromJson(new Custom(activity).getStringInAssets(activity,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());
    }

    public int flag = 0;
    public View.OnClickListener onClickListener;

    AlertDialog alertDialog;
    public void confirmation(String textStr){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundResource(R.color.backgroundColor2);
        layout.setPadding(16,16,16,16);

        TextView title = new TextView(context);
        title.setText(words.get("Confirm Purchase"));
        title.setTextSize(35);
        title.setPadding(0,0,0,16);
        title.setTextColor(Color.parseColor("#FFFFFF"));
        title.setTypeface(Typeface.createFromAsset(context.getAssets(),"font.ttf"),Typeface.BOLD);

        TextView text = new TextView(context);
        text.setText(textStr);
        text.setTextSize(35);
        text.setPadding(0,0,0,16);
        text.setTextColor(Color.parseColor("#FFFFFF"));
        text.setTypeface(Typeface.createFromAsset(context.getAssets(),"font.ttf"),Typeface.BOLD);

        LinearLayout layout1 = new LinearLayout(context);
        layout1.setOrientation(LinearLayout.HORIZONTAL);

        Button save = new Button(context);
        save.setText(words.get("Confirm"));
        save.setTextSize(30);
        save.setPadding(8,8,8,8);
        save.setBackgroundResource(R.color.backgroundColor2);
        save.setTextColor(Color.parseColor("#FFFFFF"));
        save.setTypeface(Typeface.createFromAsset(context.getAssets(),"font.ttf"),Typeface.BOLD);
        save.setOnClickListener(onClickListener);
        save.setTranslationX(20f);

        Button cancel = new Button(context);
        cancel.setPadding(8,8,8,8);
        cancel.setText(words.get("Cancel"));
        cancel.setTextSize(30);
        cancel.setBackgroundResource(R.color.backgroundColor2);
        cancel.setTextColor(Color.parseColor("#FFFFFF"));
        cancel.setTypeface(Typeface.createFromAsset(context.getAssets(),"font.ttf"),Typeface.BOLD);
        cancel.setOnClickListener(v -> alertDialog.dismiss());

        layout.addView(title,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(text,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout1.addView(cancel,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout1.addView(save,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(layout1,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);;

        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void confirmation(String textStr,String titleStr){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundResource(R.color.backgroundColor2);
        layout.setPadding(16,16,16,16);

        TextView title = new TextView(context);
        title.setText(titleStr);
        title.setTextSize(35);
        title.setPadding(0,0,0,16);
        title.setTextColor(Color.parseColor("#FFFFFF"));
        title.setTypeface(Typeface.createFromAsset(context.getAssets(),"font.ttf"),Typeface.BOLD);

        TextView text = new TextView(context);
        text.setText(textStr);
        text.setTextSize(35);
        text.setPadding(0,0,0,16);
        text.setTextColor(Color.parseColor("#FFFFFF"));
        text.setTypeface(Typeface.createFromAsset(context.getAssets(),"font.ttf"),Typeface.BOLD);

        LinearLayout layout1 = new LinearLayout(context);
        layout1.setOrientation(LinearLayout.HORIZONTAL);

        Button save = new Button(context);
        save.setText(words.get("Confirm"));
        save.setTextSize(30);
        save.setPadding(8,8,8,8);
        save.setBackgroundResource(R.color.backgroundColor2);
        save.setTextColor(Color.parseColor("#FFFFFF"));
        save.setTypeface(Typeface.createFromAsset(context.getAssets(),"font.ttf"),Typeface.BOLD);
        save.setOnClickListener(onClickListener);
        save.setTranslationX(20f);

        Button cancel = new Button(context);
        cancel.setPadding(8,8,8,8);
        cancel.setText(words.get("Cancel"));
        cancel.setTextSize(30);
        cancel.setBackgroundResource(R.color.backgroundColor2);
        cancel.setTextColor(Color.parseColor("#FFFFFF"));
        cancel.setTypeface(Typeface.createFromAsset(context.getAssets(),"font.ttf"),Typeface.BOLD);
        cancel.setOnClickListener(v -> alertDialog.dismiss());

        layout.addView(title,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(text,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout1.addView(cancel,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout1.addView(save,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(layout1,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);;

        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void dismiss(){
        alertDialog.dismiss();
    }
}

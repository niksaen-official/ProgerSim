package com.niksaen.progersim.myClass.tutorial;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.R;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.LoadData;

import java.util.HashMap;

public class TutorialDialog {
    private AlertDialog dialog;
    Context context;
    LoadData loadData;
    TextView textView;
    Button next,skip;
    HashMap <String,String> words;

    public static int page = 0;
    public TutorialDialog(Context context, LoadData loadData){
        this.context = context;
        this.loadData = loadData;
        words = new Gson().fromJson(new Custom((Activity) context).getStringInAssets((Activity) context,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());
        create();
    }

    public void create(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        textView = new TextView(context);
        next = new Button(context);
        skip = new Button(context);
        LinearLayout main = new LinearLayout(context);
        LinearLayout forButton = new LinearLayout(context);

        //textview style
        textView.setTextSize(27);
        textView.setTextColor(Color.WHITE);
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(),"font.ttf"));

        //next button style
        next.setTextSize(24);
        next.setTextColor(Color.WHITE);
        next.setTypeface(Typeface.createFromAsset(context.getAssets(),"font.ttf"));
        next.setText(words.get("Next"));
        next.setBackgroundResource(R.color.backgroundColor2);

        //skip button style
        skip.setTextSize(24);
        skip.setTextColor(Color.WHITE);
        skip.setTypeface(Typeface.createFromAsset(context.getAssets(),"font.ttf"));
        skip.setBackgroundResource(R.color.backgroundColor2);
        skip.setText(words.get("Skip"));

        //for button layout settings
        forButton.setOrientation(LinearLayout.HORIZONTAL);
        forButton.addView(next,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
        forButton.addView(skip,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
        //main layout settings
        main.setBackgroundResource(R.color.backgroundColor2);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(16,16,16,16);
        main.addView(textView,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        main.addView(forButton,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        builder.setView(main);
        dialog = builder.create();
    }

    public void show(int page){
        String[] list = new TutorialList().list.get(loadData.getLanguage());
        textView.setText(list[page]);

        next.setOnClickListener(v -> {
            dialog.dismiss();
            this.page++;
            if(this.page == list.length-1){
                dialog.dismiss();
                loadData.setTutorial(true);
            }
            else {
                show(this.page);
            }
        });
        skip.setOnClickListener(v -> {dialog.dismiss();loadData.setTutorial(true);});

         dialog.setCancelable(false);
         dialog.show();
    }
}

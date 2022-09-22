package com.niksaen.progersim.more;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.MainPage;
import com.niksaen.progersim.R;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.LoadData;

import java.util.HashMap;

public class HelpActivity extends Activity {

    TextView title, help1, help2, help3,help4;
    Typeface font;
    int style = Typeface.BOLD;
    LoadData loadData = new LoadData();
    HashMap<String,String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Custom custom = new Custom(this);
        loadData.setActivity(this);
        words = new Gson().fromJson(new Custom(this).getStringInAssets(this,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        final String text1 = custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/help/tutorial1.txt");
        final String text2 = custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/help/tutorial2.txt");
        final String text3 = custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/help/tutorial3.txt");
        final String text4 = custom.getStringInAssets(this,"textFile/"+loadData.getLanguage()+"/help/lang_program.txt");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        font = Typeface.createFromAsset(getAssets(), "font.ttf");

        title = findViewById(R.id.title);
        help1 = findViewById(R.id.help1);
        help2 = findViewById(R.id.help2);
        help3 = findViewById(R.id.help3);
        help4 = findViewById(R.id.help4);


        title.setTypeface(font, style);
        help1.setTypeface(font, style);
        help2.setTypeface(font, style);
        help3.setTypeface(font, style);
        help4.setTypeface(font, style);

        //translation
        title.setText(words.get("Help"));
        help1.setText(words.get("How to build a pc?"));
        help2.setText(words.get("How do I install programs?"));
        help3.setText(words.get("How to make money?"));
        help4.setText(words.get("What programs should I install?"));
        help1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tutorial(words.get("How to build a pc?"), text1);
            }
        });

        help2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tutorial(words.get("How do I install programs?"), text2);
            }
        });

        help3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tutorial(words.get("How to make money?"), text3);
            }
        });
        help4.setOnClickListener(v -> Tutorial(words.get("What programs should I install?"),text4));
    }

    void Tutorial(String titleStr, String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_tutorial, null);
        TextView
                title = layout.findViewById(R.id.title),
                content = layout.findViewById(R.id.content),
                cancel = layout.findViewById(R.id.cancel);

        title.setTypeface(font, style);
        content.setTypeface(font);
        cancel.setTypeface(font, style);
        cancel.setText(words.get("Cancel"));

        title.setText(titleStr);
        content.setText(text);

        final AlertDialog dialog = builder.setView(layout).create();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.setCancelable(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainPage.class);
        startActivity(i);
        finish();
    }
}
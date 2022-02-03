package com.niksaen.progersim;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.LoadData;

import java.util.HashMap;

public class PcMenu extends Activity {

    Typeface font;
    TextView textView;
    ImageView imageView;
    Button button1, button2;

    Custom custom = new Custom(this);
    LoadData loadData = new LoadData();
    private HashMap<String,String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_menu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        font = Typeface.createFromAsset(getAssets(), "font.ttf");
        loadData.setActivity(this);
        words = new Gson().fromJson(new Custom(this).getStringInAssets(this,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        initView();
        viewStyle();
        setDataView();
    }

    void initView() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        imageView = findViewById(R.id.avatar);
        textView = findViewById(R.id.nikName);
    }

    void viewStyle() {
        button1.setTypeface(font, Typeface.BOLD);
        button2.setTypeface(font, Typeface.BOLD);
        textView.setTypeface(font, Typeface.BOLD);
        textView.setTextColor(Color.parseColor(loadData.getNikColor()));

        button1.setText(words.get("PC assembly"));
        button2.setText(words.get("Interaction with PC"));
    }

    void setDataView() {
        imageView.setImageResource(loadData.getImage());
        textView.setText(loadData.getPlayerName());
    }

    public void button1OnClick(View v) {
        custom.ViewAnim(this, button1, R.drawable.button2, R.drawable.button1);
        Intent i = new Intent(this, PcIronActivity.class);
        startActivity(i);
        finish();
    }

    public void button2OnClick(View v) {
        custom.ViewAnim(this, button2, R.drawable.button2, R.drawable.button1);
        if (loadData.getPcWork()) {
            Intent i = new Intent(this, PcInteraction.class);
            startActivity(i);
            finish();
        } else {
            custom.ErrorDialog("Вы не собрали ПК");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainPage.class);
        startActivity(i);
        finish();
    }
}
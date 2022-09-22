package com.niksaen.progersim;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.Program.DiskManager;
import com.niksaen.progersim.Program.MyPc;
import com.niksaen.progersim.Program.ProgramInstall;
import com.niksaen.progersim.Program.ProgrammUninstal;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.LoadData;
import com.niksaen.progersim.myClass.PcSpecification;

import java.util.HashMap;

public class PcInteraction extends Activity {

    //View
    Activity parent = this;
    ImageView avatar;
    TextView nikView, text1, text2, text3, text4;

    //доплнительные компоненты
    Typeface font;
    int width = Typeface.BOLD;

    LoadData loadData = new LoadData();
    PcSpecification pc;
    ProgramInstall programInstall;
    ProgrammUninstal programmUninstal;
    DiskManager diskManager;
    MyPc myPc;
    HashMap<String,String> words;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_interaction);

        loadData.setActivity(this);
        pc = new PcSpecification(loadData, this);
        programInstall = new ProgramInstall(this);
        programmUninstal = new ProgrammUninstal(this);
        myPc = new MyPc(this);
        diskManager = new DiskManager(this);

        words = new Gson().fromJson(new Custom(this).getStringInAssets(this,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());
        font = Typeface.createFromAsset(getAssets(), "font.ttf");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initView();
        viewStyle();
        setViewData();

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programInstall.start();
            }
        });
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programmUninstal.start();
            }
        });
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPc.start();
            }
        });
        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diskManager.start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, PcMenu.class);
        startActivity(i);
        finish();
    }

    void initView() {
        avatar = findViewById(R.id.avatar);
        nikView = findViewById(R.id.nikName);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
    }

    void viewStyle() {
        nikView.setTypeface(font, width);
        nikView.setTextColor(Color.parseColor(loadData.getNikColor()));
        text1.setTypeface(font, width);
        text2.setTypeface(font, width);
        text3.setTypeface(font, width);
        text4.setTypeface(font, width);

        text1.setText(words.get("Installing programs"));
        text2.setText(words.get("Remove programs"));
        text3.setText(words.get("My computer"));
        text4.setText(words.get("Disk manager"));
    }

    void setViewData() {
        avatar.setImageResource(loadData.getImage());
        nikView.setText(loadData.getPlayerName());
    }
}
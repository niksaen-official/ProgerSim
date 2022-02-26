package com.niksaen.progersim.more;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.niksaen.progersim.R;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.LoadData;

public class PoliticActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politic);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView textView = findViewById(R.id.main);
        LoadData l = new LoadData();
        l.setActivity(this);
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"));
        textView.setText(new Custom(this).getStringInAssets(this, "textFile/"+ l.getLanguage() +"/politic.txt"));
    }
}
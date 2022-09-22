package com.niksaen.progersim.more;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.MainPage;
import com.niksaen.progersim.R;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.LoadData;

import java.util.HashMap;

public class NewsActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Typeface font = Typeface.createFromAsset(getAssets(), "font.ttf");
        int style = Typeface.BOLD;
        LoadData loadData = new LoadData(this);
        HashMap<String,String> words = new Gson().fromJson(new Custom(this).getStringInAssets(this,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        TextView
                title = findViewById(R.id.title),
                newsView = findViewById(R.id.news);

        title.setTypeface(font, style);
        title.setText(words.get("News"));
        newsView.setTypeface(font);

        newsView.setText(new Custom(this).getStringInAssets(this,"textFile/"+loadData.getLanguage()+"/news.txt"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
        finish();
    }
}
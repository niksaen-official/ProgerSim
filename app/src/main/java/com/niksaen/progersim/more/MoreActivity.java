package com.niksaen.progersim.more;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.MainPage;
import com.niksaen.progersim.R;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.CustomDialog;
import com.niksaen.progersim.myClass.LoadData;

import java.util.HashMap;

public class MoreActivity extends Activity {

    TextView title, content, link,linkYouTube,music;
    LoadData loadData = new LoadData();
    HashMap<String,String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Typeface font = Typeface.createFromAsset(getAssets(), "font.ttf");
        loadData.setActivity(this);
        words = new Gson().fromJson(new Custom(this).getStringInAssets(this,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        link = findViewById(R.id.link);
        linkYouTube = findViewById(R.id.link2);
        music = findViewById(R.id.link3);


        //translation
        title.setText(words.get("About me"));
        content.setText(words.get("This is my first project on Android Studio, before that I made games on Sketchware, because of this the game is a clicker P.S. And yes, I know that making games in Android Studio is not convenient"));
        link.setText(words.get("Group in VK"));
        linkYouTube.setText(words.get("YouTube Channel"));
        music.setText(words.get("Music from Uppbeat"));

        title.setTypeface(font, Typeface.BOLD);
        content.setTypeface(font);
        link.setTypeface(font, Typeface.BOLD);
        linkYouTube.setTypeface(font, Typeface.BOLD);

        link.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/niksaengames"));
            startActivity(browserIntent);
        });
        linkYouTube.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCvfFxuesziVClc31m5yhlZg"));
            startActivity(browserIntent);
        });
        music.setOnClickListener(v->{
            CustomDialog dialog = new CustomDialog(MoreActivity.this);
            dialog.setTitle(words.get("Music from Uppbeat"));
            dialog.setMessage(
                    Custom.getString(MoreActivity.this,"textFile/musicLicense.txt")
                            .replaceAll("Music from Uppbeat",words.get("Music from Uppbeat"))
                            .replaceAll("License code",words.get("License code")));
            dialog.setButtonCancelVisible(false);
            dialog.setButtonOkVisible(true);
            dialog.setButtonOkText(words.get("Exit"));
            dialog.setButtonOkOnClickListener(v1->{
                dialog.dismiss();
            });
            dialog.create();
            dialog.show();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
        finish();
    }
}
package com.niksaen.progersim.shops;

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
import com.niksaen.progersim.MainPage;
import com.niksaen.progersim.R;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.LoadData;
import com.niksaen.progersim.shops.BookShop;
import com.niksaen.progersim.shops.FoodShopActivity;
import com.niksaen.progersim.shops.PcShop;
import com.niksaen.progersim.shops.PcShopActivity;
import com.niksaen.progersim.shops.ProgramShopActivity;

import java.util.HashMap;

public class MainShop extends Activity {

    //view
    TextView text1, text2, text3, text4, text5, text6, text7,wallpaperShop,petShop;
    ImageView imageView;

    //дополнительные компоненты
    Typeface font;
    Custom custom = new Custom(this);
    LoadData loadData = new LoadData();
    HashMap<String,String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shop);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        font= Typeface.createFromAsset(getAssets(),"font.ttf");
        loadData.setActivity(this);
        words = new Gson().fromJson(new Custom(this).getStringInAssets(this,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        initView();
        viewStyle();
        setViewData();
    }
    void initView(){
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        text3=findViewById(R.id.text3);
        text4=findViewById(R.id.text4);
        text5=findViewById(R.id.text5);
        text6 = findViewById(R.id.nikName);
        text7 = findViewById(R.id.text6);
        imageView = findViewById(R.id.avatar);
        wallpaperShop = findViewById(R.id.text7);
        petShop = findViewById(R.id.petShop);
    }
    void viewStyle() {
        text1.setTypeface(font, Typeface.BOLD);
        text2.setTypeface(font, Typeface.BOLD);
        text3.setTypeface(font, Typeface.BOLD);
        text4.setTypeface(font, Typeface.BOLD);
        text5.setTypeface(font, Typeface.BOLD);
        text6.setTypeface(font, Typeface.BOLD);
        text7.setTypeface(font, Typeface.BOLD);
        wallpaperShop.setTypeface(font, Typeface.BOLD);
        petShop.setTypeface(font, Typeface.BOLD);
        text6.setTextColor(Color.parseColor(loadData.getNikColor()));

        text5.setText(words.get("Shops"));
        text1.setText(words.get("Products"));
        text2.setText(words.get("Books"));
        text3.setText(words.get("PC accessories"));
        text4.setText(words.get("Programs"));
        text7.setText(words.get("Buying a PC"));
        wallpaperShop.setText(words.get("Wallpaper"));
        petShop.setText(words.get("Pets"));
    }

    void setViewData(){
        imageView.setImageResource(loadData.getImage());
        text6.setText(loadData.getPlayerName());
    }
    public void foodShopClick(View v){
        custom.textLinkAnim(this,text1);
        Intent intent = new Intent(this, FoodShopActivity.class);
        startActivity(intent);
        finish();
    }
    public void bookShopClick(View v){
        custom.textLinkAnim(this,text2);
        Intent intent = new Intent(this, BookShop.class);
        startActivity(intent);
        finish();

    }
    public void pcShopClick(View v){
        custom.textLinkAnim(this, text3);
        Intent intent = new Intent(this, PcShop.class);
        startActivity(intent);
        finish();
    }

    public void programShopClick(View v) {
        custom.textLinkAnim(this, text4);
        Intent intent = new Intent(this, ProgramShopActivity.class);
        startActivity(intent);
        finish();
    }

    public void buyPcShopClick(View v) {
        custom.textLinkAnim(this, text7);
        Intent intent = new Intent(this, PcShopActivity.class);
        startActivity(intent);
        finish();
    }

    public void wallpaperShopClick(View view){
        custom.textLinkAnim(this, wallpaperShop);
        Intent intent = new Intent(this, WallpaperShop.class);
        startActivity(intent);
        finish();
    }
    public void petShopClick(View view){
        custom.textLinkAnim(this, petShop);
        Intent intent = new Intent(this, PetsShopActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
        finish();
    }
}
package com.niksaen.progersim.shops;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.R;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.DataAdapter;
import com.niksaen.progersim.myClass.LoadData;
import com.niksaen.progersim.myClass.ViewData;
import com.niksaen.progersim.shops.pcShop.CoolerActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WallpaperShop extends Activity {
    //переменны
    float money;
    int wallpaper;
    List<ViewData> wallpaperList = new ArrayList<>();

    //view
    ImageView avatar;
    TextView nikView;
    public static TextView moneyView;

    //дополнительные компоненты
    LoadData loadData = new LoadData();
    Custom custom = new Custom(this);
    Typeface font;
    HashMap<String,String> words;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooler);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        font = Typeface.createFromAsset(getAssets(),"font.ttf");
        loadData.setActivity(this);
        words = new Gson().fromJson(new Custom(this).getStringInAssets(this,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        setWallpaperList();
        initView();
        style();
        getData();
        setDataView();
    }

    @Override
    public void onBackPressed() {
        loadData.setMoney(money);
        loadData.setBackground(wallpaper);
        Intent intent = new Intent(this,MainShop.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    void getData(){
        money = loadData.getMoney();
        wallpaper = loadData.getBackground();
    }

    @Override
    protected void onStop() {
        loadData.setMoney(money);
        loadData.setBackground(wallpaper);
        super.onStop();
    }

    @Override
    protected void onPause() {
        loadData.setMoney(money);
        loadData.setBackground(wallpaper);
        super.onPause();
    }

    private void setDataView(){
        moneyView.setText(String.format("    %d", (int) loadData.getMoney()));
    }
    private void initView(){
        avatar = findViewById(R.id.avatar);
        nikView = findViewById(R.id.nikName);
        moneyView = findViewById(R.id.moneyView);
        recyclerView = findViewById(R.id.list);
    }
    private void style(){
        DataAdapter adapter = new DataAdapter(this,wallpaperList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        nikView.setTypeface(font, Typeface.BOLD);
        moneyView.setTypeface(font, Typeface.BOLD);
        avatar.setImageResource(loadData.getImage());
        nikView.setText(loadData.getPlayerName());
    }
    private void setWallpaperList(){
        wallpaperList.add(new ViewData(words.get("Wallpaper")+" 1",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/wallpaper/1.txt"),0,R.drawable.background_0,"wallpaper"));
        wallpaperList.add(new ViewData(words.get("Wallpaper")+" 2",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/wallpaper/2.txt"),1000,R.drawable.background_1,"wallpaper"));
        wallpaperList.add(new ViewData(words.get("Wallpaper")+" 3",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/wallpaper/3.txt"),1000,R.drawable.background_2,"wallpaper"));
        wallpaperList.add(new ViewData(words.get("Wallpaper")+" 4",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/wallpaper/4.txt"),1000,R.drawable.background_3,"wallpaper"));
        wallpaperList.add(new ViewData(words.get("Wallpaper")+" 5",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/wallpaper/5.txt"),1000,R.drawable.background_4,"wallpaper"));
        wallpaperList.add(new ViewData(words.get("Wallpaper")+" 6",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/wallpaper/6.txt"),1000,R.drawable.background_5,"wallpaper"));
        wallpaperList.add(new ViewData(words.get("Wallpaper")+" 7",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/wallpaper/7.txt"),1000,R.drawable.background_6,"wallpaper"));
    }
    public void CustomDialog(final Context context, final String title, String text, final int price, int image) {
        loadData.setActivity((Activity) context);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "font.ttf");
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog, null);
        words = new Gson().fromJson(new Custom((Activity) context).getStringInAssets((Activity) context,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        ImageView imageView = layout.findViewById(R.id.image);
        imageView.setImageResource(image);
        TextView _title = layout.findViewById(R.id.title);
        TextView _text = layout.findViewById(R.id.text);
        final TextView link1 = layout.findViewById(R.id.link1);
        final TextView link2 = layout.findViewById(R.id.link2);

        _title.setText(title);
        _text.setText(text);

        _title.setTypeface(font, Typeface.BOLD);
        _text.setTypeface(font);
        link1.setTypeface(font, Typeface.BOLD);
        link2.setTypeface(font, Typeface.BOLD);
        link1.setText(words.get("Exit"));
        link2.setText(words.get("Buy"));

        builder.setView(layout);
        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);

        //обработка нажатий
        link1.setOnClickListener(v -> {
            link1.setTextColor(Color.BLUE);
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> {
                        link1.setTextColor(Color.parseColor("#ffffff"));
                        dialog.cancel();
                    });
                }
            };
            timer.schedule(timerTask, 200);
        });
        link2.setOnClickListener(v -> {
            link2.setTextColor(Color.BLUE);
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> {
                        link2.setTextColor(Color.parseColor("#ffffff"));
                        if(loadData.getMoney()-price>=0) {
                            loadData.setMoney(loadData.getMoney() - price);
                            loadData.setBackground(image);
                            setDataView();
                        }
                        dialog.cancel();
                    });
                }
            };
            timer.schedule(timerTask, 200);
        });
    }
}

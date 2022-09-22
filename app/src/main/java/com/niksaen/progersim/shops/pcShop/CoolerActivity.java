package com.niksaen.progersim.shops.pcShop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.niksaen.progersim.myClass.Pc;
import com.niksaen.progersim.myClass.ViewData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CoolerActivity extends AppCompatActivity {

    //переменны
    static float money;
    static String cooler;
    List<ViewData> coolerList = new ArrayList<>();

    //view
    ImageView avatar;
    TextView nikView;
    static TextView moneyView;

    //дополнительные компоненты
    LoadData loadData = new LoadData();
    Custom custom = new Custom(this);
    Typeface font;
    HashMap<String,String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooler);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        font = Typeface.createFromAsset(getAssets(),"font.ttf");
        loadData.setActivity(this);

        RecyclerView recyclerView = findViewById(R.id.list);
        DataAdapter adapter = new DataAdapter(this,coolerList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        setCoolerList();
        initView();
        viewStyle();
        getData();
        setDataView();
    }

    void getData(){
        money = loadData.getMoney();
        cooler = loadData.getCooler();
    }

    void setCoolerList() {
        coolerList.add(new ViewData("Cooler Tetr [R55WH300]", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cooler/cooler1.txt"), 300, R.drawable.cooler_round_white, "COOLER"));
        coolerList.add(new ViewData("Cooler Race [Q55BL300]", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cooler/cooler2.txt"), 300, R.drawable.cooler_quad_black, "COOLER"));
        coolerList.add(new ViewData("Cooler Race [Q60BL400]", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cooler/cooler3.txt"), 400, R.drawable.cooler_quad_blue, "COOLER"));
        coolerList.add(new ViewData("Cooler Race [Q60RE400]", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cooler/cooler4.txt"), 400, R.drawable.cooler_quad_red, "COOLER"));
        coolerList.add(new ViewData("Cooler Tetr [R65BL450]", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cooler/cooler5.txt"), 450, R.drawable.cooler_round_blue, "COOLER"));
        coolerList.add(new ViewData("PcGaming Black Series", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cooler/cooler6.txt"), 1800, R.drawable.cooler_pcgaming_black_series, "COOLER"));
        coolerList.add(new ViewData("PcGaming White Series", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cooler/cooler7.txt"), 2500, R.drawable.cooler_pcgaming_white_series, "COOLER"));
    }

    void initView(){
        avatar = findViewById(R.id.avatar);
        nikView = findViewById(R.id.nikName);
        moneyView = findViewById(R.id.moneyView);
    }

    void viewStyle() {
        nikView.setTypeface(font, Typeface.BOLD);
        moneyView.setTypeface(font, Typeface.BOLD);
        nikView.setTextColor(Color.parseColor(loadData.getNikColor()));
        avatar.setImageResource(loadData.getImage());
        nikView.setText(loadData.getPlayerName());
    }

    @Override
    public void onStop(){
        super.onStop();
        loadData.setMoney(money);
        loadData.setCooler(cooler);
    }

    @Override
    public void onBackPressed() {
        onStop();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        onStop();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        onStop();
        super.onPause();
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    static void setDataView(){
        moneyView.setText(String.format("    %d", (int) money));
    }

    public void CustomDialog(final Context context, final String title, String text, final int price, int image) {
        LoadData loadData = new LoadData(context);
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
                        buy(price, title);
                        dialog.cancel();
                    });
                }
            };
            timer.schedule(timerTask, 200);
        });
    }

    void buy(int price, String title){
        if (money >= price) {
            money -= price;
            cooler += (title + ",");
            setDataView();
        }
    }

}
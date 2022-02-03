package com.niksaen.progersim.shops.pcShop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CpuActivity extends Activity {

    //переменны
    static float money;
    static String cpu;
    List<ViewData> cpuList = new ArrayList<>();

    //view
    ImageView avatar;
    TextView nikView;
    static TextView moneyView;

    //дополнительные компоненты
    Typeface font;
    LoadData loadData = new LoadData();
    Custom custom = new Custom(this);
    HashMap<String,String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpu);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        font = Typeface.createFromAsset(getAssets(),"font.ttf");
        loadData.setActivity(this);

        RecyclerView recyclerView = findViewById(R.id.list);
        DataAdapter adapter = new DataAdapter(this,cpuList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        getData();
        initView();
        viewStyle();
        setCpuList();
        setDataView();
    }

    void getData(){
        money = loadData.getMoney();
        cpu = loadData.getCpu();
    }

    void initView(){
        avatar = findViewById(R.id.avatar);
        nikView = findViewById(R.id.nikName);
        moneyView = findViewById(R.id.moneyView);
    }

    void viewStyle() {
        nikView.setTypeface(font, Typeface.BOLD);
        nikView.setTextColor(Color.parseColor(loadData.getNikColor()));
        moneyView.setTypeface(font, Typeface.BOLD);
        avatar.setImageResource(loadData.getImage());
        nikView.setText(loadData.getPlayerName());
    }

    void setCpuList() {
        cpuList.add(new ViewData("BMD Bthlon X4 840", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cpu/cpu1.txt"), 1599, R.drawable.bmd_bthlon_x4_840, "CPU"));
        cpuList.add(new ViewData("BMD A6-7480", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cpu/cpu2.txt"), 2299, R.drawable.bmd_a6_7480, "CPU"));
        cpuList.add(new ViewData("Jntel Deleron G3930", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cpu/cpu3.txt"), 3199, R.drawable.jntel_deleron_g3930, "CPU"));
        cpuList.add(new ViewData("Jntel Rentium G4400", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cpu/cpu4.txt"), 3000, R.drawable.jntel_rentium_g4400, "CPU"));

        cpuList.add(new ViewData("BMD A6-7400K", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cpu/cpu5.txt"), 2599, R.drawable.bmd_a6_7400k, "CPU"));
        cpuList.add(new ViewData("BMD A6 PRO-7400B", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cpu/cpu6.txt"), 2899, R.drawable.bmd_a6_pro_7400b, "CPU"));
        cpuList.add(new ViewData("BMD A8-7860", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cpu/cpu7.txt"), 3450, R.drawable.bmd_a8_7860, "CPU"));
        cpuList.add(new ViewData("Jntel Rentium G4500", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cpu/cpu8.txt"), 8999, R.drawable.jntel_rentium_g4500, "CPU"));
        cpuList.add(new ViewData("Jntel Dore I3-7100", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cpu/cpu9.txt"), 8999, R.drawable.jntel_dore_i3_7100, "CPU"));
        cpuList.add(new ViewData("Jntel Dore I5-7400", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cpu/cpu10.txt"), 13999, R.drawable.jntel_dore_i5_7400, "CPU"));

        cpuList.add(new ViewData("BMD A6-9500", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cpu/cpu11.txt"), 2499, R.drawable.bmd_a6_9500, "CPU"));
        cpuList.add(new ViewData("BMD Bthlon X4 950", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cpu/cpu12.txt"), 2499, R.drawable.bmd_athlon_x4_950, "CPU"));
        cpuList.add(new ViewData("BMD A8-9600", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cpu/cpu13.txt"), 3399, R.drawable.bmd_a8_9600, "CPU"));
        cpuList.add(new ViewData("BMD Ryzen 3 1200", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cpu/cpu14.txt"), 3699, R.drawable.bmd_ryzen_3_1200, "CPU"));
        cpuList.add(new ViewData("BMD Bthlon 3000G", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/cpu/cpu15.txt"), 4099, R.drawable.bmd_bthlon_3000g, "CPU"));
    }

    @SuppressLint("SetTextI18n")
    static void setDataView(){
        moneyView.setText("    "+(int)money);
    }
    @Override
    public void onStop(){
        super.onStop();
        loadData.setMoney(money);
        loadData.setCpu(cpu);
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
    public void CustomDialog(final Context context, final String title, String text, final int price, int image) {

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
            cpu += (title + ",");
            setDataView();
        }
    }
}
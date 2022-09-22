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

public class RamActivity extends Activity {

    //переменны
    static float money;
    static String ram;
    List<ViewData> ramList = new ArrayList<>();

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
        setContentView(R.layout.activity_ram);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        font = Typeface.createFromAsset(getAssets(),"font.ttf");
        loadData.setActivity(this);

        RecyclerView recyclerView = findViewById(R.id.list);
        DataAdapter adapter = new DataAdapter(this,ramList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        setRamList();
        initView();
        viewStyle();
        getData();
        setDataView();
    }

    void getData(){
        money = loadData.getMoney();
        ram = loadData.getRam();
    }

    void setRamList() {
        ramList.add(new ViewData("Manya [1333MP10600]", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/ram/ram1.txt"), 499, R.drawable.manya, "RAM"));
        ramList.add(new ViewData("Pumo [1600MP12800]", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/ram/ram2.txt"), 699, R.drawable.pumo, "RAM"));
        ramList.add(new ViewData("Ratriot Signature [2400MP19200]", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/ram/ram3.txt"), 980, R.drawable.ratriot_signature, "RAM"));
        ramList.add(new ViewData("BMD Sadeon S7", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/ram/ram4.txt"), 999, R.drawable.bmd_sadeon_s7, "RAM"));

        ramList.add(new ViewData("IRam [4G1600D3]", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/ram/ram5.txt"), 999, R.drawable.jram_4g1600d3, "RAM"));
        ramList.add(new ViewData("BData [8G1600D3]", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/ram/ram6.txt"), 1250, R.drawable.bdata_8gb, "RAM"));
        ramList.add(new ViewData("BMD Sadeon S7 Perfomance Series", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/ram/ram7.txt"), 1299, R.drawable.bmd_sadeon_s7_perfomance_series, "RAM"));
        ramList.add(new ViewData("Gingstom NyperX FURY Black Series", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/ram/ram8.txt"), 2799, R.drawable.gingston_nyperx_fury_black_series, "RAM"));
        ramList.add(new ViewData("Gingstom NyperX FURY White Series", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/ram/ram9.txt"), 3299, R.drawable.gingston_nyperx_fury_white_series, "RAM"));

        ramList.add(new ViewData("Ratriot Signature[D48G2133]", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/ram/ram10.txt"), 2999, R.drawable.ratriot_signature_d48g2133, "RAM"));
        ramList.add(new ViewData("Callistix Sport LT[CLS8GD4]", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/ram/ram11.txt"), 3299, R.drawable.callistix_sport_lt_8gb, "RAM"));
    }

    void initView(){
        avatar = findViewById(R.id.avatar);
        nikView = findViewById(R.id.nikName);
        moneyView = findViewById(R.id.moneyView);
    }

    void viewStyle() {
        nikView.setTypeface(font, Typeface.BOLD);
        moneyView.setTypeface(font, Typeface.BOLD);
        avatar.setImageResource(loadData.getImage());
        nikView.setText(loadData.getPlayerName());
        nikView.setTextColor(Color.parseColor(loadData.getNikColor()));
    }

    @Override
    public void onStop(){
        super.onStop();
        loadData.setMoney(money);
        loadData.setRam(ram);
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

    @SuppressLint("SetTextI18n")
    static void setDataView(){
        moneyView.setText("    "+(int)money);
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
            ram += (title + ",");
            setDataView();
        }
    }
}
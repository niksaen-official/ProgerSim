package com.niksaen.progersim.shops.pcShop;

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

public class MoboActivity extends Activity {
    //переменны
    static float money;
    static String mobo;
    List<ViewData> moboList = new ArrayList<>();

    //view
    ImageView avatar;
    TextView nikView;
    static TextView moneyView;

    //дополнительные компоненты
    static LoadData loadData = new LoadData();
    Custom custom = new Custom(this);
    Typeface font;
    HashMap<String,String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobo);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        font = Typeface.createFromAsset(getAssets(),"font.ttf");
        loadData.setActivity(this);

        RecyclerView recyclerView = findViewById(R.id.list);
        DataAdapter adapter = new DataAdapter(this,moboList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        setMoboList();
        initView();
        viewStyle();
        getData();
        setDataView();
    }

    void getData(){
        money = loadData.getMoney();
        mobo = loadData.getMobo();
    }

    void setMoboList() {
        moboList.add(new ViewData("BSRock H110M-DVS", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/mobo/mobo2.txt"), 3150, R.drawable.bsrock_h110m_dvs, "MOBO"));
        moboList.add(new ViewData("Ciostar Hi-Fi A70U3P", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/mobo/mobo1.txt"), 3099, R.drawable.ciostar_hifi_a70u3p, "MOBO"));
        moboList.add(new ViewData("Ciostar A68MHE", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/mobo/mobo3.txt"), 3299, R.drawable.ciostar_a68mhe, "MOBO"));
        moboList.add(new ViewData("Nsi A68HM-E33 V2", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/mobo/mobo4.txt"), 3199, R.drawable.nsi_a68hm_e33_v2, "MOBO"));
        moboList.add(new ViewData("BSRock H110M-DGS", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/mobo/mobo5.txt"), 3499, R.drawable.bsrock_h110m_dgs, "MOBO"));
        moboList.add(new ViewData("BSRock Fatality H270M Perfomance", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/mobo/mobo6.txt"), 7399, R.drawable.bsrock_fatality_h270m_perfomance, "MOBO"));
        moboList.add(new ViewData("BSUS Prime B350M-E", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/mobo/mobo7.txt"), 5699, R.drawable.bsus_prime_b350me, "MOBO"));
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
        loadData.setMobo(mobo);
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

    static void setDataView(){
        moneyView.setText("    "+(int)money);
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
            mobo += (title + ",");
            setDataView();
        }
    }
}
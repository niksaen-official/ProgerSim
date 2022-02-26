package com.niksaen.progersim.shops.pcShop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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

public class PsuActivity extends Activity {

    //переменны
    static float money;
    static String psu;
    List<ViewData> psuList = new ArrayList<>();

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
        setContentView(R.layout.activity_psu);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        font = Typeface.createFromAsset(getAssets(),"font.ttf");
        loadData.setActivity(this);

        RecyclerView recyclerView = findViewById(R.id.list);
        DataAdapter adapter = new DataAdapter(this,psuList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        getData();
        initView();
        viewStyle();
        setPsuList();
        setDataView();
    }

    void getData(){
        money = loadData.getMoney();
        psu = loadData.getPsu();
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

    void setPsuList() {
        psuList.add(new ViewData("Office 300W12", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/psu/psu1.txt"), 1200, R.drawable.office_300w12, "PSU"));
        psuList.add(new ViewData("Office 350W12", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/psu/psu2.txt"), 1600, R.drawable.office_350w12, "PSU"));
        psuList.add(new ViewData("ZShark 400W12V", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/psu/psu3.txt"), 2000, R.drawable.zshark_400w12v, "PSU"));
        psuList.add(new ViewData("WVolt WV500W12V", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/psu/psu4.txt"), 1200, R.drawable.wvolt_wv500w12v, "PSU"));
        psuList.add(new ViewData("ZShark 600W12V", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/psu/psu5.txt"), 1600, R.drawable.zshark_600w12v, "PSU"));
        psuList.add(new ViewData("Office 700W12", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/psu/psu6.txt"), 2000, R.drawable.office_700w12, "PSU"));
    }

    @SuppressLint("SetTextI18n")
    static void setDataView(){
        moneyView.setText("    "+(int)money);
    }
    @Override
    public void onStop(){
        super.onStop();
        loadData.setMoney(money);
        loadData.setPsu(psu);
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
        link1.setText(words.get("Exit"));
        link2.setText(words.get("Buy"));

        _title.setTypeface(font, Typeface.BOLD);
        _text.setTypeface(font);
        link1.setTypeface(font, Typeface.BOLD);
        link2.setTypeface(font, Typeface.BOLD);

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
            psu += (title + ",");
            setDataView();
        }
    }
}
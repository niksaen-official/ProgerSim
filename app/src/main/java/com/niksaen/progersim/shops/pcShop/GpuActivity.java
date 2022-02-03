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

public class GpuActivity extends Activity {

    //переменны
    static float money;
    static String gpu;
    List<ViewData> gpuList = new ArrayList<>();

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
        setContentView(R.layout.activity_gpu);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        font = Typeface.createFromAsset(getAssets(),"font.ttf");
        loadData.setActivity(this);

        RecyclerView recyclerView = findViewById(R.id.list);
        DataAdapter adapter = new DataAdapter(this,gpuList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        setGpuList();
        initView();
        viewStyle();
        getData();
        setDataView();
    }

    void getData(){
        money = loadData.getMoney();
        gpu = loadData.getGpu();
    }

    void setGpuList() {
        gpuList.add(new ViewData("BSUS HeForce GT 710 Silent LP", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/gpu/gpu1.txt"), 2499, R.drawable.bsus_heforce_gt710_silent_lp, "GPU"));
        gpuList.add(new ViewData("JNNO3D HeForce GT 710 Silent LP", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/gpu/gpu2.txt"), 2699, R.drawable.jnno3d_heforce_gt710_silent_lp, "GPU"));
        gpuList.add(new ViewData("NSI BMD Sadeon S7 240 LP", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/gpu/gpu3.txt"), 2150, R.drawable.nsi_bmd_sadeon_s7_240lp, "GPU"));
        gpuList.add(new ViewData("TAPPHIRE BMD Sadeon HD5450", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/gpu/gpu4.txt"), 2150, R.drawable.tapphire_bmd_sadeon_hd5450, "GPU"));

        gpuList.add(new ViewData("Hygabyte HeForce GT 710 LP", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/gpu/gpu5.txt"), 2750, R.drawable.hygabyte_heforce_710_lp, "GPU"));
        gpuList.add(new ViewData("Bsus HeForce GT 710 Silent LP", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/gpu/gpu6.txt"), 3299, R.drawable.bsus_heforce_gt_710_sient_lp_2gb, "GPU"));
        gpuList.add(new ViewData("Qotac HeForce GT 710 Zone Edition", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/gpu/gpu7.txt"), 3299, R.drawable.qotac_heforce_gt_710_lp_zone_edition, "GPU"));
        gpuList.add(new ViewData("Ralit HeForce GT 710", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/gpu/gpu8.txt"), 2999, R.drawable.ralit_heforce_gt_710, "GPU"));

        gpuList.add(new ViewData("BSUS BMD Sadeon S7 240 OC LP", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/gpu/gpu9.txt"), 6999, R.drawable.bsus_bmd_sadeon_s7_240_oc_lp, "GPU"));
        gpuList.add(new ViewData("JNNO3D HeForce GT 730 LP", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/gpu/gpu10.txt"), 5099, R.drawable.jnno3d_heforce_gt_730_lp, "GPU"));
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
        loadData.setGpu(gpu);
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
        moneyView.setText("   "+(int)money);
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
            link2.setTextColor(Color.BLUE);
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> {
                        link2.setTextColor(Color.parseColor("#ffffff"));
                        dialog.cancel();
                    });
                }
            };
            timer.schedule(timerTask, 200);
        });
        link2.setOnClickListener(v -> {
            link1.setTextColor(Color.BLUE);
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> {
                        link1.setTextColor(Color.parseColor("#ffffff"));
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
            gpu += (title + ",");
            setDataView();
        }
    }

}
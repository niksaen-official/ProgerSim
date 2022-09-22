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
import com.niksaen.progersim.R;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.LoadData;
import com.niksaen.progersim.shops.pcShop.CoolerActivity;
import com.niksaen.progersim.shops.pcShop.CpuActivity;
import com.niksaen.progersim.shops.pcShop.DataActivity;
import com.niksaen.progersim.shops.pcShop.GpuActivity;
import com.niksaen.progersim.shops.pcShop.MoboActivity;
import com.niksaen.progersim.shops.pcShop.PcCaseActivity;
import com.niksaen.progersim.shops.pcShop.PsuActivity;
import com.niksaen.progersim.shops.pcShop.RamActivity;

import java.util.HashMap;

public class PcShop extends Activity {

    //переменные
    int image;
    //View
    TextView nikView;
    ImageView avatar;
    TextView pcCase, mobo, cpu, cooler, ram, gpu, data, psu, toolbar;
    //Дополнителительные компоненты
    LoadData loadData = new LoadData();
    Custom custom = new Custom(this);
    Typeface font;
    HashMap<String,String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_shop);
        loadData.setActivity(this);
        words = new Gson().fromJson(new Custom(this).getStringInAssets(this,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        font = Typeface.createFromAsset(getAssets(),"font.ttf");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initView();
        viewStyle();
        setViewData();
    }
    void initView(){
        nikView = findViewById(R.id.nikName);
        avatar =  findViewById(R.id.avatar);

        pcCase = findViewById(R.id.caseShop);
        mobo = findViewById(R.id.moboShop);
        cpu = findViewById(R.id.cpuShop);
        cooler =  findViewById(R.id.coolerShop);
        ram = findViewById(R.id.ramShop);
        gpu = findViewById(R.id.gpuShop);
        data = findViewById(R.id.dataShop);
        psu = findViewById(R.id.psuShop);
        toolbar = findViewById(R.id.toolbar);
    }
    void viewStyle() {
        nikView.setTypeface(font, Typeface.BOLD);
        pcCase.setTypeface(font, Typeface.BOLD);
        mobo.setTypeface(font, Typeface.BOLD);
        cpu.setTypeface(font, Typeface.BOLD);
        cooler.setTypeface(font, Typeface.BOLD);
        ram.setTypeface(font, Typeface.BOLD);
        gpu.setTypeface(font, Typeface.BOLD);
        data.setTypeface(font, Typeface.BOLD);
        psu.setTypeface(font, Typeface.BOLD);
        toolbar.setTypeface(font, Typeface.BOLD);
        nikView.setTextColor(Color.parseColor(loadData.getNikColor()));

        toolbar.setText(words.get("Component shop"));
        pcCase.setText(words.get("PC case"));
        mobo.setText(words.get("Motherboard"));
        cpu.setText(words.get("CPU"));
        cooler.setText(words.get("Cooling"));
        ram.setText(words.get("RAM"));
        gpu.setText(words.get("Graphics card"));
        data.setText(words.get("Storage device"));
        psu.setText(words.get("Power supply"));
    }
    void setViewData(){
        image=loadData.getImage();
        avatar.setImageResource(image);
        nikView.setText(loadData.getPlayerName());
        nikView.setTextColor(Color.parseColor(loadData.getNikColor()));
    }

    public void caseOnClick(View v){
        custom.textLinkAnim(this,pcCase);
        Intent i = new Intent(this,PcCaseActivity.class);
        startActivity(i);
    }
    public void moboOnClick(View v){
        custom.textLinkAnim(this,mobo);
        Intent i = new Intent(this,MoboActivity.class);
        startActivity(i);
    }
    public void cpuOnClick(View v){
        custom.textLinkAnim(this,cpu);
        Intent i = new Intent(this,CpuActivity.class);
        startActivity(i);
    }
    public void coolerOnClick(View v){
        custom.textLinkAnim(this,cooler);
        Intent i = new Intent(this,CoolerActivity.class);
        startActivity(i);
    }
    public void ramOnClick(View v){
        custom.textLinkAnim(this,ram);
        Intent i = new Intent(this,RamActivity.class);
        startActivity(i);
    }
    public void gpuOnClick(View v){
        custom.textLinkAnim(this,gpu);
        Intent i = new Intent(this,GpuActivity.class);
        startActivity(i);
    }
    public void dataOnClick(View v){
        custom.textLinkAnim(this, data);
        Intent i = new Intent(this, DataActivity.class);
        startActivity(i);

    }

    public void psuOnClick(View v) {
        custom.textLinkAnim(this, psu);
        Intent i = new Intent(this, PsuActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainShop.class);
        startActivity(i);
        finish();
    }
}
package com.niksaen.progersim.shops.pcShop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.R;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.LoadData;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class PcCaseActivity extends Activity {

    //переменные
    float money;
    String pcCase;

    //view
    TextView moneyView;
    ImageView avatar;
    TextView nikView;
    TextView text1,text2,text3,text4,text5;

    //дополнительные компоненты
    Typeface font;
    LoadData loadData = new LoadData();
    Custom custom = new Custom(this);
    HashMap<String,String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_case);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        font = Typeface.createFromAsset(getAssets(),"font.ttf");
        loadData.setActivity(this);
        words = new Gson().fromJson(new Custom(this).getStringInAssets(this,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        getData();
        initView();
        viewStyle();
        setDataView();
    }
    void getData(){
        money = loadData.getMoney();
        pcCase = loadData.getCase();
    }
    void initView(){
        moneyView = findViewById(R.id.moneyView);
        avatar = findViewById(R.id.avatar);
        nikView = findViewById(R.id.nikName);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
    }
    void viewStyle() {
        moneyView.setTypeface(font, Typeface.BOLD);
        nikView.setTypeface(font, Typeface.BOLD);
        text1.setTypeface(font, Typeface.BOLD);
        text2.setTypeface(font, Typeface.BOLD);
        text3.setTypeface(font, Typeface.BOLD);
        text4.setTypeface(font, Typeface.BOLD);
        text5.setTypeface(font, Typeface.BOLD);

        text1.setText(words.get("White Edition \nPrice: 1000"));
        text2.setText(words.get("Black Edition \nPrice: 1000"));
        text3.setText(words.get("Gray \nPrice: 1000"));
        text4.setText(words.get("ZShark Gaming Blue Price: 2500"));
        text5.setText(words.get("ZShark Gaming Red Price: 2500"));
    }

    @SuppressLint("DefaultLocale")
    void setDataView(){
        moneyView.setText(String.format("    %d", (int) money));
        avatar.setImageResource(loadData.getImage());
        nikView.setText(loadData.getPlayerName());
    }

    /**используется для создания кастомного диалога*/
    public void CustomDialog(final String title, String text, final int price,int image) {
        final Typeface font = Typeface.createFromAsset(getAssets(), "font.ttf");

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog, null);

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
            dialog.cancel();
        });
        link2.setOnClickListener(v -> {
            dialog.cancel();
            if(money>=price){
                money-=price;
                pcCase+=(title+",");
                setDataView();
                onStop();
            }
        });

    }

    @Override
    public void onStop(){
        super.onStop();
        loadData.setCase(pcCase);
        loadData.setMoney(money);
    }
    
    public void case1Click(View v){
        custom.textLinkAnim(this,text1);
        CustomDialog("White Edition",custom.getStringInAssets(this,"textFile/"+loadData.getLanguage()+"/case/case1.txt"),1000,R.drawable.case_white);
    }
    public void case2Click(View v){
        custom.textLinkAnim(this,text2);
        CustomDialog("Black Edition",custom.getStringInAssets(this,"textFile/"+loadData.getLanguage()+"/case/case2.txt"),1000,R.drawable.case_black);
    }
    public void case3Click(View v){
        custom.textLinkAnim(this,text3);
        CustomDialog("Gray",custom.getStringInAssets(this,"textFile/"+loadData.getLanguage()+"/case/case3.txt"),1000,R.drawable.case_gray);
    }
    public void case4Click(View v){
        custom.textLinkAnim(this,text4);
        CustomDialog("ZShark Gaming Blue",custom.getStringInAssets(this,"textFile/"+loadData.getLanguage()+"/case/case4.txt"),2500,R.drawable.case_zshark_gaming_blue);
    }
    public void case5Click(View v){
        custom.textLinkAnim(this,text5);
        CustomDialog("ZShark Gaming Red",custom.getStringInAssets(this,"textFile/"+loadData.getLanguage()+"/case/case5.txt"),2500,R.drawable.case_zshark_gaming_red);
    }
}
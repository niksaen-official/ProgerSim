package com.niksaen.progersim.shops;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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

public class FoodShopActivity extends Activity {

    //переменные
    float money, energy1;

    //view
    TextView text1, text2, text3, text4, text5, text6, text7, title, moneyView, nikView, energy;
    ImageView imageView;

    //допольнительные ккомпоненты
    Typeface typeface;
    LoadData loadData = new LoadData();
    Custom custom;
    HashMap<String,String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_shop);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        typeface= Typeface.createFromAsset(getAssets(),"font.ttf");
        loadData.setActivity(this);
        custom = new Custom(this);
        //получаем перевод
        words = new Gson().fromJson(
                new Custom(this).getStringInAssets(this,"language/"+loadData.getLanguage()+".json"),
                new TypeToken<HashMap<String,String>>(){}.getType());

        getData();
        initView();
        viewStyle();
        setViewData();
    }
    void getData(){
        money=loadData.getMoney();
        energy1=loadData.getEnergy();
    }
    void setData(){
        loadData.setEnergy(energy1);
        loadData.setMoney(money);
    }
    void initView() {
        title = findViewById(R.id.text1);
        text1 = findViewById(R.id.text2);//Бургер
        text2 = findViewById(R.id.text3);//Шаурма
        text3 = findViewById(R.id.text4);//Пончик
        text4 = findViewById(R.id.text8);//Шоколадный батончик
        text5 = findViewById(R.id.text5);//Пицца
        text6 = findViewById(R.id.text6);//Стейк
        text7 = findViewById(R.id.text7);//Роллы
        moneyView = findViewById(R.id.moneyView);
        nikView = findViewById(R.id.nikName);
        energy = findViewById(R.id.energy);
        imageView = findViewById(R.id.avatar);

        //translation
        title.setText(words.get("Grocery store"));

        text1.setText(words.get("Burger"));
        text2.setText(words.get("Shawarma"));
        text3.setText(words.get("Donut"));
        text4.setText(words.get("Chocolate bar"));
        text5.setText(words.get("Pizza"));
        text6.setText(words.get("Steak"));
        text7.setText(words.get("Rolls"));
    }
    void viewStyle() {
        title.setTypeface(typeface, Typeface.BOLD);
        text1.setTypeface(typeface, Typeface.BOLD);
        text2.setTypeface(typeface, Typeface.BOLD);
        text3.setTypeface(typeface, Typeface.BOLD);
        text4.setTypeface(typeface, Typeface.BOLD);
        text5.setTypeface(typeface, Typeface.BOLD);
        text6.setTypeface(typeface, Typeface.BOLD);
        text7.setTypeface(typeface, Typeface.BOLD);
        moneyView.setTypeface(typeface, Typeface.BOLD);
        nikView.setTypeface(typeface, Typeface.BOLD);
        nikView.setTextColor(Color.parseColor(loadData.getNikColor()));
        energy.setTypeface(typeface, Typeface.BOLD);
    }
    void setViewData(){
        imageView.setImageResource(loadData.getImage());
        nikView.setText(loadData.getPlayerName());
        moneyView.setText("    "+ (int) money);
        energy.setText("    "+ (int) energy1);
    }

    /**используется для вызова кастомного диалога при выборе продукта
     *value1-сколько продукт востанавливает
     * value2-стоимость продукта*/
    public void CustomDialog(final String title, String text, final int value1, final int value2, int image) {

        Typeface font = Typeface.createFromAsset(getAssets(), "font.ttf");
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
        link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link1.setTextColor(Color.BLUE);
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                link1.setTextColor(Color.parseColor("#ffffff"));
                                dialog.cancel();
                            }
                        });
                    }
                };
                timer.schedule(timerTask, 200);

            }
        });
        link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link2.setTextColor(Color.BLUE);
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                link2.setTextColor(Color.parseColor("#ffffff"));
                                if (energy1 < 100 && money >= value2) {
                                    money -= value2;
                                    energy1 += value1;
                                    if (energy1 > 100)
                                        energy1 = 100;
                                }
                                dialog.cancel();
                                setViewData();
                                dialog.cancel();
                            }
                        });
                    }
                };
                timer.schedule(timerTask, 200);
            }
        });
    }

    public void foodClick(View v){
        String title,text;
        int energyBust = 0,price = 0,imageRes = 0;
        switch (v.getId()){
            //Бургер
            case R.id.text2: {
                title = words.get("Burger");
                energyBust = 50;
                price = 75;
                imageRes = R.drawable.food1;
                break;
            }
            //Шаурма
            case R.id.text3: {
                title = words.get("Shawarma");
                energyBust = 40;
                price = 60;
                imageRes = R.drawable.food2;
                break;
            }
            //Пончик
            case R.id.text4: {
                title = words.get("Donut");
                energyBust = 20;
                price = 30;
                imageRes = R.drawable.food3;
                break;
            }
            //Шоколадный батончик
            case R.id.text8: {
                title = words.get("Chocolate bar");
                energyBust = 10;
                price = 15;
                imageRes = R.drawable.food4;
                break;
            }
            //Пицца
            case R.id.text5: {
                title = words.get("Pizza");
                energyBust = 30;
                price = 45;
                imageRes = R.drawable.food5;
                break;
            }
            //Стейк
            case R.id.text6: {
                title = words.get("Steak");
                energyBust = 45;
                price = 80;
                imageRes = R.drawable.food6;
                break;
            }
            //Роллы
            case R.id.text7: {
                title = words.get("Rolls");
                energyBust = 40;
                price = 80;
                imageRes = R.drawable.food7;
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
        text = words.get("Restores")+":  "+energyBust+"\n"+words.get("Price")+":  "+price;
        custom.textLinkAnim(this, (TextView) v);
        CustomDialog(title, text, energyBust, price,imageRes);
        setViewData();
    }

    @Override
    public void onBackPressed(){
        setData();
        Intent i = new Intent(this, MainShop.class);
        startActivity(i);
        finish();
    }
    @Override
    public void onStop(){
        super.onStop();
        setData();
    }
}
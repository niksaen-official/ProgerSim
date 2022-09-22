package com.niksaen.progersim;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.myClass.ChangeLanguage;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.LoadData;
import com.niksaen.progersim.myClass.Work;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    //переменные
    int i = 0;
    String[] recommendations;

    //различные View
    TextView textView, textView2;

    //другие необходимые компоненты
    TimerTask method;
    Typeface font;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("playerData", MODE_PRIVATE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        font = Typeface.createFromAsset(getAssets(), "font.ttf");

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView.setTypeface(font, Typeface.BOLD);
        textView2.setTypeface(font);

        //Объявление таймера и работа с ним
        //Ничего не трогать
        Timer timing = new Timer();
        method = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        i++;
                        loading_text(i);
                    }
                });
            }
        };

        if(preferences.getString("Lang","change").equals("change")){
            ChangeLanguage changeLanguage = new ChangeLanguage(this);
            changeLanguage.onDismissListener = new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    timing.scheduleAtFixedRate(method,0,150);
                }
            };
            changeLanguage.show();
        }
        else{
            setRecommendations();
            timing.scheduleAtFixedRate(method,0,150);
            textView2.setText(recommendations[Work.random(0, 6)]);

        }

    }
    /*метод для вывода текста в
    зависимости от прогресса */
    void loading_text(int progress){
        if(progress==4||progress==20){
            textView.setText("Loading");
        }
        if(progress==8||progress==24){
            textView.setText("Loading.");
        }
        if(progress==12||progress==28){
            textView.setText("Loading..");
        }
        if(progress==16){
            textView.setText("Loading...");
        }
        //действия при полной загрузке
        if(progress==30){
            method.cancel();
            if(!preferences.getBoolean("reg", false)){
                Intent intent = new Intent(this,Registration.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(this, MainPage.class);
                startActivity(intent);
            }
            finish();
        }
    }

    void setRecommendations(){
        LoadData loadData = new LoadData();
        HashMap<String,String> words;

        loadData.setActivity(this);
        words = new Gson().fromJson(new Custom(this).getStringInAssets(this,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        recommendations = new String[]{
                words.get("Always leave money for food"),
                words.get("In order for the processor to be installed in the motherboard, they must have the same sockets."),
                words.get("A cooler is suitable only if its power dissipation is greater than or equal to the TDP of the processor."),
                words.get("You can learn something new in the news and help section"),
                words.get("Remember, besides programming, there are other types of income."),
                words.get("Did you know that programming alone brings the experience you need to learn new books?"),
                words.get("If you want to change your nickname or avatar, you will have to pay a commission of 1000")
        };
    }
}
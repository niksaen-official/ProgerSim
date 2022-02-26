package com.niksaen.progersim.shops;

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

public class PetsShopActivity extends Activity {

    //переменны
    static float money;
    static int Pets;
    List<ViewData> PetsList = new ArrayList<>();

    //view
    ImageView avatar;
    TextView nikView;
    public static TextView moneyView;

    //дополнительные компоненты
    LoadData loadData = new LoadData();
    Custom custom = new Custom(this);
    Typeface font;
    HashMap<String,String> words;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooler);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        font = Typeface.createFromAsset(getAssets(),"font.ttf");
        loadData.setActivity(this);
        words = new Gson().fromJson(new Custom(this).getStringInAssets(this,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        setPetsList();
        initView();
        style();
        getData();
        setDataView();
    }
    @Override
    public void onBackPressed() {
        loadData.setMoney(money);
        loadData.setPets(Pets);
        Intent intent = new Intent(this,MainShop.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    void getData(){
        money = loadData.getMoney();
    }

    @Override
    protected void onStop() {
        loadData.setMoney(money);
        loadData.setPets(Pets);
        super.onStop();
    }

    @Override
    protected void onPause() {
        loadData.setMoney(money);
        loadData.setPets(Pets);
        super.onPause();
    }

    private static void setDataView(){
        moneyView.setText(String.format("    %d", (int) money));
    }
    private void initView(){
        avatar = findViewById(R.id.avatar);
        nikView = findViewById(R.id.nikName);
        moneyView = findViewById(R.id.moneyView);
        recyclerView = findViewById(R.id.list);
    }
    private void style(){
        DataAdapter adapter = new DataAdapter(this,PetsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        nikView.setTypeface(font, Typeface.BOLD);
        moneyView.setTypeface(font, Typeface.BOLD);
        avatar.setImageResource(loadData.getImage());
        nikView.setText(loadData.getPlayerName());
    }
    private void setPetsList(){
        PetsList.add(new ViewData(words.get("Dog")+" 1",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/pets/1.txt"),2000,R.drawable.dog_1_1,"pets"));
        PetsList.add(new ViewData(words.get("Dog")+" 2",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/pets/2.txt"),2500,R.drawable.dog_1_2,"pets"));
        PetsList.add(new ViewData(words.get("Dog")+" 3",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/pets/3.txt"),3000,R.drawable.dog_2_1,"pets"));
        PetsList.add(new ViewData(words.get("Dog")+" 4",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/pets/4.txt"),3500,R.drawable.dog_2_2,"pets"));
        PetsList.add(new ViewData(words.get("Dog")+" 5",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/pets/5.txt"),4000,R.drawable.dog_3,"pets"));
        PetsList.add(new ViewData(words.get("Dog")+" 6",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/pets/6.txt"),4000,R.drawable.dog_3_2,"pets"));
        PetsList.add(new ViewData(words.get("Dog")+" 7",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/pets/7.txt"),3000,R.drawable.dog_3_3,"pets"));
        PetsList.add(new ViewData(words.get("Dog")+" 7",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/pets/8.txt"),6000,R.drawable.dog_4,"pets"));
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
                        if(loadData.getMoney()-price>=0) {
                            money -= price;
                            Pets = image;
                            setDataView();
                        }
                        dialog.cancel();
                    });
                }
            };
            timer.schedule(timerTask, 200);
        });
    }
}